package models;

import java.util.Scanner;

public class RescueSailors extends Event{

	public RescueSailors() {
		super("Rescue Sailors", 0);
	}

	@Override
	public void attack(Trader trader) {
		
		System.out.println("You came accross some stranded sailors who are robbed by Pirates and left behind to drown");
		System.out.println("Would you like to rescue? (Please enter Y/N)");
		Scanner scan = new Scanner(System.in);
		String decision = scan.next();
		while (!decision.equalsIgnoreCase("Y")) {
			if (decision.equalsIgnoreCase("N")) {
				break;
			}else {
				System.out.println("Please enter Y/N");
				decision = scan.next();
			}
		}
		if(decision.equalsIgnoreCase("Y")) {
			int sailors = (int)Math.floor(Math.random()*(30-10+1)+10);
			System.out.println(String.format("you've been rewared with %d coins for rescuing %s sailor/s.", sailors * 2, sailors));
			trader.addCoins(sailors * 2);
		}
	}
}
