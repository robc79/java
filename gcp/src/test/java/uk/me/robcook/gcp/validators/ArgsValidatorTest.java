package uk.me.robcook.gcp.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

public class ArgsValidatorTest
{
    private static Stream<Arguments> provideFailingArgs()
    {
        return Stream.of(Arguments.of((Object) new String[] { "filename", "another_arg" }));
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @MethodSource("provideFailingArgs")
    public void ShouldFailIfArgsLengthNotEqualToOne(String[] failingArgs)
    {
        // Arrange
        var validator = new ArgsValidator();

        // Act
        var result = validator.Validate(failingArgs);

        // Assert
        assertFalse(result);
    }
}
