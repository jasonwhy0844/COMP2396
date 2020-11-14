/**
 * This is a subclass to of Hand class which represents Straight cards, and is used to model a hand of Straight
 * 
 * @author Wong Ho Yuen Jason
 *
 */
public class Straight extends Hand {

	/**
	 * Checks if this is a valid Straight
	 * 
	 * @return a boolean value, true means it is a valid Straight
	 */
	@Override
	public boolean isValid() {
		Card c0 = getCard(0);
		Card c1 = getCard(1);
		Card c2 = getCard(2);
		Card c3 = getCard(3);
		Card c4 = getCard(4);
		sort();
		
		if (c0 != null) {
			int smallestRank = c0.getRank();
			if (size() == 5) {
				//prevent straight flush
				if (c0.getSuit() == c1.getSuit() && c1.getSuit() == c2.getSuit() && c2.getSuit() == c3.getSuit() && c3.getSuit() == c4.getSuit()) return false;
				if (c1.getRank() == smallestRank + 1 && 
					c2.getRank() == smallestRank + 2 && 
					c3.getRank() == smallestRank + 3 &&
					c4.getRank() == smallestRank + 4)
					return true;
				//10JQKA
				if (c0.getRank() == 9 && //10
					c1.getRank() == 10 && //J
					c2.getRank() == 11 && //Q
					c3.getRank() == 12 && //K
					c4.getRank() == 0) //A
					return true;
			}
		}
		return false;
	}

	/**
	 * Returns a string value specifying Straight
	 * 
	 * @return a string value "Straight"
	 */
	@Override
	public String getType() {
		return "Straight";
	}
	
	/**
	 * Builds Straight with the specified player and list of cards
	 * 
	 * @param player the specified player
	 * @param cards the cards the specified player wants to play
	 */
	public Straight(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}

}
