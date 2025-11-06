package uk.me.robcook.rosalind;

import java.text.ParseException;

import uk.me.robcook.rosalind.args.ArgsParser;
import uk.me.robcook.rosalind.args.ParseArgs;
import uk.me.robcook.rosalind.commands.Command;
import uk.me.robcook.rosalind.commands.CommandName;
import uk.me.robcook.rosalind.commands.HelpCommand;
import uk.me.robcook.rosalind.handlers.CommandDispatcher;
import uk.me.robcook.rosalind.handlers.HelpHandler;
import uk.me.robcook.rosalind.handlers.ParseHandler;

public class Ros
{
    private final ParseArgs parser;
    private final CommandDispatcher dispatcher;

    public Ros(final ParseArgs validator, final CommandDispatcher dispatcher)
    {
        this.parser = validator;
        this.dispatcher = dispatcher;
    }

    public void run(String[] args)
    {
        Command command = null;

        try
        {
            command = parser.validate(args);
        }
        catch (ParseException ex)
        {
            System.err.println(String.format("<!> %s", ex.getMessage()));
            System.err.println();

            System.out.println("Usage: Ros command args");
            System.out.println();
            System.exit(1);
        }

        dispatcher.invoke(command);
    }

    public static void main(String[] args)
    {
        var parser = new ArgsParser();
        
        var dispatcher = new CommandDispatcher();
        var parseHandler = new ParseHandler(System.out, System.err);
        dispatcher.registerHandler(CommandName.parse, parseHandler);
        var helpHandler = new HelpHandler(System.out, System.err);
        dispatcher.registerHandler(CommandName.help, helpHandler);

        var program = new Ros(parser, dispatcher);
        program.run(args);
    }
}
