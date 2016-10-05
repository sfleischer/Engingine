package game.engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import game.engine.construct.*;
import game.engine.detection.narrow.Hitbox;
import game.engine.resolution.Resolution;


public class Engine {

	ArrayList<Body> bodies = new ArrayList<Body>();
	
	static int width;
	static int height;
	static Vector gravity = new Vector(0, 500/12.5*10);

	public Engine(){
		bodies.add(new Circle(1, 50, 50, 30, 0, 10, Color.red));
		bodies.add(new Circle(1, 200, 200, -50, -50, 10, Color.blue));
		bodies.add(new Circle(1, 200, 160, -50, -20, 10, Color.green));
		bodies.add(new Circle(1, 190, 300, 1000, -20, 10, Color.magenta));
	}
	
	public void draw(Graphics2D g2){
		for(Body b : bodies){
			b.draw(g2);
		}
	}
	
	public void detectCollisions(){
		for(int i = 0; i < bodies.size(); i++){
			for(int j = i+1; j < bodies.size(); j++){
				if( Hitbox.checkCollision(bodies.get(i), bodies.get(j)) ){
					Resolution.resolveCollision(bodies.get(i), bodies.get(j));
					//System.out.println("hit");
				}
			}
		}
	}
	
	public void updateSize(int w, int h){
		width = w;
		height = h;
	}
	
	public void move(double dt){
		detectCollisions();
		for(Body b : bodies){
			b.checkCollisionWithWall(width, height);
			b.setForce(gravity);
			b.move(dt);
		}
	}
	
	
}
