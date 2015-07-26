package engine;

public class JuliaFractalAlgo extends AImageAlgo {



	public JuliaFractalAlgo(JuliaFractalAlgoModel model)	
	{
		super(model);
		_model=new JuliaFractalAlgoModel(model);
		_name="Julia";
	}
	public AlgoType getType()
	{ return AlgoType.Julia;}

	//JuliaFractalAlgoModel getModel() {return _model;}
	@Override
	int[] computeIntensityArray()
	{
		JuliaFractalAlgoModel model=(JuliaFractalAlgoModel)_model;
		System.out.println("inside computreIntensityArray()");
		System.out.println(Double.toString(model._real_c)+" "+Double.toString(model._imag_c));
		int nx=_model._nx;
		int ny=_model._ny;
		double xmin=_model._xmin;
		double ymin=_model._ymin;
		double xmax=_model._xmax;
		double ymax=_model._ymax;
		
		int[] nbiter=new int[nx*ny];
		try {
			checkDimensionsAreOK();
		}
		catch (Exception e)
		{

		}

		for (int ix=0;ix<nx;ix++)
			for (int iy=0;iy<ny;iy++)
			{
				double x= xmin+(xmax-xmin)*ix/(double)(nx-1);
				double y= ymin+(ymax-ymin)*iy/(double)(ny-1);
				double d= Math.abs(x)+Math.abs(y);
				int iter=0;
				double rap=0.;
				while (iter<model._nb_iter_max && d<1e5)
				{
					double xtemp=x;
					xtemp=x*x-y*y+model._real_c;
					y=2.*x*y+model._imag_c;
					x=xtemp;
					double d2= Math.abs(x)+Math.abs(y);
					rap=d2/d;
					d=d2;
					iter++;
				}
				nbiter[ix+iy*nx]=(int)(rap/45.);
			}

		return nbiter;
	}
}
