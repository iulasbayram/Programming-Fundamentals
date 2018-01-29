import java.util.HashMap;

public class Poem {
	private String title;
	private int numberOfWords, orderOfPoet;
	
	public Poem(String title, int numberOfWords, int orderOfPoet) {
		setTitle(title);
		setNumberOfWords(numberOfWords);
		setOrderOfPoet(orderOfPoet);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumberOfWords() {
		return numberOfWords;
	}

	public void setNumberOfWords(int numberOfWords) {
		this.numberOfWords = numberOfWords;
	}
	
	public int getOrderOfPoet() {
		return orderOfPoet;
	}

	public void setOrderOfPoet(int orderOfPoet) {
		this.orderOfPoet = orderOfPoet;
	}


}
