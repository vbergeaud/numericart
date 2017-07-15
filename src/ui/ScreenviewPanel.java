package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import engine.FractalPictureMaker;
import engine.Observer;
import engine.AImageAlgo;

public class ScreenviewPanel extends JPanel implements Observer{

	AImageAlgo _aia;
	public ScreenviewPanel(AImageAlgo a)
	{
		_aia=a;
		super.setPreferredSize(new Dimension(StyleSheet.SCREENVIEW_WIDTH,StyleSheet.TOTAL_HEIGHT));
		this.addMouseWheelListener(new MouseWheelListener()
		{

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int notches = e.getWheelRotation();
				int xm = e.getX();
				int ym= e.getY();
				double xl=(double)xm/(double)(ScreenviewPanel.this.getWidth());
				double yl=(double)ym/(double)(ScreenviewPanel.this.getHeight());
				_aia.modifyZoom(notches,xl,yl);
			}

			
			
		});
		this.addMouseListener(new MouseListener()
		{
			int ix_before;
			int iy_before;
			boolean noshift=false;
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				noshift=false;
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				noshift=true;
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ix_before=arg0.getX();
				iy_before=arg0.getY();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (noshift) return;
				
				int ix=arg0.getX();
				int iy=arg0.getY();
				double xl=-(double)(ix-ix_before)/(double)(ScreenviewPanel.this.getWidth());
				double yl=-(double)(iy-iy_before)/(double)(ScreenviewPanel.this.getHeight());
				_aia.shift(xl,yl);
			}
			
		});
	}
	protected void paintComponent(Graphics g)
	{
		
		BufferedImage bi = _aia.createImage();
		g.drawImage(bi,1,1,this);
	}
	public void update(Object obj)
	{
		System.out.println("Paint in ScreenviewPanel");
		repaint();
	}
	
}
