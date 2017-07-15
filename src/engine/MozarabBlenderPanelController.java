package engine;

import java.io.File;

public class MozarabBlenderPanelController {

	MozarabBlenderAlgoModel _mbam;
	
	public MozarabBlenderPanelController(MozarabBlenderAlgoModel mbam)
	{
		_mbam=mbam;
	}
	public void setDimension(int i)
	{
		_mbam.setDimension(i);
	}
	public void setRatio(double ratio)
	{
		_mbam.setRatio(ratio);
	}
	public void setFile(File file)
	{
		_mbam.setFile(file);
	}
}
