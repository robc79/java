package uk.me.robcook.rosalind.commands;

import java.text.ParseException;

import uk.me.robcook.rosalind.args.HelpArgs;

public class HelpCommand extends Command<HelpArgs>
{

    public HelpCommand(String[] args)
    {
        super(CommandName.help, args);
    }

    @Override
    public void parseArguments() throws ParseException
    {
        var args = getArgs();
        
        CommandName commandName = null;

        if (args.length < 1)
        {
            throw new ParseException("Help expects zero or one arguments.", -1);
        }
        else if (args.length == 1)
        {
            try
            {
                commandName = CommandName.valueOf(args[0]);    
            }
            catch (IllegalArgumentException ex)
            {
                throw new ParseException("Invalid command supplied to help.", -1);
            }
        }

        parsedArgs = new HelpArgs(commandName);
    }

    @Override
    public String getHelpText()
    {
        return "Usage: Ros help [command]";
    }
}
