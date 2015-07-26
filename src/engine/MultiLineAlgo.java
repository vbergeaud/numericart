package engine;

import java.util.ArrayList;

import engine.AImageAlgo.AlgoType;

public class MultiLineAlgo extends AImageAlgo {


	MultiLineAlgoModel _model;
	protected MultiLineAlgo(MultiLineAlgoModel mlam) {
		super(mlam);
		_model=mlam;
		_name="MultiLine";
	}
	public AlgoType getType()
	{return AlgoType.MultiLine;}

	@Override
	int[] computeIntensityArray(){
		int nx=_model._nx;
		int ny=_model._ny;
		double xmin=_model._xmin;
		double ymin=_model._ymin;
		double xmax=_model._xmax;
		double ymax=_model._ymax;
		int[] nbiter=new int[nx*ny];
		for (int i=0;i<nx*ny;i++)
			nbiter[i]=255;
		//double angle=0.0;
		double x0=0.0;
		double y0=0.0;
		double spacing=_model.getSpacing();
		for (int iline=0; iline<_model.getLines().size(); iline++)
		{
			int nbsteps=(int) ((xmax-xmin)/2.0/spacing)*2;
			Line line=_model.getLines().get(iline);
			double angle=line.getAngle()/180.*3.1415926535189;
			double ac=Math.cos(angle);
			double as=Math.sin(angle);
			double at=Math.tan(angle);
			for (int istep=-nbsteps; istep<nbsteps+1;istep++)
			{
				if (at<1.0 && at>-1)
				{
				x0=0;
				y0=Math.sqrt(spacing*spacing/(1-as*as))*(double)istep;
				for (int ix=0;ix<nx;ix++)
				{
					double x= xmin+(xmax-xmin)*(double)(ix)/nx;
					double y=y0+x*at;
					int iy=(int) Math.floor((y-ymin)/(ymax-ymin)*ny);
					if (iy*nx+ix-1>=0 && iy*nx+ix+1<nx*ny && iy>=0 && iy<ny)
					{
						nbiter[iy*nx+ix]=0;
						nbiter[iy*nx+ix-1]=128;
						nbiter[iy*ny+ix+1]=128;
					}
				}
				}
				else
				{
					y0=0;
					x0=Math.sqrt(spacing*spacing/(1-ac*ac))*(double)istep;
					for (int iy=0;iy<ny;iy++)
					{
						double y= ymin+(ymax-ymin)*(double)(iy)/ny;
						double x=x0+y*(1/at);
						int ix=(int) Math.floor((x-xmin)/(xmax-xmin)*nx);
						if ((iy-1)*nx+ix>=0 && (iy+1)*nx+ix<nx*ny && ix>=0 && ix<nx)
						{
							nbiter[iy*nx+ix]=0;
							nbiter[(iy-1)*nx+ix]=128;
							nbiter[(iy+1)*ny+ix]=128;
						}
					}
				}
			}
		}
		return nbiter;
	}

}
