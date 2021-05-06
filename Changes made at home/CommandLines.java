package Main;

import Validations.InputValidation;
import java.util.*;
import elements.*;


public class CommandLines<reutrn> {
	InputValidation check = new InputValidation();
	private String name;
	private int days;
	private int shipNo = 0;
	private int homeIslandNo = 0;
	private String categoryNumber;
	private ArrayList<Items> selectedCategoryItems;
	private int itemNoToPurchase;
	public CommandLines() {};
	
	public String geTraderName(){
		return name;
	}
	public int getDaystoPlayGame() {
		return days;
	}
	public int getShipNo() {
		return shipNo;
	}

	public int getHomeIslandNo() {
		return homeIslandNo;
	}
	public String chooseName() {
		System.out.println("Please choose a Trader name");
		Scanner scanner = new Scanner(System.in);
		name = scanner.next();
		
		while(!(check.validName(name))) {
			System.out.println("Username must be between 3-12 alphabetical characters:");
			name = scanner.next();
		}    	
		return "Your trader name is " + name;
	}
		
		
	public String daysToPlay() {
		String tempInput;
		System.out.println("Please choose how many days would you like to play the game."
				+ " It should be between 20 to 50 days");
		Scanner scanner = new Scanner(System.in);
		tempInput = scanner.next();
		
		while(!(check.validNumberInRange(tempInput, 20, 50))) {
				System.out.println("Your input should a number be between 20 to 50 days.");
				tempInput = scanner.next();
		}
		days = Integer.parseInt(tempInput);
		return "You have "+ days + " days to sail.";	
	}
	
	
	public Ship chooseShip(ArrayList<Ship> ships){
		String tempShip;
		System.out.println("Please choose a ship from the following");
		for(int number=0; number< ships.size(); number++) {
			System.out.println(number+1 +": " +ships.get(number));	
		}
		System.out.println("Please enter a ship number you'd like to choose:");
		Scanner scanner = new Scanner(System.in);
		tempShip = scanner.next();
		
		while(!(check.validNumberInRange(tempShip, 1, 4))) {
			System.out.println("Please choose from the available options:");
			tempShip = scanner.next();
		}
		shipNo = Integer.parseInt(tempShip) - 1;
		return ships.get(shipNo);
	}
	
	
	public Islands homeIsland(ArrayList<Islands> homeIsland) {
		return homeIsland.get(homeIslandNo);
	}

	
	public void start(ArrayList<Islands> island, ArrayList<Ship> ship) {
		System.out.println("Please enter start to begin the game.");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		if (input.toUpperCase() == "START") {
			System.out.println("Hello " + name + ". Welcome to " + homeIsland(island).getName() + ". Your ship details are:\n" + 
		chooseShip(ship));
		}
	}
	
	public void visitStore(ArrayList<Islands> island, int islandNo) {
		String input = "";
		System.out.println(name + ", You can visit " +  island.get(islandNo).getStore().getName() +
				" store. Enter Yes to see store's items or No to continoue");
		Scanner scanner = new Scanner(System.in);
		input = scanner.next();
		while(check.inputMatch(input, "yes", "no") == false) {
			System.out.println("Please enter Yes or No");
			input = scanner.next();
		}
		
		if (input.equalsIgnoreCase("YES")) {
			getItemsByCategory(island, homeIslandNo);
		}
		else {
			System.out.println("Enjoy your time in your home Isalnd");
		}
		
	}
	
	
	public void purchase(ArrayList<Islands> island, Trader trader) {

		System.out.println("Please choose an item to purchase. Enter a number");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		while(!(check.validNumberInRange(input, 1, selectedCategoryItems.size()))) {
			System.out.println("Please eneter a valid number.");
			input = scanner.next();
		}
		itemNoToPurchase = Integer.parseInt(input);
		System.out.println("Please enter the quantity you want to purchase");
		Scanner scanner2 = new Scanner(System.in);
		int quantity = scanner2.nextInt();
		Items item = selectedCategoryItems.get(itemNoToPurchase);
		homeIsland(island).getStore().makePurchase(item, trader.getCoins() , quantity);
	}
	
	
	public void purchase1(Trader trader) {

		System.out.println("Please choose an item to purchase. Enter a number");
		Scanner scanner = new Scanner(System.in);
		
		String input = scanner.next();
		while(!(check.validNumberInRange(input, 1, selectedCategoryItems.size()))) {
			System.out.println("Please eneter a valid number.");
			input = scanner.next();
		}
		itemNoToPurchase = Integer.parseInt(input);
		System.out.println("Please enter the quantity you want to purchase");
		Scanner scanner2 = new Scanner(System.in);
		int quantity = scanner2.nextInt();
		Items item = selectedCategoryItems.get(itemNoToPurchase -1);
		trader.purchase(item , quantity);
		System.out.println("You purchased " + quantity + item );
		purchase_again(trader);
	}
	
