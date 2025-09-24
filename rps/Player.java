package rps;

import java.util.ArrayList;

public abstract class Player
{
    private final String name;
    protected final ArrayList<GameRound> history;
    
    public Player(final String name)
    {
        this.name = name;
        this.history = new ArrayList<>();
    }

    public String getName() { return this.name; }

    public abstract Choice pick();

    public void recordHistory(final GameRound round)
    {
        this.history.add(round);
    }
}
