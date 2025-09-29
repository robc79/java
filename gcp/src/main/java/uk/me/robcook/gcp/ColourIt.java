package uk.me.robcook.gcp;

import uk.me.robcook.gcp.validators.*;
import uk.me.robcook.gcp.domain.*;
import uk.me.robcook.gcp.parsers.*;

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
            System.out.println();
            System.out.println("Where filename is a DIMACS formatted text file for the graph colouring problem.");
            System.exit(1);
        }

        var graph = parser.parse(args[0]);

        if (graph == null)
        {
            System.err.println("<!> Failed to create graph from file.");
            System.exit(1);
        }

        if (!graph.validate())
        {
            System.err.println(("<!> Invalid graph constructed."));
            System.exit(1);
        }

        var colouring = graph.colour(ColouringHeuristic.SATURATION_DEGREE);
        colouring.print(System.out);
    }

    public static void main(String[] args)
    {
        var validator = new ArgsValidator();
        var parser = new DimacsFileParser();
        var program = new ColourIt(validator, parser);
        program.run(args);
    }
}
