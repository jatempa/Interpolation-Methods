package interpolation;

public class Interpol {

	public static void main(String[] args) {
		InterpolationHelper helper;
		
		if(args.length == 0 || args.length < 2) {
			System.out.println("Debe ejecutarse: $ java Interpol pathFileName value");
			return;			
		} else {
			helper = new InterpolationHelper(args[0], Double.parseDouble(args[1]), new Lagrange());
			helper.getResult();	
			helper = new InterpolationHelper(args[0], Double.parseDouble(args[1]), new CubicSpline());
			helper.getResult();	
		}
	}
}