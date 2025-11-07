package uk.me.robcook.rosalind.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GeneticSequenceTests
{
    @Test
    public void shouldConstructNewGeneticSequence()
    {
        // Arrange
        final String description = "some description here";
        final String sequence = "ACGTTGACACT";

        // Act
        var sut = new GeneticSequence(description, sequence);

        // Assert
        assertEquals(description, sut.getDescription());
        assertEquals(sequence.toUpperCase(), sut.getSequence());
    }
}
