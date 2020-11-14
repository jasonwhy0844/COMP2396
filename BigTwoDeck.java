/**
 * BigTwoDeck is the subclass of class Deck, modelling a deck of cards used in a Big Two card game.
 * 
 * @author Wong Ho Yuen Jason
 *
 */
public class BigTwoDeck extends Deck {

	/**
	 * Initializes a deck of Big Two cards.
	 * It removes all cards from the deck, create 52 Big Two cards and add them to the deck.
	 */
	@Override
	public void initialize() {
		removeAllCards();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				Card card = new Card(i, j);
				addCard(card);
			}
		}
	}
}
