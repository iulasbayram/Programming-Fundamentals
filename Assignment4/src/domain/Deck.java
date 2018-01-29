/**
 * @author Hasan Yeniada - Ismail Ulas Bayram , 220201024 - 220201040
 */
package domain;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> cardList = new ArrayList<Card>();

	//This method creates a deck and specifies qualifications of cards.
	public void init(){
		
		for (int heartNumber = 1; heartNumber < 14; heartNumber++) {
			Card card = new Card(Type.HEARTS,heartNumber,heartNumber*7);
			cardList.add(card);
		}
		
		for (int spadeNumber = 1; spadeNumber < 14; spadeNumber++) {
			Card card = new Card(Type.SPADES,spadeNumber,spadeNumber*5);
			cardList.add(card);
		}
		
		for (int clubNumber = 1; clubNumber < 14; clubNumber++) {
			Card card = new Card(Type.CLUBS,clubNumber,clubNumber*11);
			cardList.add(card);
		}
		
		for (int diamondNumber = 1; diamondNumber < 14; diamondNumber++) {
			Card card = new Card(Type.DİAMONDS,diamondNumber,diamondNumber*9);
			cardList.add(card);
		}
	}
	
	//This method suffles and distributes cards.
		public void randomShuffledCards (Player human, Player computer){
			Collections.shuffle(cardList);
					
			for (int i = 0; i < cardList.size(); i+=2) {
				human.addCard(cardList.get(i));
				computer.addCard(cardList.get(i+1));
			}

		}
}
