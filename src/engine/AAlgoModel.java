package engine;

import java.util.ArrayList;


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
