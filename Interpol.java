package interpolation;

import java.io.*;
import java.util.*;

public class Interpol {
    private double t;
    private Vector<Double> x, y;

	public Interpol(double t) {
		this.t = t;
		x = new Vector<Double>();
		y = new Vector<Double>();
	}

	public void readFile(String path){
		try {
			FileReader input = new FileReader(path);   
		    /* Filter FileReader through a Buffered read to read a line at a time */
		    BufferedReader bufRead = new BufferedReader(input);
	        // Read first line
		    String line = bufRead.readLine();
		    StringTokenizer xy;
		    // Read through file one line at time.
		    while (line != null){
		    	xy = new StringTokenizer(line, "\t");
		    	while(xy.hasMoreTokens()) { 
		    		x.addElement(Double.parseDouble(xy.nextToken())); 
		    		y.addElement(Double.parseDouble(xy.nextToken())); 
		    	}
		        line = bufRead.readLine();
		    }
		    bufRead.close();
		} catch (IOException e) {
		    // If another exception is generated, print a stack trace
		    e.printStackTrace();
		}
	}

	public void getResult(InterpolationMethod interpolationMethod) {
		if(interpolationMethod != null) {
			System.out.println(interpolationMethod.toString());		
			System.out.println("x = "+t+",  f(x) = "+interpolationMethod.calculateResult(t, x, y));			
		}
	}

	public static void main(String[] args) {
		if(args.length == 0 || args.length < 2) {
			System.out.println("Debe ejecutarse: $ java Interpol pathFileName value");
			return;			
		} else {
			Interpol interpol = new Interpol(Double.parseDouble(args[1]));
			interpol.readFile(args[0]);
			interpol.getResult(new Lagrange());
			interpol.getResult(new CubicSpline());
		}
	}
}