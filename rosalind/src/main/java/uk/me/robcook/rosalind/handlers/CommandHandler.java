package uk.me.robcook.rosalind.handlers;

@FunctionalInterface
public interface CommandHandler<TCommand>
{
    void handle(TCommand command);
}
