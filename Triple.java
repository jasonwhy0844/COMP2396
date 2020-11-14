/**
 * This is a subclass to of Hand class which represents Triple cards,
 * and is used to model a hand of Triple
 * 
 * @author Wong Ho Yuen Jason
 * @version 4.10.0
 *
 */
public class Triple extends Hand {

	/**
	 * Checks if this is a valid Triple
	 * @return a boolean value, true means it is a valid Triple
	 */
	@Override
	public boolean isValid() {
		Card c0 = getCard(0);
		Card c1 = getCard(1);
		Card c2 = getCard(2);
		
		if (c0 != null) {
			if (size() == 3) {
				if (c0.getRank() == c1.getRank() && c1.getRank() == c2.getRank())
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns a string value specifying Triple
	 * 
	 * @return a string value "Triple"
	 */
	@Override
	public String getType() {
		return "Triple";
	}
	
	/**
	 * Builds Triple with the specified player and list of cards
	 * 
	 * @param player the specified player
	 * @param cards the cards the specified player wants to play
	 */
	public Triple(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}

}
