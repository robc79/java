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

    public void invoke(Command<? extends Args> command)
    {
        var commandName = command.getName();

        if (handlers.containsKey(commandName))
        {
            var handler = handlers.get(commandName);
            handler.handle(command);
        }
        else
        {
            throw new IllegalArgumentException("No handler registration found.");
        }
    }
}
