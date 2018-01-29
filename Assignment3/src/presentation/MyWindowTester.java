package presentation;

import javax.swing.JFrame;

import domain.Company;
import fileAccess.DataAccessLayer;

public class MyWindowTester {
    public static void main(String[] args) {
    	Company c1 = new Company();
		DataAccessLayer d1 = new DataAccessLayer();
		d1.DataAccess(c1);
    	MyWindow w = new MyWindow(c1);
    	JFrame frame = new JFrame("CargoApp");
		
		frame.add(w);
		frame.setResizable(false);
		frame.setBounds(100, 100, 500, 400);
		frame.setLocation(250,250);
		frame.setVisible(true);	}
}
