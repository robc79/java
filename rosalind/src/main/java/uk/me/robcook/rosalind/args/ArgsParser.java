package uk.me.robcook.rosalind.args;

import java.text.ParseException;
import java.util.Arrays;

import uk.me.robcook.rosalind.commands.Command;
import uk.me.robcook.rosalind.commands.CommandName;
import uk.me.robcook.rosalind.commands.HelpCommand;
import uk.me.robcook.rosalind.commands.ParseCommand;

public class ArgsParser implements ParseArgs
{
    private Command command;

    @Override
    public Command validate(final String[] args) throws ParseException
    {
        CommandName commandName;

        if (args.length < 1)
        {
            commandName = CommandName.help;
        }
        else
        {
            try
            {
                commandName = CommandName.valueOf(args[0]);    
            }
            catch (IllegalArgumentException ex)
            {
                throw new ParseException("Invalid command supplied.", -1);
            }
        }

        var commandArgs = Arrays.copyOfRange(args, 1, args.length);

        command = switch (commandName)
        {
            case CommandName.help -> new HelpCommand(commandArgs);
            case CommandName.parse -> new ParseCommand(commandArgs);
            default -> null;
        };

        if (command == null)
        {
            throw new ParseException("Command not found.", -1);
        }

        command.validateArguments(commandArgs);

        return command;
    }
}
