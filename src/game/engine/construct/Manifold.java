package game.engine.construct;

public class Manifold<A, B> {
	public A object1;
	public B object2;
	public double penetration;
	public Vector normal;
	
	public Manifold(){
		
	}
	
	public Manifold(A obj1, B obj2){
		object1 = obj1;
		object2 = obj2;
	}
	
	public A getBodyA(){
		return object1;
	}
	
	public B getBodyB(){
		return object2;
	}
	
	public Vector getNormal(){
		return normal;
	}
	
	public double getPenetration(){
		return penetration;
	}
}
