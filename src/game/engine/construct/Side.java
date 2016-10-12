package game.engine.construct;

/**
 * A side represents a side of a polygon. It contains two points and can calculate
 * the vector normal to the polygon. Note, the order or point1 and point2 matter
 * to accurately calculate the normal vector. To calculate the direction of the
 * vector, put the first vector on the base of your right hand and the fingers on
 * the second vector, then naturally curl your fingers. This will point in the 
 * direction of the normal vector. A Side object also contains information
 * about the distance from the side to the center of mass (useful for collision
 * detection and penetration depth resolution).
 * 
 * @author sfleischer
 * @version 10/10/16
 */
public class Side {
	private Vector point1;
	private Vector point2;
	private Vector center;
	private double height;
	
	public Side(Vector p1, Vector p2, int h){
		point1 = p1;
		point2 = p2;
		height = h;
	}
	
	public Side(Vector p1, Vector p2, Vector c, double h){
		center = c;
		
		Vector corner1 = p1.subtract(center);
		Vector corner2 = p2.subtract(center);
		double angle1 = corner1.angle() + Math.PI;
		double angle2 = corner2.angle() + Math.PI;
		
		//adjust the ordering of point1 and point2
		if(angle2 < angle1 && angle1 - angle2 < Math.PI || angle1 < angle2 && angle2- angle1 > Math.PI){
			point1 = p1;
			point2 = p2;
		} else {
			point1 = p2;
			point2 = p1;
		}
		
		height = h;
	}
	
	/**
	 * 
	 * @return The distance between the center of the object and the side (the apothem if the object is regular)
	 */
	public double getHeight(){
		return height;
	}
	
	/**
	 * 
	 * @return The vector normal to the surface such that the vector is pointing away from the center
	 */
	public Vector getNormalVector(){
		return point2.subtract(point1).normalOut();
	}
	
	/**
	 * This method checks whether or not a direction vector is pointing at a side of an object
	 * @param dir The direction of the vector pointing toward the center of the object
	 * @return Whether or not the vector is intersecting the line of the side
	 */
	public boolean isVectorPointingAtSide(Vector dir){
		Vector corner1 = point1.subtract(center);
		Vector corner2 = point2.subtract(center);
		double angle1 = corner1.angle();
		double angle2 = corner2.angle();
		double theta = dir.reverse().angle();
		
		if(angle2 - angle1 > Math.PI){
			return theta > angle2 || theta < angle1 ;
		}
		
		return angle1 >= theta && theta >= angle2;
	}
	
	
}
