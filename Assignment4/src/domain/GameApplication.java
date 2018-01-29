/**
 * @author Hasan Yeniada - Ismail Ulas Bayram , 220201024 - 220201040
 */
package domain;

public class GameApplication {
	public static void main(String[] args) {
		Player human = new HumanPlayer();
		Player computer = new ComputerPlayer();
		Simulation simulation = new Simulation();
		simulation.startMenu(human, computer);
		
	}
}
