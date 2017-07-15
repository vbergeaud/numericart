package engine;



public abstract class AAlgoModel extends AObservable {
	int _nx, _ny, _print_precision;
	double _xmax, _ymax, _xmin, _ymin;
	//private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	AAlgoModel(int nx, int ny, double xmax, double ymax, double xmin, double ymin)
	{
		_nx=nx;
		_ny=ny;
		_xmax=xmax;
		_ymax=ymax;
		_xmin=xmin;
		_ymin=ymin;
		_print_precision=4;
	}
	
	AAlgoModel(AAlgoModel model)
	{
		_nx=model._nx;
		_ny=model._ny;
		_xmax=model._xmax;
		_xmin=model._xmin;
		_ymin=model._ymin;
		_ymax=model._ymax;
		_print_precision=model._print_precision;
	}
	public double getXmax(){return _xmax;}
	public void setPrecision(int pp){_print_precision=pp;}
	public void zoom(int notches, double lx, double ly)
	{
		double zoom=((double) (100+20*notches))/100.;
		if (zoom <0.5) zoom=0.5;
		if (zoom > 2.0) zoom=2;
		double xav=_xmin+(_xmax-_xmin)*lx;
		double yav=_ymin+(_ymax-_ymin)*ly;
		_xmin=xav+(_xmin-xav)*zoom;
		_xmax=xav+(_xmax-xav)*zoom;
		_ymin=yav+(_ymin-yav)*zoom;
		_ymax=yav+(_ymax-yav)*zoom;
		this.notifyObserver("zoom");
	}

	public void shift(double lx, double ly) {
		double xav=(_xmax-_xmin)*lx;
		double yav=(_ymax-_ymin)*ly;

		_xmin+=xav;
		_xmax+=xav;
		_ymin+=yav;
		_ymax+=yav;
		this.notifyObserver("shift");
		// TODO Auto-generated method stub
		
	}

	/*public void addObserver(Observer obs)
	{
		listObserver.add(obs);
	}

	public void removeObserver()
	{
		listObserver.clear();
	}

	public void notifyObserver(String str)
	{
		for (Observer obs : listObserver)
			obs.update(str);
	}
	*/
}
