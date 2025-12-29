package uk.me.robcook.rosalind.args;

import java.text.ParseException;

import uk.me.robcook.rosalind.commands.Command;

public interface ParseArguments
{
    Command<?> parseAndValidate(String[] args) throws ParseException;
}
