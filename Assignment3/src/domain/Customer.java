package domain;

public class Customer {
	private String id;
	private String name;
	private String phone_number;
	private String adress;
	
	public Customer() {
		
	}
	public Customer(String id, String name, String phone_number, String adress) {
		setId(id);
		setName(name);
		setPhone_number(phone_number);
		setAdress(adress);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
}
