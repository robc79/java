package uk.me.robcook.rosalind.handlers;

import java.io.PrintStream;

import uk.me.robcook.rosalind.commands.CountCommand;

public class CountHandler implements CommandHandler<CountCommand>
{
    private final PrintStream out;
    private final PrintStream err;

    public CountHandler(final PrintStream out, final PrintStream err)
    {
        this.out = out;
        this.err = err;
    }

    @Override
    public void handle(CountCommand command)
    {
    }
}
