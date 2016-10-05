package game.engine.detection.broad;

import java.util.ArrayList;

import game.engine.construct.Body;
import game.engine.construct.Pair;

public class BruteForce implements Detectable{
	
	public ArrayList<Pair<Body, Body>> detect (ArrayList<Body> bodies){
		
		ArrayList<Pair<Body, Body>> pairs = new ArrayList<>();
		for(int i = 0; i < bodies.size(); i++){
			for(int j = i+1; j < bodies.size(); j++){
				pairs.add(new Pair<Body, Body>(bodies.get(i), bodies.get(j)));
			}
		}
		return pairs;
	}
}
