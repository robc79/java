package uk.me.robcook.rosalind.args.validators;

import java.util.Set;

public class ArgsValidator implements ValidateArgs
{
    private static final Set<String> commands = Set.of("parse");

    @Override
    public boolean Validate(final String[] args)
    {
        if (args.length < 2)
        {
            return false;
        }

        var command = args[0];

        if (!commands.contains(command))
        {
            return false;
        }

        return true;
    }
}
