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
		String a = "anh hung 001";
		System.out.println(a.substring(0,a.length()-1));
	}


}
