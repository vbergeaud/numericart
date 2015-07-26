package engine;

public class JuliaFractalAlgoModel extends AAlgoModel {

	double _real_c;
	double _imag_c;
	int      _nb_iter_max;
	JuliaFractalAlgoModel(double xmin, double xmax, double ymin, double ymax, int nx, int ny, double rc, double ic, int nitermax)
	{
		super(nx,ny,xmax,ymax,xmin,ymin);
		_real_c=rc;
		_imag_c=ic;
		_nb_iter_max=nitermax;
	}
	public JuliaFractalAlgoModel(JuliaFractalAlgoModel model) {
		super(model);
		_real_c=model._real_c;
		_imag_c=model._imag_c;
		_nb_iter_max=model._nb_iter_max;
	}
	void setRealSeed(double s)
	{
		_real_c=s;
		notifyObserver("done");
	}
	void setImagSeed(double s)
	{
		_imag_c=s;
		notifyObserver("done");
	}
	public double getRealSeed()
	{
		return _real_c;
	}
	public double getImagSeed()
	{
		return _imag_c;
	}

}
