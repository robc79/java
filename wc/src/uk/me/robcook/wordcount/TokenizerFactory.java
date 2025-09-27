package uk.me.robcook.wordcount;

import java.io.FileNotFoundException;

public interface TokenizerFactory
{
    Tokenizer Make(String filename) throws IllegalArgumentException, FileNotFoundException;
}
