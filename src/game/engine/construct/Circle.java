package game.engine.construct;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.*;

public class Circle extends Body {
	
	private double radius;
	
	/**
	 * 
	 * @param m The mass of the circle
	 * @param x The x position of the center of the circle
	 * @param y The y position of the center of the circle
	 * @param vx The x velocity of the circle 
	 * @param vy The y velocity of the circle
	 * @param r The radius of the circle
	 */
	public Circle(double m, double x, double y, double vx, double vy, double r, Color c){
		super(m, x, y, vx, vy, c);
		radius = r;
	}

	@Override
	public void draw(Graphics2D g2) {
		//g2.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));
		//g2.fill(new Ellipse2D.Double(position.x - radius, position.y - radius, radius*2, radius*2));
		g2.setColor(color);
		g2.fill(new Ellipse2D.Double(position.x - radius, position.y - radius, radius*2, radius*2));
	}

	@Override
	public Vector normalToSurface(Vector direction) {
		return new Vector(-direction.x, -direction.y);
	}
	
	@Override
	public void checkCollisionWithWall(int xwall, int ywall){
		if(position.x + radius > xwall && velocity.x > 0 || position.x - radius < 0 && velocity.x < 0){
			velocity.x = -velocity.x;
		}
			
		if(position.y + radius > ywall && velocity.y > 0){
			if(velocity.y < 5){
				velocity.y = 0;
				force = new Vector(0,0);
			}
			velocity.y = -velocity.y;
			position.y = ywall - radius;
		}
		else if (position.y - radius < 0 && velocity.y < 0){
			velocity.y = -velocity.y;
		}
	}
	
	//getter and setter methods
	
	public double getRadius(){
		return radius;
	}

}
