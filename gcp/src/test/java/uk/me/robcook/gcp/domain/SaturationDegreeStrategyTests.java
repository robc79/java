package uk.me.robcook.gcp.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class SaturationDegreeStrategyTests
{
    @RepeatedTest(1000)
    public void shouldReturnRandomUncolouredVertexWhenGraphUncoloured()
    {
        // Arrange
        var graph = makeGraph();
        var assignments = new ArrayList<List<Integer>>();
        var strategy = new Graph.SaturationDegreeStrategy();

        // Act
        var vertex = strategy.pick(graph, assignments);

        // Assert
        assertNotNull(vertex);
    }

    @Test
    public void shouldReturnOnlyUncolouredVertexWhenAllVerticesBarOneColoured()
    {
        // Arrange
        var graph = makeGraph();
        
        var assignments = new ArrayList<List<Integer>>();
        assignments.add(0, new ArrayList<Integer>(Arrays.asList(1)));
        assignments.add(1, new ArrayList<Integer>(Arrays.asList(2, 4)));

        var strategy = new Graph.SaturationDegreeStrategy();

        // Act
        var vertex = strategy.pick(graph, assignments);

        // Assert
        assertEquals(3, vertex);
    }

    @Test
    public void shouldReturnMaximumSaturatedUncolouredVertexWhenVerticesPartiallyColoured()
    {
        // Arrange
        var graph = makeGraph();
        
        var assignments = new ArrayList<List<Integer>>();
        assignments.add(0, new ArrayList<Integer>(Arrays.asList(2)));
        assignments.add(1, new ArrayList<Integer>(Arrays.asList(3)));

        var strategy = new Graph.SaturationDegreeStrategy();

        // Act
        var vertex = strategy.pick(graph, assignments);

        // Assert
        assertEquals(1, vertex);
    }

    private Map<Integer, List<Integer>> makeGraph()
    {
        // Make a graph with four vertices and five edges:
        //
        // 1 - 2
        // | \ |
        // 4 - 3
        
        var graph = new HashMap<Integer, List<Integer>>();
        graph.put(1, new ArrayList<Integer>(Arrays.asList(2, 3, 4)));
        graph.put(2, new ArrayList<Integer>(Arrays.asList(1, 3)));
        graph.put(3, new ArrayList<Integer>(Arrays.asList(1, 2, 4)));
        graph.put(4, new ArrayList<Integer>(Arrays.asList(1, 3)));
        
        return graph;
    }
}
