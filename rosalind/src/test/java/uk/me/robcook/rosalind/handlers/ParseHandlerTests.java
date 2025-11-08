package uk.me.robcook.rosalind.handlers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import uk.me.robcook.rosalind.commands.ParseCommand;
import uk.me.robcook.rosalind.domain.GeneticSequence;
import uk.me.robcook.rosalind.parsers.fasta.FileParser;

public class ParseHandlerTests
{
    @Test
    public void shouldPrintSequenceIfParseSucceeds()
    {
        // Arrange
        final String filename = "filename";
        var mockOut = Mockito.mock(PrintStream.class);
        
        var mockParser = Mockito.mock(FileParser.class);
        var sequence = new GeneticSequence("description", "ACTG");
        when(mockParser.parse(filename)).thenReturn(sequence);

        var sut = new ParseHandler(mockOut, mockParser);
        var command = new ParseCommand(new String[] { filename });

        // Act
        sut.handle(command);

        // Assert
        Mockito.verify(mockParser, times(1)).parse(filename);
        Mockito.verify(mockOut, atLeastOnce()).println(anyString());
    }

    @Test
    public void shouldPrintNothingIfParseFails()
    {
        // Arrange
        final String filename = "filename";
        var mockOut = Mockito.mock(PrintStream.class);
        
        var mockParser = Mockito.mock(FileParser.class);
        when(mockParser.parse(filename)).thenReturn(null);

        var sut = new ParseHandler(mockOut, mockParser);
        var command = new ParseCommand(new String[] { filename });

        // Act
        sut.handle(command);

        // Assert
        Mockito.verify(mockParser, times(1)).parse(filename);
        Mockito.verify(mockOut, never()).println(anyString());
    }
}
