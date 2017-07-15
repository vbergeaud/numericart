package engine;

import ui.StyleSheet;



public class ImageAlgoFactory {
	public static AImageAlgo make(AImageAlgo.AlgoType type)
	{
		switch (type)
		{
		case Julia:
			JuliaFractalAlgoModel jfam = new JuliaFractalAlgoModel(-3.,3.,-3.,3.,StyleSheet.SCREENVIEW_WIDTH,StyleSheet.TOTAL_HEIGHT,1.,1.,256);
			return  new JuliaFractalAlgo(jfam);
			
		case MultiLine :
			MultiLineAlgoModel mlam=new MultiLineAlgoModel(-3.,3.,-3.,3.,StyleSheet.SCREENVIEW_WIDTH,StyleSheet.TOTAL_HEIGHT);
;			return new MultiLineAlgo(mlam);
			
		case MozarabBlender :
			MozarabBlenderAlgoModel mbam=new MozarabBlenderAlgoModel(-3,3,-3,3,StyleSheet.SCREENVIEW_WIDTH,StyleSheet.TOTAL_HEIGHT,
					"C:/Users/Bergeaud/workspace/Numericart/resources/example.jpg",3,0.8);
			return new MozarabBlenderAlgo(mbam);
			
		default:
			return null;	
		}

	}
}
