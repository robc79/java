package uk.me.robcook.rosalind.handlers;

import java.util.HashMap;
import java.util.Map;

import uk.me.robcook.rosalind.args.Args;
import uk.me.robcook.rosalind.commands.Command;

public class CommandDispatcher
{
    private final Map<Class<?>, CommandHandler<? extends Command<? extends Args>>> handlers;
    
    public CommandDispatcher()
    {
        handlers = new HashMap<Class<?>, CommandHandler<? extends Command<? extends Args>>>();
    }

    public <T extends Command<? extends Args>> void registerHandler(
        final Class<T> name,
        final CommandHandler<T> handler)
    {
        handlers.put(name, handler);
    }

    @SuppressWarnings("unchecked")
    public <TCommand extends Command<? extends Args>> void invoke(TCommand command)
    {
        var handler = handlers.get(command.getClass());

        if (handler == null)
        {
            throw new IllegalArgumentException("No handler registration found.");
        }

        ((CommandHandler<TCommand>) handler).handle(command);
    }
}
