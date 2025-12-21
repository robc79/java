package uk.me.robcook.rosalind.domain;

import java.util.Set;

public class DnaSequence extends GeneticSequence
{
    public DnaSequence(GeneticSequence source) throws SequenceException
    {
        super(source.getDescription(), source.getSequence(), Set.of('A', 'C', 'G', 'T'));
    }

    public RnaSequence transcribe(String description) throws SequenceException
    {
        var rna = getSequence().replace('T', 'U');

        return new RnaSequence(description, rna);
    }
}
