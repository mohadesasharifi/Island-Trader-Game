package elements;

public class RescueSailors extends Event{

	public RescueSailors() {
		super("Rescue Sailors", 0);
	}

	@Override
	public void attack(Trader trader) {
		// TODO Auto-generated method stub
	}
	
	public void rescued(Trader trader) {
		int sailors = (int)Math.floor(Math.random()*(30-10+1)+10);
		System.out.println(String.format("you've been rewared with %-4d for rescuing %s sailors", sailors * 2, sailors));
		trader.addCoins(sailors * 2);
	}
}
