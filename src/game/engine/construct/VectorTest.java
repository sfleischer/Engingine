package game.engine.construct;

import static org.junit.Assert.*;

import org.junit.Test;

public class VectorTest {
	
	private static final double EPSILON = 1e-15;

	@Test
	public void perpendicularDotTest() {
		Vector v = new Vector(1,0);
		Vector u = new Vector(0,1);
		assertEquals(v.dot(u), 0, EPSILON);
	}
	
	@Test
	public void standardDotTest() {
		Vector v = new Vector(1,1);
		Vector u = new Vector(1,1);
		assertEquals(v.dot(u), 2, EPSILON);
	}
	
	@Test
	public void perpendicularCrossTest() {
		Vector v = new Vector(1,0);
		Vector u = new Vector(0,1);
		assertEquals(v.cross(u), 1, EPSILON);
	}
	
	@Test
	public void parallelCrossTest() {
		Vector v = new Vector(1,1);
		Vector u = new Vector(1,1);
		assertEquals(v.cross(u), 0, EPSILON);
	}
	
	@Test
	public void zeroMagnitudeTest() {
		Vector v = new Vector(0,0);
		assertEquals(v.magnitude(), 0, EPSILON);
	}
	
	@Test
	public void integerMagnitudeTest() {
		Vector v = new Vector(5,0);
		assertEquals(v.magnitude(), 5, EPSILON);
	}
	
	@Test
	public void standardMagnitudeTest() {
		Vector v = new Vector(5,4);
		assertEquals(v.magnitude(), Math.sqrt(41), EPSILON);
	}
	
	@Test
	public void perpendicularAngleTest() {
		Vector v = new Vector(0,1);
		assertEquals(v.angle(), Math.PI/2, EPSILON);
	}
	
	@Test
	public void Q2AngleTest() {
		Vector v = new Vector(-1,1);
		assertEquals(v.angle(), 3*Math.PI/4, EPSILON);
	}
	
	@Test
	public void zeroAngleTest() {
		Vector v = new Vector(20,0);
		assertEquals(v.angle(), 0, EPSILON);
	}
	
	@Test
	public void Q3AngleTest() {
		Vector v = new Vector(-3,-3);
		assertEquals(v.angle(), -1*3*Math.PI/4, EPSILON);
	}
	
	@Test
	public void subtractionTest() {
		Vector v = new Vector(10,10);
		Vector u = new Vector(-1, 9);
		Vector sub = v.subtract(u);
		assertEquals(sub.x, 11, EPSILON);
		assertEquals(sub.y, 1, EPSILON);
	}
	
	@Test
	public void additionTest() {
		Vector v = new Vector(10,10);
		Vector u = new Vector(-1, 9);
		Vector a = v.add(u);
		assertEquals(a.x, 9, EPSILON);
		assertEquals(a.y, 19, EPSILON);
	}
	

}
