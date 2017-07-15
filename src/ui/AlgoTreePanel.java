package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import engine.AImageAlgo;
import engine.AlgoTree;
import engine.AlgoTreeController;
import engine.ImageAlgoFactory;
import engine.Observer;

//classe permettant l'affichage de l'arbre des algos
//Deux boutons en tête d'arbre  : new et delete
public class AlgoTreePanel extends JPanel implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTree _tree;
	DefaultTreeModel _model;
	JScrollPane _jsp;
	AlgoTree _at;
	AlgoTreeController _atc;
	AlgoTreePanel(AlgoTree at, AlgoTreeController atc)
	{
		_at=at;
		_atc=atc;
		// Deux sous-panneaux
		//sous-panneau boutons
		JPanel button_panel = new JPanel();
		button_panel.setPreferredSize(new Dimension(StyleSheet.PANEL_WIDTH,40));
		JButton add_button=new JButton("Add Julia");
		button_panel.add(add_button);
		add_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				at.create(ImageAlgoFactory.make(AImageAlgo.AlgoType.Julia));
			}
		});
		JButton add_ml_button=new JButton("Add MultiLine");
		button_panel.add(add_ml_button);
		add_ml_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				at.create(ImageAlgoFactory.make(AImageAlgo.AlgoType.MultiLine));
			}
		});	
		JButton add_mb_button=new JButton("Add Mozarab");
		button_panel.add(add_mb_button);
		add_mb_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				at.create(ImageAlgoFactory.make(AImageAlgo.AlgoType.MozarabBlender));
			}
		});	
		JButton save_pic_button = new JButton("Save picture");
		save_pic_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				//Create a file chooser
				final JFileChooser fc = new JFileChooser();
			
				//In response to a button click:
				int returnVal = fc.showSaveDialog(AlgoTreePanel.this);
				
				File file=fc.getSelectedFile();
				
				_atc.performSave(file);
			}
		});
		//sous-panneau tree
		JPanel tree_panel = new JPanel();
		tree_panel.setPreferredSize(new Dimension(StyleSheet.PANEL_WIDTH,180));


		this.setLayout(new BorderLayout());
		this.add(button_panel,BorderLayout.NORTH);
		this.add(tree_panel,BorderLayout.CENTER);
		this.add(save_pic_button,BorderLayout.SOUTH);
		buildTree();
		this.setVisible(true);
	}

	private void buildTree()
	{
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("Racine");
		System.out.println("taille arbre : "+Integer.toString(_at.size()));
		for (int i=0; i<_at.size();i++)
		{
			DefaultMutableTreeNode algo = new DefaultMutableTreeNode(_at.get(i));
			root.add(algo);
		}
		_model=new DefaultTreeModel(root);
		_tree=new JTree(_model);

		//behaviour for selection change
		_tree.addTreeSelectionListener(new TreeSelectionListener(){

			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				if (_tree.getLastSelectedPathComponent()!=null){
					AImageAlgo aia=(AImageAlgo)((DefaultMutableTreeNode)(_tree.getLastSelectedPathComponent())).getUserObject();
					_atc.setCurrent(aia);
				}

			}

		});

		_tree.setRootVisible(false);
		_tree.setEditable(true);
		_tree.getModel().addTreeModelListener(new TreeModelListener()
		{
			public void treeNodesInserted(TreeModelEvent evt)
			{

			}

			@Override
			public void treeNodesChanged(TreeModelEvent evt) {
				Object[] listNoeuds=evt.getChildren();
				int[] listIndices=evt.getChildIndices();
				DefaultMutableTreeNode node=(DefaultMutableTreeNode)listNoeuds[0];
				String newname=(String)node.getUserObject();
				_at.get(listIndices[0]).setName(newname);
			}

			@Override
			public void treeNodesRemoved(TreeModelEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void treeStructureChanged(TreeModelEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		_jsp=new JScrollPane(_tree);
		this.add(_jsp);

	}

	public void update(Object obj)
	{
		try{
			AImageAlgo aia=(AImageAlgo)obj;
			DefaultMutableTreeNode rootnode=(DefaultMutableTreeNode)_model.getRoot();
			_model.insertNodeInto( new DefaultMutableTreeNode(aia),rootnode, 
					rootnode.getChildCount());
			_tree.setSelectionInterval(rootnode.getChildCount()-1,rootnode.getChildCount());
			return;
		}
		catch(ClassCastException e)
		{
		}
		String message= new String ((String) obj);
		if (message.equals("changed")){
			repaint();
		}
	}

}


