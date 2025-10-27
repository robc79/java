package uk.me.robcook.rosalind.commands;

import java.text.ParseException;

public abstract class Command
{
    private final CommandName name;
    private final String[] args;

    public Command(final CommandName name, final String[] args)
    {
        this.name = name;
        this.args = args;
    }

    public CommandName getName() { return name; }

    public String[] getArgs() { return args; }

    public abstract void validateArguments(String[] args) throws ParseException;
}
