package uk.me.robcook.rosalind.args.commands;

import java.io.File;

public class ParseCommand extends Command
{
    public ParseCommand(String name)
    {
        super(name);
    }

    @Override
    public boolean validateArguments(String[] args)
    {
        if (args.length != 1)
        {
            return false;
        }

        var file = new File(args[0]);

        if (!file.exists())
        {
            return false;
        }

        return true;
    }
}
