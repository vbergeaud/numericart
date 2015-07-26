package ui;

import java.awt.Dimension;
import java.awt.Graphics;
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
