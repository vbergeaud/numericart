package engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class AlgoTree extends ArrayList<AImageAlgo> implements IObservable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static AlgoTree _at=null;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	int _currentId=0;
	
	private AlgoTree(){}

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
		this.setCurrentId(size()-1);
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
public void performSave(File file)
{
	
	try {
	    // retrieve image
		
	    BufferedImage bi = getCurrentAlgo().createImage(true);
	    ImageIO.write(bi, "png", file);
	} catch (IOException e) {
		JOptionPane.showMessageDialog(null, "error writing file!");
	}
}

}
