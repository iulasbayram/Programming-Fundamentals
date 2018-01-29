/**
 * @author Hasan Yeniada - Ismail Ulas Bayram , 220201024 - 220201040
 */
package domain;

import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player{
	
	public ComputerPlayer(){
		super("unknown_user");
	}

	public ComputerPlayer(String name) {
		super(name);
	}
	
	public void setName(){
		Random random = new Random();
		int number = random.nextInt(100) +1;
		String randomName = "computerPlayer#" + number;
		super.setName(randomName);
	}
	
	
	//This method returns optimal card according to humanCard. If computer starts new hand, this method returns card randomly.
	public Card getNextCard(Card humanCard){
		Random random = new Random();
		if (humanCard==null){
			return getCardList().get(random.nextInt(getCardList().size()));
		}else{
			ArrayList<Card> compCardList = new ArrayList<Card>();
			for (Card card: getCardList()) {
				if (card.getNumber()>humanCard.getNumber()) compCardList.add(card);
			}
			if(compCardList.isEmpty()){
				return getCardList().get(random.nextInt(getCardList().size()));
			}else{
				Card optimalCard = compCardList.get(random.nextInt(compCardList.size())); //Computer should put optimal card.
				for (Card card : compCardList) {
					if(optimalCard.getNumber() > card.getNumber())
						optimalCard=card;
				}
				return optimalCard;
			}
		}
		
		
	}
}
