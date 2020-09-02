package pruebas;

import java.text.DecimalFormat;

public class Prueba {

	public static void main(String[] args) {

		DecimalFormat df = new DecimalFormat("#0.00");
		
		double var = 1.0;
		
		
		System.out.println(df.format(var));
		
		
		double var2 = Math.round(var*100.00)/100.00;
		
		
		System.out.println(var2);
	}
}