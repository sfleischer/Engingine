package game.engine.detection.narrow;

import game.engine.construct.*;

public class Hitbox {

	public static boolean checkCollision(Body A, Body B){
		if (A instanceof Circle && B instanceof Circle)
			return DetectCircleVCircle((Circle) A, (Circle) B);
		return false;
	}
	
	private static boolean DetectCircleVCircle (Circle A, Circle B){
		Vector distance = A.position.subtract(B.position);
		double d = distance.magnitude();
		double min_d = (A.getRadius() + B.getRadius());
		return d < min_d;
	}
	
	private static void correctPenetrationDepth(Circle A, Circle B){
		//fail("unimplemented");
	}
}
