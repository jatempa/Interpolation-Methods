package interpolation;

import java.util.Vector;

public class Lagrange {
	
	public double Lagrng(double t, Vector<Double> xx, Vector<Double> yy){
		int n = xx.size();
		
		double sum = 0;
		for (int i = 0; i < n; i++) {
			double product = yy.elementAt(i);
			for (int j = 0; j <  n; j++) {
				if (i != j){
					product = product * (t - xx.elementAt(j)) / (xx.elementAt(i) - xx.elementAt(j));
				}
			}
			sum = sum + product;
		}
		
		return sum;
	}

}