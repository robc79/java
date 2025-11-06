package uk.me.robcook.rosalind.domain;

public class GeneticSequenceBuilder
{
    private String description;

    public GeneticSequenceBuilder setDescription(final String description)
    {
        this.description = description;

        return this;
    }

    public GeneticSequence build()
    {
        return new GeneticSequence();
    }
}
