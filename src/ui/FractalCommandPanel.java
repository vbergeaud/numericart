package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import engine.FractalPanelControler;
import engine.JuliaFractalAlgoModel;
import engine.Observer;


public class FractalCommandPanel extends AParameterPanel implements Observer {

	private static final long serialVersionUID = 4264378449099658745L;
	JTextField jt1 = new JTextField();
	JTextField jt2= new JTextField();
	JuliaFractalAlgoModel _jfam;

	public FractalCommandPanel (JuliaFractalAlgoModel aia, FractalPanelControler fpc)
	{
		super(aia);
		_jfam=aia;
		Box bv= Box.createVerticalBox();
		Box bh1=Box.createHorizontalBox();
		JSlider js1=new JSlider(-40,40,0);
		js1.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e)
			{
				fpc.controlReal(js1.getValue()/20.0);
			}
		});
		JSlider js2=new JSlider(-40,40,0);
		js2.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e)
			{
				fpc.controlImag(js2.getValue()/20.0);
			}
		});

		bv.add(new JLabel("Julia set seed"));
		jt1.setPreferredSize(new Dimension(100,30));
		jt1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				fpc.controlReal(Double.parseDouble(jt1.getText()));
			}
		});
		bh1.add(jt1);

		bh1.add(new JLabel("+i"));
		jt2.setPreferredSize(new Dimension(100,30));
		jt2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				fpc.controlImag(Double.parseDouble(jt2.getText()));
			}
		});
		bh1.add(jt2);
		bv.add(Box.createRigidArea(new Dimension(10,10)));
		bv.add(bh1);
		bv.add(Box.createRigidArea(new Dimension(10,10)));
		bv.add(js1);
		bv.add(Box.createRigidArea(new Dimension(10,10)));
		bv.add(js2);
		this.setPreferredSize(new Dimension(200,800));
		this.add(bv);
		this.setVisible(true);
	}


	public void update(Object obj)
	{
		jt1.setText(new Double(_jfam.getRealSeed()).toString());
		jt2.setText(new Double(_jfam.getImagSeed()).toString());

	}
}
