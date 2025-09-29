package uk.me.robcook.gcp.validators;

@FunctionalInterface
public interface ValidateArgs
{
    boolean Validate(String[] args);   
}
