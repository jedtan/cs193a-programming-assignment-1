package com.example.jedtan.rockpaperscissors;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;
import android.util.Log;
import java.util.Random;

// Jed Tan <jedtan@stanford.edu>
// Roshambo - This game allows you to play Rock-Paper-Scissors!
// Pretty straight forward, press your choice of the three buttons
// on the bottom to pick your move! You and the computer's move will
// be displayed on the middle image views, along with an indicator
// of how you did. The numbers on the top give score counts!

public class Play extends AppCompatActivity {
    private int playerScore = 0;
    private int computerScore = 0;

    public enum Moves {
        ROCK, PAPER, SCISSORS
    }

    public static int randInt(int min, int max) {

        Random randomGenerator = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomInt = randomGenerator.nextInt(3);

        return randomInt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    public int findWinner(Moves playerMove, Moves opponentMove){
        if (playerMove == opponentMove) return 2;
        else if (playerMove == Moves.ROCK && opponentMove == Moves.PAPER){
            return 1;
        }
        else if (playerMove == Moves.ROCK && opponentMove == Moves.SCISSORS){
            return 0;
        }
        else if (playerMove == Moves.PAPER && opponentMove == Moves.ROCK){
            return 0;
        }
        else if (playerMove == Moves.PAPER && opponentMove == Moves.SCISSORS){
            return 1;
        }
        else if (playerMove == Moves.SCISSORS && opponentMove == Moves.ROCK){
            return 1;
        }
        else if (playerMove == Moves.SCISSORS && opponentMove == Moves.PAPER){
            return 0;
        }
        return 2;
    }

    public void updateViews(int winner, Moves playerMove, Moves opponentMove){
        ImageView userView = (ImageView) findViewById(R.id.userSelection);
        ImageView compView = (ImageView) findViewById(R.id.computerSelection);
        TextView userScore = (TextView) findViewById(R.id.userScore);
        TextView compScore = (TextView) findViewById(R.id.compScore);
        ImageView winLoss = (ImageView) findViewById(R.id.winLoss);
        if (winner == 0){
            playerScore++;
            userScore.setText(Integer.toString(playerScore));
            winLoss.setImageResource(R.drawable.win);
        }
        else if(winner == 1){
            computerScore++;
            compScore.setText(Integer.toString(computerScore));
            winLoss.setImageResource(R.drawable.lose);
        }
        else{
            winLoss.setImageResource(R.drawable.draw);
        }
        if (playerMove == Moves.ROCK){
            userView.setImageResource(R.drawable.rock);
        }
        else if (playerMove == Moves.PAPER){
            userView.setImageResource(R.drawable.paper);
        }
        else if (playerMove == Moves.SCISSORS){
            userView.setImageResource(R.drawable.scissors);
        }
        if (opponentMove == Moves.ROCK){
            compView.setImageResource(R.drawable.rock);
        }
        else if (opponentMove == Moves.PAPER){
            compView.setImageResource(R.drawable.paper);
        }
        else if (opponentMove == Moves.SCISSORS){
            compView.setImageResource(R.drawable.scissors);
        }


    }


    public void playMove(View view){
        Moves playerMove = Moves.ROCK;
        if (view.getId() == R.id.rock) {
            playerMove = Moves.ROCK;
        }
        else if(view.getId() == R.id.paper){
            playerMove = Moves.PAPER;
        }
        else if (view.getId() == R.id.scissors) {
            playerMove = Moves.SCISSORS;
        }
        Log.d("PlayerMove", playerMove.toString());
        Moves opponentMove = Moves.values()[randInt(0, 2)];
        int winner = findWinner(playerMove, opponentMove);

        Log.d("OpponentMove", opponentMove.toString());
        Log.d("Winner", Integer.toString(winner));

        updateViews(winner, playerMove, opponentMove);
    }

}
