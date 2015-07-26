package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import engine.AAlgoModel;
import engine.Line;
import engine.MultiLineAlgoModel;
import engine.MultiLinePanelController;
import engine.Observer;



public class MultiLinePanel extends AParameterPanel implements Observer{

	JList<Line> _list;
	JButton _add_button;
	JButton _del_button;
	JTextField _angle;
	JTextField _x;
	JTextField _y;
	JSlider _angleslider;
	JSlider _spacingslider;
	MultiLinePanelController _mlpc;
	DefaultListModel<Line> _listmodel;
	MultiLinePanel(MultiLineAlgoModel model, MultiLinePanelController mlpc)
	{
		super(model);
		_mlpc=mlpc;
		initPanel();

	}
	private void initPanel()
	{
		MultiLineAlgoModel mlam=(MultiLineAlgoModel)_model;
		_listmodel=new DefaultListModel<Line>();
		for (int i=0; i< mlam.getLines().size();i++)
		{
			_listmodel.addElement(mlam.getLines().get(i));
		}
		_list=new JList<Line>(_listmodel);
		_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		_list.setFixedCellHeight(20);
		_list.setVisibleRowCount(10);
		_list.setAutoscrolls(true);
		_list.setPreferredSize(new Dimension(200,400));
		_list.addListSelectionListener(new ListSelectionListener(){

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			mlam.setCurrentLine(arg0.getFirstIndex());
			
		}
		});
		_add_button=new JButton("add");
		_add_button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				_mlpc.addNewLine();
			
				
			}
			
		});
		_del_button = new JButton("del");
		Box button_box=Box.createVerticalBox();
		button_box.add(_add_button);
		button_box.add(_del_button);

		Box lb=Box.createHorizontalBox();
		lb.add(new JScrollPane(_list));
		lb.add(button_box);

		_angle=new JTextField("0.0");
		_x=new JTextField("0.0");
		_y=new JTextField("0.0");
		//		_separation=new JTextField("0.0");
		_angleslider=new JSlider();
		_angleslider.setMaximum(90);
		_angleslider.setMinimum(-90);
		_angleslider.setValue(0);
		_angleslider.setPaintTicks(true);
		_angleslider.setMajorTickSpacing(10);
		_angleslider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent event)
			{
				_mlpc.setAngle((double)((JSlider)event.getSource()).getValue());

			}
		});
		_spacingslider=new JSlider();
		_spacingslider.setMaximum(100);
		_spacingslider.setMinimum(10);
		_spacingslider.setValue(50);
		_spacingslider.setPaintTicks(true);
		_spacingslider.setMajorTickSpacing(10);
		_spacingslider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent event)
			{
				_mlpc.setSpacing((double)((JSlider)event.getSource()).getValue()/100.0);

			}
		});
	
		Box anglebox=Box.createVerticalBox();
		anglebox.add(_angle);
		anglebox.add(_angleslider);
		anglebox.add(_x);
		anglebox.add(_spacingslider);
		anglebox.add(_y);
		Box topbox=Box.createVerticalBox();
		topbox.add(lb);
		topbox.add(Box.createVerticalGlue());
		topbox.add(anglebox);
		this.add(topbox);
		this.setVisible(true);

	}
	@Override
	public void update(Object obj) {
		String command =(String)obj;
		MultiLineAlgoModel mlam=(MultiLineAlgoModel)_model;
		_list.setSelectedIndex(mlam.get_current_line());
		if (command=="changed"||command=="newLine")
		{
			_angle.setText(Double.toString(mlam.getCurrentLine().getAngle()));
	
		}
	 if (command=="newLine")
		{
			_listmodel.addElement(mlam.getCurrentLine());
		}
	 System.out.println(Integer(mlam.get_current_line()).toString());
		repaint();

	}
}