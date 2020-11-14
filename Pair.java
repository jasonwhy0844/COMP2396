/**
 * This is a subclass of Hand class which represents Pair cards, and is used to model a hand of Pair
 * 
 * @author Wong Ho Yuen Jason
 *
 */
public class Pair extends Hand {

	/**
	 * Checks if this is a valid Pair
	 * 
	 * @return a boolean value, true means it is a valid Pair
	 */
	@Override
	public boolean isValid() {
		Card c0 = getCard(0);
		Card c1 = getCard(1);
		
		if (c0 != null) {
			if (size() == 2 && c0.getRank() == c1.getRank()) return true;
		}
		return false;
	}

	/**
	 * Returns a string value specifying Pair
	 * 
	 * @return a string value of "Pair"
	 */
	@Override
	public String getType() {
		return "Pair";
	}

	/**
	 * Builds Pair with the specified player and list of cards
	 * @param player the specified player
	 * @param cards the cards the specified player wants to play
	 */
	public Pair(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
}
