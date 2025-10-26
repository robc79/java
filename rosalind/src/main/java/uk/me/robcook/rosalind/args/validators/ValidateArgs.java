package uk.me.robcook.rosalind.args.validators;

import uk.me.robcook.rosalind.args.commands.Command;

public interface ValidateArgs
{
    boolean validate(String[] args);

    Command getCommand();
}
