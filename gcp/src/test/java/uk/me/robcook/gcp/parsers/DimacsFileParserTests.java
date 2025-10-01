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
}
