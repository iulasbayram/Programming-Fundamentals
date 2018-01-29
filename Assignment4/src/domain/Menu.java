/**
 * @author Hasan Yeniada - Ismail Ulas Bayram , 220201024 - 220201040
 */
package domain;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	
	
	public String getNameInput() {
		String name = "";
		boolean bool1 = true;
		Scanner scan = new Scanner(System.in);
		while (bool1) {
			name = scan.nextLine();
			if (name.matches("[^0-9]*") && !name.matches(".*[\\p{Punct}].*") && name.matches("[\\S].*") ){
				bool1 = false;
			}else{ System.out.println("Enter a valid name!");}
		}
		return name;
	}
	
	public int getIndexInput(int cardCount) {
		String choice = "";
		Scanner input = new Scanner(System.in);
		boolean invalidInput = true;
		while(invalidInput) {
			choice = input.nextLine();
			if( choice.matches("[0-9].*") && choice.matches("[\\S].*") && Integer.parseInt(choice) > 0 && Integer.parseInt(choice) <= cardCount ) {
				invalidInput = false;
			}else{ System.out.println("Enter a valid index");}
		}
		return Integer.parseInt(choice) - 1;
	}

	public void showHumanCards(Player human){
		int i=1;
		for (Card card : human.getCardList()) {
			System.out.println(i+". card --> " + card.toString());
			i++;
		}
	}
	
	//This method specifies the score at the end of the hands and specify who will begin to next hand.
	public int scorebyNumber(Card humanCard, Card compCard, Player human, Player computer,int whoStart){
		if (humanCard.getNumber() > compCard.getNumber()) {
			human.setScore(humanCard.getScore());
			return 1;  // 1 means that human won the hand and computer will begin to next hand.
		}else if(compCard.getNumber() > humanCard.getNumber()){
			computer.setScore(compCard.getScore());
			return 0;  // 0 means that computer won the hand and human will begin to next hand.
		}else{
			return 2;  // 2 means that there is no winner, the starter of last hand will begin to next hand.
		}
	}
	
	//This method prints out result of hand.
	public void getResultOfHand(Card humanCard, Card compCard, Player human, Player computer,int whoStart, int handNumber){
		if(whoStart==1){
			System.out.println(handNumber +". hand, " + human.getName() + " wins.");
			System.out.println(human.getName() + " gets " + humanCard.getScore() + " points!");
		}else if(whoStart==0){
			System.out.println(handNumber +". hand, " + computer.getName() + " wins.");
			System.out.println(computer.getName() + " gets " + compCard.getScore() + " points!");
		}else{
			System.out.println(handNumber + ". hand, there is no winner becaouse " + human.getName() + " and " + computer.getName() + " put on same card number." );
		}
	}
	
	//This method prints out result of game.
	public void getResultOfGame(Player human, Player computer){
		if (human.getScore() > computer.getScore()) {
			System.out.println("Game is over. Winner user is " + human.getName() + ". Total points of him/her are " +human.getScore());
		} else {
			System.out.println("Game is over. Winner user is " + computer.getName() + ". Total points of computer are " +computer.getScore());
		}
	}
		
	public void menu(Player human, Player computer, int whoStart){
		computer.setName();
		System.out.println("Please, enter your username:");
		String humanName= getNameInput(); //Input for human name.
		human.setName(humanName);
		int handNumber=1;
		boolean endGame = true;
			
		while (endGame) { 
			
			Card humanCard = null;
			Card compCard = null;
			
			if(!computer.isEmpty() && !human.isEmpty() && whoStart==1){
				System.out.println();
				System.out.println("**********BEGINNING OF "+ handNumber + ". HAND**********");
				
				System.out.println(computer.getName() + " is starting hand.");
				System.out.println();
				compCard = computer.getNextCard(humanCard); //If humanCard is null, this method returns a card from computerCardList randomly.
				System.out.println("-----" + computer.getName() + " puts " + compCard.toString() + "-----");
				System.out.println("*---------------------------*");
				showHumanCards(human); //Showing for human cards.
				System.out.println("*---------------------------*");

				
				System.out.println("Please, select a card from cards above.");
				
				int number=getIndexInput(human.getCardList().size()); //Getting an index input from humanCardList.
				humanCard = human.getNextCard(number);
				
				System.out.println("-----" +human.getName() + " puts " + humanCard.toString() + "-----");
				
				int whoStartTemp = whoStart; // Copy of whoStart
				
				whoStart = scorebyNumber(humanCard, compCard, human, computer, whoStart); // After calculate and set score, returning number to whoStart.
				getResultOfHand(humanCard, compCard, human, computer, whoStart, handNumber); //Printing result of hand
				
				if(whoStart==2) //If there is equality
					whoStart=whoStartTemp;
				
				human.removeCard(humanCard);
				computer.removeCard(compCard);
				
				System.out.println();
				System.out.println("**********ENDING OF " + handNumber + ". HAND**********");
				
				handNumber += 1;
	
			}else if(!computer.isEmpty() && !human.isEmpty() && whoStart==0){
				System.out.println();
				System.out.println("**********BEGINNING OF "+ handNumber + ". HAND**********");

				System.out.println(human.getName() + " is starting hand.");
				System.out.println("*---------------------------*");
				showHumanCards(human); //Showing for human cards.
				System.out.println("*---------------------------*");

				
				System.out.println("Please, select a card from cards above");

				int number=getIndexInput(human.getCardList().size());
				humanCard = human.getNextCard(number);

				System.out.println("-----" + human.getName() + " puts " + humanCard.toString() + "-----");

				compCard = computer.getNextCard(humanCard);
				System.out.println("-----" + computer.getName() + " puts " + compCard.toString() + "-----");

				int whoStartTemp = whoStart; //Copy of whoStart
				
				whoStart = scorebyNumber(humanCard, compCard, human, computer, whoStart); // After calculate and set score, returning number to whoStart.
				getResultOfHand(humanCard, compCard, human, computer, whoStart, handNumber); //Printing result of hand
				
				if(whoStart==2) //If there is equality
					whoStart=whoStartTemp;
				
				handNumber += 1;
				human.removeCard(humanCard);
				computer.removeCard(compCard);
				
				System.out.println();
				System.out.println("**********ENDING OF " + handNumber + ". HAND**********");
				
				handNumber += 1;

			}else{
				System.out.println();
				getResultOfGame(human, computer);
				endGame = false;
			}
		}
	}
}
