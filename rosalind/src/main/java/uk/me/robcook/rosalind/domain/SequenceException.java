package uk.me.robcook.rosalind.domain;

public class SequenceException extends Exception
{
    public SequenceException(String message)
    {
        super(message);
    }

    public SequenceException(String message, Throwable cause)
    {
        super(message, cause);
        initCause(cause);
    }
}
