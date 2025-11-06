package uk.me.robcook.rosalind.parsers.fasta;

import java.text.ParseException;

import uk.me.robcook.rosalind.domain.GeneticSequenceBuilder;

@FunctionalInterface
public interface LineParser
{
    void parse(final String line, final GeneticSequenceBuilder builder) throws ParseException;
}
