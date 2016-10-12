package game.engine.construct;


import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.geom.Rectangle2D;
import java.awt.Color;

public class Box extends Body{

	private Vector corner1;
	private Vector corner2;
	private Vector corner3;
	private Vector corner4;
	private double width;
	private double height;
	private ArrayList<Vector> corners; //stores the corners (vector relative to the center)
	private ArrayList<Vector> points;  //stores the points (vector relative to the origin)
	private ArrayList<Side>   sides;   //stores the sides of the box
	
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
	 *   the ArrayList corners is a list that contains all of these corners
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
	public Box(double m, double x, double y, double vx, double vy, double w, double h, Color c){
		super(m, x, y, vx, vy, c);
		width = w;
		height = h;
		
		points  = new ArrayList<Vector>();
		corners = new ArrayList<Vector>();
		sides   = new ArrayList<Side>();
		
		updatePoints();
	}
	
	public void updatePoints(){
		points.clear();
		corners.clear();
		
		points.add(new Vector(position.x - width/2, position.y - height/2));
		points.add(new Vector(position.x + width/2, position.y - height/2));
		points.add(new Vector(position.x + width/2, position.y + height/2));
		points.add(new Vector(position.x - width/2, position.y + height/2));
		
		corner1 = position.subtract(points.get(0));
		corner2 = position.subtract(points.get(1));
		corner3 = position.subtract(points.get(2));
		corner4 = position.subtract(points.get(3));
		
		corners.add(corner1);
		corners.add(corner2);
		corners.add(corner3);
		corners.add(corner4);
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
	 * vector is pointing to. Note: This method needs changing.
	 * @param direction The vector that is pointing to a specific surface
	 * @return The vector that is normal to that surface
	 */
	public Vector normalToSurface (Vector direction){
		Vector reverse = new Vector(-direction.x, -direction.y);
		double angle = reverse.angle();
		
		//find index of the point with the largest angle
		int index = 0;
		double max = corner1.angle();
		for(int i = 0; i < corners.size(); i++){
			if(max < corners.get(i).angle()){
				index = i;
				max = corners.get(i).angle();
			}
		}
		
		//Rotate the array so that the largest point is first. Awesome method.
		Collections.rotate(corners, -index);
		
		//this for loop covers the first three cases
		for(int i = 0; i < corners.size() - 1; i++){
			if(corners.get(i).angle() < angle && angle < corners.get(i+1).angle())
				return corners.get(i+1).subtract(corners.get(i)).normalOut();
		}
		//the last case
		return corners.get(corners.size()-1).subtract(corners.get(0)).normalOut();
	}
	
	public void checkCollisionWithWall(int xwall, int ywall){
		if(position.x + width/2 > xwall && velocity.x > 0 
				|| position.x - width/2 < 0 && velocity.x < 0){
			velocity.x = -velocity.x * restitution;
		}
			
		if(position.y + height/2 > ywall && velocity.y > 0){
			if(velocity.y < 5){
				velocity.y = 0;
				//force = new Vector(0,0);
			}
			velocity.y = -velocity.y * restitution;
			position.y = ywall - height/2;
		}
		else if (position.y - height/2 < 0 && velocity.y < 0){
			velocity.y = -velocity.y * restitution;
		}
	}
	
	/**
	 * This method moves the object using integration of its force and velocity values
	 * @param dt The small increment the object is actually moving in
	 */
	public void move (double dt) {
		velocity.x = velocity.x + force.x * dt;
		velocity.y = velocity.y + force.y * dt;
		position.x = position.x + velocity.x * dt;
		position.y = position.y + velocity.y * dt;
		updatePoints();
	}
	
	/**
	 * 
	 * @param p The point to be tested to see if it is in the box
	 * @return True or false for whether or not the point is inside the box
	 */
	public boolean contains(Vector p){
		Vector interior = p.subtract(corner1);
		return false;
	}
	
	public double getWidth(){
		return width;
	}
	
	public double getHeight(){
		return height;
	}
	
	public ArrayList<Vector> getPoints(){
		return points;
	}
	
	public ArrayList<Vector> getCorners(){
		return corners;
	}
	
}
