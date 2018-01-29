package domain;

import java.util.ArrayList;

public class SenderCustomer extends Customer {
	private ArrayList<Cargo> cargoesOfSender;
	
	public SenderCustomer(String id , String name , String phone_number , String date) {
		super(id,name,phone_number, date);
	}
	
	public ArrayList<Cargo> getCargoesOfSender() {
		return cargoesOfSender;
	}

	public void setCargoesOfSender(ArrayList<Cargo> cargoesOfSender) {
		this.cargoesOfSender = cargoesOfSender;
	}
}
