/**
 * This is a subclass of Hand class which represents Flush cards, and are used to model a hand of flush
 * 
 * @author Wong Ho Yuen Jason
 *
 */
public class Flush extends Hand {

	/**
	 * Checks if this is a valid Flush
	 * 
	 * @return a boolean value, true means it is a valid Flush
	 */
	@Override
	public boolean isValid() {
		Card c0 = getCard(0);
		Card c1 = getCard(1);
		Card c2 = getCard(2);
		Card c3 = getCard(3);
		Card c4 = getCard(4);
		
		if (c0 != null) {
			int c0Suit = c0.getSuit();
			if(size() == 5 && c0Suit == c1.getSuit() && c0Suit == c2.getSuit() && c0Suit == c3.getSuit() && c0Suit == c4.getSuit()) return true;
		}
		return false;
	}

	/**
	 * Returns a string value specifying Flush
	 * 
	 * @return a string value of "Flush"
	 */
	@Override
	public String getType() {
		return "Flush";
	}
	
	/**
	 * Builds Flush with the specified player and list of cards
	 * 
	 * @param player the specified player
	 * @param cards the cards the specified player wants to play
	 */
	public Flush(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}

}
