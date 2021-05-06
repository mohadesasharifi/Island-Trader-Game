package dice_game;

public class Game {
    Player player1;
    Player player2;
    Dice dice;
    
    /**
     *  Game object constructor
     */
    public Game(String name1, String name2) {
        // instantiate two players
        player1 = new Player(1, name1);
        player2 = new Player(2, name2);
        
        // instantiate dice object
        dice = new Dice();
    }
    
    /**
     *  Display winner based on score value
     */
    private Player winner() {
        if(player1.score > player2.score) {
            return player1;
        } else if(player2.score > player1.score) {
            return player2;
        } else {
            return null;
        }
    }
    
    /**
     *  Play the game with following sequential steps:
     *  1. Roll dice
     *  2. Update score of current player based on dice value from above step.
     *  3. Switch current player.
     *  4. Repeat steps from 1 until playCounter is zero (repeat that many times).
     *  5. Outside above loop, check which player has higher score. Display winner, or tie.
     */
    public String play() {
        Player currentPlayer = player1; // start with this player
        int currentDice;
        int playCounter = 6;  // play till this count
        
        while(playCounter-- > 0) {
            // play dice
            currentDice = dice.roll();
            
            // update score of current player
            currentPlayer.updateScore(currentDice);
            
            // switch players
            if(currentPlayer == player1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
        }
        
        // display winner, or game tie
        if(winner() == player1) {
            return player1.getName();
        } else {
            return player2.getName();
        }
    }
}

