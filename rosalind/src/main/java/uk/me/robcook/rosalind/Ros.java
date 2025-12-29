package uk.me.robcook.rosalind;

import java.text.ParseException;

import uk.me.robcook.rosalind.args.ArgumentsParser;
import uk.me.robcook.rosalind.args.ParseArguments;
import uk.me.robcook.rosalind.commands.Command;
import uk.me.robcook.rosalind.commands.CountCommand;
import uk.me.robcook.rosalind.commands.HelpCommand;
import uk.me.robcook.rosalind.commands.ParseCommand;
import uk.me.robcook.rosalind.commands.TranscribeCommand;
import uk.me.robcook.rosalind.handlers.CommandDispatcher;
import uk.me.robcook.rosalind.handlers.CountHandler;
import uk.me.robcook.rosalind.handlers.HelpHandler;
import uk.me.robcook.rosalind.handlers.ParseHandler;
import uk.me.robcook.rosalind.handlers.TranscribeHandler;
import uk.me.robcook.rosalind.parsers.fasta.FastaFileParser;

public class Ros
{
    private final ParseArguments parser;
    private final CommandDispatcher dispatcher;

    public Ros(final ParseArguments parser, final CommandDispatcher dispatcher)
    {
        this.parser = parser;
        this.dispatcher = dispatcher;
    }

    public void run(String[] args)
    {
        Command<?> command = null;

        try
        {
            command = parser.parseAndValidate(args);
        }
        catch (ParseException ex)
        {
            command = new HelpCommand(new String[] {});
        }

        dispatcher.invoke(command);
    }

    public static void main(String[] args)
    {
        var parser = new ArgumentsParser();
        var dispatcher = new CommandDispatcher();
        
        var fileParser = new FastaFileParser(System.err);
        
        var parseHandler = new ParseHandler(System.out, System.err, fileParser);
        dispatcher.registerHandler(ParseCommand.class, parseHandler);

        var helpHandler = new HelpHandler(System.out);
        dispatcher.registerHandler(HelpCommand.class, helpHandler);

        var countHandler = new CountHandler(System.out, System.err, fileParser);
        dispatcher.registerHandler(CountCommand.class, countHandler);

        var transcribeHandler = new TranscribeHandler(System.out, System.err, fileParser);
        dispatcher.registerHandler(TranscribeCommand.class, transcribeHandler);

        var program = new Ros(parser, dispatcher);
        program.run(args);
    }
}
