/**
 *@author Hasan YENIADA-Ismail Ulas BAYRAM , 220201024-220201040
 */
package presentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import domain.AirConditioner;
import domain.EstateAgent;
import domain.House;

public class EstateAgentGUI implements ActionListener{
	private EstateAgent agent;

	private JFrame estateAgentFrame,orderHouseFrame, addHouseFrame, removeHouseFrame, searchHouseFrame, priceRangeFrame,
	numberOfRoomsRangeFrame, sizeRangeFrame;
	private JPanel estateAgentPanel,orderHousePanel, addHousePanel, removeHousePanel, searchHousePanel,
	priceRangePanel, numberOfRoomsRangePanel, sizeRangePanel;
	private JButton orderHouses, addHouse, removeHouse, search, addHouseOkButton, removeHouseOkButton,
	priceRangeOkButton, sizeRangeOkButton, numberOfRoomsRangeOkButton;
	private JTable table;
	private DefaultTableModel defaultTable;
	private JScrollPane scrollPane;
	private GroupLayout layout;
	private JRadioButton ascendingButton, descendingButton, yesButton, noButton, priceButton, numberOfRoomsButton, sizeButton;
	private JLabel sizeLabel, priceLabel, numberOfRoomLabel, numberOfBathLabel, airCondLabel, idLabel,
	priceMinRangeLabel, priceMaxRangeLabel, sizeMinRangeLabel, sizeMaxRangeLabel, numberOfRoomsMinRangeLabel, numberOfRoomsMaxRangeLabel;
	private JTextField sizeField, priceField, numberOfRoomField, numberOfBathField, idField, priceMinRangeField, priceMaxRangeField, 
	sizeMinRangeField, sizeMaxRangeField, numberOfRoomsMinRangeField, numberOfRoomsMaxRangeField;
	private ButtonGroup ascendDescendButtonGroup , yesNoButtonGroup, priceNumberSizeButtonGroup;
	private ArrayList<House> houseList;
	private String[][] datas;
	private	String[] columnDatas;
	
	public EstateAgentGUI(EstateAgent agent) {
		
		this.agent = agent;
		houseList = new ArrayList<House>(agent.getHouseList());	
	
	}

	public void create() { // containers are created
	    estateAgentFrame = new JFrame("Estate Agent");
		estateAgentPanel = new JPanel();		
		
		layout = new GroupLayout(estateAgentPanel);
		
		arrangeComponents(); // components are created
        arrangeLayout();     // group layout is arranged
		
        estateAgentFrame.add(estateAgentPanel); // panel is to estateAgentFrame
		estateAgentPanel.setLayout(layout);
        
	    estateAgentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		estateAgentFrame.setBounds(250, 125, 670 , 500);
	    estateAgentFrame.setVisible(true); 
	}
	
	public void arrangeComponents() {

		orderHouses = new JButton("Order Houses");
		addHouse = new JButton("Add House");
		removeHouse = new JButton("Remove House");
		search = new JButton("Search");
		
		columnDatas = agent.getColumnDatas();
        datas = agent.getDatas();	
        
		defaultTable = new DefaultTableModel(datas, columnDatas);
		table = new JTable(defaultTable);
		
		scrollPane = new JScrollPane(table);
		
		orderHouses.addActionListener(this);
		addHouse.addActionListener(this);
		removeHouse.addActionListener(this);   // ActionListener is added to every button
		search.addActionListener(this);
	}
	
