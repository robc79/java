package uk.me.robcook.validators;

import java.io.File;

public class ArgsValidator implements ValidateArgs
{
    @Override
    public boolean Validate(String[] args)
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

        if (!file.getName().endsWith(".col"))
        {
            return false;
        }

        return true;
    }
}
