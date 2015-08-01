package ui;

import javax.swing.JPanel;

import engine.AAlgoModel;

public abstract class AParameterPanel extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
AAlgoModel _model;
  
  AParameterPanel (AAlgoModel model)
  {
	  defineModel(model);
  }
 void defineModel(AAlgoModel model)
  {
	_model=model;  
  }
}
