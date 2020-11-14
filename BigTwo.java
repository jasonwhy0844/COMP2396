import java.util.ArrayList;

/**
 * This class is used for representing a Big Two card game.
 * 
 * @author Wong Ho Yuen Jason
 *
 */
public class BigTwo {

	/**
	 * A constructor for creating a Big Two card game.
	 * Create 4 players and add them to the player list. 
	 * Create a BigTwoConsole object for providing the user interface.
	 */
	public BigTwo() {
		for (int i = 0; i < 4; i++) {
			playerList.add(new CardGamePlayer());
		}
		bigTwoConsole = new BigTwoConsole(this);
	}
	
	private Deck deck;
	private ArrayList<CardGamePlayer> playerList = new ArrayList<CardGamePlayer>();
	private ArrayList<Hand> handsOnTable = new ArrayList<Hand>();
	private int currentIdx = -1;
	private BigTwoConsole bigTwoConsole;
	
	/**
	 * A method for retrieving the deck of cards being used.
	 *
	 * @return the deck of cards being used.
	 */	
	public Deck getDeck() {
		return deck;
	}
	
	/**
	 * Retrieves the list of players
	 * 
	 * @return the list of players
	 */
	public ArrayList<CardGamePlayer> getPlayerList(){
		return playerList;
	}
	
	/**
	 * Retrieves the list of hands played on the table
	 * 
	 * @return the list of hands played on the table
	 */
	public ArrayList<Hand> getHandsOnTable(){
		return handsOnTable;
	}
	
	/**
	 * Retrieves the index of the current player
	 * 
	 * @return the index of the current player
	 */
	public int getCurrentIdx() {
		return currentIdx;
	}
	
	/**
	 * Starts the game with a (shuffled) deck of cards and implements the Big Two game logics
	 * 
	 * @param deck the deck of card being used
	 */
	public void start(BigTwoDeck deck) {
		//generate 4 decks for 4 players
		for (int i = 0; i < 4; i++) {
			for (int j=0; j<13; j++) { 
				playerList.get(i).addCard(deck.getCard(0));
				deck.removeCard(0);
			}
		 }
		
		//sort hands
		playerList.get(0).sortCardsInHand();
		playerList.get(1).sortCardsInHand();
		playerList.get(2).sortCardsInHand();
		playerList.get(3).sortCardsInHand();
		
		//find player with diamond 3
		for (int i = 0; i < 4; i++) {
			BigTwoCard diamondThree = new BigTwoCard(0,2);
			if (playerList.get(i).getCardsInHand().contains(diamondThree)) {
				currentIdx = i;
				break;
			}
		}
		
		bigTwoConsole.setActivePlayer(currentIdx);
		bigTwoConsole.repaint();
		
		CardList selectedCards = new CardList();
		boolean legal = false;
		Hand currentHand = null;
		
		while(!legal) { //input until legal for first turn
			int[] selected = bigTwoConsole.getSelected();
			selectedCards.removeAllCards();

			if (selected != null) selectedCards = playerList.get(currentIdx).play(selected);
			Card diamondThree = new Card(0, 2);
			currentHand = composeHand(playerList.get(currentIdx), selectedCards);
			
			if (currentHand != null && selectedCards.contains(diamondThree)) {
				legal = true;
			};
			
			if (!legal) {
				System.out.println("Not a legal move!!!");
			};
			
			if (legal) {
				playerList.get(currentIdx).removeCards(selectedCards); 
			}
			
			if (currentHand != null && legal)
				System.out.println("{" + currentHand.getType() + "} " + currentHand.toString());
			else if (currentHand == null && legal)
				System.out.println("{Pass}");
			
		}; // end first turn
		
		handsOnTable.add(currentHand); // assign selected cards to previous cards for later beats comparison
	
		boolean endGame = false;
		int passCounter = 0;
		
		while (!endGame) {
			legal = false; //assume !legal hand
			
			currentIdx = (currentIdx + 1) % 4; // go to next player
			bigTwoConsole.setActivePlayer(currentIdx); 
			System.out.println();
			bigTwoConsole.repaint();

			while (!legal) {
				int[] selected = null;
				selected = bigTwoConsole.getSelected();
				selectedCards.removeAllCards();
				if (selected == null && passCounter == 3) {
					System.out.println("Not a legal move!!!");
					continue;
				}

				if (selected != null) selectedCards = playerList.get(currentIdx).play(selected);
				Hand selectedHand = null; 
				selectedHand = composeHand(playerList.get(currentIdx), selectedCards);
				int lastHandOnTable = handsOnTable.size() - 1;
				
				if (selectedHand != null) {
					if (passCounter == 3 || selectedHand.beats(handsOnTable.get(lastHandOnTable))) {
						legal = true;
						passCounter = 0;
					}
				}
				
				if (selectedCards.isEmpty()) { //pass
					if (passCounter != 3) {
						legal = true;
						passCounter++;
					}

				} 
				//remove played card
				if (legal) {
					playerList.get(currentIdx).removeCards(selectedCards); 
					if (selectedHand != null)
						handsOnTable.add(selectedHand); 

					}
				if (selectedHand != null && legal)
					System.out.println("{" + selectedHand.getType() + "} " + selectedHand.toString());
				else if (selectedHand == null && legal)
					System.out.println("{Pass}");
							
				if (!legal)
					System.out.println("Not a legal move!!!");		
				
			};	//end while(!legal)
				
			if (playerList.get(currentIdx).getNumOfCards() == 0) { // end game if activePlayer finished all cards
				endGame = true;				
			};				
		} // end while(!endGame)
		System.out.println();
		System.out.println("Game ends");
		for (int i = 0; i < 4; i++) {
			if (playerList.get(i).getNumOfCards() == 0)
				System.out.println(playerList.get(i).getName() + " wins the game.");
			else 
				System.out.println(playerList.get(i).getName() + " has " + playerList.get(i).getNumOfCards() + " cards in hand.");
		};
	}
	
	/**
	 * Creates and shuffles a deck of cards and start the game with the deck of cards
	 *
	 * @param args default Java main arguments input
	 * 			
	 */
	public static void main(String[] args) {
		BigTwo game = new BigTwo();
		BigTwoDeck gameDeck = new BigTwoDeck();
		gameDeck.shuffle();
		game.start(gameDeck);
	}
	
	/**
	 * Returns a valid hand from the specified list of cards of the player
	 * 
	 * @param player the current player object
	 * @param cards the list of cards the player intended to play
	 * 
	 * @return a valid hand from the specified list of cards of the player, null indicates there is no valid hand
	 */
	public static Hand composeHand(CardGamePlayer player, CardList cards) {
		if (cards != null) {
			Single s = new Single(player, cards);
			if (s.isValid()) return s;
			
			Pair p = new Pair(player, cards);
			if (p.isValid()) return p;
			
			Triple t = new Triple(player, cards);
			if (t.isValid()) return t;
			
			StraightFlush sf = new StraightFlush(player, cards);
			if (sf.isValid()) return sf;
			
			Straight st = new Straight(player, cards);
			if (st.isValid()) return st;
			
			Flush f = new Flush(player, cards);
			if (f.isValid()) return f;
			
			FullHouse fh = new FullHouse(player, cards);
			if (fh.isValid()) return fh;
			
			Quad q = new Quad(player, cards);
			if (q.isValid()) return q;
		}
		return null;
	}
}
