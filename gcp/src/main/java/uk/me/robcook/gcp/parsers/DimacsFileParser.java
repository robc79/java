package uk.me.robcook.gcp.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.Scanner;

import uk.me.robcook.gcp.domain.Graph;

public class DimacsFileParser implements GraphFileParser
{
    @Override
    public Graph parse(final String filename, final PrintStream err)
    {
        try (var scanner = makeScanner(filename, err))
        {
            if (scanner == null)
            {
                return null;
            }
            
            var problemLine = parseProblemLine(scanner, err);

            if (problemLine == null)
            {
                return null;
            }

            var numberOfVertices = parseNumberOfVertices(problemLine, err);
            var numberOfEdges = parseNumberOfEdges(problemLine, err);

            if (numberOfVertices == -1 || numberOfEdges == -1)
            {
                return null;
            }

            var graph = new Graph(numberOfVertices, numberOfEdges);
            
            try
            {
                parseEdgeLines(scanner, graph, err);
            }
            catch (ParseException ex)
            {
                return null;
            }

            return graph;
        }
    }

    private Scanner makeScanner(final String filename, final PrintStream err)
    {
        Scanner scanner = null;

        try
        {
            scanner = new Scanner(new File(filename));
        }
        catch (FileNotFoundException ex)
        {
            err.println("<!> File not found.");
        }

        return scanner;
    }

    private String parseProblemLine(final Scanner scanner, final PrintStream err)
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
            err.println("<!> Problem line not found in file.");
        }

        return problemLine;
    }

    private int parseNumberOfVertices(final String problemLine, final PrintStream err)
    {
        var words = problemLine.split(" ");
        int number;

        try
        {
            number = Integer.parseInt(words[2]);
        }
        catch (ArrayIndexOutOfBoundsException | NumberFormatException ex)
        {
            err.println("<!> Failed to parse number of vertices.");
            number = -1;
        }
        
        return number;
    }

    private int parseNumberOfEdges(final String problemLine, final PrintStream err)
    {
        var words = problemLine.split(" ");
        int number;

        try
        {
            number = Integer.parseInt(words[3]);
        }
        catch (ArrayIndexOutOfBoundsException | NumberFormatException ex)
        {
            err.println("<!> Failed to parse number of edges.");
            number = -1;
        }
        
        return number;
    }

    private void parseEdgeLines(
        final Scanner scanner,
        final Graph graph,
        final PrintStream err) throws ParseException
    {
        String line = null;

        while (scanner.hasNextLine())
        {
            line = scanner.nextLine();
            
            if (!line.startsWith("e"))
            {
                err.println(String.format("<!> Expected edge line but found '%s' instead.", line));
                throw new ParseException(line, 0);
            }

            var words = line.split(" ");
            int u;
            int v;

            try
            {
                u = Integer.parseInt(words[1]);
                v = Integer.parseInt(words[2]);
            }
            catch (ArrayIndexOutOfBoundsException | NumberFormatException ex)
            {
                err.println(String.format("<!> Failed to parse edge '%s'", line));
                throw new ParseException(line, 0);
            }

            graph.addEdge(u, v);
        }
    }
}
