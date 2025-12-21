package uk.me.robcook.rosalind.domain;

import java.util.Set;

public class RnaSequence extends GeneticSequence
{
    public RnaSequence(GeneticSequence source) throws SequenceException
    {
        this(source.getDescription(), source.getSequence());
    }
    
    public RnaSequence(String description, String sequence) throws SequenceException
    {
        super(description, sequence, Set.of('A', 'C', 'G', 'U'));
    }
}
