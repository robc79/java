package uk.me.robcook.rosalind.handlers;

import uk.me.robcook.rosalind.args.Args;
import uk.me.robcook.rosalind.commands.Command;

public abstract class CommandHandler<TCommand extends Command<? extends Args>>
{
    public abstract void handle(TCommand command);
}
