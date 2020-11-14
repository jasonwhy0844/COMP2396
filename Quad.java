/**
 * This is a subclass of Hand class which represents Quad cards,
 * and is used to model a hand of Quad
 * 
 * @author Wong Ho Yuen Jason
 * 
 */
public class Quad extends Hand {

	/**
	 * Checks if this is a valid Quad
	 * 
	 * @return a boolean value, true means it is a valid Quad
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
				//c0 == c1 == c2 == c3
				if (c0.getRank() == c1.getRank() && c1.getRank() == c2.getRank() && c2.getRank() == c3.getRank()) return true;
				//c1 == c2 == c3 == c4
				if (c1.getRank() == c2.getRank() && c2.getRank() == c3.getRank() && c3.getRank() == c4.getRank()) return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns a string value specifying Quad
	 * 
	 * @return a string value of "Quad"
	 */
	@Override
	public String getType() {
		return "Quad";
	}
	
	/**
	 * Builds Quad with the specified player and list of cards
	 * 
	 * @param player the specified player
	 * @param cards the cards the specified player wants to play
	 */
	public Quad(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
}
