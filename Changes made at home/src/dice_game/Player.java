package dice_game;

public class Player {
    int id;
    String name;
    int score;
    
    Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.score = 0;
    }
    
    /**
     *  Update player score
     */
    public void updateScore(int currentDice) {
        score += currentDice;
        System.out.format("\n%s \tscore = %d\n", name, score);
    }
    
    public int getScore() {
    	return score;
    }
    
    @Override
    public String toString() {
        return name + " [" + score + "]";
    }
    
    public String getName() {
    	return name;
    }
}
