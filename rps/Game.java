package rps;

import java.util.ArrayList;

public class Game
{
    private final int rounds;

    public Game(int rounds)
    {
        this.rounds = rounds;
    }

    public static void main(String[] args)
    {
        var game = new Game(10);
        game.Play();
    }

    public void Play()
    {
        var player1 = new HumanPlayer("Human");
        var player2 = new HistoricalRandomPlayer("Historical Henry");
        System.out.println(String.format("%s vs %s", player1.getName(), player2.getName()));
        var results = new ArrayList<Winner>();

        for(int i = 0; i < this.rounds; i++)
        {
            var winner = PlayRound(player1, player2);
            results.add(winner);
        }

        var player1WinPercentage = CalculateWinPercentage(results, Winner.PLAYER1);
        var player2WinPercentage = CalculateWinPercentage(results, Winner.PLAYER2);
        
        System.out.println(String.format("%s won %.2f%% of games", player1.getName(), player1WinPercentage));
        System.out.println(String.format("%s won %.2f%% of games", player2.getName(), player2WinPercentage));
    }

    private Winner PlayRound(Player player1, Player player2)
    {
        var player1Choice = player1.pick();
        var player2Choice = player2.pick();
        System.out.println(String.format("%s vs %s", player1Choice, player2Choice));
        var winner = DetermineWinner(player1Choice, player2Choice);
        
        if (winner == Winner.NONE)
        {
            System.out.println("Draw!");
            player1.recordHistory(new GameRound(player1Choice, player2Choice, Outcome.DRAW));
            player2.recordHistory(new GameRound(player2Choice, player1Choice, Outcome.DRAW));
        }
        else if (winner == Winner.PLAYER1)
        {
            System.out.println(String.format("%s wins!", player1.getName()));
            player1.recordHistory(new GameRound(player1Choice, player2Choice, Outcome.WIN));
            player2.recordHistory(new GameRound(player2Choice, player1Choice, Outcome.LOSE));
        }
        else if (winner == Winner.PLAYER2)
        {
            System.out.println(String.format("%s wins!", player2.getName()));
            player1.recordHistory(new GameRound(player1Choice, player2Choice, Outcome.LOSE));
            player2.recordHistory(new GameRound(player2Choice, player1Choice, Outcome.WIN));
        }

        return winner;
    }

    private Winner DetermineWinner(Choice player1Choice, Choice player2Choice)
    {
        Winner winner = null;

        if ((player1Choice == Choice.ROCK && player2Choice == Choice.PAPER) ||
            (player1Choice == Choice.PAPER && player2Choice == Choice.SCISSORS) ||
            (player1Choice == Choice.SCISSORS && player2Choice == Choice.ROCK))
        {
            winner = Winner.PLAYER2;
        }
        else if (player1Choice == player2Choice)
        {
            winner = Winner.NONE;
        }    
        else
        {
            winner = Winner.PLAYER1;
        }

        return winner;
    }

    private double CalculateWinPercentage(ArrayList<Winner> results, Winner player)
    {
        var percentage = results.stream().filter(w -> w == player).count() / (double)results.size();

        return percentage * 100;
    }
}
