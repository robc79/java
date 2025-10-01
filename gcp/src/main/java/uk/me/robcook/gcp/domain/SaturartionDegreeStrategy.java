package uk.me.robcook.gcp.domain;

import java.util.List;
import java.util.Map;

public class SaturartionDegreeStrategy implements VertexPickingStrategy
{
    @Override
    public int pick(Map<Integer, List<Integer>> adjacencyLists, List<List<Integer>> colourAssignments)
    {
        
        // TODO: Pick an uncoloured vertex with the highest saturation degree and return it.
        return -1;
    }   
}
