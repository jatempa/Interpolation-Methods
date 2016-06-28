package interpolation;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;
import java.util.StringTokenizer;

public class InterpolationHelper {
	private FileReader input;
	private BufferedReader bufRead;
	private StringTokenizer xy;
	private String pathFile;
	private double t;
    private Vector<Double> x, y;
    private InterpolationMethod interpolationMethod;

	public InterpolationHelper(String pathFile, double t, InterpolationMethod interpolationMethod){
		this.pathFile = pathFile;
		this.t = t;
		this.interpolationMethod = interpolationMethod;
	}

	private void readFile(){
		x = new Vector<Double>();
		y = new Vector<Double>();
		try {
			input = new FileReader(pathFile);   
		    /* Filter FileReader through a Buffered read to read a line at a time */
		    bufRead = new BufferedReader(input);
		   	// Read first line
		    String line = bufRead.readLine();
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

	public void getResult() {
		if(interpolationMethod != null) {
			readFile();
			System.out.println(interpolationMethod.toString());
			System.out.println("x = " + t + ",  f(x) = " + interpolationMethod.calculateResult(t, x, y));			
		} else {
			System.out.println("It is not defined an interpolation method.");
		}
	}
}