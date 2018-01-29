/**
 * @author Hasan Yeniada - Ismail Ulas Bayram , 220201024 - 220201040
 */
package domain;

import java.util.ArrayList;

public class Player {
	private String name;
	private int score;
	private ArrayList<Card> cardList = new ArrayList<Card>();
	
	public Player(String name){
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setName(){
		this.name = "";
	}

	public int getScore() {
		return score;
	}
	
	public ArrayList<Card> getCardList() {
		return cardList;
	}

	public void setCardList(ArrayList<Card> cardList) {
		this.cardList = cardList;
	}

	public void setScore(int newScore) {
		score = score + newScore;
	}
	
	public void addCard(Card card){
		cardList.add(card);
	}
	
	public void removeCard(Card card){
		if(!cardList.isEmpty() && cardList.contains(card))
			cardList.remove(card);
	}
	
	public boolean isEmpty(){
		return cardList.isEmpty();
	}
	
	public Card getNextCard(Card card){
		return card;
	}
	
	public Card getNextCard(int index){
		return null;
	}
	
	
	
	
}
