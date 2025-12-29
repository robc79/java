package uk.me.robcook.rosalind.parsers.fasta;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.Scanner;

import uk.me.robcook.rosalind.domain.GeneticSequence;
import uk.me.robcook.rosalind.domain.GeneticSequenceBuilder;
import uk.me.robcook.rosalind.domain.SequenceException;

public class FastaFileParser implements FileParser
{
    private final PrintStream err;

    public FastaFileParser(final PrintStream err)
    {
        this.err = err;
    }

    @Override
    public GeneticSequence parse(File file)
    {
        try (var scanner = makeScanner(file, err))
        {
            if (scanner == null)
            {
                return null;
            }

            GeneticSequence sequence = null;

            try
            {
                sequence = parse(scanner);

                if (sequence == null)
                {
                    err.println("<!> No sequence found.");

                    return null;
                }
            }
            catch (ParseException | IllegalStateException | SequenceException ex)
            {
                err.println(String.format("<!> %s", ex.getMessage()));
            }

            return sequence;
        }
    }

    private Scanner makeScanner(final File file, final PrintStream err)
    {
        Scanner scanner = null;

        try
        {
            scanner = new Scanner(file);
        }
        catch (FileNotFoundException ex)
        {
            err.println("<!> File not found.");
        }

        return scanner;
    }

    private GeneticSequence parse(final Scanner scanner)
        throws ParseException, IllegalStateException, SequenceException
    {
        if (!scanner.hasNextLine())
        {
            return null;
        }

        var builder = new GeneticSequenceBuilder();
        var line = scanner.nextLine();
        LineParser lineParser = new HeaderLineParser();
        lineParser.parse(line, builder);
        lineParser = new SequenceLineParser();

        while (scanner.hasNextLine())
        {
            line = scanner.nextLine();
            lineParser.parse(line, builder);
        }

        return builder.build();
    }
}
