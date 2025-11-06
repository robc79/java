package uk.me.robcook.rosalind.domain;

import java.util.ArrayList;
import java.util.List;

public class GeneticSequenceBuilder
{
    private String description;
    private StringBuilder sequence;

    public GeneticSequenceBuilder setDescription(final String description)
    {
        this.description = description;

        return this;
    }

    public GeneticSequenceBuilder appendSequence(final String sequence)
    {
        this.sequence.append(sequence);

        return this;
    }

    public GeneticSequence build()
    {
        // TODO: Populate a sequence and return it. Throw exception on failure.
        return new GeneticSequence();
    }
}
