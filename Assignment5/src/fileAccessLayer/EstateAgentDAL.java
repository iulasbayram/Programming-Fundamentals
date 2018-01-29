/**
 *@author Hasan YENIADA-Ismail Ulas BAYRAM , 220201024-220201040
 */
package fileAccessLayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

import domain.AirConditioner;
import domain.EstateAgent;
import domain.House;

public class EstateAgentDAL {
	
	EstateAgent estateAgent;
	String path;
	
	public EstateAgentDAL(EstateAgent estateAgent , String path) {
		this.estateAgent = estateAgent;
		this.path = path;
	}
	
	public static AirConditioner getAirConditionerExistOrNot (String airContinioner_exist) {
		AirConditioner exist = null;
		if(airContinioner_exist.equalsIgnoreCase("yes")) exist = AirConditioner.YES;
		else exist = AirConditioner.NO;
		
		return exist;
	}
	
	public void readHouses() {
		try {
			Scanner readHouses = new Scanner(new File(path));
			String line = readHouses.nextLine(); //first line of file is unnecessary,thus we pass it
			while(readHouses.hasNextLine()) {
				line = readHouses.nextLine();
				
				StringTokenizer st = new StringTokenizer(line,",");
				String id = st.nextToken();
				double price = Double.parseDouble(st.nextToken());
				String size = st.nextToken();
				String room_count = st.nextToken();
				String bathroom_count = st.nextToken();
				AirConditioner existOrNot = EstateAgentDAL.getAirConditionerExistOrNot(st.nextToken());
				
				//every house objects are created and added to EstateAgent
				estateAgent.createAndAddHouse(id,price,size,room_count,bathroom_count,existOrNot);;
			}
			readHouses.close();
		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void saveHouses() {
		
		try {
			String fileName = "housing.updated.txt";
			File file = new File(fileName);
			if(!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(fileName);
			PrintWriter pw = new PrintWriter(fos);
			
			for(House house : estateAgent.getHouseList()) {
				pw.println(house.getId() + "," + house.getPrice() + "," + house.getSize() + "," + house.getNumberOfRooms() + "," + house.getNumberOfBathrooms() + "," + house.getAirConditioner());
			}
			pw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
