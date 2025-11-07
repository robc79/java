package uk.me.robcook.rosalind.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class GeneticSequenceBuilderTests
{
    @Test
    public void shouldConstructNewSequence()
    {
        // Arrange
        final String expectedDescription = "description";
        final String expectedSequence = "ACGTTGAC";

        var builder = new GeneticSequenceBuilder();
        builder.setDescription(expectedDescription);
        builder.appendSequence(expectedSequence);
        
        // Act
        var sequence = builder.build();

        // Assert
        assertEquals(expectedDescription, sequence.getDescription());
        assertEquals(expectedSequence, sequence.getSequence());
    }

    @Test
    public void shouldThrowExceptionWhenDescritpionNotSet()
    {
        // Arrange
        final String sequeunce = "ACGTTGCA";
        var builder = new GeneticSequenceBuilder();
        builder.appendSequence(sequeunce);

        // Act

        // Assert
        assertThrows(IllegalStateException.class, () -> builder.build());
    }

    @Test
    public void shouldThrowExceptionWhenSequenceEmpty()
    {
        // Arrange
        final String description = "some description here";
        var builder = new GeneticSequenceBuilder();
        builder.setDescription(description);

        // Act

        // Assert
        assertThrows(IllegalStateException.class, () -> builder.build());
    }
}
