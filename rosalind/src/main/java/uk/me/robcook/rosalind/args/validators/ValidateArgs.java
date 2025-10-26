package uk.me.robcook.rosalind.args.validators;

@FunctionalInterface
public interface ValidateArgs
{
    boolean Validate(String[] args);
}
