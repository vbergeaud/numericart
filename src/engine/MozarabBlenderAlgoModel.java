package engine;

import java.io.File;

public class MozarabBlenderAlgoModel extends AAlgoModel {

	private File _file;
	private MozarabPatternModel _pattern;
	private double _ratio;
	int _dimension;
//	private String _imageBFile;
	public MozarabBlenderAlgoModel(double xmin, double xmax, double ymin, double ymax, int nx, int ny, String filename, int dim, double ratio)
	{
		super(nx,ny,xmax,ymax,xmin,ymin);
		_file=new File(filename);
		_dimension =dim;
		_ratio=ratio;
		resetPattern();
		
//		_imageBFile=imageBFile;
		
	}
	
	public File getFile()
	{
		return _file;
	}
	MozarabPatternModel getPattern() {return _pattern;}
	public double getRatio(){return _ratio;}
	void setDimension(int i){_dimension=i; resetPattern();}
	void setRatio(double r){_ratio=r;resetPattern();}
	
	void resetPattern()
	{_pattern=new MozarabPatternModel(_dimension, _ratio);
	notifyObserver("done");
	}
	void setFile(File file)
	{
		_file=file;
		notifyObserver("done");
	}

	public int getDimension() {
		return _dimension;
	}
}
