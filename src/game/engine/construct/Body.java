package game.engine.construct;

import java.awt.Graphics2D;
import java.awt.Color;

/**
 * 
 * @author sfleischer
 * @version 10/1/16
 * 
 * The body class is a superclass of all the other constructs in the game. 
 */
public abstract class Body {

	public double mass;         //mass of the object
	public double inv_mass;     //reciprocal of the mass
	public Vector position;     //position vector
	public Vector velocity;     //velocity vector
	public Vector omega;        //angular velocity vector
	protected Vector force;       //total force on the object
	protected Color color;        //the color of the body
	protected double restitution; //the coefficient of restitution of the object
	
	public Body(double m, double x, double y, double vx, double vy, Color c){
		mass = m;
		inv_mass = 1/m;
		position = new Vector(x, y);
		velocity = new Vector(vx, vy);
		omega    = new Vector(0, 0);
		force = new Vector(0, 0);
		color = c;
		restitution = 1;
	}
	
	public Body(double m, double x, double y, double vx, double vy, Color c, double rest){
		mass = m;
		inv_mass = 1/m;
		position = new Vector(x, y);
		velocity = new Vector(vx, vy);
		omega    = new Vector(0, 0);
		force = new Vector(0, 0);
		color = c;
		restitution = rest;
	}
	
	/**
	 * Default, no args constructor for Body
	 */
	public Body(){
		mass = 1;
		inv_mass = 1;
		position = new Vector(0, 0);
		velocity = new Vector(0, 0);
		force = new Vector(0, 0);
	}
	
	/**
	 * This method moves the object using integration of its force and velocity values
	 * @param dt The small increment the object is actually moving in
	 */
	public void move (double dt) {
		velocity.x = velocity.x + force.x * dt;
		velocity.y = velocity.y + force.y * dt;
		position.x = position.x + velocity.x * dt;
		position.y = position.y + position.y * dt;
	}
	
	/**
	 * All bodies must be able to draw
	 * @param g2 Graphics2D object from the Scene JPanel
	 */
	public abstract void draw (Graphics2D g2);
	
	/**
	 * All bodies must be able to return the normal to the surface that a direction 
	 * vector is pointing to.
	 * @param direction The vector that is pointing to a specific surface
	 * @return The vector that is normal to that surface
	 */
	public abstract Vector normalToSurface (Vector direction);
	
	//getter and setter methods
	
	/**
	 * @return The restitution of the object
	 */
	public double getRestitution(){
		return restitution;
	}
	
	/**
	 * @param f The total force on the object
	 */
	public void setForce(Vector f){
		force = f;
	}
}
