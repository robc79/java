package uk.me.robcook.rosalind.parsers.fasta;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.anyString;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import uk.me.robcook.rosalind.domain.GeneticSequenceBuilder;

public class SequenceLineParserTests
{
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"some random line"})
    public void shouldAlwaysReturnSequenceLineParserForNextParser(String line)
    {
        // Arrange
        var parser = new HeaderLineParser();

        // Act
        var nextParser = parser.getNextParserFor(line);

        // Assert
        assertInstanceOf(SequenceLineParser.class, nextParser);
    }

    @Test
    public void shouldAppendSequence() throws ParseException
    {
        // Arrange
        var parser = new SequenceLineParser();
        var builder = Mockito.mock(GeneticSequenceBuilder.class);
        var line = "any sequence of chars here";

        // Act
        parser.parse(line, builder);

        // Assert
        Mockito.verify(builder, Mockito.times(1)).appendSequence(line);
    }
}
