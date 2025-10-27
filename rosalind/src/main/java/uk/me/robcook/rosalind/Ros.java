package uk.me.robcook.rosalind;

import java.text.ParseException;

import uk.me.robcook.rosalind.args.ArgsParser;
import uk.me.robcook.rosalind.args.ParseArgs;
import uk.me.robcook.rosalind.args.commands.Command;

public class Ros
{
    private final ParseArgs parser;

    public Ros(ParseArgs validator)
    {
        this.parser = validator;
    }

    public void run(String[] args)
    {
        Command command;

        try
        {
            command = parser.validate(args);
        }
        catch (ParseException ex)
        {
            System.err.println(String.format("<!> %s", ex.getMessage()));
            System.err.println();

            System.out.println("Usage: Ros command args");
            System.out.println();
            System.exit(1);
        }
        
        // TODO: Send command to a handler to deal with.
    }

    public static void main(String[] args)
    {
        var parser = new ArgsParser();
        var program = new Ros(parser);
        program.run(args);
    }
}
