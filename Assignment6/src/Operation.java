import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Operation {
	HashMap<String,ArrayList<Poem>> wordsByPoemMap;
	HashMap<String,ArrayList<Integer>> wordsByIndexMap;
	ArrayList<Poem> poemList;
	DataAccessLayer DAL;
	
	public Operation() {   // DAL and hashmaps are created
		wordsByPoemMap = new HashMap<String,ArrayList<Poem>>();
		wordsByIndexMap = new HashMap<String,ArrayList<Integer>>();
		poemList = new ArrayList<Poem>();
		DAL = new DataAccessLayer(this);
	}
	
	public void start(){
		init();  // hashmaps are built at DAL
		Menu menu = new Menu(this,DAL);  
		menu.startMenu();  // menu starts
	}
	
	public void init(){  // according to poets.txt, datas are put to related hashmaps
		DAL.createAllIndexAndWordsList();
		DAL.createAllWordsMapbyIndex();
		DAL.createAllWordsMapbyPoem();
	}
	
	public ArrayList<Poem> getPoemWhichHasMaxOcc(String word){
		ArrayList<Integer> maxOccList = new ArrayList<Integer>();
		ArrayList<Integer> maxList = new ArrayList<Integer>();
		ArrayList<Poem> poemThatHasMaxOccList = new ArrayList<Poem>();
		ArrayList<Poem> poemList=null;
		for (String poemWord : wordsByIndexMap.keySet()) {
			if(poemWord.equalsIgnoreCase(word)){
				ArrayList<Integer> indexList = wordsByIndexMap.get(poemWord);
				ArrayList<Integer> boundList = new ArrayList<Integer>();
				poemList = wordsByPoemMap.get(poemWord);
				int initBound=0;
				boundList.add(initBound);
				for (int i = 0; i < poemList.size(); i++) {
					initBound += poemList.get(i).getNumberOfWords();
					boundList.add(initBound);
				}
				maxOccList = new ArrayList<Integer>();
				for (Integer integer : indexList) {
					for (int i = 1; i < boundList.size(); i++) {
						int interval = boundList.get(i) - boundList.get(i-1);
						if(boundList.get(i-1) < integer && integer < boundList.get(i))
							maxOccList.add(interval);
						else if(integer < 0 && boundList.get(i-1) < integer*-1 && integer*-1 < boundList.get(i))
							maxOccList.add(interval);
					}
				}
			}	
			
		}
		
		if(!maxOccList.isEmpty()){
			Map<Integer, Integer> map = new HashMap<>();
			for (Integer integer : maxOccList) {
				Integer val = map.get(integer);
				map.put(integer, val == null ? 1 : val + 1);
			}

			Entry<Integer, Integer> max = null;
			
			for (Entry<Integer, Integer> e : map.entrySet()) {
				if (max == null || e.getValue() > max.getValue())
					max = e;
			}
	   
			maxList.add(max.getKey());
	    
			for (Integer integer : maxList) {
				for (Poem poem : poemList) {
					if (poem.getNumberOfWords()==integer) 
						poemThatHasMaxOccList.add(poem);		
				}
			}
			return poemThatHasMaxOccList;	
			
		}else
			return null;
	}
	
	public ArrayList<String> getTopTenPopularWordsList(){
		HashMap<String,Integer> firstList = new HashMap<String,Integer>();

		for (String word : wordsByIndexMap.keySet()) {
			ArrayList<Integer> indexList = wordsByIndexMap.get(word);
			int sizeOfList = indexList.size();
			firstList.put(word, sizeOfList);
		}
		
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>( firstList.entrySet() );
	        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
	        {
	            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
	            {
	                return (o2.getValue()).compareTo( o1.getValue() );
	            }
	        } );

	        Map<String, Integer> result = new LinkedHashMap<String, Integer>();
	        for (Map.Entry<String, Integer> entry : list)
	        {
	            result.put( entry.getKey(), entry.getValue() );
	        }
	        
	        ArrayList<String> topTenWordsList = new ArrayList<String>();
	        ArrayList<String> sortedList = new ArrayList<String>();
	        for (String word : result.keySet()) {
				sortedList.add(word);
			}
	      
	        for (int i = 0; i < 10; i++) {
				topTenWordsList.add(sortedList.get(i));
			}
	        
	        return topTenWordsList;
	}
	
	public ArrayList<String> poetsThatBeginInputWord(String inputWord){
		ArrayList<String> titleList = new ArrayList<String>();
		for (String word : wordsByIndexMap.keySet()) {
			if (word.equalsIgnoreCase(inputWord)) {
				ArrayList<Integer> wordIndexList = wordsByIndexMap.get(word);
				for (Integer index : wordIndexList) {
					int indexOfFirstWord = 1;
					for (Poem poem : poemList) {
						if (index < 0 && index*-1==indexOfFirstWord) {
							titleList.add(poem.getTitle());
							indexOfFirstWord = indexOfFirstWord + poem.getNumberOfWords();
						}else{
							indexOfFirstWord = indexOfFirstWord + poem.getNumberOfWords();
						}
					}
				}
			}
		}
		return titleList;
	}
	
	public ArrayList<String> findAcrostics(String inputWord){
		String[] charList = inputWord.split("");
		ArrayList<String> charArray = new ArrayList<String>();
		for (int i = 0; i < charList.length; i++) {
			charArray.add(charList[i]);
		}
		
		ArrayList<String> lineList = new ArrayList<String>();
		ArrayList<Integer> allIndex = new ArrayList<Integer>();
		ArrayList<Integer> allIndex2 = new ArrayList<Integer>();
		ArrayList<Integer> allIndex3 = new ArrayList<Integer>();

		ArrayList<String> allWords = new ArrayList<String>();
		int index = 1;
		for (String string : wordsByIndexMap.keySet()) {
			ArrayList<Integer> indexList = wordsByIndexMap.get(string);
			for (Integer integer : indexList) {
				allIndex.add(integer);
			}
		}
		for (Integer integer : allIndex) {
			if(integer < 0){
				allIndex2.add(integer*-1);
				allIndex3.add(integer);
			}else
				allIndex2.add(integer);
		}
		Collections.sort(allIndex2);
		
		for (Integer integer : allIndex3) {
			for (Integer subInteger : allIndex2) {
				if(integer*-1==subInteger)
					allIndex2.set(subInteger-1,integer);
			}
		}
		
		for (Integer integer : allIndex2) {
			for (String string : wordsByIndexMap.keySet()) {
				ArrayList<Integer> indexList = wordsByIndexMap.get(string);
				for (Integer integer2 : indexList) {
					if(integer==integer2)
						allWords.add(string);
				}
			}
		}
		
		Collections.sort(allIndex3);
		Collections.reverse(allIndex3);
		allIndex3.add((allIndex2.size()+1)*-1);
		ArrayList<Integer> boundries = new ArrayList<Integer>();
		boundries.add(0);
		int boundry = 0;
		for (int i = 1; i < allIndex3.size(); i++) {
			boundry = boundry + (allIndex3.get(i) - allIndex3.get(i-1))*-1;
			boundries.add(boundry);
		}
		
		for (int i = 1; i < boundries.size(); i++) {
			String line = "";
			for (int j = boundries.get(i-1); j < boundries.get(i); j++) {
				line = line + allWords.get(j) + " ";
			}
			lineList.add(line);
		}
		
		ArrayList<String> acrosticList = new ArrayList<String>();
		int lower = 0;
		int higher = charArray.size();
		int amountOfIncrease = 0;
		boolean control = true;
		while(control){
			int allowence = 0;
			for (int i = lower; i < higher; i++) {
				if(charArray.get(i-amountOfIncrease).equalsIgnoreCase(lineList.get(i).substring(0,1))){
					allowence = allowence + 1;
				}
			}
			if(allowence==charArray.size()){
				for (int j = lower; j < higher; j++) {
					acrosticList.add(lineList.get(j));
				}
				control = false;
			}
			if(higher==lineList.size())
				control = false;
			amountOfIncrease+=1;
			lower+=1;
			higher+=1;
		}
		
		return acrosticList;
		
	}
	
	public HashMap<String, ArrayList<Poem>> getWordsByPoemMap() {
		return wordsByPoemMap;
	}

	public void setWordsByPoemMap(HashMap<String, ArrayList<Poem>> wordsByPoemMap) {
		this.wordsByPoemMap = wordsByPoemMap;
	}

	public HashMap<String, ArrayList<Integer>> getWordsByIndexMap() {
		return wordsByIndexMap;
	}

	public void setWordsByIndexMap(HashMap<String, ArrayList<Integer>> wordsByIndexMap) {
		this.wordsByIndexMap = wordsByIndexMap;
	}

	public ArrayList<Poem> getPoemList() {
		return poemList;
	}

	public void setPoemList(ArrayList<Poem> poemList) {
		this.poemList = poemList;
	}

	

	
}
