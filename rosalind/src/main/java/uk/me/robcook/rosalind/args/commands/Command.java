package uk.me.robcook.rosalind.args.commands;

public abstract class Command
{
    private final String name;

    public Command(final String name)
    {
        this.name = name;
    }

    public String getName() { return name; }

    public abstract boolean validateArguments(String[] args);
}
