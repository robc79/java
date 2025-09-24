package rps;

import java.util.Map;
import java.util.random.RandomGenerator;

public class HistoricalRandomPlayer extends Player
{
    private static final RandomGenerator prng = RandomGenerator.getDefault();

    private static final Map<Integer, Choice> num2choice = Map.of(
        1, Choice.ROCK,
        2, Choice.PAPER,
        3, Choice.SCISSORS
    );

    private static final Map<Choice, Choice> beatenBy = Map.of(
        Choice.ROCK, Choice.PAPER,
        Choice.PAPER, Choice.SCISSORS,
        Choice.SCISSORS, Choice.ROCK
    );

    public HistoricalRandomPlayer(final String name)
    {
        super(name);
    }

    @Override
    public Choice pick()
    {
        Choice choice = null;

        if (this.history.size() > 0)
        {
            var num = prng.nextInt(this.history.size());
            var randomEntry = this.history.get(num);
            choice = beatenBy.get(randomEntry.opponentsChoice());
        }
        else
        {
            var num = prng.nextInt(3) + 1;
            choice = num2choice.get(num); 
        }

        return choice;
    }
}
