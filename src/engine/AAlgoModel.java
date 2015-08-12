package engine;



public abstract class AAlgoModel extends AObservable {
	int _nx, _ny;
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
	}
	
	AAlgoModel(AAlgoModel model)
	{
		_nx=model._nx;
		_ny=model._ny;
		_xmax=model._xmax;
		_xmin=model._xmin;
		_ymin=model._ymin;
		_ymax=model._ymax;
	}
	public double getXmax(){return _xmax;}
	
	public void zoom(int notches, double lx, double ly)
	{
		double zoom=((double) (100+5*notches))/100.;
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
