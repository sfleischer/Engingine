package game.engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import game.engine.construct.*;


public class Engine {

	ArrayList<Body> bodies = new ArrayList<Body>();
	
	public Engine(){
		bodies.add(new Circle(1, 50, 50, 10, 0, 10, Color.red));
		bodies.add(new Circle(1, 200, 200, -50, -50, 10, Color.blue));
	}
	
	public void draw(Graphics2D g2){
		for(Body b : bodies){
			b.draw(g2);
		}
	}
	
	public void move(double dt){
		for(Body b : bodies){
			b.move(dt);
		}
	}
	
	
}
