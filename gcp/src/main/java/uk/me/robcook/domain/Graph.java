package uk.me.robcook.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph
{
    private final Map<Integer, List<Integer>> adjacenyList;
    private int numberofVertices;
    private int numberOfEdges;
    
    public Graph(final int numberOfVertices, final int numberOfEdges)
    {
        adjacenyList = new HashMap<Integer, List<Integer>>();
        this.numberofVertices = numberOfVertices;
        this.numberOfEdges = numberOfEdges;
    }

    public int getNumberofVertices() { return numberofVertices; }

    public int getNumberOfEdges() { return numberOfEdges; }

    public Colouring colour(ColouringHeuristic heuristic)
    {
        // TODO: Colour the graph using the supplied heuristic.
        
        return new Colouring();
    }
}
