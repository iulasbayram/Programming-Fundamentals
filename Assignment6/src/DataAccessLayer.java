import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DataAccessLayer {
	private Operation operation;
	private ArrayList<String> controlList;
	private ArrayList<Integer> allIndex;
	private ArrayList<String>  allString;
	private ArrayList<String> allStringWithTitles;
	
	public DataAccessLayer(Operation operation) {
		this.operation = operation;
	}
	
	public boolean isWordExistInpoemList(String word){
		if(!controlList.contains(word))
			return true;
		else
			return false;
	}
	
	public void createAllIndexAndWordsList(){
		try {
			Scanner scanner = new Scanner(new File("poets.txt"));
			String delims = ",:;!'-. ";
			String title = "";
			int orderOfPoem = 1;
			int numOfPoemWords =0;
			int index = 1;
			allIndex = new ArrayList<Integer>();
			allString = new ArrayList<String>();
			allStringWithTitles = new ArrayList<String>();
			ArrayList<Poem> poemList = operation.getPoemList();
			String line = scanner.nextLine();

			while(scanner.hasNextLine()){
				int orderOfLine=1;
				if(!line.isEmpty()){
					StringTokenizer sT = new StringTokenizer(line,delims);
					String firstsT = sT.nextToken();
					if (firstsT.equals("Title")) {
						title = sT.nextToken();
						while(sT.hasMoreTokens())
							title = title + " " + sT.nextToken();
						numOfPoemWords = 0 ;
						allStringWithTitles.add(firstsT);
						allStringWithTitles.add(title);
					}else{
						while(sT.hasMoreTokens()){	
							if(orderOfLine == 1){
								allIndex.add(index*-1);
								allString.add(firstsT.toLowerCase());
								allStringWithTitles.add(firstsT.toLowerCase());
								index = index + 1;
								numOfPoemWords = numOfPoemWords + 1;
							}
							
							String notFirstsT = sT.nextToken();
							allIndex.add(index);
							allString.add(notFirstsT.toLowerCase());
							allStringWithTitles.add(notFirstsT.toLowerCase());
							numOfPoemWords = numOfPoemWords + 1;
							index = index + 1;
							orderOfLine = orderOfLine + 1;
						}
						
					}
					line = scanner.nextLine();
					if(line.isEmpty()){
						Poem poem = new Poem(title,numOfPoemWords,orderOfPoem);
						poemList.add(poem);
						orderOfPoem = orderOfPoem + 1;
						line = scanner.nextLine();
					}
				}
			}
			operation.setPoemList(poemList);
		}catch (IOException e) {
			System.out.println("File is not found." + e.getMessage());
		}
	}
	
	public void createAllWordsMapbyIndex(){
		HashMap<String,ArrayList<Integer>> wordsByIndexMap = operation.getWordsByIndexMap();
		controlList = new ArrayList<String>();
		ArrayList<Integer> allIndexListOfWord = null;
	
		for (int i = 0; i < allString.size(); i++) {
			if(isWordExistInpoemList(allString.get(i).toUpperCase())){
				controlList.add(allString.get(i));
				allIndexListOfWord = new ArrayList<Integer>();
				for (int j = 0; j < allString.size(); j++) {
					if(allString.get(i).equalsIgnoreCase(allString.get(j)))
						allIndexListOfWord.add(allIndex.get(j));
				}
			}
			wordsByIndexMap.put(allString.get(i), allIndexListOfWord);
		}
		operation.setWordsByIndexMap(wordsByIndexMap);
	}

	public void createAllWordsMapbyPoem(){
		ArrayList<Poem> poemList = operation.getPoemList();
		HashMap<String,ArrayList<Poem>> wordsByPoemMap = operation.getWordsByPoemMap();
		controlList = new ArrayList<String>();
		ArrayList<String> titleList;
		ArrayList<Poem> poemListOfWord;
		for (String word : allString) {
			if (!controlList.contains(word)) {
				controlList.add(word);
				poemListOfWord = new ArrayList<Poem>();
				titleList = new ArrayList<String>();
				String title = "";
				for(int i=0; i<allStringWithTitles.size();i++){
					if(allStringWithTitles.get(i).equals("Title"))
						title = allStringWithTitles.get(i+1);
					else if(allStringWithTitles.get(i).equalsIgnoreCase(word) && !titleList.contains(title)){
						titleList.add(title);
						for (Poem poem : poemList) {
							if(poem.getTitle().equals(title)){
								poemListOfWord.add(poem);
								
							}
						}
					}
				}
				wordsByPoemMap.put(word, poemListOfWord);
			}
		}
		operation.setWordsByPoemMap(wordsByPoemMap);
	}
	
	public void writeNewPath(String path){
		try {
			Scanner scanner = new Scanner(new File(path));
			ArrayList<String> inputList = new ArrayList<String>();
			while(scanner.hasNextLine()){
				inputList.add(scanner.nextLine());
			}
			for (String string : inputList) {
				System.out.println(string);
			}
			
			FileOutputStream fos = new FileOutputStream("poets.txt",true);
			PrintWriter pw = new PrintWriter(fos);
			for (String string : inputList) {
				pw.println(string);
			}
			pw.close();
		} catch (IOException e) {
			System.out.println("File is not found." + e.getMessage());;
		}
		
	}
	
	
	
}
