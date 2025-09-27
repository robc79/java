package uk.me.robcook.wordcount;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Tokenizer extends AutoCloseable
{
    String nextLine();

    void close() throws IOException;

    static Tokenizer ofType(Tokenizers type, String filename) throws FileNotFoundException, IllegalArgumentException
    {
        var tokenizer = switch(type)
        {
            case Tokenizers.LINE -> new LineTokenizer(filename);
            default -> throw new IllegalArgumentException();
        };

        return tokenizer;
    }
}
