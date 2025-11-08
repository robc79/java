package uk.me.robcook.rosalind.domain;

import java.util.HashMap;
import java.util.Map;

public class GeneticSequence
{
    private final String description;
    private final String sequence;

    public GeneticSequence(final String description, final String sequence)
    {
        this.description = description;
        this.sequence = sequence.toUpperCase();
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
}
