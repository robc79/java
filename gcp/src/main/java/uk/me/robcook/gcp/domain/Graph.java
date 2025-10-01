package uk.me.robcook.gcp.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph
{
    private final Map<Integer, List<Integer>> adjacencyLists;

    private int numberOfVertices;
    private int numberOfEdges;
    
    /**
     * Construct a graph with a minimum of two vertices and one edge.
     * 
     * @param numberOfVertices total vertices for this graph.
     * @param numberOfEdges total edges for this graph.
     */
    public Graph(final int numberOfVertices, final int numberOfEdges)
    {
        if (numberOfVertices < 2)
        {
            throw new IllegalArgumentException("Number of vertices must be 2 or more.");
        }

        if (numberOfEdges < 1)
        {
            throw new IllegalArgumentException("Number of edges must be 1 or more.");
        }

        adjacencyLists = new HashMap<Integer, List<Integer>>();
        this.numberOfVertices = numberOfVertices;
        this.numberOfEdges = numberOfEdges;
    }

    public int getNumberOfVertices() { return numberOfVertices; }

    public int getNumberOfEdges() { return numberOfEdges; }

    public Map<Integer, List<Integer>> dump()
    {
        return Collections.unmodifiableMap(adjacencyLists);
    }

    public void addEdge(int u, int v)
    {
        addEdgeImpl(u, v);
        addEdgeImpl(v, u);
    }

    public boolean validate()
    {
        var verticesCorrect = numberOfVertices == adjacencyLists.size();
        var edgesCorrect = numberOfEdges == countEdges();

        return verticesCorrect && edgesCorrect;
    }

    public Colouring colour(final VertexPickingStrategy picker)
    {
        var colourAssignments = new ArrayList<List<Integer>>();

        while (sumAssignedVertices(colourAssignments) != numberOfVertices)
        {
            var vertex = picker.pick(adjacencyLists, colourAssignments);
            var colour = assignVertexColour(colourAssignments, vertex);
            
            if (colourAssignments.size() - 1 < colour)
            {
                colourAssignments.set(colour, new ArrayList<>(Arrays.asList(vertex)));
            }
            else
            {
                colourAssignments.get(colour).add(vertex);
            }
        }
        
        return new Colouring(colourAssignments);
    }

    private void addEdgeImpl(int u, int v)
    {
        List<Integer> adjacencyList;
        
        if (adjacencyLists.containsKey(u))
        {
            adjacencyList = adjacencyLists.get(u);
        }
        else
        {
            adjacencyList = new ArrayList<Integer>();
            adjacencyLists.put(u, adjacencyList);
        }

        if (!adjacencyList.contains(v))
        {
            adjacencyList.add(v);
        }
    }

    private int countEdges()
    {
        var sum = 0;

        for (var adjacencyList : adjacencyLists.values())
        {
            sum += adjacencyList.size();
        }

        return sum / 2;
    }

    private int sumAssignedVertices(final List<List<Integer>> colourAssignments)
    {
        var sum = 0;

        for (var vertices : colourAssignments)
        {
            sum += vertices.size();
        }

        return sum;
    }

    private int assignVertexColour(final List<List<Integer>> colourAssignments, int vertex)
    {
        var assignedColour = -1;
        var neighboursOfVertex = adjacencyLists.get(vertex);
        var coloursOfNeighbours = coloursOf(neighboursOfVertex, colourAssignments);
        
        for (var i=0; i<colourAssignments.size(); i++)
        {
            if (!coloursOfNeighbours.contains(i))
            {
                assignedColour = i;
                break;
            }
        }
        
        return assignedColour;
    }

    private List<Integer> coloursOf(final List<Integer> vertices, final List<List<Integer>> colourAssignments)
    {
        var colours = new ArrayList<Integer>();

        for (var vertex : vertices)
        {
            var assignedColour = assignedColourOf(colourAssignments, vertex);

            if (assignedColour != -1)
            {
                colours.add(assignedColour);
            }
        }

        return colours;
    }

    private int assignedColourOf(final List<List<Integer>> colourAssignments, int vertex)
    {
        var assignedColour = -1;

        for (int i=0; i<colourAssignments.size(); i++)
        {
            if (colourAssignments.get(i).contains(vertex))
            {
                assignedColour = i;
                break;
            }
        }

        return assignedColour;
    }
}
