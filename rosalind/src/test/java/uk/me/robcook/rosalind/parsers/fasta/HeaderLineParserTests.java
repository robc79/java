package uk.me.robcook.rosalind.parsers.fasta;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.util.function.BiConsumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import uk.me.robcook.rosalind.domain.GeneticSequenceBuilder;

public class HeaderLineParserTests
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
}
