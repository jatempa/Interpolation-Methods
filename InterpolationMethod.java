package interpolation;

import java.util.Vector;

interface InterpolationMethod {
	public double calculateResult(double t, Vector<Double> xx, Vector<Double> yy);
}