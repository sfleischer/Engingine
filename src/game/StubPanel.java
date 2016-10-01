package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.JPanel;
import javax.swing.Timer;

import game.engine.Engine;

public class StubPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final double dt = .02;
	
	Engine engine;
	Timer timer;
	
	public StubPanel(){
		super();
		engine = new Engine();
		timer = new Timer ( (int) (1/dt), this);
		timer.start();
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		engine.move(dt);
		engine.draw(g2);
	}
	
	public void actionPerformed(ActionEvent e){
		repaint();
	}
	
	
}
