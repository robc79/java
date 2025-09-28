package uk.me.robcook;

import uk.me.robcook.validators.*;
import uk.me.robcook.domain.*;
import uk.me.robcook.parsers.*;

public class ColourIt
{
    private final ValidateArgs validator;
    private final GraphFileParser parser;

    public ColourIt(
        final ValidateArgs validator,
        final GraphFileParser parser)
    {
        this.validator = validator;
        this.parser = parser;
    }

    public void run(String[] args)
    {
        var argsValid = validator.Validate(args);
        
        if (!argsValid)
        {
            System.out.println("Usage: ColourIt filename");
            System.exit(1);
        }

        var graph = parser.parse(args[0]);
        var colouring = graph.colour(ColouringHeuristic.SATURATION_DEGREE);

        // Colour graph using greed algorithm and saturation degree heuristic.
    }

    public static void main(String[] args)
    {
        var validator = new ArgsValidator();
        var parser = new DimacsFileParser();
        var program = new ColourIt(validator, parser);
        program.run(args);
    }
}
