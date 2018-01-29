/**
 *@author Hasan YENIADA-Ismail Ulas BAYRAM , 220201024-220201040
 */
package domain;

import java.util.ArrayList;
import java.util.Collections;

import fileAccessLayer.EstateAgentDAL;
import presentation.EstateAgentGUI;


public class EstateAgent {
	
	private EstateAgentDAL estateAgentDAL;
	private EstateAgentGUI estateAgentGUI;
	private ArrayList<House> houseList;
	
	public EstateAgent(){
		houseList = new ArrayList<House>();
		
		//creating EstateAgentDAL object
		String path = "housing.txt";
		estateAgentDAL = new EstateAgentDAL(this , path);
		estateAgentDAL.readHouses();
		
		//creating EstateAgentGUI object
		estateAgentGUI = new EstateAgentGUI(this);
		estateAgentGUI.create();
	}

	public void createAndAddHouse(String id , double price , String size , String roomNum , String bathNum , AirConditioner airConExist ) {
		House house = new House( id,price,size,roomNum,bathNum,airConExist );
		houseList.add(house); // according the given informations new house is created and added to houseList
	}
	
	public ArrayList<House> getHouseList() {
		return houseList;
	}

	public void setHouseList(ArrayList<House> houseList) {
		this.houseList = houseList;
	}
	
	public void addHouse(House house){
		houseList.add(house);
	}
	
	public boolean removeHouseByID(String id){ // according to given ID, house is removed from houseList.
		for (House house : houseList) {
			if(id.equals(house.getId())) {
				houseList.remove(house);
				return true; // if given ID is valid ,method returns true , this is used for input control.
			}
		}
		return false; // if this method returns false , given ID is invalid , this is used for input control.
	}
	
	public String[][] getDatas(){ // this method returns datas for creating table.
		 String [][]datas=new String[getHouseList().size()][6];
	     for(int i = 0 ; i < getHouseList().size() ; i++) {
	    	 	datas[i][0]= getHouseList().get(i).getId();
	    	 	datas[i][1]= "" + getHouseList().get(i).getPrice();
	    	 	datas[i][2]= getHouseList().get(i).getSize();
	    	 	datas[i][3]= getHouseList().get(i).getNumberOfRooms();
	    	 	datas[i][4]= getHouseList().get(i).getNumberOfBathrooms();
	    	 	datas[i][5]= "" + getHouseList().get(i).getAirConditioner();
	     }
	     return datas;		
	}
	
	public String[] getColumnDatas(){
		String []columnDatas=new String[6];
        columnDatas[0]="ID";
        columnDatas[1]="Price";
        columnDatas[2]="Size";
        columnDatas[3]="Room Count";
        columnDatas[4]="Bathroom Count";
        columnDatas[5]="AirConditioner";
        return columnDatas;
	}
	
	public String[][] orderByAscOrDesc(String[][] datas, int control){ 
	 	String [][] newDatas =new String[getHouseList().size()][6];
		ArrayList<Double> priceList = new ArrayList<Double>();
		for (int i = 0; i < houseList.size(); i++) {
			priceList.add(houseList.get(i).getPrice());
		}
		if(control==0){  // if control equals 0, list is sorted in ascending order
			Collections.sort(priceList);
			newDatas = getImplementDatasbyPrice(priceList);
		}else if(control==1){ // if control equals 1, list is sorted in descending order
			Collections.sort(priceList); //this sorts in ascending order
			Collections.reverse(priceList); //and this makes it descending
			newDatas = getImplementDatasbyPrice(priceList); 
		}
		return newDatas;
	}
	
	public String[][] getImplementDatasbyPrice(ArrayList<Double> priceList){
	 	String [][] newDatas =new String[getHouseList().size()][6];
	 	// according to sorted priceList, every price is associating with its house object
	 	int copyControl=0;
	 	int newIndex = 0;  
		for (int i = 0; i < priceList.size(); i++) {
			copyControl = 0; // this is for houses which have the same price
			for (House house : houseList) {
				if(priceList.get(i) == house.getPrice()){
					newIndex = i;
					if(copyControl!=0) newIndex = newIndex + 1;
					
						newDatas[newIndex][0]= house.getId();
						newDatas[newIndex][1]= "" + house.getPrice();
						newDatas[newIndex][2]= house.getSize();
						newDatas[newIndex][3]= house.getNumberOfRooms();
		   	 			newDatas[newIndex][4]= house.getNumberOfBathrooms();
		   	 			newDatas[newIndex][5]= "" + house.getAirConditioner();
		   	 			copyControl ++;
		   	 			i = newIndex;
				}	
			}
		}
		return newDatas;
		
	}
	
	public int getNewID(){
		int id= Integer.parseInt(houseList.get(houseList.size() - 1).getId()); // id becomes ID of last house at houseList.
		return id + 1;
	}
	
	// Following three method have the same procedure, they creates a new copyHouseList which has houses between given range.
	public void searchByPrice(double maxRange , double minRange){
		ArrayList<House> copyHouseList = new ArrayList<House>(houseList);
		for (House house : houseList) {
			if(house.getPrice() > maxRange || house.getPrice() < minRange)
				copyHouseList.remove(house);
		}
		houseList = new ArrayList<>(copyHouseList);
	}
	
	public void searchByNumberOfRooms(int maxRange , int minRange){
		ArrayList<House> copyHouseList = new ArrayList<House>(houseList);
		for (House house : houseList) {
			if (Integer.parseInt(house.getNumberOfRooms()) > maxRange || Integer.parseInt(house.getNumberOfRooms()) < minRange)
				copyHouseList.remove(house);
		}
		houseList = new ArrayList<>(copyHouseList);
	}
	
	public void searchBySize(int maxRange, int minRange){
		ArrayList<House> copyHouseList = new ArrayList<House>(houseList);
		for (House house : houseList) {
			if(Integer.parseInt(house.getSize()) > maxRange || Integer.parseInt(house.getSize()) < minRange)
				copyHouseList.remove(house);
		}
		houseList = new ArrayList<>(copyHouseList);
	}

	public void saveHouses() {
		estateAgentDAL.saveHouses(); // after every adding and removing operations, remaining houses are saved to updated file.
	}
}
