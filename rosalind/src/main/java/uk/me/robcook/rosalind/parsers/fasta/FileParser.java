package uk.me.robcook.rosalind.parsers.fasta;

import java.io.File;

import uk.me.robcook.rosalind.domain.GeneticSequence;

@FunctionalInterface
public interface FileParser
{
    GeneticSequence parse(File file);
}
