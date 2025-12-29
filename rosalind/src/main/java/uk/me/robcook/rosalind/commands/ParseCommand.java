package uk.me.robcook.rosalind.commands;

import java.io.File;
import java.text.ParseException;

import uk.me.robcook.rosalind.args.ParseArgs;

public class ParseCommand extends Command<ParseArgs>
{
    public ParseCommand(final String[] args)
    {
        super(CommandName.parse, args);
    }

    @Override
    public void parseArguments() throws ParseException
    {
        var args = getArgs();
        
        if (args.length != 1)
        {
            throw new ParseException("Invalid number of arguments for command.", -1);
        }

        var file = new File(args[0]);

        if (!file.exists())
        {
            throw new ParseException("File supplied does not exist.", -1);
        }

        parsedArgs = new ParseArgs(file);
    }

    @Override
    public String getHelpText()
    {
        var builder = new StringBuilder();
        builder.append("Usage: Ros parse filename\n\n");
        builder.append("Where filename is a text file in the FASTA format. ");
        builder.append("File can contain one genetic sequence.");

        return builder.toString();
    }
}