	public void arrangeLayout() {
		// group layour is arranged
		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup( // in horizontal dimension,sequencial of a scroll pane and parallel group of four button is being added to a parallel group
	   			layout.createParallelGroup(Alignment.LEADING)  
	   				.addGroup(layout.createSequentialGroup()
	   					.addComponent(scrollPane)
	    				.addGroup(layout.createParallelGroup(Alignment.LEADING) 
	    					.addComponent(orderHouses)
	    					.addComponent(addHouse)
	    					.addComponent(removeHouse)
	    					.addComponent(search))));
		
		layout.setVerticalGroup( 
				layout.createParallelGroup(Alignment.LEADING)
					.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
							.addComponent(scrollPane)
	    					.addGroup(layout.createSequentialGroup()
	    						.addComponent(orderHouses)
	    						.addComponent(addHouse)
	    						.addComponent(removeHouse)
	    						.addComponent(search)))));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==orderHouses){  // after clicking buttons in estateAgentFrame, creating new frame to make associated operations
			createOrderHouseFrame();
		}
		else if(e.getSource()==addHouse){
			createAddHouseFrame();
		}
		else if(e.getSource()==removeHouse){
			createRemoveHouseFrame();
		}
		else if(e.getSource()==search){
			createSearchHouseFrame();
		}
		else if(e.getSource()==ascendingButton){  // if user choose the ascending radio button datas is arranged.
			datas = agent.orderByAscOrDesc(datas, 0);
			defaultTable.setDataVector(datas, columnDatas); // and table in ascending order is updated
			orderHouseFrame.dispose(); // orderHouseFrame is closed finally
		}
		else if(e.getSource()==descendingButton){ // the same operations are performed
			datas = agent.orderByAscOrDesc(datas, 1);
			defaultTable.setDataVector(datas, columnDatas);
			orderHouseFrame.dispose();
		}
		else if(e.getSource()==addHouseOkButton){
			agent.setHouseList(houseList); // keeping the original houseList after search operations
			if(!priceField.getText().equals("") && !sizeField.getText().equals("") && !numberOfRoomField.getText().equals("") && !numberOfBathField.getText().equals("") && (yesButton.isSelected() || noButton.isSelected()))
			{
				boolean disposeFrame = addHouse(); // if boolean equals true , fields were filled correctly,thus frame will be closed
				datas = agent.getDatas(); // table is updated
				defaultTable.setDataVector(datas, columnDatas);
				
				if(disposeFrame == true) { addHouseFrame.dispose(); agent.saveHouses(); }

			}
			else{JOptionPane.showMessageDialog(estateAgentPanel, "Please Fill the Neccessary Fields!");}
		}
		else if(e.getSource()==removeHouseOkButton){
			agent.setHouseList(houseList);  // keeping the original houseList after search operations
			if(!idField.getText().equals("")) { 
				boolean disposeFrame = removeHouse(); // if boolean equals true, given id is invalid,thus frame is not disposed
				datas = agent.getDatas();
				defaultTable.setDataVector(datas, columnDatas);
				if(disposeFrame == true ) {removeHouseFrame.dispose(); agent.saveHouses();}
			} // if boolean equals false, given id is valid, frame is disposed
			else{JOptionPane.showMessageDialog(estateAgentPanel, "Please fill the ID field.Try Again");}
		}
		// After choosing search house button , following radio buttons are shown , and after clicking them , associated frames are shown
		else if(e.getSource()==priceButton){
			createPriceRangeFrame();
			searchHouseFrame.dispose();
			
		}
		else if(e.getSource()==numberOfRoomsButton){
			createTakingNumberOfRoomsFrame();
			searchHouseFrame.dispose();
			
		}
		else if(e.getSource()==sizeButton){
			createTakingSizeRangeFrame();
			searchHouseFrame.dispose();	
		}
		// Following three methods have the same procedure, according the given range, table is updated.
		else if(e.getSource()==priceRangeOkButton){
			if(!priceMaxRangeField.getText().equals("") && !priceMinRangeField.getText().equals("")) {
				agent.setHouseList(houseList);
				boolean disposeFrame = takePriceRange(); // disposeFrame specify whether inputs are valid or not, if valid,frame is closed.
				datas = agent.getDatas();
				defaultTable.setDataVector(datas, columnDatas);
				if(disposeFrame == true) priceRangeFrame.dispose(); 
			}
			else{JOptionPane.showMessageDialog(estateAgentPanel, "Please Fill the Neccessary Fields");}
			
		}
		else if(e.getSource()==numberOfRoomsRangeOkButton){
			if(!numberOfRoomsMaxRangeField.getText().equals("") && !numberOfRoomsMinRangeField.equals("")) {
				agent.setHouseList(houseList);
				boolean disposeFrame = takeRangeForNumberOfRooms();
				datas = agent.getDatas();
				defaultTable.setDataVector(datas, columnDatas);
				if(disposeFrame == true) numberOfRoomsRangeFrame.dispose();
			}
			else{JOptionPane.showMessageDialog(estateAgentPanel, "Please Fill the Neccessary Fields");}
		}
		else if(e.getSource()==sizeRangeOkButton){
			agent.setHouseList(houseList);
			boolean disposeFrame = takeSizeRange();
			datas = agent.getDatas();
			defaultTable.setDataVector(datas, columnDatas);
			if(disposeFrame == true) sizeRangeFrame.dispose();
		}
		
	}
	// After choosing four main buttons, following methods are executed,and associated frame is created,and inputs are taken,and table is updated,finally housing file is updated
	public void createOrderHouseFrame(){
		orderHouseFrame = new JFrame();
		
		orderHousePanel= new JPanel(new GridLayout(2,1));
		
		ascendingButton = new JRadioButton("Ascending Price");
		ascendingButton.addActionListener(this);
		
		descendingButton = new JRadioButton("Descending Price");
		descendingButton.addActionListener(this);
		
		ascendDescendButtonGroup = new ButtonGroup();
		ascendDescendButtonGroup.add(ascendingButton);
		ascendDescendButtonGroup.add(descendingButton);
		
		orderHousePanel.add(ascendingButton);
		orderHousePanel.add(descendingButton);
		
		orderHouseFrame.pack();
		orderHouseFrame.setTitle("Order House Button");
		orderHouseFrame.setSize(300,100);
		orderHouseFrame.setVisible(true);
		orderHouseFrame.add(orderHousePanel);
	}
	
	public void createAddHouseFrame(){
		addHouseFrame = new JFrame("ADD HOUSE");
		addHousePanel = new JPanel(new GridLayout(12,1));
		sizeLabel = new JLabel("Size");
		priceLabel = new JLabel("Price");
		numberOfRoomLabel = new JLabel("Number of Rooms");
		numberOfBathLabel = new JLabel("Number of Bath");
		airCondLabel = new JLabel("Air Conditioner");
		sizeField = new JTextField(10);
		priceField = new JTextField(10);
		numberOfRoomField = new JTextField(10);
		numberOfBathField = new JTextField(10);
		yesButton = new JRadioButton("YES");
		noButton = new JRadioButton("NO");
		yesNoButtonGroup = new ButtonGroup();
		yesNoButtonGroup.add(yesButton);
		yesNoButtonGroup.add(noButton);
		addHouseOkButton = new JButton("OK");
		
		addHousePanel.add(priceLabel);
		addHousePanel.add(priceField);
		addHousePanel.add(sizeLabel);
		addHousePanel.add(sizeField);
		addHousePanel.add(numberOfRoomLabel);
		addHousePanel.add(numberOfRoomField);
		addHousePanel.add(numberOfBathLabel);
		addHousePanel.add(numberOfBathField);
		addHousePanel.add(airCondLabel);
		addHousePanel.add(yesButton);
		addHousePanel.add(noButton);
		addHousePanel.add(addHouseOkButton);
		addHouseOkButton.addActionListener(this);
		
		addHouseFrame.pack();
		addHouseFrame.setTitle("Add House Button");
		addHouseFrame.setSize(500,300);
		addHouseFrame.setVisible(true);
		addHouseFrame.add(addHousePanel);
		}
	public boolean addHouse(){
			boolean validInputs = true;
			String price1 = priceField.getText();
			String numberOfRooms = numberOfRoomField.getText();
			String numberOfBaths = numberOfBathField.getText();
			String size = sizeField.getText();
			
			AirConditioner airConditioner =null;
			if(yesButton.isSelected()) airConditioner = AirConditioner.YES;
			else if(noButton.isSelected()) airConditioner = AirConditioner.NO;
			
			 if( price1.matches("[0-9]*") && !price1.matches(".*[\\p{Punct}].*") && price1.matches("[\\S].*") 
					&& numberOfRooms.matches("[0-9]*") && !numberOfRooms.matches(".*[\\p{Punct}].*") && numberOfRooms.matches("[\\S].*") 
					&& numberOfBaths.matches("[0-9]*") && !numberOfBaths.matches(".*[\\p{Punct}].*") && numberOfBaths.matches("[\\S].*") 
					&& size.matches("[0-9]*") && !size.matches(".*[\\p{Punct}].*") && size.matches("[\\S].*") )
				{
					String id = Integer.toString(agent.getNewID());
					double price2 = Double.parseDouble(price1); 
					
					agent.createAndAddHouse(id, price2, size, numberOfRooms, numberOfBaths, airConditioner);

					JOptionPane.showMessageDialog(estateAgentPanel, "House whose ID: " + id + " is added succesfully");
			    }
			 else{JOptionPane.showMessageDialog(estateAgentPanel, "Please Fill The Fields CORRECTLY."); validInputs = false;}
		
		return validInputs;
	}
		
	public void createRemoveHouseFrame(){
			removeHouseFrame = new JFrame("REMOVE HOUSE");
			
			removeHousePanel = new JPanel(new GridLayout(4,1));
			idLabel = new JLabel("Please, enter the ID to remove house: ");
			idField = new JTextField(10);
			removeHouseOkButton = new JButton("OK");
			
			removeHouseOkButton.addActionListener(this);
			
			removeHousePanel.add(idLabel);
			removeHousePanel.add(idField);
			removeHousePanel.add(removeHouseOkButton);
			
			removeHouseFrame.pack();
			removeHouseFrame.setTitle("Remove House Button");
			removeHouseFrame.setSize(300,150);
			removeHouseFrame.setVisible(true);
			removeHouseFrame.add(removeHousePanel);
		}
	public boolean removeHouse(){
		String id = idField.getText();
		boolean validID = agent.removeHouseByID(id);
			if(validID == false) JOptionPane.showMessageDialog(estateAgentFrame, "Invalid ID, Try Again");
			else JOptionPane.showMessageDialog(estateAgentPanel, "House whose ID: " + id + " is removed succesfully");
		return validID;
	}
	
	public void createSearchHouseFrame(){
			searchHouseFrame = new JFrame("SEARCH HOUSE");
			searchHousePanel = new JPanel();
			
			priceButton = new JRadioButton("By price");
			numberOfRoomsButton = new JRadioButton("By number of rooms");
			sizeButton = new JRadioButton("By size");
			
			priceNumberSizeButtonGroup = new ButtonGroup();
			priceNumberSizeButtonGroup.add(priceButton);
			priceNumberSizeButtonGroup.add(numberOfRoomsButton);
			priceNumberSizeButtonGroup.add(sizeButton);
			
			searchHousePanel.add(priceButton);
			searchHousePanel.add(numberOfRoomsButton);
			searchHousePanel.add(sizeButton);
			
			priceButton.addActionListener(this);
			numberOfRoomsButton.addActionListener(this);
			sizeButton.addActionListener(this);
			
			searchHouseFrame.pack();
			searchHouseFrame.setSize(200,150);
			searchHouseFrame.setVisible(true);
			searchHouseFrame.add(searchHousePanel);
		}
	
	public void createPriceRangeFrame(){
			priceRangeFrame = new JFrame("Search For Price");
			priceRangePanel = new JPanel();
			
			priceMaxRangeLabel = new JLabel("Max Price");
			priceMinRangeLabel = new JLabel("Min Price");
			priceMinRangeField = new JTextField(10);
			priceMaxRangeField = new JTextField(10);
			priceRangeOkButton = new JButton("OK");
			
			priceRangeOkButton.addActionListener(this);
			
			priceRangePanel.add(priceMaxRangeLabel);
			priceRangePanel.add(priceMaxRangeField);
			priceRangePanel.add(priceMinRangeLabel);
			priceRangePanel.add(priceMinRangeField);
			priceRangePanel.add(priceRangeOkButton);
			
			priceRangeFrame.pack();
			priceRangeFrame.setSize(200,150);
			priceRangeFrame.setVisible(true);
			priceRangeFrame.add(priceRangePanel);
			
			
		}
	public boolean takePriceRange(){
		boolean validRange = true;
		String maxRange1 = priceMaxRangeField.getText();
		String minRange1 = priceMinRangeField.getText();
		if( maxRange1.matches("[0-9]*") && !maxRange1.matches(".*[\\p{Punct}].*") && maxRange1.matches("[\\S].*") 
				&& minRange1.matches("[0-9]*") && !minRange1.matches(".*[\\p{Punct}].*") && minRange1.matches("[\\S].*")
				&& Double.parseDouble(maxRange1) > Double.parseDouble(minRange1)) {
			
			double maxRange2 = Double.parseDouble(maxRange1);
			double minRange2 = Double.parseDouble(minRange1);
			agent.searchByPrice(maxRange2, minRange2);
		}
		else{JOptionPane.showMessageDialog(estateAgentPanel, "Please Give a Valid Range!"); validRange = false;}
		
		return validRange;
	}
	
	public void createTakingNumberOfRoomsFrame(){
			numberOfRoomsRangeFrame = new JFrame("Search For Number Of Rooms");
			numberOfRoomsRangePanel = new JPanel();
			
			numberOfRoomsMaxRangeLabel = new JLabel("Max Number Of Rooms");
			numberOfRoomsMinRangeLabel = new JLabel("Min Number Of Rooms");
			numberOfRoomsMaxRangeField = new JTextField(10);
			numberOfRoomsMinRangeField = new JTextField(10);
			numberOfRoomsRangeOkButton = new JButton("OK");
			
			numberOfRoomsRangeOkButton.addActionListener(this);
			
			numberOfRoomsRangePanel.add(numberOfRoomsMaxRangeLabel);
			numberOfRoomsRangePanel.add(numberOfRoomsMaxRangeField);
			numberOfRoomsRangePanel.add(numberOfRoomsMinRangeLabel);
			numberOfRoomsRangePanel.add(numberOfRoomsMinRangeField);
			numberOfRoomsRangePanel.add(numberOfRoomsRangeOkButton);
			
			numberOfRoomsRangeFrame.pack();
			numberOfRoomsRangeFrame.setSize(300,200);
			numberOfRoomsRangeFrame.setVisible(true);
			numberOfRoomsRangeFrame.add(numberOfRoomsRangePanel);
		}
	public boolean takeRangeForNumberOfRooms(){
		boolean validRange = true;
		String maxRange1 = numberOfRoomsMaxRangeField.getText();
		String minRange1 = numberOfRoomsMinRangeField.getText();
		if( maxRange1.matches("[0-9]*") && !maxRange1.matches(".*[\\p{Punct}].*") && maxRange1.matches("[\\S].*") 
				&& minRange1.matches("[0-9]*") && !minRange1.matches(".*[\\p{Punct}].*") && minRange1.matches("[\\S].*")
				&& Double.parseDouble(maxRange1) > Double.parseDouble(minRange1) )
			{
				int maxRange2 = Integer.parseInt(maxRange1);
				int minRange2 = Integer.parseInt(minRange1);
				agent.searchByNumberOfRooms(maxRange2, minRange2);
			}
		else{JOptionPane.showMessageDialog(estateAgentPanel, "Please Give a Valid Range"); validRange = false;}
		
		return validRange;
	}

	public void createTakingSizeRangeFrame(){
			sizeRangeFrame = new JFrame("Search For Size");
			sizeRangePanel = new JPanel();
			sizeMaxRangeLabel = new JLabel("Max Size Range");
			sizeMinRangeLabel = new JLabel("Min Size Range");
			sizeMaxRangeField = new JTextField(10);
			sizeMinRangeField = new JTextField(10);
			sizeRangeOkButton = new JButton("OK");
			sizeRangeOkButton.addActionListener(this);
			sizeRangePanel.add(sizeMaxRangeLabel);
			sizeRangePanel.add(sizeMaxRangeField);
			sizeRangePanel.add(sizeMinRangeLabel);
			sizeRangePanel.add(sizeMinRangeField);
			sizeRangePanel.add(sizeRangeOkButton);
			sizeRangeFrame.pack();
			sizeRangeFrame.setTitle("Size Range Button");
			sizeRangeFrame.setSize(300,200);
			sizeRangeFrame.setVisible(true);
			sizeRangeFrame.add(sizeRangePanel);
		}
	public boolean takeSizeRange(){
		boolean validRange = true;
		String maxRange1 = sizeMaxRangeField.getText();
		String minRange1 = sizeMinRangeField.getText();
		if( maxRange1.matches("[0-9]*") && !maxRange1.matches(".*[\\p{Punct}].*") && maxRange1.matches("[\\S].*") 
				&& minRange1.matches("[0-9]*") && !minRange1.matches(".*[\\p{Punct}].*") && minRange1.matches("[\\S].*")
				&& Integer.parseInt(maxRange1) > Integer.parseInt(minRange1)) {
			
			int maxRange2 = Integer.parseInt(maxRange1);
			int minRange2 = Integer.parseInt(minRange1);
			agent.searchBySize(maxRange2, minRange2);
		}
		else{JOptionPane.showMessageDialog(estateAgentPanel, "Please Give a Valid Range!"); validRange = false;}
		
		return validRange;
	}
	
	public boolean validID() {
		String id = idField.getText();
		boolean invalidID = true;
		if(id.matches("[0-9]*") && !id.matches(".*[\\p{Punct}].*") && id.matches("[\\S].*")) invalidID = false;
		return invalidID;
	}
}
