package domain;

import java.util.Date;

public class Cargo {
	private int id;
	private double weight;
	private double price;
	private String order_date;
	private SenderCustomer sender;
	private ReceiverCustomer receiver;
	private Customer payer;
	
	public Cargo() {
		//order_date = new Date();
	}
	public Cargo(int id, double weight, double price, String order_date, SenderCustomer sender, ReceiverCustomer receiver,Customer payer) {
		setId(id);
		setWeight(weight);
		setPrice(price);
		setOrder_date(order_date);
		setSender(sender);
		setReceiver(receiver);	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public SenderCustomer getSender() {
		return sender;
	}
	public void setSender(SenderCustomer sender) {
		this.sender = sender;
	}
	public ReceiverCustomer getReceiver() {
		return receiver;
	}
	public void setReceiver(ReceiverCustomer receiver) {
		this.receiver = receiver;
	}
	public Customer getPayer() {
		return payer;
	}
	public void setPayer(Customer payer) {
		this.payer = payer;
	}
}