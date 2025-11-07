package uk.me.robcook.rosalind.domain;

public class GeneticSequenceBuilder
{
    private String description;
    private StringBuilder sequence;

    public GeneticSequenceBuilder()
    {
        sequence = new StringBuilder();
    }

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

    public GeneticSequence build() throws IllegalStateException
    {
        var sequenceString = sequence.toString();

        if (description == null || sequenceString.length() < 1)
        {
            throw new IllegalStateException("Both description and sequence must be set.");
        }

        return new GeneticSequence(description, sequenceString);
    }
}
