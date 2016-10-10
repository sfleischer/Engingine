package game.engine.detection.narrow;

import java.awt.Rectangle;
import game.engine.construct.*;

public class Hitbox {

	public static boolean checkCollision(Body A, Body B){
		if (A instanceof Circle && B instanceof Circle)
			return DetectCircleVCircle((Circle) A, (Circle) B);
		if (A instanceof Box && B instanceof Circle)
			return DetectCircleVBox((Circle) B, (Box) A);
		if (A instanceof Circle && B instanceof Box)
			return DetectCircleVBox((Circle) A, (Box) B);
		return false;
	}
	
	private static boolean DetectCircleVCircle (Circle A, Circle B){
		Vector distance = A.position.subtract(B.position);
		double d = distance.magnitude();
		double min_d = (A.getRadius() + B.getRadius());
		return d < min_d;
	}
	
	
	private static boolean DetectCircleVBox (Circle A, Box B){
		Rectangle aabb = A.getAABB();
		for(Vector p : B.getPoints()){
			if(aabb.contains(p.x, p.y))
				return true;
		}
		return false;
	}
	
	private static void correctPenetrationDepth(Manifold m){
		Body A = (Body) m.getBodyA();
		Body B = (Body) m.getBodyB();
		Vector BtoA = A.position.subtract(B.position);
		double adjustment = m.getPenetration()/2;
		A.position = A.position.add(BtoA.scalar(adjustment));
		B.position = B.position.add(BtoA.reverse().scalar(adjustment));
	}
}
