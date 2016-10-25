/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

/**
 *
 * @author Frederik and Emil 
 */
public class GameBoard implements IGameModel {

    private int playersTurn = 1; //Current player
    private int[][] gameArea = new int[3][3]; //Creates a virtual grid for our game
    private boolean gameDraw = false; //Out cool mechanic to check if the game is a draw
    
    public GameBoard() 
    {
        newGame();
    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    @Override
    public int getNextPlayer() 
    {
        return playersTurn;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver ==
     * true this method will always return false.
     */
    @Override
    public boolean play(int col, int row) 
    {
        if (gameArea[col][row] == -1) 
        {
            gameArea[col][row] = playersTurn;
            if (playersTurn == 1) 
            {
                playersTurn = 0;
            } else {
                playersTurn = 1;
            }
        } 
        else 
        {
            return false;
        }
        return true;
    }

    public boolean isDraw() 
    {
        return !threeInARow() && (gameArea[0][0] != -1 && gameArea[0][1] != -1 && gameArea[0][2] != -1
                && gameArea[1][0] != -1 && gameArea[1][1] != -1 && gameArea[1][2] != -1
                && gameArea[2][0] != -1 && gameArea[2][1] != -1 && gameArea[2][2] != -1);
    }

    @Override
    public boolean isGameOver() 
    {
        if(threeInARow() || isDraw())
        {
            return true;
        }
        return false;    
    }
    
    private boolean threeInARow() //Cool check if there is 3 in a row
            {
        //Lodrat check
        if (gameArea[0][0] == gameArea[0][1] && gameArea[0][1] == gameArea[0][2] && gameArea[0][0] != -1 && gameArea[0][1] != -1 && gameArea[0][2] != -1) 
        {
            return true;
        }
        else if (gameArea[1][0] == gameArea[1][1] && gameArea[1][1] == gameArea[1][2] && gameArea[1][0] != -1 && gameArea[1][1] != -1 && gameArea[1][2] != -1) 
        {
            return true;
        }
        else if (gameArea[2][0] == gameArea[2][1] && gameArea[2][1] == gameArea[2][2] && gameArea[2][0] != -1 && gameArea[2][1] != -1 && gameArea[2][2] != -1) 
        {
            return true;
        }
        
        //Vandret check
        else if (gameArea[0][0] == gameArea[1][0] && gameArea[1][0] == gameArea[2][0] && gameArea[0][0] != -1 && gameArea[1][0] != -1 && gameArea[2][0] != -1) 
        {
            return true;
        }
        else if (gameArea[0][1] == gameArea[1][1] && gameArea[1][1] == gameArea[2][1] && gameArea[0][1] != -1 && gameArea[1][1] != -1 && gameArea[2][1] != -1) 
        {
            return true;
        }
        else if (gameArea[0][2] == gameArea[1][2] && gameArea[1][2] == gameArea[2][2] && gameArea[0][2] != -1 && gameArea[1][2] != -1 && gameArea[2][2] != -1) 
        {
            return true;
        }
        
        //Side Check
        else if (gameArea[0][0] == gameArea[1][1] && gameArea[1][1] == gameArea[2][2] && gameArea[0][0] != -1 && gameArea[1][1] != -1 && gameArea[2][2] != -1) 
        {
            return true;
        }
        else if (gameArea[0][2] == gameArea[1][1] && gameArea[1][1] == gameArea[2][0] && gameArea[0][2] != -1 && gameArea[1][1] != -1 && gameArea[2][0] != -1) 
        {
            return true;
        }
        return false;
}

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    @Override
    public int getWinner() 
    {
        if (isDraw()) 
        {
            return -1;
        }
        else if (playersTurn == 0) 
        {
            return 1;
        }
        else if (playersTurn == 1) 
        {
            return 0;
        }
        return -1;
    }

    /**
     * Resets the game to a new game state.
     */
    @Override
    public void newGame() 
    {
        playersTurn = 1;
        for (int i = 0; i < gameArea.length; i++) 
        {
            for (int j = 0; j < gameArea.length; j++) 
            {
                gameArea[i][j] = -1;
            }
        }
    }
}
