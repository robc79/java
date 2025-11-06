package uk.me.robcook.rosalind.domain;

public class GeneticSequence
{
    private final String description;
    private final String sequence;

    public GeneticSequence(final String description, final String sequence)
    {
        this.description = description;
        this.sequence = sequence;
    }

    public String getDescription() { return description; }

    public String getSequence() { return sequence; }
}
