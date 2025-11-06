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
        out.println();
        
        try (var scanner = makeScanner(filename, err))
        {
            if (scanner == null)
            {
                return;
            }

            try
            {
                var sequence = parse(scanner);

                if (sequence == null)
                {
                    err.println("<!> No sequence found.");

                    return;
                }

                dump(sequence);
            }
            catch (ParseException | IllegalStateException ex)
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

    private GeneticSequence parse(final Scanner scanner) throws ParseException, IllegalStateException
    {
        if (!scanner.hasNextLine())
        {
            return null;
        }

        var builder = new GeneticSequenceBuilder();
        var line = scanner.nextLine();
        LineParser lineParser = new HeaderLineParser();
        lineParser.parse(line, builder);

        while (scanner.hasNextLine())
        {
            line = scanner.nextLine();
            lineParser = lineParser.getNextParserFor(line);
            lineParser.parse(line, builder);
        }

        return builder.build();
    }

    private void dump(GeneticSequence sequence)
    {
        out.println(String.format("Description -> %s", sequence.getDescription()));
        out.println();
        out.println("Sequence:");
        out.println(sequence.getSequence());
    }
}
