package uk.me.robcook.rosalind.parsers.fasta;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import uk.me.robcook.rosalind.domain.GeneticSequenceBuilder;

public class HeaderLineParserTests
{
    @Test
    public void shouldFailIfLineDoesntBeginWithRightAngleBracketChar()
    {
        // Arrange
        var parser = new HeaderLineParser();
        var builder = new GeneticSequenceBuilder();
        var badLine = "no right angle bracket here!";

        // Act

        // Assert
        assertThrows(ParseException.class, () -> parser.parse(badLine, builder));
    }

    @Test
    public void shouldSetDescription() throws ParseException
    {
        // Arrange
        var parser = new HeaderLineParser();
        var builder = Mockito.mock(GeneticSequenceBuilder.class);
        var line = ">some description here";

        // Act
        parser.parse(line, builder);

        // Assert
        Mockito.verify(builder, Mockito.times(1)).setDescription(anyString());
    }
}
