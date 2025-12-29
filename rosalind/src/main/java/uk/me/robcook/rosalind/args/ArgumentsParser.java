package uk.me.robcook.rosalind.args;

import java.text.ParseException;
import java.util.Arrays;

import uk.me.robcook.rosalind.commands.Command;
import uk.me.robcook.rosalind.commands.CommandName;
import uk.me.robcook.rosalind.commands.CountCommand;
import uk.me.robcook.rosalind.commands.HelpCommand;
import uk.me.robcook.rosalind.commands.ParseCommand;
import uk.me.robcook.rosalind.commands.TranscribeCommand;

public class ArgumentsParser implements ParseArguments
{
    private Command<?> command;

    @Override
    public Command<?> parseAndValidate(final String[] args) throws ParseException
    {
        CommandName commandName;
        String[] commandArgs;

        if (args.length < 1)
        {
            commandName = CommandName.help;
            commandArgs = new String[] {};
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

            commandArgs = Arrays.copyOfRange(args, 1, args.length);
        }

        command = switch (commandName)
        {
            case CommandName.help -> new HelpCommand(commandArgs);
            case CommandName.parse -> new ParseCommand(commandArgs);
            case CommandName.count -> new CountCommand(commandArgs);
            case CommandName.transcribe -> new TranscribeCommand(commandArgs);
            default -> null;
        };

        if (command == null)
        {
            throw new ParseException("Command not found.", -1);
        }

        command.parseArguments();

        return command;
    }
}
