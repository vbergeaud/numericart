package engine;

import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

public class JuliaFractalAlgo extends AImageAlgo {



	public JuliaFractalAlgo(JuliaFractalAlgoModel model)	
	{
		super(model);
		_model=new JuliaFractalAlgoModel(model);
		_name="Julia";
	}
	public AlgoType getType()
	{ return AlgoType.Julia;}

	private void computeIntensitySubArray(int ixmin, int ixmax, byte[] nbiter, int precision)
	{
		JuliaFractalAlgoModel model=(JuliaFractalAlgoModel)_model;
		System.out.println("inside computeIntensitySubArray()");
		System.out.println(Double.toString(model._real_c)+" "+Double.toString(model._imag_c));
		int nx=_model._nx*precision;
		int ny=_model._ny*precision;
		double xmin=_model._xmin;
		double ymin=_model._ymin;
		double xmax=_model._xmax;
		double ymax=_model._ymax;

		for (int ix=ixmin;ix<ixmax;ix++)
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
				nbiter[ix+iy*nx]=(byte)(rap/45.);
			}
	}

	//JuliaFractalAlgoModel getModel() {return _model;}
	@Override
	byte[] computeIntensityArray(int precision)
	{
		int nx=_model._nx*precision;
		int ny=_model._ny*precision;

		final byte[] nbiter=new byte[nx*ny];
		try {
			checkDimensionsAreOK();
		}
		catch (Exception e)
		{

		}
		class subArrayComputer implements Runnable{
			int _ixmin, _ixmax;
			subArrayComputer(int ixmin, int ixmax)
			{
				_ixmin=ixmin;
				_ixmax=ixmax;
			}
			@Override
			public void run() {
				computeIntensitySubArray(_ixmin,_ixmax,nbiter,precision);	
			}

		};
		int nbt=4;
		String maint=Thread.currentThread().getName();
		Thread[] mythreads=  new Thread[nbt];
		for (int i=0; i<nbt; i++)
		{
			mythreads[i]=new Thread(new subArrayComputer ((i*nx)/nbt,((i+1)*nx)/nbt));
			mythreads[i].start();
		}
		//Thread t1=new Thread(new subArrayComputer(0,nx/2));
		//Thread t2=new Thread(new subArrayComputer(nx/2,nx));

		//		computeIntensitySubArray(0,nx/2,nbiter);
		//	computeIntensitySubArray(nx/2,nx,nbiter);
		//t1.start();
		//t2.start();
		if (Thread.currentThread().getName().equals(maint))
		{
			boolean finished=false;
			while (!finished)
			{
				finished=true;
				for (int i=0; i<nbt;i++)
					if (!(mythreads[i].getState().equals(Thread.State.TERMINATED)))
					{
						finished=false;
	//					System.out.println("thread "+Integer.toString(i)+" state "+mythreads[i].getState());
					}
			}
		}
		return nbiter;
	}
}
