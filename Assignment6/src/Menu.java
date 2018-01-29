import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private DataAccessLayer DAL;
	private Operation operation;
	
	public Menu(Operation operation , DataAccessLayer DAL){
		this.operation = operation;
		this.DAL = DAL;
	}
	
	public void printMenu() {
		System.out.println("------MENU----");
		System.out.println("1.) Adding a new poet.");
		System.out.println("2.) Finding the poet that has maximum occurrence of the word.");
		System.out.println("3.) Top-10 popular words.");
		System.out.println("4.) Find poets that begins with the given word.");
		System.out.println("5.) Find Acrostics.");
		System.out.println("6.) Exit.");
		System.out.println("Please, choose one operation of above that.");
	}
	
	public void startMenu() {
		printMenu();
		
		boolean printmenu = true;
		Scanner choiceOperation = new Scanner(System.in);
		boolean mainBool = true;
		
		while(mainBool) {	
			String choice = choiceOperation.nextLine();
			if( choice.equals("1")) {   // ADDING NEW POEM.
				Scanner pathScanner = new Scanner(System.in);
				System.out.println("Enter the path of file to add a new poem:");
				String newPathName = pathScanner.nextLine();
				DAL.writeNewPath(newPathName);
			}
			else if(choice.equals("2")) {  // MAXIMUM OCCURENCE.
				System.out.println("Enter a word to find the poem that given word occurs maximum:");
				String word = takeWord();
				
				ArrayList<Poem> poemList = operation.getPoemWhichHasMaxOcc(word);
				// if poemList is null, given word does not occur in any poem
				if(poemList == null) { System.out.println("There is no poem which given word occur!");}
				else{
					System.out.println("Poems which begin with given word:");
					for (Poem poem : poemList) {
						System.out.println(poem.getTitle());
					}
				}
			}		
			else if (choice.equals("3")){  // 10 POPULAR WORD.
				ArrayList<String> top10WordsList = operation.getTopTenPopularWordsList();
				System.out.println("Top 10 most frequent words:");
				for (String popularWords : top10WordsList) {
					System.out.println(popularWords);
				}
			}
			else if ( choice.equals("4")) {  //POEMS WHICH BEGIN WITH GIVEN WORD.
				Scanner inputScanner = new Scanner(System.in);
				System.out.println("Enter a word to find poems which begins with that word:");
				String word = inputScanner.nextLine();

				ArrayList<String> list = operation.poetsThatBeginInputWord(word);
				// if size is equal to zero, there is no poem which begins with given word
				if(list.size() == 0) { System.out.println("There is no poem which begin with given word!");}
				else{
					System.out.println("Poems which begin with given word:");
					for (String string : list) {
						System.out.println(string);
					}
				}
			}
			else if ( choice.equals("5")) {   // FIND ACROSTICS
				System.out.println("Enter a word to search acrostic(s) for it: ");
				String word = takeWord();
				
				ArrayList<String> acrosticList = operation.findAcrostics(word);
				for (String string : acrosticList) {
					if(acrosticList.size() > 0) System.out.println(string);
					else System.out.println("There is no acrostic(s) for given word!");
				}
			}
			else if (choice.equals("6")){   // EXIT
				mainBool = false;
			 	printmenu = false;
			}
			else {
			 	mainBool = true  ;
			 	System.out.println("Please choose a valid operation!");
			 	}		
			
			if(printmenu == true){printMenu();} 
			else{System.out.println("End of the application.See You!");}
		}		
	}
	
	public String takeWord() {
		String word = "";
		boolean bool = true;
		Scanner scan = new Scanner(System.in);
		while (bool) {
			word = scan.nextLine();
			if (word.matches("[^0-9]*") && !word.matches(".*[\\p{Punct}].*") && word.matches("[\\S].*") ){
				bool = false;}
			else{System.out.println("Enter a valid word please:");}
		}
		return word;
	}
}
