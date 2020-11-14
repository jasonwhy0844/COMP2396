/**
 * This class is a subclass of the Card class, and is used to model a card used in a Big Two card game.
 * 
 * @author Wong Ho Yuen Jason
 *
 */

public class BigTwoCard extends Card {

	/**
	 * Builds a card with the specified suit and rank
	 * 
	 * @param suit suit of the card, an integer between 0 and 3
	 * @param rank rank of the card, an integer between 0 and 12
	 */
	public BigTwoCard(int suit, int rank) {
		super(suit, rank);
	}
	
	/**
	 * Compares this card with the specified card for order
	 * 
	 * @param card the card for comparison
	 * 
	 * @return a negative integer, zero, or a positive integer as this card is less than, equal to, or greater than the specified card
	 * 
	 */
	@Override
	public int compareTo(Card card) {
		//both card rank 3 to K
		if(this.rank >= 2 && this.rank <= 12 && card.rank >= 2 && card.rank <= 12) {
			if (this.rank > card.rank) {
				return 1;
			} else if (this.rank < card.rank) {
				return -1;
			} else {//same rank then compare suit																
				if (this.suit > card.suit) {
					return 1;
				} else if (this.suit < card.suit) {
					return -1;
				}
			}
		}
		
		//both card A to 2
		if(this.rank >= 0 && this.rank <= 1 && card.rank >= 0 && card.rank <= 1) {
			if (this.rank > card.rank) {
				return 1;
			} else if (this.rank < card.rank) {
				return -1;
			} else {//same rank then compare suit																	
				if (this.suit > card.suit) {
					return 1;
				} else if (this.suit < card.suit) {
					return -1;
				} 
			}
		}
		
		//this card is A to 2, specified card is 3 to K
		if((this.rank == 0 || this.rank == 1) && card.rank >= 2 && card.rank <= 12) {
			return 1;
		}
		//this card is 3 to K, specified card is A to 2
		if(this.rank >= 2 && this.rank <= 12 && (card.rank == 0 || card.rank == 1)) {
			return -1;
		}
		return 0;
	}
}