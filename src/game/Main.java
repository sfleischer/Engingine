package game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

	public static void main(String args[]){
		JFrame frame = new JFrame("Engingine");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new StubPanel());
		frame.setMinimumSize(new Dimension(500,500));
		frame.setVisible(true);
	}
}
