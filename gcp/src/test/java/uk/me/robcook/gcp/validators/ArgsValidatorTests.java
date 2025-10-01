package uk.me.robcook.gcp.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ArgsValidatorTests
{
    private static Stream<Arguments> provideFailingArgs()
    {
        return Stream.of(Arguments.of((Object) new String[] { "filename", "another_arg" }));
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @MethodSource("provideFailingArgs")
    public void shouldFailIfArgsLengthNotEqualToOne(String[] failingArgs)
    {
        // Arrange
        var validator = new ArgsValidator();

        // Act
        var result = validator.Validate(failingArgs);

        // Assert
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = { "some_file.txt", "graph.col.b" })
    public void shouldFailIfFilenameNotEndingInCol(String failingFilename)
    {
        // Arrange
        var validator = new ArgsValidator();
        var args = new String[] { failingFilename };
        
        // Act
        var result = validator.Validate(args);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldFailIfFileDoesntExist()
    {
        // Arrange
        final var filename = "non_existant_file.col";
        var validator = new ArgsValidator();
        var args = new String[] { filename };

        // Act
        var result = validator.Validate(args);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldSucceed() throws IOException
    {
        // Arrange
        final var filename = "filename";
        final var extension = ".col";
        var tmpFile = File.createTempFile(filename, extension);
        tmpFile.deleteOnExit();

        var validator = new ArgsValidator();
        var args = new String[] { tmpFile.getAbsolutePath() };

        // Act
        var result = validator.Validate(args);

        // Assert
        assertTrue(result);
    }
}
