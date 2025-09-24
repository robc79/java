package rps;

import java.util.Map;
import java.util.Scanner;

public class HumanPlayer extends Player
{
    private static final Map<String, Choice> input2choice = Map.of(
        "r", Choice.ROCK,
        "p", Choice.PAPER,
        "s", Choice.SCISSORS
    );

    public HumanPlayer(final String name)
    {
        super(name);
    }

    @Override
    public Choice pick()
    {
        Choice choice = null;
        
        @SuppressWarnings("resource")
        var scanner = new Scanner(System.in);

        var rawInput = "";

        while (!input2choice.keySet().contains(rawInput))
        {
            System.out.print("Enter your choice (r, p, s): ");
            rawInput = scanner.nextLine();
        }

        choice = input2choice.get(rawInput);

        return choice;
    }
}
