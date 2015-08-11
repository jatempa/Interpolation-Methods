package interpolation;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Interpol {

	public static void main(String[] args) {
		try {
			DecimalFormat f1 = new DecimalFormat("00.00000");
		    double t = Double.parseDouble(args[0]);
			//args[0] = "/home/jatempa/workspace/interpolation/src/methods/puntos.dat";
		    /*	Sets up a file reader to read the file passed on the command line one character at a time */
		    FileReader input = new FileReader("/home/atempa/Desktop/puntos.dat");
		    /* Filter FileReader through a Buffered read to read a line at a time */
		    BufferedReader bufRead = new BufferedReader(input);  			
		    String line; 	// String that holds current file line
		    StringTokenizer xy;
		    Vector<Double> x = new Vector<Double>();
		    Vector<Double> y = new Vector<Double>();
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
			    
			Lagrange lgr = new Lagrange();
			System.out.println("Lagrange");		
			System.out.println("x = "+t+",  f(x) = "+f1.format(lgr.Lagrng(t, x, y)));
			CSpline spl = new CSpline();
			System.out.println("Cubic Spline");
			System.out.println("x = "+t+",  f(x) = "+f1.format(spl.Cubic_Spline(t, x, y)));
			
		} catch (ArrayIndexOutOfBoundsException e){
		    /* If no file was passed on the command line, this expception is generated. A message indicating how to the class should be called is displayed */
		    System.out.println("Usage: java ReadFile filename\n");			

		} catch (IOException e){
		    // If another exception is generated, print a stack trace
		    e.printStackTrace();
		}
	}
}
