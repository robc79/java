package uk.me.robcook.rosalind.parsers.fasta;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import java.io.File;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FastaFileParserTests
{
    @Test
    public void shouldParseValidFileSuccessfully()
    {
        // Arrange
        var mockErr = Mockito.mock(PrintStream.class);

        var sut = new FastaFileParser(mockErr);

        final var filename = "valid_sequence.txt";
        var classLoader = getClass().getClassLoader();
        var absoluteFilename = classLoader.getResource(filename).getFile();
        var file = new File(absoluteFilename);

        // Act
        var sequence = sut.parse(file);

        // Assert
        assertNotNull(sequence);
        Mockito.verify(mockErr, never()).println(anyString());
    }

    @Test
    public void shouldFailIfFileDoesntExist()
    {
        // Arrange
        var mockErr = Mockito.mock(PrintStream.class);

        var sut = new FastaFileParser(mockErr);

        final var filename = "not_there.txt";
        var file = new File(filename);

        // Act
        var sequence = sut.parse(file);

        // Assert
        assertNull(sequence);
        Mockito.verify(mockErr, times(1)).println("<!> File not found.");
    }

    @Test
    public void shouldFailIfHeaderMissing()
    {
        // Arrange
        var mockErr = Mockito.mock(PrintStream.class);

        var sut = new FastaFileParser(mockErr);

        final var filename = "missing_header.txt";
        var classLoader = getClass().getClassLoader();
        var absoluteFilename = classLoader.getResource(filename).getFile();
        var file = new File(absoluteFilename);

        // Act
        var sequence = sut.parse(file);

        // Assert
        assertNull(sequence);
        Mockito.verify(mockErr, times(1)).println("<!> Invalid starting character, expected '>'.");
    }

    @Test
    public void shoudlFailIfSequenceMissing()
    {
        // Arrange
        var mockErr = Mockito.mock(PrintStream.class);

        var sut = new FastaFileParser(mockErr);

        final var filename = "missing_sequence.txt";
        var classLoader = getClass().getClassLoader();
        var absoluteFilename = classLoader.getResource(filename).getFile();
        var file = new File(absoluteFilename);

        // Act
        var sequence = sut.parse(file);

        // Assert
        assertNull(sequence);
        Mockito.verify(mockErr, times(1)).println("<!> Both description and sequence must be set.");
    }

    @Test
    public void shoudlFailIfFileEmpty()
    {
        // Arrange
        var mockErr = Mockito.mock(PrintStream.class);

        var sut = new FastaFileParser(mockErr);

        final var filename = "empty_file.txt";
        var classLoader = getClass().getClassLoader();
        var absoluteFilename = classLoader.getResource(filename).getFile();
        var file = new File(absoluteFilename);

        // Act
        var sequence = sut.parse(file);

        // Assert
        assertNull(sequence);
        Mockito.verify(mockErr, times(1)).println(startsWith("<!> No sequence found."));
    }
}
