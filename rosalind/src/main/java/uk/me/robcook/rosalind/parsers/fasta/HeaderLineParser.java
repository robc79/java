package uk.me.robcook.rosalind.parsers.fasta;

import java.text.ParseException;

import uk.me.robcook.rosalind.domain.GeneticSequenceBuilder;

public class HeaderLineParser implements LineParser
{

    @Override
    public void parse(final String line, final GeneticSequenceBuilder builder) throws ParseException
    {
        if (!line.startsWith(">"))
        {
            throw new ParseException("Invalid starting character, expected '>'.", 0);
        }

        var description = line.substring(1, line.length());
        builder.setDescription(description);
    }
}
