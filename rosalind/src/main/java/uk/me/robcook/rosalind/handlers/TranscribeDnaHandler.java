package uk.me.robcook.rosalind.handlers;

import java.io.PrintStream;

import uk.me.robcook.rosalind.commands.TranscribeDnaCommand;
import uk.me.robcook.rosalind.domain.DnaSequence;
import uk.me.robcook.rosalind.domain.SequenceException;
import uk.me.robcook.rosalind.parsers.fasta.FileParser;

public class TranscribeDnaHandler extends CommandHandler<TranscribeDnaCommand>
{
    private final PrintStream out;
    private final PrintStream err;
    private final FileParser parser;

    public TranscribeDnaHandler(final PrintStream out, final PrintStream err, final FileParser parser)
    {
        this.out = out;
        this.err = err;
        this.parser = parser;
    }

    @Override
    public void handle(TranscribeDnaCommand command)
    {
        var geneticSequence = parser.parse(command.getParsedArgs().dnaFile());

        try
        {
            var dnaSequence = new DnaSequence(geneticSequence);
            var rnaDescription = String.format("RNA transcription of %s", dnaSequence.getDescription());
            var rnaSequence = dnaSequence.transcribe(rnaDescription);

            // TODO: Save rna sequence to file.
        }
        catch (SequenceException ex)
        {
            // TODO: Print errror...
        }
    }
    
}
