package uk.me.robcook.rosalind;

import uk.me.robcook.rosalind.args.validators.ArgsValidator;
import uk.me.robcook.rosalind.args.validators.ValidateArgs;

public class Ros
{
    private final ValidateArgs validator;

    public Ros(ValidateArgs validator)
    {
        this.validator = validator;
    }

    public void run(String[] args)
    {
        var argsValid = validator.validate(args);

        if (!argsValid)
        {
            System.out.println("Usage: Ros command args");
            System.out.println();
            System.exit(1);
        }

        var command = validator.getCommand();
        // TODO: Pass command to a handler to run.
    }

    public static void main(String[] args)
    {
        var validator = new ArgsValidator();
        var program = new Ros(validator);
        program.run(args);
    }
}
