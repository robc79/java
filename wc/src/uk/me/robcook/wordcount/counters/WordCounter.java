package uk.me.robcook.wordcount.counters;

import uk.me.robcook.wordcount.Counter;

public class WordCounter implements Counter
{
    @Override
    public int count(final String line)
    {
        var normalizedLine = line.trim();
        var words = normalizedLine.split(" ");

        return words.length;
    }   
}
