package game.engine.construct;


import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;

public class Box extends Body{

	private Vector corner1;
	private Vector corner2;
	private Vector corner3;
	private Vector corner4;
	private double width;
	private double height;
	
	/*
	 * Corners of the box are arranged like the following
	 * 
	 *   corner1 *------------* corner2
	 *           |            |
	 *           |            |
	 *           |            |
	 *           |            |
	 *           |            |
	 *   corner4 *------------* corner3
	 *   
	 *   note that the box can rotate and the corners are not tied to their orientation
	 */
	
	/**
	 * 
	 * @param m The mass of the box
	 * @param x The x position of the center of the box
	 * @param y The y position of the center of the box
	 * @param vx The x velocity of the box
	 * @param vy The y velocity of the box
	 * @param h The height of the Box
	 * @param w The width of the Box
	 */
	public Box(double m, double x, double y, double vx, double vy, double h, double w, Color c){
		super(m, x, y, vx, vy, c);
		width = w;
		height = h;
		corner1 = position.subtract(new Vector(x - w/2, y - h/2));
		corner2 = position.subtract(new Vector(x + w/2, y - h/2));
		corner3 = position.subtract(new Vector(x + w/2, y + h/2));
		corner4 = position.subtract(new Vector(x - w/2, y + h/2));
	}
	/**
	 * All bodies must be able to draw
	 * @param g2 Graphics2D object from the Scene JPanel
	 */
	public void draw (Graphics2D g2){
		//no rotations yet
		g2.setColor(color);
		g2.fill(new Rectangle2D.Double(position.x-width/2, position.y-height/2, width, height));
	}
	
	/**
	 * All bodies must be able to return the normal to the surface that a direction 
	 * vector is pointing to.
	 * @param direction The vector that is pointing to a specific surface
	 * @return The vector that is normal to that surface
	 */
	public Vector normalToSurface (Vector direction){
		Vector reverse = new Vector(-direction.x, -direction.y);
		double angle = reverse.angle();
		if(corner1.angle() < angle && angle <= corner2.angle())
			return corner2.subtract(corner1).normalOut();
		else if(corner2.angle() < angle && angle <= corner3.angle())
			return corner3.subtract(corner2).normalOut();
		else if(corner3.angle() < angle && angle <= corner4.angle())
			return corner4.subtract(corner3).normalOut();
		else 
			return corner1.subtract(corner4).normalOut();
	}
	
	public void checkCollisionWithWall(int xwall, int ywall){
		if(position.x + width/2 > xwall && velocity.x > 0 || position.x - width/2 < 0 && velocity.x < 0){
			velocity.x = -velocity.x;
		}
			
		if(position.y + height/2 > ywall && velocity.y > 0){
			if(velocity.y < 5){
				velocity.y = 0;
				force = new Vector(0,0);
			}
			velocity.y = -velocity.y;
			position.y = ywall - height/2;
		}
		else if (position.y - height/2 < 0 && velocity.y < 0){
			velocity.y = -velocity.y;
		}
	}
}
