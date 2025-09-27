package uk.me.robcook.wordcount.counters;

public class LineCounter implements Counter
{
    @Override
    public int count(final String line)
    {
        return 1;
    }   
}
