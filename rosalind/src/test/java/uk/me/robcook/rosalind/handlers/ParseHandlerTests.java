package uk.me.robcook.rosalind.handlers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import uk.me.robcook.rosalind.commands.ParseCommand;

public class ParseHandlerTests
{
    @Test
    public void shouldParseValidFileSuccessfully()
    {
        // Arrange
        var mockOut = Mockito.mock(PrintStream.class);
        var mockErr = Mockito.mock(PrintStream.class);

        var sut = new ParseHandler(mockOut, mockErr);

        final var filename = "valid_sequence.txt";
        var classLoader = getClass().getClassLoader();
        var absoluteFilename = classLoader.getResource(filename).getFile();
        var command = new ParseCommand(new String[] { absoluteFilename });
        
        // Act
        sut.handle(command);

        // Assert
        Mockito.verify(mockOut, atLeastOnce()).println(anyString());
        Mockito.verify(mockErr, never()).println(anyString());
    }

    @Test
    public void shouldFailIfFileDoesntExist()
    {
        // Arrange
        var mockOut = Mockito.mock(PrintStream.class);
        var mockErr = Mockito.mock(PrintStream.class);

        var sut = new ParseHandler(mockOut, mockErr);

        final var filename = "valid_sequence.txt";
        var command = new ParseCommand(new String[] { filename });

        // Act
        sut.handle(command);

        // Assert
        Mockito.verify(mockErr, times(1)).println(startsWith("<!>"));
    }

    @Test
    public void shouldFailIfHeaderMissing()
    {
        // Arrange
        var mockOut = Mockito.mock(PrintStream.class);
        var mockErr = Mockito.mock(PrintStream.class);

        var sut = new ParseHandler(mockOut, mockErr);

        final var filename = "missing_header.txt";
        var classLoader = getClass().getClassLoader();
        var absoluteFilename = classLoader.getResource(filename).getFile();
        var command = new ParseCommand(new String[] { absoluteFilename });

        // Act
        sut.handle(command);

        // Assert
        Mockito.verify(mockErr, times(1)).println(startsWith("<!>"));
    }

    @Test
    public void shoudlFailIfSequenceMissing()
    {
        // Arrange
        var mockOut = Mockito.mock(PrintStream.class);
        var mockErr = Mockito.mock(PrintStream.class);

        var sut = new ParseHandler(mockOut, mockErr);

        final var filename = "missing_sequence.txt";
        var classLoader = getClass().getClassLoader();
        var absoluteFilename = classLoader.getResource(filename).getFile();
        var command = new ParseCommand(new String[] { absoluteFilename });

        // Act
        sut.handle(command);

        // Assert
        Mockito.verify(mockErr, times(1)).println(startsWith("<!>"));
    }

    @Test
    public void shoudlFailIfFileEmpty()
    {
        // Arrange
        var mockOut = Mockito.mock(PrintStream.class);
        var mockErr = Mockito.mock(PrintStream.class);

        var sut = new ParseHandler(mockOut, mockErr);

        final var filename = "empty_file.txt";
        var classLoader = getClass().getClassLoader();
        var absoluteFilename = classLoader.getResource(filename).getFile();
        var command = new ParseCommand(new String[] { absoluteFilename });

        // Act
        sut.handle(command);

        // Assert
        Mockito.verify(mockErr, times(1)).println(startsWith("<!>"));
    }
}
