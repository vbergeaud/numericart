package engine;

import java.util.ArrayList;


public class MultiLineAlgoModel extends AAlgoModel {

	ArrayList<Line> _lines;
	private int _current_line;
	double _spacing;
	
	public MultiLineAlgoModel(double xmin, double xmax, double ymin, double ymax, int nx, int ny)
	{
		super(nx,ny,xmax,ymax,xmin,ymin);
		_lines=new ArrayList<Line>();
		_lines.add(new Line(0.0,0.0,0.0));
		set_current_line(0);
		_spacing=1.0;
	}
	public ArrayList<Line> getLines() {return _lines;}
	public void setCurrentLine(int i)
	{
		set_current_line(i);
	}
	public void deleteLine(int i)
	{
		_lines.remove(i);
		notifyObserver("go");
	}
	public void setAngle(double a)
	{
		_lines.get(get_current_line()).setAngle(a);
		notifyObserver("go");
	}
	public void setSpacing(double s)
	{
		_spacing=s;
		notifyObserver("go");
	}
	public Line getCurrentLine()
	{
		return _lines.get(get_current_line());
	}
	public void addNewLine()
	{
		_lines.add(new Line(0.0,0.0,0.0));
		set_current_line(_lines.size()-1);
		notifyObserver("newLine");
	}
	public double getSpacing() {
		return _spacing;
	}
	public int get_current_line() {
		return _current_line;
	}
	public void set_current_line(int _current_line) {
		this._current_line = _current_line;
	}

}
