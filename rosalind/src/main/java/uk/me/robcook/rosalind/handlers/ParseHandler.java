package uk.me.robcook.rosalind.handlers;

import java.io.PrintStream;

import uk.me.robcook.rosalind.commands.ParseCommand;

public class ParseHandler implements CommandHandler<ParseCommand>
{
    private final PrintStream out;
    private final PrintStream err;

    public ParseHandler(final PrintStream out, final PrintStream err)
    {
        this.out = out;
        this.err = err;
    }

    public void handle(ParseCommand command)
    {
        var filename = command.getArgs()[0];
        
        out.println(String.format("Parsing file -> '%s'.", filename));
    }   
}
