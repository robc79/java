package uk.me.robcook.rosalind.commands;

import java.io.File;
import java.text.ParseException;

import uk.me.robcook.rosalind.args.CountArgs;

public class CountCommand extends Command<CountArgs>
{
    public CountCommand(String[] args)
    {
        super(CommandName.count, args);
    }

    @Override
    public void parseArguments(String[] args) throws ParseException
    {
        if (args.length != 1)
        {
            throw new ParseException("Invalid number of arguments for command.", -1);
        }

        var file = new File(args[0]);

        if (!file.exists())
        {
            throw new ParseException("File supplied does not exist.", -1);
        }

        parsedArgs = new CountArgs(file);
    }

    @Override
    public String getHelpText()
    {
        var builder = new StringBuilder();
        builder.append("Usage: Ros count filename\n\n");
        builder.append("Where filename is a text file in the FASTA format. ");
        builder.append("File can contain one genetic sequence.");
        builder.append("\nOutputs a count of all individual nucleotides.");

        return builder.toString();
    }
    
}
