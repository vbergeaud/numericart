package ui;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import javax.swing.JSplitPane;

import engine.AImageAlgo;
import engine.AlgoTree;
import engine.AlgoTreeController;

import engine.ImageAlgoFactory;

import engine.Observer;

public class MainWindow extends JFrame implements Observer{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ScreenviewPanel _sp;
	AParameterPanel _apa;
	AlgoTreePanel _atp;
	JSplitPane _split_global;
	JSplitPane _split_right;
	public MainWindow()
	{

		AlgoTree at=AlgoTree.getInstance();

		/*		// creating Julia Fractal Algo, view and controller 
		AImageAlgo jfa=ImageAlgoFactory.make(AImageAlgo.AlgoType.Julia);		
		JuliaFractalAlgoModel jfam=(JuliaFractalAlgoModel)jfa.getModel();
		FractalPanelControler fc=new FractalPanelControler(jfam);
		FractalCommandPanel fcp=new FractalCommandPanel(jfam,fc);
		at.add(jfa);
		 */	
		// creating MultiLine Algo, view and controller
		AImageAlgo mla=ImageAlgoFactory.make(AImageAlgo.AlgoType.MultiLine);

		//MultiLineAlgoModel mlam=(MultiLineAlgoModel)mla.getModel();
		_apa=AParameterPanelFactory.buildAParameterPanel(mla);
		//TODO : controller
		//TODO : view 
		at.add(mla);
		_sp=new ScreenviewPanel(mla);

		_atp = new AlgoTreePanel(at, new AlgoTreeController(at));
		at.addObserver(_atp);
		at.addObserver(this);
		//jfam.addObserver(sp);
		//jfam.addObserver(fcp);
		mla.getModel().addObserver(_sp);
		MultiLinePanel mlp=(MultiLinePanel)_apa;
		mlp.update("toto");		 
		buildFrames();

	}
	private void buildFrames()
	{

		_split_right=new JSplitPane(JSplitPane.VERTICAL_SPLIT,_atp,_apa);

		_split_global=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, _sp,_split_right);

		_sp.setPreferredSize(new Dimension(StyleSheet.SCREENVIEW_WIDTH,StyleSheet.TOTAL_HEIGHT));
		_split_global.setDividerLocation(StyleSheet.SCREENVIEW_WIDTH);
		_split_right.setDividerLocation(StyleSheet.TREE_HEIGHT);
		_atp.setPreferredSize(new Dimension(StyleSheet.PANEL_WIDTH,StyleSheet.TREE_HEIGHT));
		this.getContentPane().add(_split_global,BorderLayout.CENTER);
		this.setSize(StyleSheet.PANEL_WIDTH+StyleSheet.SCREENVIEW_WIDTH, StyleSheet.TOTAL_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String[] args)
	{
		MainWindow mw=new MainWindow();
	}
	@Override
	public void update(Object obj) {
		int loc = _split_global.getDividerLocation();
		int loc2=_split_right.getDividerLocation();
		System.out.println("reecriture panneaux");
		AlgoTree at=AlgoTree.getInstance();
		_apa=AParameterPanelFactory.buildAParameterPanel(at.getCurrentAlgo());
		_split_right.setBottomComponent(_apa);
		_sp=new ScreenviewPanel(at.getCurrentAlgo());
		at.getCurrentAlgo().getModel().addObserver(_sp);
		_split_global.setLeftComponent(_sp);
		_split_global.setDividerLocation(loc);
		_split_right.setDividerLocation(loc2);
		_split_right.repaint();


	}
}


