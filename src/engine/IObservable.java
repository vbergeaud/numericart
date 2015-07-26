package engine;

import java.util.ArrayList;

public interface IObservable 
{

	public  void addObserver(Observer obs);

	public  void removeObserver();

	public  void notifyObserver(Object obj);
}
