package uk.me.robcook.rosalind.commands;

import java.io.File;
import java.text.ParseException;

public class ParseCommand extends Command
{
    public ParseCommand(final String[] args)
    {
        super(CommandName.parse, args);
    }

    @Override
    public void validateArguments(String[] args) throws ParseException
    {
        if (args.length != 1)
        {
            throw new ParseException("Invalid number of arguments for command.", -1);
        }

        var file = new File(args[0]);

        if (!file.exists())
        {
            throw new ParseException("File supplied does not exist.", -1);
        }
    }
}
