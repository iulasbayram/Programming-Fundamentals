package presentation;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import domain.*;

public class MyWindow extends JPanel implements ActionListener{
    JButton button1;
    JLabel label0,label1,label2,label3,label4;
    JTextField field1,field2;
	JRadioButton radio1,radio2,radio3;
    ButtonGroup group;
	boolean vehicleAvailable = false;
	Company company;
	double weight = 0.0;
	double price = 0.0;
	int cargo_id = 999;
	
	public MyWindow(Company com) {
    	super();
    	FlowLayout flow = new FlowLayout(FlowLayout.CENTER ,10,10);
    	this.setLayout(flow);
    	this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    	company = com;    	   
    	label0 = new JLabel("WELCOME TO OUR CARGO COMPANY");
    	label1 = new JLabel("To search for available vehicles,Press SEARCH FOR VEHICLE button:");
    	label2 = new JLabel("CARGO WEIGHT:");
       	label3 = new JLabel("CARGO DATE:");
    	label4 = new JLabel("CHOOSE TRANSPORTATION TYPE:");
    	button1= new JButton("SEARCH FOR VEHICLE");
    	radio1 = new JRadioButton("RAIL");
    	radio2 = new JRadioButton("AIR");
    	radio3 = new JRadioButton("ROAD");
    	field1 = new JTextField(4);
    	field2 = new JTextField(6);
    	group = new ButtonGroup();
    	group.add(radio1);
    	group.add(radio2);
    	group.add(radio3);
 		this.add(label0);
 		this.add(label1);
    	this.add(label2);
    	this.add(field1);
    	this.add(label3);
    	this.add(field2);
    	this.add(label4);
    	this.add(radio1);
    	this.add(radio2);
    	this.add(radio3);
    	this.add(button1);
    	button1.addActionListener(this);
    }	
    @Override
	public void actionPerformed(ActionEvent e) {
    	if(e.getActionCommand().equals("SEARCH FOR VEHICLE"))  {
    	  if(!field1.getText().equals("") && !field2.getText().equals("") && (radio1.isSelected() || radio2.isSelected() || radio3.isSelected())){

    		weight = Double.parseDouble( field1.getText());
        	String date = field2.getText();
        	TransportationType type = null;
        	if(radio1.isSelected()) { type = TransportationType.RAIL;}
        	else if(radio2.isSelected()){ type = TransportationType.AIR;}
        	else if(radio3.isSelected()){ type = TransportationType.ROAD;}

        	boolean vehicleAvailable = false;
    		for(Vehicle vehicle : company.getVehicles()) {
    			if(weight < vehicle.getUpper_weight_limit() && type.equals(vehicle.getTransType()) ) {
    				vehicleAvailable = true;
    			}
    		}
    		if(vehicleAvailable == true){
    			String msj = "There is available vehicle!"; 
    			JOptionPane.showMessageDialog(this, msj, "Sonuç" , JOptionPane.INFORMATION_MESSAGE);
    			double price_cargo = totalPrice(type, weight);
    			
    			Object[] options = {"YES","NO"};
				int choice = JOptionPane.showOptionDialog(this,  "Do you accept $" + price_cargo + " price for cargo?", "Which one",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
			if(choice != -1) {
    			if(choice == 0) {  
    				Customer payer = null;
    	    		SenderCustomer sender = null;
    				String id = takeIDofSender();
    	    		boolean senderExist = SenderRecordedOrNot(id);
    				
    					if(senderExist == true){
							JOptionPane.showMessageDialog(this, "You have already a record in our company!");
							sender = takeSender(id);}
						else{
							JOptionPane.showMessageDialog(this, "You do not have a record in our company,Give informations to record:");
							sender = CreateSenderCustomer();
							sender.setId(id);
						}
    					ReceiverCustomer receiver = CreateReceiverCustomer();
    					options[0] = "SENDER"; options[1] = "RECEIVER";
        				int ch = JOptionPane.showOptionDialog(this, "Plaese choose who will pay for cargo:", "WHICH ONE", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
        			if(ch != -1) {
    					if(ch == 0){payer = sender;}
						else if(ch == 1){payer = receiver;}
    					
    					JOptionPane.showMessageDialog(this, payer.getName().toUpperCase() +" will pay $" + price_cargo + " for cargo:");
						company.getSenders().add(sender);
						Cargo cargo = new Cargo(cargo_id++ , weight , price , date , sender , receiver , payer );
						cargo.setPayer(payer);
						company.getCargoes().add(cargo);
		    			JOptionPane.showMessageDialog(this, "Cargo order has taken succesfully!");
    				}
				}
				else{
	    			String message = "To search a new available vehicle, press search button!";
					JOptionPane.showMessageDialog(this, message, "RESULT" , JOptionPane.INFORMATION_MESSAGE);
					}    
				}
    		}
    		else {				
    			String mesa = "There is no available vehicle now!";
    			JOptionPane.showMessageDialog(this, mesa, "RESULT" , JOptionPane.INFORMATION_MESSAGE);
    			}    		
    	   }
          else{JOptionPane.showMessageDialog(this,"Please fill the neccessary fields!");}
    	  field1.setText("");field2.setText("");
      }
    }
    
    public double totalPrice(TransportationType type , double weight) {
		price = 0.0;
    	if(type.equals(TransportationType.AIR)) price = (weight * 5);
    	else if(type.equals(TransportationType.RAIL)) price = (weight * 2);
    	else if(type.equals(TransportationType.ROAD)) price = (weight * 3.5);
    	return price;
    }
    public SenderCustomer CreateSenderCustomer() {
    	String name = "";
    	String phone = "";
    	String adress = "";
   
		boolean bool2 = true;
		
		while (bool2) {
			name = JOptionPane.showInputDialog(this,"Enter your name:");
			if (name.matches("[^0-9]*") && !name.matches(".*[\\p{Punct}].*") && name.matches("[\\S].*") ){
				bool2 = false;}
			else{JOptionPane.showMessageDialog(this, "Enter valid name!");}
    	  }
	    phone = JOptionPane.showInputDialog(this,"Enter your phone number:");
    	adress = JOptionPane.showInputDialog(this,"Enter your adress");
    	SenderCustomer sendercustomer = new SenderCustomer("",name,phone,adress);
    	
    	return sendercustomer;	
	}
    public boolean SenderRecordedOrNot(String id) {
    	boolean sender_exist = false;
    	for(Customer customer : company.getSenders()) {
    		if(id.equals(customer.getId())) sender_exist = true;
    	}
    	return sender_exist;
    }
    public ReceiverCustomer CreateReceiverCustomer() {
    	String name = "";
    	String id = "";
    	String phone = "";
    	String adress = "";
 
    	boolean bool1 = true;
    	while (bool1) {
			id = JOptionPane.showInputDialog(this,"Enter receiver's id:");
			if (id.matches("[^0-9]*") && !id.matches(".*[\\p{Punct}].*") && id.matches("[\\S].*") ){
				bool1 = false;}
			else{JOptionPane.showMessageDialog(this, "Enter valid id!");}
    	}
    
		boolean bool2 = true;
		while (bool2) {
			name = JOptionPane.showInputDialog(this,"Enter receiver's name:");
			if (name.matches("[^0-9]*") && !name.matches(".*[\\p{Punct}].*") && name.matches("[\\S].*") ){
				bool2 = false;}
			else{JOptionPane.showMessageDialog(this, "Enter valid name!");}
    	  }
	    phone = JOptionPane.showInputDialog(this,"Enter receiver's phone number:");
    	adress = JOptionPane.showInputDialog(this,"Enter receiver's adress");
    	ReceiverCustomer receivercustomer = new ReceiverCustomer(name,id,phone,adress);
    	
    	return receivercustomer;	
    }
    public String takeIDofSender() {
    	String id = ""; 
    	boolean bool1 = true;
    	while (bool1) {
			id = JOptionPane.showInputDialog(this,"Enter sender's id:");
			if (id.matches("[^0-9]*") && !id.matches(".*[\\p{Punct}].*") && id.matches("[\\S].*") ){
				bool1 = false;}
			else{JOptionPane.showMessageDialog(this, "Enter valid id!");}
    	}
    	return id;
    	}	
    public SenderCustomer takeSender(String id) {
    	SenderCustomer sender_customer = null;
    	for(SenderCustomer sender : company.getSenders()) {
    		if(id.equalsIgnoreCase(sender.getId())) {
    			sender_customer = sender;
    		}
    	}
    	return sender_customer;
    }
}




