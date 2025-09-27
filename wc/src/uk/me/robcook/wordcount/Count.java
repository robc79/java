package uk.me.robcook.wordcount;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Count
{
    private final String[] args;
    private final ValidateArgs validator;
    private final TokenizerFactory tokenizerFactory;

    public Count(String[] args, ValidateArgs validator, TokenizerFactory tokenizerFactory)
    {
        this.args = args;
        this.validator = validator;
        this.tokenizerFactory = tokenizerFactory;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        var validator = new ArgsValidator();
        var tokenizerFactory = new LineTokenizerFactory();
        var program = new Count(args, validator, tokenizerFactory);
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
        
        try (var tokenizer = tokenizerFactory.Make(args[1]))
        {
            String line;

            while ((line = tokenizer.nextLine()) != null)
            {
                count += counter.count(line);
            }
        }

        System.out.println(String.format("%s %d", args[0], count));
    }
}
