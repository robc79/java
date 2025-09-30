package uk.me.robcook.gcp.validators;

import java.io.File;

public class ArgsValidator implements ValidateArgs
{
    @Override
    public boolean Validate(String[] args)
    {
        if (args == null)
        {
            return false;
        }

        if (args.length != 1)
        {
            return false;
        }

        var file = new File(args[0]);

        if (!file.getName().endsWith(".col"))
        {
            return false;
        }

        if (!file.exists())
        {
            return false;
        }
        
        return true;
    }
}
