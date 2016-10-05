package game.engine.construct;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
//import java.awt.geom.*;

/**
 * The wall class is an immovable surface that encloses the screen.
 * @author sfleischer
 *
 */
public class Wall extends Body {

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(color);
		g2.draw(new Rectangle2D.Double(aabb.getX(), aabb.getY(), aabb.getWidth(), aabb.getHeight()));
	}

	@Override
	public Vector normalToSurface(Vector direction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkCollisionWithWall(int xwall, int ywall) {
		// TODO Auto-generated method stub

	}

}
