package uk.me.robcook.wordcount.tokenizers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LineTokenizer implements Tokenizer
{
    private final File file;
    private final Scanner scanner;

    public LineTokenizer(String filename) throws FileNotFoundException
    {
        file = new File(filename);
        scanner = new Scanner(file);
    }

    public String nextLine()
    {
        if (scanner.hasNextLine())
        {
            return scanner.nextLine();
        }

        return null;
    }

    @Override
    public void close() throws IOException
    {
        scanner.close();
    }

    
}
