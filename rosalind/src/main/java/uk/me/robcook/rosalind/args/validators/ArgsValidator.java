package uk.me.robcook.rosalind.args.validators;

import java.util.Arrays;
import java.util.Set;

import uk.me.robcook.rosalind.args.commands.ParseCommand;

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

        var commandObject = switch (command)
        {
            case "parse" -> new ParseCommand(command);
            default -> null;
        };

        var commandArgs = Arrays.copyOfRange(args, 1, args.length);

        if (!commandObject.validateArguments(commandArgs))
        {
            return false;
        }

        return true;
    }
}
