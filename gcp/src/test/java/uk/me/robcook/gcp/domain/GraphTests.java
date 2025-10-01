package uk.me.robcook.gcp.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class GraphTests
{
    // Succeeds for positive vertices and edges
    // invalid when vertices don't match
    // invalid when edges don't match
    // valid when vertices and edges match

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
        assertEquals("Number of vertices must be more than 1.", ex.getMessage());
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
        assertEquals("Number of edges must be more than 0.", ex.getMessage());
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
}
