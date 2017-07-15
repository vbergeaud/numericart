package engine;

import java.util.ArrayList;
import java.util.Collections;

public class MozarabPatternModel {
	int _nsquares;
	int _nedges;
	int _nintersquares;
	int[] _square_ordering;
	int[] _edge_ordering;
	int[] _intersquare_ordering;
	int _dimension;
	double _ratio;
	
	MozarabPatternModel(int n, double ratio)
	{
		buildModel(n);
		_ratio=ratio;
	}
	
	void buildModel(int n)
	{
		_dimension=n;
		_nsquares=n*n;
		_nintersquares=2*(n*n-n);
		_nedges=4*n;
		_square_ordering=listShuffle(_nsquares);
		_edge_ordering=listShuffle(_nedges);
		_intersquare_ordering=listShuffle(_nintersquares);
	}
	int[] listShuffle(int n)
	{
		ArrayList<Integer> l=new ArrayList<Integer>();
		for (int i=0; i<n; i++)
			l.add(i);
		java.util.Collections.shuffle(l);
		int[] shuffled=new int [n];
		for (int i=0; i<n; i++)
			shuffled[i]=l.get(i);
		return shuffled;
	}
	int getDimension(){return _dimension;}
	int getSquareOrdering(int i){return _square_ordering[i];}
	double getRatio(){return _ratio;}
}
