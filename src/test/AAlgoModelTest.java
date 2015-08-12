package test;
//package engine;
import static org.junit.Assert.*;

import org.junit.Test;

import engine.AAlgoModel;
import engine.JuliaFractalAlgo;
import engine.JuliaFractalAlgoModel;

public class AAlgoModelTest {

	@Test
	public void testZoom() {
		AAlgoModel am=new JuliaFractalAlgoModel(0, 1, 0, 1, 100, 100, 0, 0, 10);
		am.zoom(10,0.5,0.5);
		assertEquals(am.getXmax(),1.25,0.0001);
		
		
	}

}
