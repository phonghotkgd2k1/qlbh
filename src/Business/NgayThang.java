package Business;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;

public class NgayThang {
	private SimpleDateFormat simple;
	private Calendar cal;
	private DefaultComboBoxModel<Integer> ngay, thang, nam;

	public NgayThang() {
		simple = new SimpleDateFormat("d M yyyy");
		cal = Calendar.getInstance();

		ngay = new DefaultComboBoxModel<Integer>();
		thang = new DefaultComboBoxModel<Integer>();
		nam = new DefaultComboBoxModel<Integer>();
	}

	public void Ngay() {
		for (int i = 1; i <= 31; i++) {
			ngay.addElement(i);
		}
	}

	public void Thang() {
		for (int i = 1; i <= 12; i++) {
			thang.addElement(i);
		}

	}

	public void Nam() {
		int y = cal.get(Calendar.YEAR);
		for (int i = 2000; i <= y; i++) {
			nam.addElement(i);
		}
	}

	public Calendar getCal() {
		return cal;
	}

	public void setCal(Calendar cal) {
		this.cal = cal;
	}

	public String getDate() {
		Date date = cal.getTime();

		return simple.format(date);
	}

	public DefaultComboBoxModel<Integer> getNgay() {
		return ngay;
	}

	public void setNgay(DefaultComboBoxModel<Integer> ngay) {
		this.ngay = ngay;
	}

	public DefaultComboBoxModel<Integer> getThang() {
		return thang;
	}

	public void setThang(DefaultComboBoxModel<Integer> thang) {
		this.thang = thang;
	}

	public DefaultComboBoxModel<Integer> getNam() {
		return nam;
	}

	public void setNam(DefaultComboBoxModel<Integer> nam) {
		this.nam = nam;
	}
}
