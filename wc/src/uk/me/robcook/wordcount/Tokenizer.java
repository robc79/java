package uk.me.robcook.wordcount;

import java.io.IOException;

public interface Tokenizer extends AutoCloseable
{
    String nextLine();

    void close() throws IOException;
}
