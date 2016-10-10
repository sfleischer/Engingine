package game.engine.detection.broad;

import game.engine.construct.*;
import java.util.ArrayList;

public interface Detectable {

	public ArrayList<Pair<Body, Body>> detect (ArrayList<Body> bodies);
}
