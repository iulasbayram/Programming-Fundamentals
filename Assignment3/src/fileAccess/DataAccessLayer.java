package fileAccess;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import domain.Company;
import domain.TransportationType;
import domain.Vehicle;

public class DataAccessLayer {
	
	public static TransportationType getType(String type) {
		TransportationType tType = null;

		if ( type.equalsIgnoreCase("air")){ tType = TransportationType.AIR;}
		else if ( type.equalsIgnoreCase("rail")) {tType = TransportationType.RAIL;}
		else if ( type.equalsIgnoreCase("road")) {tType = TransportationType.ROAD;}

		return tType;
	} 
	
	public void DataAccess( Company company) {
		try {
			Scanner readVehicles = new Scanner(new File("vehicles.txt"));
			while(readVehicles.hasNextLine()){
				String line = readVehicles.nextLine();
				StringTokenizer st = new StringTokenizer(line, ", ");
				
				String id = st.nextToken();
				
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		        Date tarih = df.parse(st.nextToken());
		        
		        TransportationType tType = getType(st.nextToken());
				double upperLimit = Double.parseDouble(st.nextToken());
				
				Vehicle vehicle = new Vehicle(id,tarih,tType,upperLimit);
				company.getVehicles().add(vehicle);
			
			}
			readVehicles.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error is:" + e.getMessage());
		} catch(ParseException p) {
			System.out.println("Error is:" + p.getMessage());
		}
	}
}
