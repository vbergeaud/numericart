package test;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.Line;

public class LineTest {

	Line testline=new Line(10.0,10.0,0.0);
	@Test
	public void testToString() {
		assertEquals(testline.toString(),"Line 0.0");
	}

}
