package test;

import engine.Line;
import junit.framework.TestCase;

public class TestLine extends TestCase {
	public void testConstructor(){
  Line line = new Line (0.0,10.0,0.0);
  assertEquals(line.toString(),"Line 0.0");
	}
};
