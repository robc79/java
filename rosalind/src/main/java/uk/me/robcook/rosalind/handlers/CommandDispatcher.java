package uk.me.robcook.rosalind.handlers;

import java.util.HashMap;
import java.util.Map;

import uk.me.robcook.rosalind.commands.Command;
import uk.me.robcook.rosalind.commands.CommandName;

public class CommandDispatcher
{
    private final Map<CommandName, CommandHandler> handlers;
    
    public CommandDispatcher()
    {
        handlers = new HashMap<CommandName, CommandHandler>();
    }

    public void registerHandler(final CommandName name, final CommandHandler handler)
    {
        handlers.put(name, handler);
    }

    public void invoke(Command command)
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
