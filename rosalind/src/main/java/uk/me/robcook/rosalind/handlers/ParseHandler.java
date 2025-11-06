package uk.me.robcook.rosalind.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.Scanner;

import uk.me.robcook.rosalind.commands.ParseCommand;
import uk.me.robcook.rosalind.domain.GeneticSequence;
import uk.me.robcook.rosalind.domain.GeneticSequenceBuilder;
import uk.me.robcook.rosalind.parsers.fasta.HeaderLineParser;
import uk.me.robcook.rosalind.parsers.fasta.LineParser;

public class ParseHandler implements CommandHandler<ParseCommand>
{
    private final PrintStream out;
    private final PrintStream err;

    public ParseHandler(final PrintStream out, final PrintStream err)
    {
        this.out = out;
        this.err = err;
    }

    public void handle(ParseCommand command)
    {
        var filename = command.getArgs()[0];
        out.println(String.format("Parsing file -> '%s'.", filename));
        
        try (var scanner = makeScanner(filename, err))
        {
            if (scanner == null)
            {
                return;
            }

            try
            {
                var sequence = parse(scanner);
            }
            catch (ParseException ex)
            {
                err.println(String.format("<!> %s", ex.getMessage()));
            }
        }
    }

    private Scanner makeScanner(final String filename, final PrintStream err)
    {
        Scanner scanner = null;

        try
        {
            scanner = new Scanner(new File(filename));
        }
        catch (FileNotFoundException ex)
        {
            err.println("<!> File not found.");
        }

        return scanner;
    }

    private GeneticSequence parse(final Scanner scanner) throws ParseException
    {
        var builder = new GeneticSequenceBuilder();
        LineParser lineParser = new HeaderLineParser();

        while (scanner.hasNextLine())
        {
            var line = scanner.nextLine();
            lineParser.parse(line, builder);
        }

        return builder.build();
    }
}
