/**
 * @author Hasan Yeniada - Ismail Ulas Bayram , 220201024 - 220201040
 */
package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Simulation {
		
	public int randomStarting(){
		Random randomInt = new Random();
		return randomInt.nextInt(2);
	}	
	
	
	public boolean continueOrExit(){
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		if(input.equals("y") || input.equals("Y")){
			return true;
		}else{
			return false;
		}
	}
	
	//This method starts game using menu and all of methods inside this method.
	public void startMenu(Player human, Player computer){
		boolean control = true;
		
		while(control){
			Deck deck = new Deck();
			deck.init();
			deck.randomShuffledCards(human, computer);  //Shuffling cards
			
			int whoStart = randomStarting(); //Returning a number who specify starting player.
			
			Menu menu = new Menu();
			menu.menu(human, computer, whoStart); //Starting menu
			
			DataAccessLayer saveDatas = new DataAccessLayer();
			saveDatas.dataAccessLayer(human, computer);
			System.out.println("To countinue, please enter Y");
			control= continueOrExit();
		}
	}
	
}
