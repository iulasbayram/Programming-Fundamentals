package domain;

import java.util.ArrayList;
import java.util.Date;

public class Vehicle {
	private String id;
	private Date departure_date;
	private TransportationType transType;
	private ArrayList<Cargo> cargoes ;
	private double upper_weight_limit;
	
	public Vehicle() {}
	
	public Vehicle(String id , Date departure_date , TransportationType transType , double upper_weight) {
		setId(id);
		setDeparture_date(departure_date);
		setTransType(transType);
		setUpper_weight_limit(upper_weight);
		cargoes = new ArrayList<Cargo> () ;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDeparture_date() {
		return departure_date;
	}
	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
	}
	public TransportationType getTransType() {
		return transType;
	}
	public void setTransType(TransportationType transType) {
		this.transType = transType;
	}
	public ArrayList<Cargo> getCargoes() {
		return cargoes;
	}
	public void setCargoes(ArrayList<Cargo> cargoes) {
		this.cargoes = cargoes;
	}
	public double getUpper_weight_limit() {
		return upper_weight_limit;
	}
	public void setUpper_weight_limit(double upper_weight_limit) {
		this.upper_weight_limit = upper_weight_limit;
	}
}
