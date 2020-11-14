/**
 * This is a subclass to of Hand class which represents single cards, and is used to model a hand of Single
 * 
 * @author Wong Ho Yuen Jason
 *
 */
public class Single extends Hand {

	/**
	 * Checks if this is a valid Single
	 * 
	 * @return a boolean value, true means it is a valid Single
	 */
	@Override
	public boolean isValid() {
		if (getCard(0) != null) {
			if(size() == 1) return true;
		}
		return false;
	}

	/**
	 * Returns a string value specifying Single
	 * 
	 * @return a string value "Single"
	 */
	@Override
	public String getType() {
		return "Single";
	}

	/**
	 * Builds Single with the specified player and list of cards
	 * 
	 * @param player the specified player
	 * @param cards the cards the specified player wants to play
	 */
	public Single(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
}
