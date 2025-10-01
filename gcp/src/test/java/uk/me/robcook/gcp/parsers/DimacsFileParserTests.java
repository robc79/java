package uk.me.robcook.gcp.parsers;

import static org.junit.jupiter.api.Assertions.assertNull;

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
}
