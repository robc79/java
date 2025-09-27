package uk.me.robcook.wordcount;

import java.io.FileNotFoundException;

public class LineTokenizerFactory implements TokenizerFactory
{
    @Override
    public Tokenizer Make(String filename) throws FileNotFoundException
    {
        return new LineTokenizer(filename);
    }
}
