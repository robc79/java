package uk.me.robcook.wordcount;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ArgsValidator implements ValidateArgs
{
    private final static int EXPECTED_ARGS_LENGTH = 2;
    private final ArrayList<String> allowedWords = new ArrayList<>(Arrays.asList("char", "word", "line"));

    @Override
    public boolean validate(String[] args)
    {
        if (args.length != EXPECTED_ARGS_LENGTH)
        {
            return false;
        }

        var word = args[0];

        if (!allowedWords.contains(word))
        {
            return false;
        }

        var filename = args[1];
        var file = new File(filename);
        
        if (!file.exists())
        {
            return false;
        }

        return true;
    }
}
