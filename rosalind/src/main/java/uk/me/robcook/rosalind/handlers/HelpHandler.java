package uk.me.robcook.rosalind.handlers;

import java.io.PrintStream;

import uk.me.robcook.rosalind.commands.CommandName;
import uk.me.robcook.rosalind.commands.CountCommand;
import uk.me.robcook.rosalind.commands.HelpCommand;
import uk.me.robcook.rosalind.commands.ParseCommand;
import uk.me.robcook.rosalind.commands.TranscribeCommand;

public class HelpHandler extends CommandHandler<HelpCommand>
{
    private final PrintStream out;

    public HelpHandler(final PrintStream out)
    {
        this.out = out;
    }

    @Override
    public void handle(HelpCommand command)
    {
        if (command.getParsedArgs() == null)
        {
            dumpGenericHelp();
        }
        else
        {
            dumpCommandHelp(command);
        }
    }

    private void dumpGenericHelp()
    {
        out.println("Usage: Ros command args");
        out.println("");
        out.print("Where command is one of: ");

        for(var commandName : CommandName.values())
        {
            out.print(String.format("%s, ", commandName));
        }

        out.println();
        out.println("For help on a specific command: Ros help command");
    }

    private void dumpCommandHelp(HelpCommand command)
    {
        var commandName = command.getParsedArgs().commandName();

        var targetCommand = switch (commandName)
        {
            case CommandName.parse -> new ParseCommand(command.getArgs());
            case CommandName.count -> new CountCommand(command.getArgs());
            case CommandName.transcribe -> new TranscribeCommand(command.getArgs());
            default -> new HelpCommand(command.getArgs());
        };

        out.println(targetCommand.getHelpText());
    }
}
