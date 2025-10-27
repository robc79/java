package uk.me.robcook.rosalind.args;

import java.text.ParseException;
import java.util.Arrays;

import uk.me.robcook.rosalind.commands.Command;
import uk.me.robcook.rosalind.commands.CommandName;
import uk.me.robcook.rosalind.commands.ParseCommand;

public class ArgsParser implements ParseArgs
{
    private Command command;

    @Override
    public Command validate(final String[] args) throws ParseException
    {
        if (args.length < 2)
        {
            throw new ParseException("At least 2 arguments required.", -1);
        }

        CommandName commandName;

        try
        {
            commandName = CommandName.valueOf(args[0]);    
        }
        catch (IllegalArgumentException ex)
        {
            throw new ParseException("Invalid command supplied.", -1);
        }
        
        var commandArgs = Arrays.copyOfRange(args, 1, args.length);

        command = switch (commandName)
        {
            case CommandName.parse -> new ParseCommand(commandArgs);
            default -> null;
        };

        if (command == null)
        {
            throw new ParseException("Invalid command specified.", -1);
        }

        command.validateArguments(commandArgs);

        return command;
    }
}
