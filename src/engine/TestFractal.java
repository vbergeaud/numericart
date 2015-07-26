package engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestFractal extends JPanel {

	public void paintComponent(Graphics g) {
		FractalPictureMaker fpm=new FractalPictureMaker();
		BufferedImage bi=fpm.createImage();
		g.drawImage(bi, 1,1,this);
	}
	  
	public static void main(String[] args) {
	      JFrame frame = new JFrame();
	      frame.setContentPane(new TestFractal());
	      frame.setSize(1600, 800);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setVisible(true);
	}

}
