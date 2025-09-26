package uk.me.robcook.wordcount;

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
