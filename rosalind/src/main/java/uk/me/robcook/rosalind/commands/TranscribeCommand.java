package uk.me.robcook.rosalind.commands;

import java.io.File;
import java.text.ParseException;

import uk.me.robcook.rosalind.args.TranscribeArgs;

public class TranscribeCommand extends Command<TranscribeArgs>
{
    public TranscribeCommand(final String[] args)
    {
        super(CommandName.transcribe, args);
    }

    @Override
    public void parseArguments(String[] args) throws ParseException
    {
        if (args.length != 4)
        {
            throw new ParseException("Invalid number of arguments for command.", -1);
        }

        File dnaFile = null;
        File rnaFile = null;

        if (args[0].equals("-i") && args[2].equals("-o"))
        {
            dnaFile = new File(args[1]);
            rnaFile = new File(args[3]);
        }

        if (args[0].equals("-o") && args[2].equals("-i"))
        {
            rnaFile = new File(args[1]);
            dnaFile = new File(args[3]);
        }

        if (dnaFile == null || rnaFile == null)
        {
            throw new ParseException(("Both DNA and RNA file must be supplied."), 0);
        }

        if (!dnaFile.exists())
        {
            throw new ParseException("DNA file supplied does not exist.", -1);
        }

        parsedArgs = new TranscribeArgs(dnaFile, rnaFile);
    }

    @Override
    public String getHelpText()
    {
        var builder = new StringBuilder();
        builder.append("Usage: Ros transcribe -i dna_sequence_file -o rna_sequence_file\n\n");
        builder.append("Where dna_sequence_file is a text file in the FASTA format. ");
        builder.append("File can contain one DNA sequence. ");
        builder.append("Transcribes DNA to RNA and outputs to a file rna_sequence_file.");

        return builder.toString();
    }
    
}
