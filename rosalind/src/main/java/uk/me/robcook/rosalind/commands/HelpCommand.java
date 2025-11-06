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
    }
}
