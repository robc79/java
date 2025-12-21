package uk.me.robcook.rosalind.commands;

import java.text.ParseException;

public class TranscribeCommand extends Command
{
    public TranscribeCommand(final String[] args)
    {
        super(CommandName.transcribe, args);
    }

    @Override
    public void validateArguments(String[] args) throws ParseException
    {
        
    }

    @Override
    public String getHelpText()
    {
        var builder = new StringBuilder();
        builder.append("Usage: Ros transcribe filename\n\n");
        builder.append("Where filename is a text file in the FASTA format. ");
        builder.append("File can contain one DNA sequence. ");
        builder.append("Transcribes DNA to RNA.");

        return builder.toString();
    }
    
}
