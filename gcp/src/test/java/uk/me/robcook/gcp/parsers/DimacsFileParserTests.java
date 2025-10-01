package uk.me.robcook.gcp.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.startsWith;

import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DimacsFileParserTests
{
    @Test
    public void shouldFailWhenFileToParseNotFound()
    {
        // Arrange
        final var nonExistantFilename = "some_missing_file.col";
        var mockError = Mockito.mock(PrintStream.class);
        var parser = new DimacsFileParser();

        // Act
        var result = parser.parse(nonExistantFilename, mockError);

        // Assert
        assertNull(result);
        Mockito.verify(mockError).println("<!> File not found.");
    }

    @Test
    public void shouldFailWhenFileIsMissingProblemLine()
    {
        // Arrange
        final var filename = "missing_problem.col";
        var classLoader = getClass().getClassLoader();
        var absoluteFilename = classLoader.getResource(filename).getFile();
        var mockError = Mockito.mock(PrintStream.class);
        var parser = new DimacsFileParser();

        // Act
        var result = parser.parse(absoluteFilename, mockError);

        // Assert
        assertNull(result);
        Mockito.verify(mockError).println("<!> Problem line not found in file.");
    }

    @Test
    public void shouldFailWhenFileHasMissingNumberOfVertices()
    {
        // Arrange
        final var filename = "missing_problem_vertices.col";
        var classLoader = getClass().getClassLoader();
        var absoluteFilename = classLoader.getResource(filename).getFile();
        var mockError = Mockito.mock(PrintStream.class);
        var parser = new DimacsFileParser();

        // Act
        var result = parser.parse(absoluteFilename, mockError);

        // Assert
        assertNull(result);
        Mockito.verify(mockError).println("<!> Failed to parse number of vertices.");
    }

    @Test
    public void shouldFailWhenFileHasMissingNumberOfEdges()
    {
        // Arrange
        final var filename = "missing_problem_edges.col";
        var classLoader = getClass().getClassLoader();
        var absoluteFilename = classLoader.getResource(filename).getFile();
        var mockError = Mockito.mock(PrintStream.class);
        var parser = new DimacsFileParser();

        // Act
        var result = parser.parse(absoluteFilename, mockError);

        // Assert
        assertNull(result);
        Mockito.verify(mockError).println("<!> Failed to parse number of edges.");
    }

    @Test
    public void shouldFailWhenEdgeRowMissingData()
    {
        // Arrange
        final var filename = "missing_edge_data.col";
        var classLoader = getClass().getClassLoader();
        var absoluteFilename = classLoader.getResource(filename).getFile();
        var mockError = Mockito.mock(PrintStream.class);
        var parser = new DimacsFileParser();

        // Act
        var result = parser.parse(absoluteFilename, mockError);

        // Assert
        assertNull(result);
        Mockito.verify(mockError).println(startsWith("<!> Failed to parse edge"));
    }

    @Test
    public void shouldReturnGraphWhenFileCorrectlyFormatted()
    {
        // Arrange
        final var filename = "valid_graph.col";
        var classLoader = getClass().getClassLoader();
        var absoluteFilename = classLoader.getResource(filename).getFile();
        var mockError = Mockito.mock(PrintStream.class);
        var parser = new DimacsFileParser();

        // Act
        var result = parser.parse(absoluteFilename, mockError);

        // Assert
        assertNotNull(result);
        assertEquals(4, result.getNumberOfVertices());
        assertEquals(4, result.getNumberOfEdges());
        assertEquals(4, result.dump().size());
        assertTrue(result.validate());
        Mockito.verify(mockError, Mockito.never()).println(anyString());
    }
}
