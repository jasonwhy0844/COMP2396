/**
 * This is a subclass to of Hand class which represents StraightFlush cards,
 * and is used to model a hand of StraightFlush
 * 
 * @author Wong Ho Yuen Jason
 *
 */
public class StraightFlush extends Hand {

	/**
	 * Checks if this is a valid StraightFlush
	 * 
	 * @return a boolean value, true means it is a valid StraightFlush
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
			int c0Suit = c0.getSuit();
			int c0Rank = c0.getRank();
			
			if (size() == 5) {
				if (c1.getSuit() == c0Suit && 
					c2.getSuit() == c0Suit &&
					c3.getSuit() == c0Suit &&
					c4.getSuit() == c0Suit) {
					if(c1.getRank() == c0Rank + 1 &&
					   c2.getRank() == c0Rank + 2 &&
					   c3.getRank() == c0Rank + 3 &&
					   c4.getRank() == c0Rank + 4)
						return true;
					//10JQKA
					if (c0Rank == 9 && //10
						c1.getRank() == 10 &&//J
						c2.getRank() == 11 &&//Q
						c3.getRank() == 12 &&//K
						c4.getRank() == 0)//A
						return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns a string value specifying StraightFlush
	 * 
	 * @return a string value "StraightFlush"
	 */
	@Override
	public String getType() {
		return "StraightFlush";
	}
	
	/**
	 * Builds StraightFlush with the specified player and list of cards
	 * 
	 * @param player the specified player
	 * @param cards the cards the specified player wants to play
	 */
	public StraightFlush(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
}
