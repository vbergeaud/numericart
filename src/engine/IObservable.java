package engine;


public interface IObservable 
{

	public  void addObserver(Observer obs);

	public  void removeObserver();

	public  void notifyObserver(Object obj);
}
