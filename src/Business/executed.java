package Business;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class executed {
	private Connection con;
	private DecimalFormat decimal;
	String ngay1, ngay2;

	public void setCon(Connection con) {
		this.con = con;
	}

	public void setFormat(DecimalFormat format) {
		this.decimal = format;
	}

	public String getNgay1() {
		return ngay1;
	}

	public String getNgay2() {
		return ngay2;
	}

	public void setNgay1(int y, int m, int d) {
		ngay1 = "'" + y + "/" + m + "/" + d + "'";

	}

	public void setNgay2(int y, int m, int d) {
		ngay2 = "'" + y + "/" + m + "/" + d + "'";

	}

	public executed(Connection con, int d1, int d2, int m1, int m2, int y1, int y2) {
		this.con = con;

		ngay1 = "'" + y1 + "/" + m1 + "/" + d1 + "'";
		ngay2 = "'" + y2 + "/" + m2 + "/" + d2 + "'";

		decimal = new DecimalFormat("###,###,###,###,###.### ");
	}

	public executed(Connection con) {
		this.con = con;
	}

	public String DinhDangSo(Object so) {

		return decimal.format(so);
	}

	public int baocao_tonghoadon() {

		String sql = "SELECT COUNT(MaHD) tong FROM HoaDonBan where CONVERT(DATE , NGAYTAO , 126) BETWEEN " + ngay1
				+ " and " + ngay2;
		int sohoadon = 0;
		try {
			ResultSet rs = con.createStatement().executeQuery(sql);
			if (rs.next()) {
				sohoadon = rs.getInt(1);
			}
		} catch (Exception e) {
			System.err.println(e.getClass() + "  baocao_tonghoadon " + e.getMessage());
		}

		return sohoadon;
	}

	public double baocao_tongdoanhthu() {
		String sql = "SELECT  sum(CTHD.ThanhTien * (100 - hd.GiamGia)/100 ) FROM ChiTietHoaDon CTHD  JOIN HoaDonBan HD ON HD.MaHD = CTHD.MaHD where CONVERT(DATE , NGAYTAO , 126) BETWEEN "
				+ ngay1 + " and " + ngay2;
		double chiphi = 0;
		try {
			ResultSet rs = con.createStatement().executeQuery(sql);
			if (rs.next()) {
				chiphi = rs.getDouble(1);
			}
			rs.close();
		} catch (Exception e) {
			System.err.println(e.getClass() + " baocao_tongdoanhthu " + e.getMessage());
		}
		return chiphi;
	}

	public double baocao_tongloinhuan() {

		String sql = "SELECT SUM( m.GiaNhap * CTHD.SoLuong) FROM MatHang m  JOIN ChiTietHoaDon CTHD ON M.MaHang = CTHD.MaHang JOIN HoaDonBan HD ON HD.MaHD = CTHD.MaHD where CONVERT(DATE , NGAYTAO , 126) BETWEEN "
				+ ngay1 + " and " + ngay2;
		double tongloinhuan = 0;
		try {
			ResultSet rs = con.createStatement().executeQuery(sql);

			if (rs.next()) {
				tongloinhuan = rs.getDouble(1);
			}
			rs.close();
		} catch (Exception e) {
			System.err.println(e.getClass() + " baocao_tongloinhuan " + e.getMessage());
		}
		return tongloinhuan;
	}

//	public void baocao_sotienchitieu() {
//		String sql = "SELECT SUM(SoLuong*DonGia) FROM PHIEUKHO JOIN CTPHIEU  on phieukho.maphieu = ctphieu.maphieu WHERE LOAIPHIEU =N'NHAP' ";
//
//		try {
//			Statement st = con.createStatement();
//			ResultSet rs = st.executeQuery(sql);
//			while (rs.next()) {
//				lbsotienchitieu_kq
//						.setText(new DecimalFormat("###,###,###,###,###.### đ").format(rs.getBigDecimal(1)) + "");
//			}
//
//		} catch (Exception e) {
//			System.err.println(e.getClass() + " baocao_sotienchitieu " + e.getMessage());
//		}
//
//	}

	public ArrayList<String> getTenCot(String txt) {
		ArrayList<String> ds = new ArrayList<String>();
		String sql = " SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + txt + "' ";
		try {
			ResultSet rs = con.createStatement().executeQuery(sql);
			// ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				ds.add(rs.getString(1));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("executed.napTenCot: " + e.getMessage());
		}
		return ds;
	}

	public void ghiFile(ArrayList<String> fields, ArrayList<String> rows, String tableName, String id, String url) {
		String field = "";
		for (int i = 0; i < fields.size(); i++) {
			field += fields.get(i);
			if (i == (fields.size() - 1))
				break;
			field += ",";
		}

		String sql = "select " + field + " from " + tableName + " where " + id + " = ?";

		try {
			FileOutputStream f = new FileOutputStream(url);
			OutputStreamWriter out = new OutputStreamWriter(f, "UTF-8");
			PrintWriter pw = new PrintWriter(out);

			PreparedStatement pr = con.prepareStatement(sql);
			ResultSet rs = null;
			int size = rows.size();
			for (int i = 0; i < size; i++) {
				pr.setString(1, rows.get(i));
				rs = pr.executeQuery();
				if (rs.next()) {
					for (int k = 0; k < fields.size(); k++) {
						pw.print(rs.getString(k + 1) + " ;");
					}
					pw.print('\n');
				}
			}

			pw.close();
			rs.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ghi file lỗi");
			System.out.println("excuted.ghiFile: " + e.getMessage());
		}

	}

	public void ghiFile(ArrayList<String> fields, JTable table, String tableName, String id, String url) {
		String field = "";
		for (int i = 0; i < fields.size(); i++) {
			field += fields.get(i);
			if (i == (fields.size() - 1))
				break;
			field += ",";
		}
		String sql = "select " + field + " from " + tableName + " where " + id + " = ?";
		try {

			FileOutputStream f = new FileOutputStream(url);
			OutputStreamWriter out = new OutputStreamWriter(f, "UTF-8");
			PrintWriter pw = new PrintWriter(out);

			PreparedStatement pr = con.prepareStatement(sql);
			ResultSet rs = null;

			int size = table.getRowCount();
			for (int i = 0; i < size; i++) {
				pr.setString(1, (String) table.getValueAt(i, 1));
				rs = pr.executeQuery();

				if (rs.next()) {
					for (int j = 0; j < fields.size(); j++) {
						pw.print(rs.getString(j + 1) + " ;");
					}
					pw.print('\n');
				}
			}

			pw.close();
			rs.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ghi file lỗi");
			System.out.println("excuted.ghiFile: " + e.getMessage());
		}
	}
}
