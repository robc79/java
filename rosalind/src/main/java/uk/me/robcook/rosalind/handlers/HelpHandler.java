package uk.me.robcook.rosalind.handlers;

import java.io.PrintStream;
import java.util.Arrays;

import uk.me.robcook.rosalind.commands.HelpCommand;

public class HelpHandler implements CommandHandler<HelpCommand>
{
    private final PrintStream out;
    private final PrintStream err;

    public HelpHandler(final PrintStream out, final PrintStream err)
    {
        this.out = out;
        this.err = err;
    }

    @Override
    public void handle(HelpCommand command)
    {
        out.println(String.format("Help invoked for -> %s", Arrays.toString(command.getArgs())));

        if (command.getArgs().length == 0)
        {
            // TODO: Show general help text.
        }
        else
        {
            // TOOD: Invoke method to show detailed command help text.
        }
    }
}
