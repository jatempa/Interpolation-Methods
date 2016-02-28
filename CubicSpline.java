package interpolation;

import java.util.Vector;

public class CubicSpline implements InterpolationMethod {

	public double calculateResult(double t, Vector<Double> xx, Vector<Double> yy){
		int i = 1, l = 0, n = 0;
		int lim_left = 0, lim_right = 0;

		n =  xx.size();
		lim_right = n - 5;
		double matrix[][] = new double[n-2][n-1];
		Vector<Double> datos= new Vector<Double>();

		datos.add(2 * (xx.elementAt(i+1) - xx.elementAt(i-1)));
		datos.add((xx.elementAt(i+1) - xx.elementAt(i)));

		for (int z = 0; z < (n-4); z++) {
			datos.add(0.0);
		}

		datos.add(((6 / (xx.elementAt(i+1) - xx.elementAt(i))) * (yy.elementAt(i+1) - yy.elementAt(i))) + ((6 / (xx.elementAt(i)-xx.elementAt(i-1))) * (yy.elementAt(i-1)-yy.elementAt(i))));	 

		for(i = 2; i < (n-2); i++){
			for (int z = 0; z < lim_left; z++) {
				datos.add(0.0);
			}
			lim_left++;

			datos.add((xx.elementAt(i)-xx.elementAt(i-1)));
			datos.add(2 * (xx.elementAt(i+1) - xx.elementAt(i-1)));
			datos.add((xx.elementAt(i+1) - xx.elementAt(i)));

			for (int z = 0; z < lim_right; z++) {
				datos.add(0.0);
			}
			lim_right--;

			datos.add(((6 / (xx.elementAt(i+1) - xx.elementAt(i))) * (yy.elementAt(i+1) - yy.elementAt(i))) + ((6 / (xx.elementAt(i) - xx.elementAt(i-1))) * (yy.elementAt(i-1)-yy.elementAt(i))));
		}   	 	 

		for (int z = 0; z < (n-4); z++) {
			datos.add(0.0);
		}

		datos.add((xx.elementAt(i)-xx.elementAt(i-1)));
		datos.add(2 * (xx.elementAt(i+1) - xx.elementAt(i-1)));
		datos.add(((6 / (xx.elementAt(i+1) - xx.elementAt(i))) * (yy.elementAt(i+1) - yy.elementAt(i))) + ((6 / (xx.elementAt(i)-xx.elementAt(i-1))) * (yy.elementAt(i-1) - yy.elementAt(i))));

		for(int j = 0; j < i; j++){
			for(int k = 0; k < (i+1); k++){
				matrix[j][k] = datos.elementAt(l);
				l++;
			}
		}

		return Interpol(t, xx, yy, Gauss(matrix, (n-2)), n);
	}

	public double[] Gauss(double M[][], int N){
		double d2x[] = new double[N+2];
		int i, j, k;
		double pivote, cero;

		for(i = 0; i < N; i++){
			pivote = M[i][i];

			for(j = i; j < (N+1); j++){
				M[i][j] = M[i][j]/pivote; // divide a todo el renglon i entre el elemento diagonal
			}

			for(k = 0; k < N; k++) // k controla los renglones independientemente de i
			{
				if(k != i) // evita hacer cero el elemento diagonal
				{
					cero = -M[k][i];
					for(j = i; j < (N+1); j++){	
						M[k][j] = M[k][j] + cero*M[i][j]; // hace cero a toda la columna i excepto el elemento diagonal
					}
				}
			}
		}
		
		d2x[0] = 0;
		for (int l = 0; l < (d2x.length - 2); l++) {
			d2x[l+1] = M[l][N];
		}
		d2x[N+1] = 0;
		
		return d2x;
	}

	public float Interpol(double t, Vector<Double> x, Vector<Double> fx, double d2x[], int n){
		boolean flag = false;
		int i = 1;
		float result = 0;
		try{
			while(i < n){
				if (( t  >= x.elementAt(i-1)) && ( t <= x.elementAt(i))){

					  result = (float) ( ((d2x[i-1]/(6*(x.elementAt(i)-x.elementAt(i-1)))) * Math.pow((x.elementAt(i) - t), 3)) + 
							           ((d2x[i]/(6*(x.elementAt(i)-x.elementAt(i-1)))) * Math.pow((t - x.elementAt(i-1)), 3)) + 
							           ((fx.elementAt(i-1)/(x.elementAt(i)-x.elementAt(i-1)) - ((d2x[i-1]*(x.elementAt(i)-x.elementAt(i-1))) / 6)) * (x.elementAt(i) - t)) + 
							           (((fx.elementAt(i)/(x.elementAt(i)-x.elementAt(i-1))) - ((d2x[i]*(x.elementAt(i)-x.elementAt(i-1)))/6)) * (t - x.elementAt(i-1))) );
					
					flag = true;
				}
	
				i++;
	
			};
	
			if(flag == false){
				System.out.println("Outside range");
			}
		} catch(Exception ex){
			System.out.println(ex);
		}

		return result;
	}

	@Override
	public String toString(){
		return "Cubic Spline";
	}
}