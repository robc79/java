package uk.me.robcook.gcp.domain;

import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

public class SaturationDegreeStrategy implements VertexPickingStrategy
{
    private static final RandomGenerator prng = RandomGenerator.getDefault();

    @Override
    public int pick(Map<Integer, List<Integer>> adjacencyLists, List<List<Integer>> colourAssignments)
    {
        // No colours assigned? Pick a random vertex from the graph and return it.
        if (colourAssignments.size() == 0)
        {
            return prng.nextInt(1, adjacencyLists.size());
        }

        // Calculate the saturation degree of each uncoloured vertex in the graph.
        // Return the one with the biggest saturation degree.
        var maxSaturation = 0;
        int pickedVertex = -1;

        for (var vertex : adjacencyLists.keySet())
        {
            if (Graph.assignedColourOf(colourAssignments, vertex) == -1)
            {
                var coloursOfNeighbours = Graph.coloursOf(adjacencyLists.get(vertex), colourAssignments);

                if (coloursOfNeighbours.size() > maxSaturation)
                {
                    maxSaturation = coloursOfNeighbours.size();
                    pickedVertex = vertex;
                }
            }
        }
        
        return pickedVertex;
    }   
}
