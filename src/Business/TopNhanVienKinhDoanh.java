package Business;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;

public class TopNhanVienKinhDoanh extends JPanel {
	private JComboBox<String> comboBox_topnhanvien;
	private JTable table;
	private String ngay1, ngay2;
	private executed ect;

	/**
	 * Create the panel.
	 */

	public TopNhanVienKinhDoanh(int d1, int d2, int m1, int m2, int y1, int y2) {
		ect = new executed(DangNhap.con);
		ect.setNgay1(y1, m1, d1);
		ect.setNgay2(y2, m2, d2);
		ngay1 = ect.getNgay1();
		ngay2 = ect.getNgay2();
		GUI();
	}

	private void GUI() {
		setLayout(new BorderLayout(0, 5));

		JPanel panel_north = new JPanel();
		add(panel_north, BorderLayout.NORTH);
		panel_north.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Top nhân viên kinh doanh");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		panel_north.add(lblNewLabel, BorderLayout.WEST);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setRowHeight(40);
		table.setFont(new Font("Arial", Font.PLAIN, 17));
		scrollPane.setViewportView(table);

		comboBox_topnhanvien = new JComboBox();
		comboBox_topnhanvien.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) comboBox_topnhanvien.getSelectedItem();
				if (s.equalsIgnoreCase("Doanh thu cao nhất")) {
					napds();

				} else if (s.equalsIgnoreCase("Lợi nhuận cao nhất")) {
					napds2();

				}
			}
		});
		comboBox_topnhanvien
				.setModel(new DefaultComboBoxModel(new String[] { "Doanh thu cao nhất", "Lợi nhuận cao nhất" }));
		comboBox_topnhanvien.setSelectedItem("Doanh thu cao nhất");
		comboBox_topnhanvien.setFont(new Font("Arial", Font.BOLD, 17));
		panel_north.add(comboBox_topnhanvien, BorderLayout.EAST);

	}

	public void napds() {

		String header[] = { "Mã nhân viên", "Tên nhân viên", "doanh thu" };
		DefaultTableModel datarow = new DefaultTableModel(header, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String sql = "select top 10 MaNV ,N.TenNhanVien ,SUM( CTHD.ThanhTien * (100- hd.GiamGia) /100) tong from HoaDonBan hd JOIN ChiTietHoaDon CTHD ON CTHD.MaHD = HD.MaHD JOIN NhanVien N ON N.MaNhanVien = hd.MaNV where CONVERT(DATE ,HD.NGAYTAO ,126) BETWEEN " +ngay1+" and "+ngay2+" group by MaNV , N.TenNhanVien order by tong desc";

		try {
			ResultSet rs = DangNhap.con.createStatement().executeQuery(sql);
			while (rs.next()) {

				String data[] = { rs.getString(1), rs.getString(2), rs.getDouble(3) + "" };
				datarow.addRow(data);
			}
			table.setModel(datarow);
		} catch (Exception e) {

		}
	}

	public void napds2() {
		String header[] = { "Mã nhân viên", "Tên nhân viên", "loi nhuan" };
		DefaultTableModel datarow = new DefaultTableModel(header, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		datarow.setRowCount(0);
		String sql = "select top 10 MaNV ,N.TenNhanVien ,SUM( CTHD.ThanhTien * (100- hd.GiamGia) /100 - (M.GIANHAP * CTHD.SOLUONG )) tong from HoaDonBan hd JOIN ChiTietHoaDon CTHD ON CTHD.MaHD = HD.MaHD JOIN NhanVien N ON N.MaNhanVien = hd.MaNV JOIN MATHANG M ON M.MAHANG = CTHD.MAHANG where CONVERT(DATE ,HD.NGAYTAO ,126) BETWEEN " +ngay1+" and "+ngay2+" group by MaNV, N.TenNhanVien  order by tong desc";

		try {
			ResultSet rs = DangNhap.con.createStatement().executeQuery(sql);

			while (rs.next()) {
				String data[] = { rs.getString(1), rs.getString(2), rs.getDouble(3) + "" };
				datarow.addRow(data);
			}
			table.setModel(datarow);
		} catch (Exception e) {

		}
	}

}
