package game.engine.construct;

/**
 * 
 * @author sfleischer
 * @version 10/1/16
 * 
 * The Vector class behaves just like a standard mathematical vector
 */
public class Vector {

	public double x;   //x component
	public double y;   //y component
	
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @param v The other vector in the dot product
	 * @return The dot product
	 */
	public double dot (Vector v){
		return x*v.x + y*v.y;
	}
	
	/**
	 * 
	 * @param v The other vector in the cross product
	 * @return The magnitude of the cross product
	 */
	public double cross (Vector v){
		double theta = this.angle() - v.angle();
		return Math.abs(this.magnitude() * v.magnitude() * Math.sin(theta));
	}
	
	/**
	 * @return The magnitude of the vector
	 */
	public double magnitude (){
		return Math.sqrt(x*x + y*y);
	}
	
	/**
	 * @return The angle of the vector from the positive x axis to
	 * the vector
	 */
	public double angle(){
		return Math.atan2(y, x);
	}
	
	/**
	 * 
	 * @param v The vector one wishes to subtract with
	 * @return A new vector of the subtraction
	 */
	public Vector subtract(Vector v){
		return new Vector(x -v.x, y - v.y);
	}
	
	/**
	 * 
	 * @param v The vector one wishes to add with
	 * @return A new vector resulting from the addition
	 */
	public Vector add(Vector v){
		return new Vector(x + v.x, y + v.y);
	}
	
	/**
	 * A normal vector can be normalIn or normalOut
	 * If the cross product of the original vector does into the page
	 * then its a normalIn normal vector.
	 * @return A vector normal to this one.
	 */
	public Vector normalIn(){
		return new Vector(y, -x);
	}
	
	/**
	 * The cross product of the original vector and this normal one
	 * 
	 * @return A vector normal to this one
	 */
	public Vector normalOut(){
		return new Vector(-y, x);
	}
	
	/**
	 * 
	 * @param s The scalar to multiply the vector with
	 * @return A new vector with the scalar multiplied with
	 */
	public Vector scalar(double s){
		return new Vector(x*s, y*s);
	}
	
	/**
	 * Creates a unit Vector version of the vector
	 * @return A unit vector
	 */
	public Vector unit(){
		return new Vector(x/magnitude(), y/magnitude());
	}
	
	/**
	 * 
	 * @param v The vector one wishes to compare with
	 * @return True if the vectors are equal in magnitude and direction and false otherwise
	 */
	public boolean equals(Vector v){
		return x == v.x && y == v.y;
	}
}
