package uk.me.robcook.wordcount;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.function.BiFunction;

import uk.me.robcook.wordcount.counters.*;
import uk.me.robcook.wordcount.tokenizers.*;
import uk.me.robcook.wordcount.validators.*;

public class Count
{
    private final String[] args;
    private final ValidateArgs validator;
    private final BiFunction<Tokenizers, String, Tokenizer> tokenizerFactory;

    public Count(
        String[] args,
        ValidateArgs validator,
        BiFunction<Tokenizers, String, Tokenizer> tokenizerFactory)
    {
        this.args = args;
        this.validator = validator;
        this.tokenizerFactory = tokenizerFactory;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        var validator = new ArgsValidator();

        BiFunction<Tokenizers, String, Tokenizer> factory = (type, filename) -> {
            try
            {
                return Tokenizer.ofType(type, filename);
            }
            catch (Exception e)
            {
                throw new RuntimeException("<!> Failed to make tokenizer.");
            }
        };

        var program = new Count(args, validator, factory);
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
        
        try (var tokenizer = tokenizerFactory.apply(Tokenizers.LINE, args[1]))
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
