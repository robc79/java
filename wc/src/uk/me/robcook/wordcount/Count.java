package uk.me.robcook.wordcount;

import java.io.FileNotFoundException;
import java.io.IOException;

import uk.me.robcook.wordcount.counters.*;
import uk.me.robcook.wordcount.tokenizers.*;

public class Count
{
    private final String[] args;
    private final ValidateArgs validator;

    public Count(String[] args, ValidateArgs validator)
    {
        this.args = args;
        this.validator = validator;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        var validator = new ArgsValidator();
        var program = new Count(args, validator);
        program.Run();
    }

    public void Run() throws IllegalArgumentException, IOException
    {
        var argsValid = validator.validate(args);

        if (!argsValid)
        {
            System.out.println("Usage: Count [char|word|line] filename");
            System.exit(1);
        }
        
        Counter counter = switch(args[0])
        {
            case "char" -> new CharCounter();
            case "word" -> new WordCounter();
            case "line" -> new LineCounter();
            default -> throw new IllegalArgumentException();
        };

        var count = 0;
        
        try (var tokenizer = makeTokenizer(Tokenizers.LINE, args[1]))
        {
            String line;

            while ((line = tokenizer.nextLine()) != null)
            {
                count += counter.count(line);
            }
        }

        System.out.println(String.format("%s %d", args[0], count));
    }

    protected Tokenizer makeTokenizer(Tokenizers type, String filename)
        throws IllegalArgumentException, FileNotFoundException
    {
        return Tokenizer.ofType(type, filename);
    }
}
