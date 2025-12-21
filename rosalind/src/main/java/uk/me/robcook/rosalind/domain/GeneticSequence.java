package uk.me.robcook.rosalind.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GeneticSequence
{
    private final String description;
    private final String sequence;

    private Set<Character> allowedValues;

    public GeneticSequence(
        final String description,
        final String sequence,
        final Set<Character> allowedValues) throws SequenceException
    {
        this.description = description;
        this.sequence = sequence.toUpperCase();
        this.allowedValues = allowedValues;

        if (allowedValues != null)
        {
            validate();
        }
    }

    public String getDescription() { return description; }

    public String getSequence() { return sequence; }

    public Map<Character, Integer> count()
    {
        var counts = new HashMap<Character, Integer>();

        for (var nucleotide : sequence.toCharArray())
        {
            if (counts.containsKey(nucleotide))
            {
                var count = counts.get(nucleotide);
                count++;
                counts.put(nucleotide, count);
            }
            else
            {
                counts.put(nucleotide, 1);
            }
        }

        return counts;
    }

    protected void validate() throws SequenceException
    {
        for (char c : getSequence().toCharArray())
        {
            if (!allowedValues.contains(c))
            {
                throw new SequenceException(String.format("Invalid value %s in sequence.", c));
            }
        }
    }
}
