package game.engine.resolution;

import game.engine.construct.*;

public class Resolution {

	public static void resolveCollision (Body A, Body B) {
		
		Vector rv = B.velocity.subtract(A.velocity);
		//Vector AtoB = B.position.subtract(B.position);
		Vector BtoA = A.position.subtract(B.position);
		
		Vector n = A.normalToSurface(BtoA).unit();
		
		//find the relative velocity along the normal
		double velAlongNormal = rv.dot(n);
		
		//if the objects are moving apart then don't resolve
		if(velAlongNormal > 0){
			return;
		}
		
		//get the lowest restitution between the two objects
		double e = Math.min(A.getRestitution(), B.getRestitution());
		
		double j = -(1 + e)*velAlongNormal;
		j = j/(A.inv_mass + B.inv_mass);
		
		Vector impulse = n.scalar(j);
		A.velocity = A.velocity.subtract(impulse.scalar(A.inv_mass));
		B.velocity = B.velocity.add(impulse.scalar(B.inv_mass));
	}
	
}
