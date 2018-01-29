/**
 *@author Hasan YENIADA-Ismail Ulas BAYRAM , 220201024-220201040
 */
package domain;

public class House {
	private double price;
	private String size, numberOfRooms, numberOfBathrooms, id;
	private AirConditioner airConditioner;
	
	public House(){
		
	}

	public House(String id, double price, String size, String numberOfRooms, String numberOfBathrooms, AirConditioner airConditioner){
		setId(id);
		setPrice(price);
		setSize(size);
		setNumberOfRooms(numberOfRooms);
		setNumberOfBathrooms(numberOfBathrooms);
		setAirConditioner(airConditioner);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(String numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public String getNumberOfBathrooms() {
		return numberOfBathrooms;
	}

	public void setNumberOfBathrooms(String numberOfBathrooms) {
		this.numberOfBathrooms = numberOfBathrooms;
	}

	public AirConditioner getAirConditioner() {
		return airConditioner;
	}

	public void setAirConditioner(AirConditioner airConditioner) {
		this.airConditioner = airConditioner;
	}
	
	@Override
	public String toString() {
		return "House [price=" + price + ", size=" + size + ", numberOfRooms=" + numberOfRooms + ", numberOfBathrooms="
				+ numberOfBathrooms + ", id=" + id + ", airConditioner=" + airConditioner + "]";
	}

}	