	public void purchase_again(Trader trader) {
		System.out.println("If you want to continue purchase enter Continue. Enter exit to stop");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		while(!(check.inputMatch(input, "continue", "exit"))) {
			System.out.println("Please enter Continue or Exit.");
			input = scanner.next();
		}
		while (input.equalsIgnoreCase("Continue")) {
			purchase1(trader);
			System.out.println("If you want to continue purchase enter Continue. Enter exit to stop");
			Scanner scanner2 = new Scanner(System.in);
			input = scanner.next();
			while(!(check.inputMatch(input, "continue", "exit"))) {
				System.out.println("Please enter Continue or Exit.");
				input = scanner.next();
			}
		}
		if (input.equalsIgnoreCase("Exit")) {
			System.out.println("Thanks for buying from our store");
		}
	}
	
	public void viewIsland(ArrayList<Islands> islands) {
		System.out.println("There are 5 islands you can travel to sell and buy items.\n"
				+ "You should try to make the most profit.\n"
				+ "Choose one of the following islands to see it's store and items.\n ");
		for (int count=0; count < 5; count++) {
			System.out.println(count+1 + ": " + islands.get(count).getName());
		}
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		while(!(check.validNumberInRange(input, 1, 5))) {
			System.out.println("Please enter a number between 1 to 5.");
			input = scanner.next();
		}
		int islandNo = Integer.parseInt(input);
		getItemsByCategory(islands, islandNo);
	}
	
	
	public void viewIsland1(ArrayList<Islands> islands) {
		System.out.println("There are 5 islands you can travel to sell and buy items.\n"
				+ "You should try to make the most profit.\n"
				+ "Choose one of the following islands to see it's store and items. ");
		for (int count=0; count < 5; count++) {
			System.out.println(count+1 + ": " + islands.get(count).getName());
		}
		boolean chooseIsland = true;
		while (chooseIsland) {
			Scanner scanner = new Scanner(System.in);
			String input = scanner.next();
			while(!(check.validNumberInRange(input, 1, 5))) {
				System.out.println("Please enter a number between 1 to 5.");
				input = scanner.next();
			}
			int islandNo = Integer.parseInt(input);
			visitStore(islands, islandNo);
			System.out.println("Enter yes to view more islands or No to continue");
			Scanner scanner2 = new Scanner(System.in);
			String input2 = scanner.next();
			while(check.inputMatch(input, "yes", "no") == false) {
				System.out.println("Please enter Yes or No");
				input2 = scanner.next();
			}
			if (input2.equalsIgnoreCase("no")){
				chooseIsland = false;
			}
		}
		
	}
	
	
	public void getItemsByCategory(ArrayList<Islands> island, int islandNo) {
		
		String[] category = homeIsland(island).getStore().getAllCategories();
		for(int number =0;  number < category.length; number ++)  {
			System.out.println("Please choose a category from bellow to see available items.");
			System.out.println(number+1 +": " + category[number]);
		}
		Scanner scanner = new Scanner(System.in);
		categoryNumber = scanner.next();
		while(!(check.validNumberInRange(categoryNumber, 1, 4))) {
			System.out.println("Please choose from the available options:");
			categoryNumber = scanner.next();
		}
		selectedCategoryItems = homeIsland(island).getStore().getProducts(category[Integer.parseInt(categoryNumber) -1]);
		int count = 1;
		for(Items item: selectedCategoryItems) {
			System.out.println(count + ": " + item);
			count+=1;
		}
	}
	public static void main(String[] args) {
			
	}
}



