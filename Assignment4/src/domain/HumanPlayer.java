/**
 * @author Hasan Yeniada - Ismail Ulas Bayram , 220201024 - 220201040
 */
package domain;

public class HumanPlayer extends Player {
	
	public HumanPlayer(){
		super("unknown_user");
	}

	public HumanPlayer(String name) {
		super(name);
	}

	public void setName(String name){
		super.setName(name);
	}
	
	public Card getNextCard(int index){
		Card card = getCardList().get(index);
		return card;
	}

}
