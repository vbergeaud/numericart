package engine;

public class MultiLinePanelController {

	MultiLineAlgoModel _mlam;
	
	public MultiLinePanelController(MultiLineAlgoModel ma)
	{
		_mlam=ma;
	}
	void setCurrentLine(int iline)
	{
		_mlam.setCurrentLine(iline);
	}
	public void setAngle(double a)
	{
		_mlam.setAngle(a);
	}
	public void setSpacing(double s)
	{
		_mlam.setSpacing(s);
	}
	public void addNewLine()
	{
		_mlam.addNewLine();
	}
}
