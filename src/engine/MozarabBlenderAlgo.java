package engine;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MozarabBlenderAlgo extends AImageAlgo {


	protected MozarabBlenderAlgo(AAlgoModel model) {
		super(model);

		// TODO Auto-generated constructor stub
	}

	@Override
	public AlgoType getType() {
		return AlgoType.MozarabBlender;
	}

	@Override
	byte[] computeIntensityArray(int precision) {
		// TODO Auto-generated method stub
		MozarabBlenderAlgoModel model=(MozarabBlenderAlgoModel)_model;
		File fileA= model.getFile();
//		File fileB= new File(model.getImageBFile());

		BufferedImage iA=null;
//		BufferedImage iB;
		try {
			 iA=ImageIO.read(fileA);
//			 iB=ImageIO.read(fileB);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedImage iB=null;
		if (iA !=null)
			iB=iA.getSubimage(0, 0, model._nx, model._ny);
		DataBufferByte mybuf=null;
		if (iB!=null)
			 mybuf=(DataBufferByte)(iB.getData().getDataBuffer());
		return makeMozarabPicture(mybuf,model);
		
	}

	private byte[] makeMozarabPicture(DataBufferByte iA, 
			MozarabBlenderAlgoModel model) {
		
		return swapSquares(iA,model);
		
	}

	private byte[] swapSquares(DataBufferByte iA, MozarabBlenderAlgoModel model) {
		int n=model.getPattern().getDimension();
		if (iA==null) return null;
		byte[] target=iA.getData();
		byte[] source=iA.getData();
		for (int i=0; i< n*n; i++)
		{
			int j=model.getPattern().getSquareOrdering(i);
			int ixfrom=i%n;
			int iyfrom=i/n;
			int ixto=j%n;
			int iyto=j/n;
			int large_square_size=(model._nx/n);
			int square_size=(int) (large_square_size*model.getRatio());
			int square_offset=(large_square_size-square_size)/2;
			int ixbegin=ixfrom*large_square_size+square_offset;
			int iybegin=iyfrom*large_square_size+square_offset;
			int ix2begin=ixto*large_square_size+square_offset;
			int iy2begin=iyto*large_square_size+square_offset;
			for (int ix=0;ix<square_size;ix++)
				for (int iy=0; iy<square_size;iy++)
					target[ix2begin+ix+model._nx*(iy2begin+iy)]=
					source[ixbegin+ix+model._nx*(iybegin+iy)];
		}
		return target;
			
		
	}

	
}
