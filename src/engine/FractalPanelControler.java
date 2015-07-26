package engine;

public class FractalPanelControler {

	JuliaFractalAlgoModel _jfam;
	
	public FractalPanelControler(JuliaFractalAlgoModel jfam)
	{
		_jfam=jfam;
	}
	
	public void controlReal(double re)
	{
		System.out.println("ControlReal"+Double.toString(re));
		if (re<-2.) _jfam.setRealSeed(-2.);
		if (re>2.) _jfam.setRealSeed(2.);
		else _jfam.setRealSeed(re);
	}
	public void controlImag(double im)
	{
		if (im<-2.) _jfam.setImagSeed(-2.);
		if (im>2.) _jfam.setImagSeed(2.);
		else _jfam.setImagSeed(im);
	}	
}
