package uk.me.robcook.rosalind.parsers.fasta;

import uk.me.robcook.rosalind.domain.GeneticSequence;

@FunctionalInterface
public interface FileParser
{
    GeneticSequence parse(String filename);
}
