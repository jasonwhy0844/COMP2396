/**
 * This is a subclass of Hand class which represents FullHouse cards, and is used to model a hand of FullHouse
 * 
 * @author Wong Ho Yuen Jason
 *
 */
public class FullHouse extends Hand {

	/**
	 * Checks if this is a valid FullHouse
	 * 
	 * @return a boolean value, true means it is a valid FullHouse
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
			if (size() == 5) {
				//c0 == c1, c2 == c3 == c4
				if (c0.getRank() == c1.getRank() && c2.getRank() == c3.getRank() && c3.getRank() == c4.getRank())
					return true;
				//c0 == c1 == c2, c3 == c4
				if (c0.getRank() == c1.getRank() && c1.getRank() == c2.getRank() && c3.getRank() == c4.getRank())
					return true;
			}
		}
		return false;
	}

	/**
	 * Returns a string value specifying FullHouse
	 * 
	 * @return a string value of "FullHouse"
	 */
	@Override
	public String getType() {
		return "FullHouse";
	}

	/**
	 * Builds FullHouse with the specified player and list of cards
	 * 
	 * @param player the specified player
	 * @param cards the cards the specified player wants to play
	 */
	public FullHouse(CardGamePlayer player, CardList card) {
		super(player, card);
	}
}
