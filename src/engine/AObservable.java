package engine;

import java.util.ArrayList;

public abstract class AObservable implements IObservable {

	ArrayList<Observer> listObserver = new ArrayList<Observer>();


	public  void addObserver(Observer obs) {
		listObserver.add(obs);
	}

	public void removeObserver() {
		listObserver.clear();
	}

	public void notifyObserver(Object obj) {
		for (Observer obs : listObserver)
			obs.update(obj);
	}	
}
