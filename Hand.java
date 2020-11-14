/**
 * This a subclass of the CardList class,
 * and is used to model a hand of cards.
 * 
 * @author Wong Ho Yuen Jason
 *
 */
public abstract class Hand extends CardList {

	/**
	 * Builds FullHouse with the specified player and list of cards
	 * 
	 * @param player the specified player
	 * @param cards the cards the specified player wants to play
	 */
	public Hand(CardGamePlayer player, CardList cards) {
		this.player = player;
		for (int i = 0; i < cards.size(); i++) {
			addCard(cards.getCard(i));
		}
	}
	
	private CardGamePlayer player;
	
	/**
	 * Retrieves the player of this hand
	 * 
	 * @return a CardGamePlayer object, the player of this hand
	 */
	public CardGamePlayer getPlayer() {
		return player;
	}
	
	/**
	 * Retrieves the top card of this hand
	 * 
	 * @return a Card object, the top card of this hand
	 */
	public Card getTopCard() {
		sort();
		return getCard(size() - 1);
	}
	
	/**
	 * Retrieves the third card of this hand
	 * 
	 * @return a Card object, the third card of this hand
	 */
	public Card getMidCard() {
		sort();
		return getCard(size() - 3);
	}
	
	/**
	 * Checks if this hand beats a specified hand
	 * 
	 * @param hand the hand pending for checking
	 * 
	 * @return a boolean value of this hand whether it can beat the specified hand, true indicates can beat
	 */
	public boolean beats(Hand hand) {
		String currentType = this.getType();
		if(hand.isEmpty()) return true;
		String typeOnBoard = hand.getType();
		Card outsideTopCard = hand.getTopCard();
		Card outsideMidCard = hand.getMidCard();
		BigTwoCard playedCard = new BigTwoCard(this.getTopCard().suit, this.getTopCard().rank);
		BigTwoCard playedMidCard = new BigTwoCard(0, 0);
		if (size() == 5) {
			playedMidCard = new BigTwoCard(this.getMidCard().suit, this.getMidCard().rank);
		}
		
		switch (currentType) {
			case "Single":
				if(hand.size() != 1 || typeOnBoard != "Single") return false;
				if(playedCard.compareTo(outsideTopCard) > 0) return true;
				break;
				
			case "Pair":
				if(hand.size() != 2 || typeOnBoard != "Pair") return false;
				if(playedCard.compareTo(outsideTopCard) > 0) return true;
				break;
				
			case "Triple":
				if(hand.size() != 3 || typeOnBoard != "Triple") return false;
				if(playedCard.compareTo(outsideTopCard) > 0) return true;
				break;
			
			case "Straight":
				if(hand.size() != 5 || typeOnBoard != "Straight") return false; //straight is smallest in combination of 5
				if(playedCard.compareTo(outsideTopCard) > 0) return true;
				break;
				
			case "Flush":
				if(hand.size() != 5 || typeOnBoard == "FullHouse" || typeOnBoard == "Quad" || typeOnBoard == "StraightFlush") return false;
				if(typeOnBoard == "Straight") return true;
				if(playedCard.compareTo(outsideTopCard) > 0) return true;
				break;
				
			case "FullHouse":
				if(hand.size() != 5 || typeOnBoard == "Quad" || typeOnBoard == "StraightFlush") return false;
				if(typeOnBoard == "Straight" || typeOnBoard == "Flush") return true;
				if(playedMidCard.compareTo(outsideMidCard) > 0) return true; //to be corrected to compare the triple
				break;
				
			case "Quad":
				if(hand.size() != 5 || typeOnBoard == "StraightFlush") return false;
				if(typeOnBoard == "Straight" || typeOnBoard == "Flush" || typeOnBoard == "FullHouse") return true;
				if(playedMidCard.compareTo(outsideMidCard) > 0) return true; //to be corrected to compare the 3rd card
				break;
				
			case "StraightFlush":
				if(hand.size() != 5) return false;
				if(typeOnBoard != "Straight") return true; //straight flush is biggest in combination of 5
				if(playedCard.compareTo(outsideTopCard) > 0) return true;
				break;
		}
		return false;
	}
	
	/**
	 * Checks if this is a valid hand, to be overridden by subclasses
	 * 
	 * @return a boolean value, true means the hand is valid
	 */
	public abstract boolean isValid();
	
	/**
	 * Returns a string value specifying the type of this hand, to be overridden by subclass
	 * @return a string value specifying the type of this hand
	 */
	public abstract String getType();
}
