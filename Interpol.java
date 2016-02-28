package interpolation;

import java.io.*;
import java.util.*;

public class Interpol {
    private double t;
    private Vector<Double> x, y;
    private FileReader input;
    private String fileName;

	public Interpol(String fileName, double t) {
		this.fileName = fileName;
		this.t = t;
	}

	public void calculate() {
		try {
			x = new Vector<Double>();
			y = new Vector<Double>();
		    input = new FileReader(fileName);
		    /* Filter FileReader through a Buffered read to read a line at a time */
		    BufferedReader bufRead = new BufferedReader(input);  			
		    String line; 	// String that holds current file line
		    StringTokenizer xy;
            // Read first line
		    line = bufRead.readLine();
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
			getResult(new Lagrange());
			getResult(new CubicSpline());			
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
			Interpol interpol = new Interpol(args[0], Double.parseDouble(args[1]));
			interpol.calculate();			
		}
	}
}