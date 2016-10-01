package game.engine.resolution;

import game.engine.construct.*;

public class Resolution {

	public static void resolveCollision (Body A, Body B) {
		
		Vector rv = A.velocity.subtract(B.velocity);
		//Vector AtoB = B.position.subtract(B.position);
		Vector BtoA = A.position.subtract(A.position);
		
		Vector n = A.normalToSurface(BtoA);
		
		//find the relative velocity along the normal
		double velAlongNormal = rv.dot(n);
		
		//if the objects are moving apart then don't resolve
		if(velAlongNormal > 0)
			return;
		
		//get the lowest restitution between the two objects
		double e = Math.min(A.getRestitution(), B.getRestitution());
		
		double j = -(1 + e)*velAlongNormal;
		j = j/(A.inv_mass + B.inv_mass);
		
		Vector impulse = n.scalar(j);
		A.velocity = A.velocity.subtract(impulse.scalar(A.inv_mass));
		B.velocity = B.velocity.subtract(impulse.scalar(B.inv_mass));
	}
}
