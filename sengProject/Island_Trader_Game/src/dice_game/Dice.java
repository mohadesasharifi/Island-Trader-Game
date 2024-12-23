package dice_game;

/**
 * this model rolls the dice by randomly selecting a number between 1 and 6
 * @author Zahid Khan
 *
 */

public class Dice {
    /**
     *  Roll the dice and return random number between 1 and 6 (both inclusive)
     */
    public int roll() {
        return 1 + (int)(Math.random() * 6);
    }
}

