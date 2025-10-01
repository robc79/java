package uk.me.robcook.gcp.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GraphTests
{
    @Test
    public void shouldFailConstructionWhenVerticesOne()
    {
        // Arrange
        final int vertices = 1;
        final int edges = 1;

        // Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Graph(vertices, edges));

        // Assert
        assertEquals(IllegalArgumentException.class, ex.getClass());
        assertEquals("Number of vertices must be 2 or more.", ex.getMessage());
    }

    @Test
    public void shouldFailConstructionWhenEdgesZero()
    {
        // Arrange
        final int vertices = 2;
        final int edges = 0;

        // Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Graph(vertices, edges));

        // Assert
        assertEquals(IllegalArgumentException.class, ex.getClass());
        assertEquals("Number of edges must be 1 or more.", ex.getMessage());
    }

    @Test
    public void shouldSucceedConstructionWithTwoVerticesAndOneEdge()
    {
        // Arrange
        final int vertices = 2;
        final int edges = 1;

        // Act
        var result = new Graph(vertices, edges);

        // Assert
        assertNotNull(result);
        assertEquals(vertices, result.getNumberofVertices());
        assertEquals(edges, result.getNumberOfEdges());
    }

    @Test
    public void shouldFailValidationWhenNumberOfVerticesDoesntMatchTotal()
    {
        // Arrange
        var graph = new Graph(3, 1);
        graph.addEdge(1, 2);

        // Act
        var result = graph.validate();

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldFailValidationWhenNumberOfEdgesDoesntMatchTotal()
    {
        // Arrange
        var graph = new Graph(3, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        // Act
        var result = graph.validate();

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldPassValidationWhenNumberOfVerticesAndEdgesMatchTotals()
    {
        // Arrange
        var graph = new Graph(3, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        // Act
        var result = graph.validate();

        // Assert
        assertTrue(result);
    }
}
