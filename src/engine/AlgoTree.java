package engine;

import java.util.ArrayList;

public class AlgoTree extends ArrayList<AImageAlgo> implements IObservable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static AlgoTree _at=null;
	private AlgoTree(){}
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	int _currentId=0;

	public static AlgoTree getInstance()
	{
		if (_at==null)
			_at=new AlgoTree();
		return _at;
	}
	void delete(AImageAlgo a)
	{
		this.remove(a);
	}
	public void create(AImageAlgo a)
	{
		AImageAlgo algo=ImageAlgoFactory.make(a.getType());
		this.add(algo);
		notifyObserver(algo);
	}
	public void changeName(int index, String name)
	{
		if (index<size())
			get(index).setName(name);
		notifyObserver("changed");
	}
	public void setCurrentId(int id){_currentId=id; notifyObserver("changed");}
	public int getCurrentId(){return _currentId;}
	public AImageAlgo getCurrentAlgo()
	{
		return this.get(_currentId);
	}
	public void addObserver(Observer obs) {
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
