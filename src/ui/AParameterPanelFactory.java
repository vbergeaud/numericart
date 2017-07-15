package ui;

import engine.AImageAlgo;
import engine.FractalPanelControler;
import engine.JuliaFractalAlgoModel;
import engine.MozarabBlenderAlgoModel;
import engine.MozarabBlenderPanelController;
import engine.MultiLineAlgoModel;
import engine.MultiLinePanelController;

public class AParameterPanelFactory {

	public static AParameterPanel buildAParameterPanel(AImageAlgo aia)
	{
		switch (aia.getType())
		{
			case Julia:
				JuliaFractalAlgoModel jfam=(JuliaFractalAlgoModel)aia.getModel();
				FractalCommandPanel fcp=new FractalCommandPanel(jfam, new FractalPanelControler(jfam));
				jfam.addObserver(fcp);
				return fcp;
				
			case MultiLine:
				MultiLineAlgoModel mlam=(MultiLineAlgoModel)aia.getModel();
				MultiLinePanel mlp=new MultiLinePanel(mlam, new MultiLinePanelController(mlam));
				mlam.addObserver(mlp);
				return mlp;
				
			case MozarabBlender:
				MozarabBlenderAlgoModel mbam=(MozarabBlenderAlgoModel)aia.getModel();
				MozarabPanel mp=new MozarabPanel(mbam, new MozarabBlenderPanelController(mbam));
				mbam.addObserver(mp);
				return mp;
				
			  default:
			    	
			    return null;
		}
		
	}
}
