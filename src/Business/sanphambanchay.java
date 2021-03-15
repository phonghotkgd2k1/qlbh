package Business;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class sanphambanchay extends JPanel {

	private JTable table;
	private executed ect;
	private String ngay1, ngay2;

	public sanphambanchay(int d1, int d2, int m1, int m2, int y1, int y2) {

		ect = new executed(DangNhap.con);
		ect.setNgay1(y1, m1, d1);
		ect.setNgay2(y2, m2, d2);
		ngay1 = ect.getNgay1();
		ngay2 = ect.getNgay2();
		GUI();
		napdulieu();
	}

	private void GUI() {
		setLayout(new BorderLayout(0, 0));

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "stt", "Tên sản phẩm", "Số lượng", "Doanh thu" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.setRowHeight(30);
		table.getColumn("Số lượng").setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public void setHorizontalAlignment(int alignment) {
				super.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
			}
		});
		table.getColumn("Doanh thu").setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public void setHorizontalAlignment(int alignment) {
				super.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
			}
		});
		table.setFont(new Font("Arial", Font.PLAIN, 17));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);
		setBorder(null);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lbTopsanphambanchay = new JLabel("Top sản phẩm bán chạy");
		lbTopsanphambanchay.setHorizontalAlignment(SwingConstants.CENTER);
		lbTopsanphambanchay.setFont(new Font("Arial", Font.BOLD, 18));
		panel.add(lbTopsanphambanchay, BorderLayout.WEST);

	}

	private void napdulieu() {
		String sql = "select top 10 m.tenhang, sum( ct.SoLuong),sum(ct.ThanhTien) thanhtien from ChiTietHoaDon ct join MatHang m on m.MaHang =ct.MaHang join hoadonban hd on hd.mahd = ct.mahd where CONVERT(DATE ,HD.NGAYTAO ,126) BETWEEN "
				+ ngay1 + " and " + ngay2 + " group by ct.MaHang, m.TenHang order by thanhtien desc";
		try {
			ResultSet rs = DangNhap.con.createStatement().executeQuery(sql);
			DefaultTableModel datarow = (DefaultTableModel) table.getModel();
			for (int i = 1; rs.next(); i++) {

				String data[] = { i + "", rs.getString(1), rs.getInt(2) + "", new DecimalFormat("###,###,###.#").format(rs.getLong(3)) };
				datarow.addRow(data);
			}
		} catch (Exception e) {
			System.out.println("sanphambanchay - napdulieu:" + e.getMessage());
		}
	}
}
