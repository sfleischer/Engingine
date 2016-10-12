package game.engine.construct;

import static org.junit.Assert.*;

import org.junit.Test;

public class SideTest {
	
	private static double EPSILON = 1e-15;

	@Test
	public void test() {
		Side s = new Side(new Vector(0,1), new Vector(1,1), 5);
		Vector n = new Vector(0,1);
		Vector u = s.getNormalVector();
		assertEquals(n.x, u.x, EPSILON);
		assertEquals(n.y, u.y, EPSILON);
	}
	
	@Test
	public void test2() {
		Side s = new Side(new Vector(1,1), new Vector(0,1), 5);
		Vector n = new Vector(0,-1);
		Vector u = s.getNormalVector();
		assertEquals(n.x, u.x, EPSILON);
		assertEquals(n.y, u.y, EPSILON);
	}
	
	@Test
	public void test3() {
		Side s = new Side(new Vector(2,2), new Vector(4,4), 2);
		Vector n = new Vector(-1,1).unit();
		Vector u = s.getNormalVector().unit();
		//System.out.println("test-- " + u);
		assertEquals(n.x, u.x, EPSILON);
		assertEquals(n.y, u.y, EPSILON);
	}
	
	@Test
	public void simpleDirTest(){
		Vector dir = new Vector(1, 0);
		Side s = new Side(new Vector(0,-1), new Vector(0,1), new Vector(1,0),2);
		assertEquals(s.isVectorPointingAtSide(dir), true);
	}
	
	@Test
	public void falseDirTest(){
		Vector dir = new Vector(-1, 0);
		Side s = new Side(new Vector(0,-1), new Vector(0,1), new Vector(1,0),2);
		assertEquals(s.isVectorPointingAtSide(dir), false);
	}
	
	@Test
	public void normalDirTest(){
		Vector dir = new Vector(-1, 0);
		Side s = new Side(new Vector(0,1), new Vector(0,-1), new Vector(-1,0),2);
		assertEquals(s.isVectorPointingAtSide(dir), true);
	}
	
	@Test
	public void failingNormalDirTest(){
		Vector dir = new Vector(1, 0);
		Side s = new Side(new Vector(0,-1), new Vector(0,1), new Vector(-1,0),2);
		assertEquals(s.isVectorPointingAtSide(dir), false);
	}
	
	@Test
	public void flatNormalDirTest(){
		Vector dir = new Vector(0, -1);
		Side s = new Side(new Vector(-1,0), new Vector(1,0), new Vector(0,-5),2);
		assertEquals(s.isVectorPointingAtSide(dir), true);
	}
	
	@Test
	public void reverseFlatNormalDirTest(){
		Vector dir = new Vector(0, -1);
		Side s = new Side(new Vector(1,0), new Vector(-1,0), new Vector(0,-5),2);
		assertEquals(s.isVectorPointingAtSide(dir), true);
	}
	
	@Test
	public void reverseFalseDirTest(){
		Vector dir = new Vector(-1, 0);
		Side s = new Side(new Vector(0,1), new Vector(0,-1), new Vector(1,0),2);
		assertEquals(s.isVectorPointingAtSide(dir), false);
	}

}
