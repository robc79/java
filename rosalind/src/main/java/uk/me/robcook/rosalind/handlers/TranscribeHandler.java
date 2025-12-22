package uk.me.robcook.rosalind.handlers;

import java.io.PrintStream;

import uk.me.robcook.rosalind.commands.TranscribeCommand;
import uk.me.robcook.rosalind.parsers.fasta.FileParser;

public class TranscribeHandler implements CommandHandler<TranscribeCommand>
{
    private final PrintStream out;
    private final PrintStream err;
    private final FileParser parser;

    public TranscribeHandler(final PrintStream out, final PrintStream err, final FileParser parser)
    {
        this.out = out;
        this.err = err;
        this.parser = parser;
    }

    @Override
    public void handle(TranscribeCommand command)
    {
        // TODO: Orchestrate transcription.
    }
    
}
