package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;

import engine.AAlgoModel;
import engine.MozarabBlenderAlgoModel;
import engine.MozarabBlenderPanelController;
import engine.MultiLineAlgoModel;
import engine.Observer;

public class MozarabPanel extends AParameterPanel implements Observer{

	MozarabBlenderPanelController _mbpc;
	JTextField jt1 = new JTextField();
	JTextField jt2= new JTextField();
	JButton jb3=new JButton("Image File ");
	
	
	MozarabPanel(MozarabBlenderAlgoModel model, MozarabBlenderPanelController mbpc) {
		super(model);
		_mbpc=mbpc;
		initPanel();
	}

	private  void initPanel()
	{
		Box rootbox=Box.createVerticalBox();
		Box b1= Box.createHorizontalBox();	
		b1.add(new JLabel("Dimension "));
		b1.add(jt1);
		jt1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				_mbpc.setDimension(Integer.getInteger(jt1.getText()));
				
			}
		});
		rootbox.add(b1);
		Box b2=Box.createHorizontalBox();
		b2.add(new JLabel("Ratio "));
		b2.add(jt2);
		jt2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				_mbpc.setRatio(Double.parseDouble(jt2.getText()));
				
			}
		});
		rootbox.add(b2);
		Box b3=Box.createHorizontalBox();
		b3.add(jb3);
		jb3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//Create a file chooser
				final JFileChooser fc = new JFileChooser();
			
				//In response to a button click:
				int returnVal = fc.showOpenDialog(MozarabPanel.this);
				
				File file=fc.getSelectedFile();
				
				
				_mbpc.setFile(file);
				
			}
			
		});
		rootbox.add(b3);
		this.setPreferredSize(new Dimension(StyleSheet.PANEL_WIDTH,StyleSheet.CONTROL_HEIGHT));
		this.add(rootbox);
		this.setVisible(true);
	}
		
	public void update(Object obj) {
		String command =(String)obj;
		MozarabBlenderAlgoModel mbam=(MozarabBlenderAlgoModel)_model;
		if (command.equals("ratio"))
		{
			jt2.setText(Double.toString(mbam.getRatio()));
			jt2.repaint();
	
		}
		if (command.equals("dimension"))
		{
		jt1.setText(Integer.toString(mbam.getDimension()));
		jt1.repaint();
		}
		repaint();
		// TODO Auto-generated method stub
		
	}

}
