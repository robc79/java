package uk.me.robcook.gcp.domain;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface VertexPickingStrategy
{
    int pick(Map<Integer, List<Integer>> adjacencyLists, List<List<Integer>> colourAssignments);    
}
