package dice_game;


/**
 * this class creates 2 players and returns their scoors, name
 * @author Zahid Khan
 *
 */
public class Player {
    int id;
    String name;
    int score;
    /**
     * this is constructor for player and it creates a player and assign it an id
     * @param id
     * @param name
     */
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.score = 0;
    }
    
    /**
     *  Update player score
     */
    public String updateScore(int currentDice) {
        score += currentDice;
        //System.out.format("\n%s \tscore = %d\n", name, score);
        return String.format("\n%s \tscore = %d\n", name, score);
    }
    
    /**
     * return score of a player
     * @return
     */
    public int getScore() {
    	return score;
    }
    
    /**
     * returns string representation of players score
     */
    @Override
    public String toString() {
        return name + " [" + score + "]";
    }
    
    /**
     * returns players' name
     * @return
     */
    public String getName() {
    	return name;
    }
}
