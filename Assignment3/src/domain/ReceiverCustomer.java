package domain;

public class ReceiverCustomer extends Customer {
	private Cargo cargo;
	
	public ReceiverCustomer(String id , String name , String phone_number , String date) {
		super(id,name,phone_number, date);
	}
	
	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
}
