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
		super.setPreferredSize(new Dimension(800,800));
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
