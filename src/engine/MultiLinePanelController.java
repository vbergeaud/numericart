package engine;

public class MultiLinePanelController {

	MultiLineAlgoModel _mlam;
	
	public MultiLinePanelController(MultiLineAlgoModel ma)
	{
		_mlam=ma;
	}
	public void setCurrentLine(int iline)
	{
		_mlam.setCurrentLine(iline);
	}
	public void deleteCurrentLine()
	{
		if (_mlam.getLines().size()==1) return;
		int cl = _mlam.get_current_line();
		if (cl==0) _mlam.set_current_line(1);
		else _mlam.set_current_line(cl-1);
		_mlam.deleteLine(cl);
			
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
