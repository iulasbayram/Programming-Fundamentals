/**
 * @author Hasan Yeniada - Ismail Ulas Bayram , 220201024 - 220201040
 */
package domain;

public class Card {
	private Type type;
	private int number;
	private int score;
	
	public Card(Type type, int number, int score){
		setType(type);
		setNumber(number);
		setScore(score);
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return type + ", " + number;
	}
	
	
	
}
