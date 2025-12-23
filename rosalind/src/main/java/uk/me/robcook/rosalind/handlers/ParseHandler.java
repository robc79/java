package uk.me.robcook.rosalind.handlers;

import java.io.PrintStream;

import uk.me.robcook.rosalind.commands.ParseCommand;
import uk.me.robcook.rosalind.domain.GeneticSequence;
import uk.me.robcook.rosalind.parsers.fasta.FileParser;

public class ParseHandler implements CommandHandler<ParseCommand>
{
    private final PrintStream out;
    private final PrintStream err;
    private final FileParser parser;

    public ParseHandler(
        final PrintStream out,
        final PrintStream err,
        final FileParser parser)
    {
        this.out = out;
        this.err = err;
        this.parser = parser;
    }

    public void handle(ParseCommand command)
    {
        var sequence = parser.parse(command.getParsedArgs().sequenceFile());

        if (sequence != null)
        {
            dump(sequence);
        }
        else
        {
            err.println("<!> Unable to parse sequence.");
        }
    }

    private void dump(GeneticSequence sequence)
    {
        out.println(String.format("Description -> %s", sequence.getDescription()));
        out.println();
        out.println("Sequence:");
        out.println(sequence.getSequence());
    }
}
