package uk.me.robcook.rosalind.handlers;

import java.io.PrintStream;

import uk.me.robcook.rosalind.commands.CountCommand;
import uk.me.robcook.rosalind.parsers.fasta.FileParser;

public class CountHandler implements CommandHandler<CountCommand>
{
    private final PrintStream out;
    private final PrintStream err;
    private final FileParser parser;

    public CountHandler(final PrintStream out, final PrintStream err, final FileParser parser)
    {
        this.out = out;
        this.err = err;
        this.parser = parser;
    }

    @Override
    public void handle(CountCommand command)
    {
        var sequence = parser.parse(command.getParsedArgs().sequenceFile());

        if (sequence != null)
        {
            var counts = sequence.count();
            out.println(String.format("Description -> %s", sequence.getDescription()));

            for(var key : counts.keySet())
            {
                out.println(String.format("%s : %d", key, counts.get((key))));
            }
        }
        else
        {
            err.println("<!> Unable to parse sequence.");
        }
    }
}
