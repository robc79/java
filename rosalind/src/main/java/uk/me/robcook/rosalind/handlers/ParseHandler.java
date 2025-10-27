package uk.me.robcook.rosalind.handlers;

import uk.me.robcook.rosalind.commands.ParseCommand;

public class ParseHandler implements CommandHandler<ParseCommand>
{
    public void handle(ParseCommand command)
    {
        System.out.println("Handling parse command.");
    }   
}
