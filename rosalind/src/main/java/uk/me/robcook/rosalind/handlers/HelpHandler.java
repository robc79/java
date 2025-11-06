package uk.me.robcook.rosalind.handlers;

import java.io.PrintStream;

import uk.me.robcook.rosalind.commands.Command;
import uk.me.robcook.rosalind.commands.CommandName;
import uk.me.robcook.rosalind.commands.HelpCommand;
import uk.me.robcook.rosalind.commands.ParseCommand;

public class HelpHandler implements CommandHandler<HelpCommand>
{
    private final PrintStream out;

    public HelpHandler(final PrintStream out)
    {
        this.out = out;
    }

    @Override
    public void handle(HelpCommand command)
    {
        if (command.getArgs().length == 0)
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

    private void dumpCommandHelp(Command command)
    {
        var commandArgs = command.getArgs();
        var commandName = CommandName.valueOf(commandArgs[0]);

        var targetCommand = switch (commandName)
        {
            case CommandName.parse -> new ParseCommand(commandArgs);
            default -> new HelpCommand(commandArgs);
        };

        out.println(targetCommand.getHelpText());
    }
}
