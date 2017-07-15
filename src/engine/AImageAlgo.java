package engine;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

public abstract class AImageAlgo {

	AAlgoModel _model;
	String _name;
	
	public enum AlgoType {Julia,MultiLine,MozarabBlender;}

	 protected AImageAlgo(AAlgoModel model) {
		_model=model;
		}
	 
	 public AAlgoModel getModel() {
		 return _model;
	 }

	boolean checkDimensionsAreOK()
	{
		if (_model._nx<2) return false;
		if (_model._ny<2) return false;
		return true;
	}
	
	public abstract AlgoType getType();
	
	public void setName(String name) {_name=name;}
	public String getName() {return _name;}
	public String toString() {return _name;}
	abstract byte[] computeIntensityArray(int precision);
	
	public BufferedImage createImage()
	{
		return createImage(false);
	}
	public BufferedImage createImage(boolean forPrint) {
		int precision;
		if (forPrint) 
			precision=_model._print_precision;
		else
			precision=1;
		int nx=_model._nx*precision;
		int ny=_model._ny*precision;
		byte[] image_intensity=  this.computeIntensityArray(precision);
		if (image_intensity==null) return null;
		byte[] tt=new byte[nx*ny];
		for (int ix=0;ix<nx;ix++)
			for (int iy=0;iy<ny;iy++)
				tt[ix+iy*ny]=(byte)(255-image_intensity[ix+iy*ny]);
		DataBufferByte db=new DataBufferByte(tt,nx*ny);
		int[]bandoffsets={0};
		//WritableRaster r=WritableRaster.createPackedRaster(db, _nx, _ny, 8, null);

		WritableRaster r=WritableRaster.createInterleavedRaster(db,nx,ny,nx,1,bandoffsets,null);
		BufferedImage bi=new BufferedImage(nx,ny,BufferedImage.TYPE_BYTE_GRAY);
		bi.setData(r);
		return bi;
	}


	public  void modifyZoom(int notches, double lx, double ly)
	{
		_model.zoom(notches,lx,ly);
	}
	public void shift(double lx, double ly)
	{
		_model.shift(lx,ly);
	}
	
}
