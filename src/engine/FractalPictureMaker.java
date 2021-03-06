package engine;


import java.awt.image.BufferedImage;



public class FractalPictureMaker extends AFractalPictureMaker implements PictureMaker {

	

	int[] _nb;
	int _nb_iter;
	
	public FractalPictureMaker()
	{
		_algo = ImageAlgoFactory.make(AImageAlgo.AlgoType.Julia);
		AlgoTree.getInstance().add(_algo); 
		createImage();
	}
	
	@Override
	public BufferedImage createImage() {

		return _algo.createImage();
	}
	// Accessors
//	public double getRealSeed(){return ((JuliaFractalAlgoModel)_algo.getModel())._real_c;}
	//public double getImagSeed(){return ((JuliaFractalAlgoModel)_algo.getModel())._imag_c;}
	void setRealSeed(double re) {((JuliaFractalAlgoModel)_algo.getModel())._real_c=re; notifyObserver("youpi!");}
	void setImagSeed(double im) {((JuliaFractalAlgoModel)_algo.getModel())._imag_c=im; notifyObserver("youpi!");}
}
