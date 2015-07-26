package engine;



public class ImageAlgoFactory {
	public static AImageAlgo make(AImageAlgo.AlgoType type)
	{
		switch (type)
		{
		case Julia:
			JuliaFractalAlgoModel jfam = new JuliaFractalAlgoModel(-3.,3.,-3.,3.,1400,1400,1.,1.,256);
			return  new JuliaFractalAlgo(jfam);
			
		case MultiLine :
			MultiLineAlgoModel mlam=new MultiLineAlgoModel(-3.,3.,-3.,3.,1400,1400);
;			return new MultiLineAlgo(mlam);
			
		default:
			return null;	
		}

	}
}
