package uk.me.robcook.rosalind.commands;

import java.text.ParseException;

import uk.me.robcook.rosalind.args.Args;

public abstract class Command<T extends Args>
{
    private final CommandName name;
    private final String[] args;
    
    protected T parsedArgs;

    public Command(final CommandName name, final String[] args)
    {
        this.name = name;
        this.args = args;
    }

    public CommandName getName() { return name; }

    public String[] getArgs() { return args; }

    public abstract void parseArguments(String[] args) throws ParseException;

    public abstract String getHelpText();

    public T getParsedArgs() { return parsedArgs; }
}
