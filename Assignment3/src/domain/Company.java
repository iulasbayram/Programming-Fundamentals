package domain;

import java.util.ArrayList;

public class Company {
	private ArrayList<Cargo> cargoes;
	private ArrayList<Vehicle> vehicles;
	private ArrayList<SenderCustomer> senders;
	
	public Company() {
		vehicles = new ArrayList<Vehicle>();
		cargoes = new ArrayList<Cargo>();
		senders = new ArrayList<SenderCustomer>();
	}
	
	public Company(ArrayList<Cargo> cargoes, ArrayList<Vehicle> vehicles, ArrayList<SenderCustomer> senders) {
		setCargoes(cargoes);
		setSenders(senders);
		setVehicles(vehicles);
		
	}

	public ArrayList<Cargo> getCargoes() {
		return cargoes;
	}
	public void setCargoes(ArrayList<Cargo> cargoes) {
		this.cargoes = cargoes;
	}
	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	public ArrayList<SenderCustomer> getSenders() {
		return senders;
	}
	public void setSenders(ArrayList<SenderCustomer> senders) {
		this.senders = senders;
	}
	
	
}
