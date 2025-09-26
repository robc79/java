package uk.me.robcook.wordcount;

public class Count
{
    private final String[] args;
    private final ValidateArgs validator;
    private final Tokenizer tokenizer;

    public Count(String[] args, ValidateArgs validator, Tokenizer tokenizer)
    {
        this.args = args;
        this.validator = validator;
        this.tokenizer = tokenizer;
    }

    public static void main(String[] args)
    {
        var validator = new ArgsValidator();
        var tokenizer = new LineTokenizer();
        var program = new Count(args, validator, tokenizer);
        program.Run();
    }

    public void Run()
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

        /*
        for(String line : tokenizer.splitIntoLines(args[1]))
        {
            count += counter.count(line);
        }
        */
        
        System.out.println(String.format("%s %d", args[0], count));
    }
}
