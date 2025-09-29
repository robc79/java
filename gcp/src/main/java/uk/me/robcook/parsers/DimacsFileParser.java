package uk.me.robcook.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import uk.me.robcook.domain.Graph;

public class DimacsFileParser implements GraphFileParser
{
    @Override
    public Graph parse(String filename)
    {
        var scanner = makeScanner(filename);
        
        if (scanner == null)
        {
            return null;
        }
        
        var problemLine = parseProblemLine(scanner);

        if (problemLine == null)
        {
            return null;
        }

        var numberOfVertices = parseNumberOfVertices(problemLine);
        var numberOfEdges = parseNumberOfEdges(problemLine);

        if (numberOfVertices == -1 || numberOfEdges == -1)
        {
            return null;
        }
        
        var graph = new Graph(numberOfVertices, numberOfEdges);

        // TODO: Parse edge rows add each to the graph.
        // TODO: Validate the number of vertices and edges in the graph.

        return graph;
    }

    private Scanner makeScanner(String filename)
    {
        Scanner scanner = null;

        try
        {
            scanner = new Scanner(new File(filename));
        }
        catch (FileNotFoundException ex)
        {
            System.err.println("<!> file not found.");
        }

        return scanner;
    }

    private String parseProblemLine(Scanner scanner)
    {
        String problemLine = null;
        String line = null;

        while (scanner.hasNextLine() && problemLine == null)
        {
            line = scanner.nextLine();

            if (line.startsWith("p"))
            {
                problemLine = line;
            }
        }

        if (problemLine == null)
        {
            System.err.println("<!> problem line not found in file.");
        }

        return problemLine;
    }

    private int parseNumberOfVertices(final String problemLine)
    {
        var words = problemLine.split(" ");
        int number;

        try
        {
            number = Integer.parseInt(words[2]);
        }
        catch (NumberFormatException ex)
        {
            System.err.println("<!> failed to parse number of vertices.");
            number = -1;
        }
        
        return number;
    }

    private int parseNumberOfEdges(final String problemLine)
    {
        var words = problemLine.split(" ");
        int number;

        try
        {
            number = Integer.parseInt(words[3]);
        }
        catch (NumberFormatException ex)
        {
            System.err.println("<!> failed to parse number of edges.");
            number = -1;
        }
        
        return number;
    }
}
