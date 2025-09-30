package uk.me.robcook.gcp.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph
{
    private final Map<Integer, List<Integer>> adjacencyLists;
    private int numberOfVertices;
    private int numberOfEdges;
    
    public Graph(final int numberOfVertices, final int numberOfEdges)
    {
        adjacencyLists = new HashMap<Integer, List<Integer>>();
        this.numberOfVertices = numberOfVertices;
        this.numberOfEdges = numberOfEdges;
    }

    public int getNumberofVertices() { return numberOfVertices; }

    public int getNumberOfEdges() { return numberOfEdges; }

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

    public Colouring colour(ColouringHeuristic heuristic)
    {
        // TODO: Colour the graph using the supplied heuristic.
        
        return new Colouring();
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

        for(var adjacencyList : adjacencyLists.values())
        {
            sum += adjacencyList.size();
        }

        return sum / 2;
    }
}
