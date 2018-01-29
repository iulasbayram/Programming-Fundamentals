/**
 * @author Hasan Yeniada - Ismail Ulas Bayram , 220201024 - 220201040
 */
package domain;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class DataAccessLayer {
	
	public void dataAccessLayer(Player human , Player computer){
		PrintWriter writer;
		try {
			writer = new PrintWriter(new FileOutputStream(human.getName()+"_"+computer.getName()+".txt"));
			writer.println(human.getName()+": " +human.getScore());
			writer.println(computer.getName()+": "+computer.getScore());
			writer.close();
		} catch (IOException e) {
			System.out.println("File is not written by dataAccessLayer method " + e.getMessage());
		}
		
	}
}
