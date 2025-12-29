package uk.me.robcook.rosalind.handlers;

import java.util.HashMap;
import java.util.Map;

import uk.me.robcook.rosalind.args.Args;
import uk.me.robcook.rosalind.commands.Command;
import uk.me.robcook.rosalind.commands.CommandName;

public class CommandDispatcher
{
    private final Map<CommandName, CommandHandler<? extends Command<? extends Args>>> handlers;
    
    public CommandDispatcher()
    {
        handlers = new HashMap<CommandName, CommandHandler<? extends Command<? extends Args>>>();
    }

    public void registerHandler(
        final CommandName name,
        final CommandHandler<? extends Command<? extends Args>> handler)
    {
        handlers.put(name, handler);
    }

    @SuppressWarnings("unchecked")
    public <TCommand extends Command<? extends Args>> void invoke(TCommand command)
    {
        var commandName = command.getName();
        var handler = handlers.get(commandName);

        if (handler == null)
        {
            throw new IllegalArgumentException("No handler registration found.");
        }

        ((CommandHandler<TCommand>) handler).handle(command);
    }
}
