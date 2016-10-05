package game.engine.construct;

/**
 * This is a generic Pair class
 * @author sfleischer
 * @version 10/5/16
 *
 * @param <A> The first object in the pair
 * @param <B> The second object in the pair
 */
public class Pair<A, B> {
	
	private A body1;
	private B body2;
	
	public Pair(A b1, B b2){
		body1 = b1;
		body2 = b2;
	}
	
	public A getFirst(){
		return body1;
	}
	
	public B getSecond(){
		return body2;
	}
}
