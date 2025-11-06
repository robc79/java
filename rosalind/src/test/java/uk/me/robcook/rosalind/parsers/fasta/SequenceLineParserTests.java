package uk.me.robcook.rosalind.parsers.fasta;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import uk.me.robcook.rosalind.domain.GeneticSequenceBuilder;

public class SequenceLineParserTests
{
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
