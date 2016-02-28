package interpolation;

import java.util.Vector;

public class Lagrange implements InterpolationMethod {
	int n = 0;
	double sum = 0, product = 0;

	public double calculateResult(double t, Vector<Double> xx, Vector<Double> yy){
		n = xx.size();
			
		for (int i = 0; i < n; i++) {
			product = yy.elementAt(i);
			for (int j = 0; j <  n; j++) {
				if (i != j){
					product = product * (t - xx.elementAt(j)) / (xx.elementAt(i) - xx.elementAt(j));
				}
			}
			sum = sum + product;
		}
		
		return sum;
	}

	@Override
	public String toString(){
		return "Lagrange";
	}
}