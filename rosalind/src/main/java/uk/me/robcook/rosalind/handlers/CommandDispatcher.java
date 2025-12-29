package uk.me.robcook.rosalind.handlers;

import java.util.HashMap;
import java.util.Map;

import uk.me.robcook.rosalind.args.Args;
import uk.me.robcook.rosalind.commands.Command;

public class CommandDispatcher
{
    private final Map<Class<?>, CommandHandler<?>> handlers;
    
    public CommandDispatcher()
    {
        handlers = new HashMap<Class<?>, CommandHandler<?>>();
    }

    public <T extends Command<? extends Args>> void registerHandler(
        final Class<T> commandType,
        final CommandHandler<T> handler)
    {
        handlers.put(commandType, handler);
    }

    @SuppressWarnings("unchecked")
    public <T extends Command<? extends Args>> void invoke(T command)
    {
        var handler = (CommandHandler<T>) handlers.get(command.getClass());

        if (handler == null)
        {
            throw new IllegalArgumentException("No handler for: " + command.getClass());
        }

        handler.handle(command);
    }
}
