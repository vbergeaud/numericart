package test;
import junit.framework.*;
import engine.Line;

public class LineTest extends TestCase{
	public void testLine() {
	Line line=new Line (10.0,10.0,0.0);
   assertEquals(line.toString(),"Line 0.0");
	}
};
