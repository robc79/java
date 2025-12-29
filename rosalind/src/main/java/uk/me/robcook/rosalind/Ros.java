package uk.me.robcook.rosalind;

import java.text.ParseException;

import uk.me.robcook.rosalind.args.ArgumentsParser;
import uk.me.robcook.rosalind.args.ParseArguments;
import uk.me.robcook.rosalind.commands.Command;
import uk.me.robcook.rosalind.commands.CommandName;
import uk.me.robcook.rosalind.commands.HelpCommand;
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
        dispatcher.registerHandler(CommandName.parse, parseHandler);

        var helpHandler = new HelpHandler(System.out);
        dispatcher.registerHandler(CommandName.help, helpHandler);

        var countHandler = new CountHandler(System.out, System.err, fileParser);
        dispatcher.registerHandler(CommandName.count, countHandler);

        var transcribeHandler = new TranscribeHandler(System.out, System.err, fileParser);
        dispatcher.registerHandler(CommandName.transcribe, transcribeHandler);

        var program = new Ros(parser, dispatcher);
        program.run(args);
    }
}
