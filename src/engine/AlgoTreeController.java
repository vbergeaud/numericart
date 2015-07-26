package engine;

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
};
