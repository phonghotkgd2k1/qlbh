package Business;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JFrame;

public class a extends JFrame {

	static String chinhsua(String s) {

		String temp[] = s.split(",");
		s = "";
		for (int i = 0; i < temp.length; i++) {
			s += temp[i];
		}
		return s;
	}
	public static void main(String[] args)   {
		
		double vnd = 100004770.3567;
		Locale locale = new Locale("en", "EN");
		 String pattern = "###,###,###.###";
		DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
	    dcf.applyPattern(pattern);  // áp dụng mẫu pattern = "###.##" cho dcf
	    System.out.println("Số " + vnd + " sau khi định dạng = " + 
	        dcf.format(vnd));
			double d = Double.parseDouble(chinhsua(dcf.format(vnd)));
			
			double vnd1 = 1444400500000.52553d;
			BigDecimal b = new BigDecimal(vnd1);
			
			BigDecimal c = b.setScale(3 , BigDecimal.ROUND_HALF_DOWN);
			
			System.err.println( c.toString());
			System.out.println( c.add(new BigDecimal("100500")));
			System.err.println( b.toString());
			
			System.err.println( dcf.format(c));
	}


}
