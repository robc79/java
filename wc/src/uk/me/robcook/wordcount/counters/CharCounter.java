package uk.me.robcook.wordcount.counters;

import uk.me.robcook.wordcount.Counter;

public class CharCounter implements Counter
{
    @Override
    public int count(final String line)
    {
        var chars = line.toCharArray();

        return chars.length;
    }
}
