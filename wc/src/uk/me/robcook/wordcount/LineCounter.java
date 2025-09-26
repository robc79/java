package uk.me.robcook.wordcount;

public class LineCounter implements Counter
{
    @Override
    public int count(final String line)
    {
        return 1;
    }   
}
