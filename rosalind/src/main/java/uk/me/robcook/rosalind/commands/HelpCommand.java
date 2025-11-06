package uk.me.robcook.rosalind.commands;

import java.text.ParseException;

public class HelpCommand extends Command
{

    public HelpCommand(String[] args)
    {
        super(CommandName.help, args);
    }

    @Override
    public void validateArguments(String[] args) throws ParseException
    {
        if (args.length > 1)
        {
            throw new ParseException("Help expects zero or one arguments.", -1);
        }
        else if (args.length == 1)
        {
            try
            {
                var commandName = CommandName.valueOf(args[0]);    
            }
            catch (IllegalArgumentException ex)
            {
                throw new ParseException("Invalid command supplied to help.", -1);
            }
        }
    }

    @Override
    public String getHelpText()
    {
        return "Usage: Ros help [command]";
    }
}
