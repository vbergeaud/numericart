package engine;

import java.io.File;

public class AlgoTreeController {
	AlgoTree _at;
	
	public AlgoTreeController(AlgoTree at)
	{
		_at=at;
	}
	
	public void setCurrent(AImageAlgo aia)
	{
		for (int i=0; i<_at.size(); i++)
		{
			if (aia==_at.get(i))
				_at.setCurrentId(i);
		}
	}
	
	public void performSave(File file)
	{
		if (file==null) return;
		else _at.performSave(file);
	}
};
