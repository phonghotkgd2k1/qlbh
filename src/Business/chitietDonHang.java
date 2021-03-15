package Business;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class chitietDonHang extends JPanel {
	private JTable table;
	private int index;
	private JLabel lbmahoadon;
	private JLabel lbmakh;
	private JLabel lbngaytao;
	private JLabel lbmanv;
	private JTextArea textArea;
	private CardLayout card;
	private JPanel pn_HD;
	private JLabel lbtongtien;
	private JLabel lbgiamgia;
	private JLabel lbkhachphaitra;
	private JLabel lbtiencongthem;

	/**
	 * Create the panel.
	 */

	private void getdata_hd(Object txt) {
		ResultSet rs = null;
		try {
			String sql = "SELECT H.* , N.TenNhanVien , K.TenKH FROM HoaDonBan H LEFT JOIN  KhachHang K  ON H.MaKH = K.MaKH LEFT JOIN NhanVien N ON H.MaNV = N.MaNhanVien where H.mahd = N'"
					+ txt + "'";
			Statement st = DangNhap.con.createStatement();
			rs = st.executeQuery(sql);
			if (!rs.next())
				System.out.println("khong co data");
			else {
				lbmahoadon.setText(rs.getString("mahd"));
				lbmakh.setText(rs.getString("tenkh") + "( " + rs.getString("makh") + " )");
				lbmanv.setText(rs.getString("manv") + "( " + rs.getString("tennhanvien") + " )");
				lbngaytao.setText(rs.getString("ngaytao"));
				textArea.setText(rs.getString("ghichu"));
			}
		} catch (Exception e) {
			System.out.println("chitietKhachHang - getdata_hd: " + e.getMessage());
		}
	}

	private void getdata_chitietHD(Object txt) {
		DefaultTableModel datarow = (DefaultTableModel) table.getModel();
		ResultSet rs = null;
		try {
			String sql = "SELECT CT.MaHang ,M.TenHang ,CT.SoLuong ,CT.DonGia ,CT.GiamGia  ,CT.ThanhTien FROM ChiTietHoaDon CT JOIN HoaDonBan H  ON H.MaHD =CT.MaHD JOIN MatHang M ON M.MaHang = CT.MaHang WHERE  H.MaHD =N'"
					+ txt
					+ "' GROUP BY H.MaHD ,CT.MaHang ,M.TenHang , CT.SoLuong ,CT.DonGia , CT.GiamGia  , CT.ThanhTien ";
			Statement st = DangNhap.con.createStatement();
			rs = st.executeQuery(sql);
			for (int i = 1; rs.next(); i++) {

				String temp[] = { i + "", rs.getString("mahang"), rs.getString("tenhang"), rs.getInt("soluong") + "",
						rs.getDouble("dongia") + "", rs.getFloat("giamgia") + "", rs.getDouble("thanhtien") + "" };
				datarow.addRow(temp);
			}
		} catch (Exception e) {
			System.out.println("chitietKhachHang - getdata_chitiethd: " + e.getMessage());
		}
	}

	private void thanhtoanHD(Object txt) {
		try {
			String sql_tontien = "SELECT SUM(ThanhTien) tongtien FROM ChiTietHoaDon  where  mahd = N'" + txt + "'";
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql_tontien);
			if (rs.next()) {
				lbtongtien.setText(DinhDangTienTe(rs.getDouble("tongtien")));
			}
			String sql_giamgia = "select giamgia from hoadonban where mahd = N'" + txt + "'";
			rs = st.executeQuery(sql_giamgia);
			if (rs.next()) {
				lbgiamgia.setText(rs.getFloat(1) + "");
			}
			String sql_tiencongthem = "select tiencongthem from hoadonban where mahd = N'" + txt + "'";
			rs = st.executeQuery(sql_tiencongthem);
			if (rs.next()) {
				lbtiencongthem.setText(rs.getDouble(1) + "");
			}
			double khachphaitra1 = (Double.parseDouble(DonHang.chinhsua(lbtongtien.getText()))
					* (100 - Float.parseFloat(lbgiamgia.getText())) / 100)
					+ Double.parseDouble(lbtiencongthem.getText());

			lbkhachphaitra.setText(DinhDangTienTe(khachphaitra1));
		} catch (Exception e) {
			System.out.println("chitietDonHang - thanhtoanHD: " + e.getMessage());
		}
	}

	public chitietDonHang(Object txt) throws SQLException {

		card = new CardLayout();
		setLayout(card);
		pn_HD = new JPanel(new BorderLayout(10, 0));
		JScrollPane js = new JScrollPane(pn_HD);
		js.getVerticalScrollBar().setUnitIncrement(10);

		add(js, Integer.toString(index));
		index++;

		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setVgap(20);
		flowLayout_1.setHgap(70);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pn_HD.add(panel, BorderLayout.NORTH);

		lbmahoadon = new JLabel();
		lbmahoadon.setFont(new Font("Arial", Font.PLAIN, 27));
		panel.add(lbmahoadon);

		JPanel panel_center = new JPanel();
		panel_center.setBackground(Color.WHITE);
		pn_HD.add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT, JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_center.add(tabbedPane, BorderLayout.CENTER);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		scrollPane_1.setFont(new Font("Arial", Font.PLAIN, 17));
		tabbedPane.addTab("Thông tin sản phẩm", new ImageIcon("‪Imager\\sanpham-20.png"), scrollPane_1, null);

		table = new JTable();
		table.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				int column = table.columnAtPoint(e.getPoint());
				if (column == 1) {
					table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				} else {
					table.setCursor(null);
				}
			}
			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = table.getSelectedColumn();
				int row = table.rowAtPoint(e.getPoint());
				int mouse = e.getButton();
				if (mouse == MouseEvent.BUTTON1 && column == 1) {
					try {
						JPanel pn = new JPanel(new BorderLayout());
						JPanel pn_them = new JPanel();
						FlowLayout flowLayout = (FlowLayout) pn_them.getLayout();
						flowLayout.setHgap(5);
						flowLayout.setVgap(25);
						pn.add(pn_them, BorderLayout.WEST);

						JLabel lbquaylai = new JLabel("");
						lbquaylai.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								if (e.getButton() == MouseEvent.BUTTON1) {
									hienthiSP(Integer.toString(0));
								}
							}
						});
						lbquaylai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						lbquaylai.setIcon(new ImageIcon("Imager\\back_25px.png"));
						lbquaylai.setToolTipText("Quay lại hóa đơn");
						pn_them.add(lbquaylai);

						pn.add(new chitietSanPham((String) table.getValueAt(row, column)), BorderLayout.CENTER);
						add(pn, Integer.toString(index));

						hienthiSP(Integer.toString(index));
						index++;
					} catch (SQLException e1) {
						System.out.println("chitietDonHang -table- mouseClicked: " + e1.getMessage());
					}
				}
			}
		});
		table.setFont(new Font("Arial", Font.PLAIN, 17));
		table.setRowHeight(60);
		String header[] = { "", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Giảm giá(%)", "Thành tiền" };
		table.setModel(new DefaultTableModel(new Object[][] {}, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// KHONG CHO CHINH SUA;
				return false;
			}
		});
		table.getColumn("").setMaxWidth(70);
		table.getColumn("Mã sản phẩm").setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public void setForeground(Color c) {
				super.setForeground(Color.blue);
			}
		});
		scrollPane_1.setViewportView(table);

		JPanel panel_east = new JPanel();
		panel_east.setPreferredSize(new Dimension(450, 10));
		panel_east.setBackground(new Color(240, 240, 240));
		pn_HD.add(panel_east, BorderLayout.EAST);
		GridBagLayout gbl_panel_east = new GridBagLayout();
		gbl_panel_east.columnWidths = new int[] { 10, 0, 0, 0 };
		gbl_panel_east.rowHeights = new int[] { 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_east.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_east.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel_east.setLayout(gbl_panel_east);

		JLabel lblNewLabel_6 = new JLabel("Khách Hàng");
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.ipady = 20;
		gbc_lblNewLabel_6.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_6.insets = new Insets(30, 20, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 1;
		panel_east.add(lblNewLabel_6, gbc_lblNewLabel_6);

		lbmakh = new JLabel();
		lbmakh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbmakh.setForeground(Color.BLUE);
		lbmakh.setFont(new Font("Arial", Font.PLAIN, 22));
		GridBagConstraints gbc_lbmakh = new GridBagConstraints();
		gbc_lbmakh.fill = GridBagConstraints.BOTH;
		gbc_lbmakh.insets = new Insets(30, 0, 5, 20);
		gbc_lbmakh.gridx = 1;
		gbc_lbmakh.gridy = 1;
		panel_east.add(lbmakh, gbc_lbmakh);

		JLabel lblNewLabel = new JLabel("Ngày tạo");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 19));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.ipady = 20;
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(10, 20, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel_east.add(lblNewLabel, gbc_lblNewLabel);

		lbngaytao = new JLabel();
		lbngaytao.setOpaque(true);
		lbngaytao.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_lbngaytao = new GridBagConstraints();
		gbc_lbngaytao.fill = GridBagConstraints.BOTH;
		gbc_lbngaytao.insets = new Insets(10, 0, 5, 20);
		gbc_lbngaytao.gridx = 1;
		gbc_lbngaytao.gridy = 2;
		panel_east.add(lbngaytao, gbc_lbngaytao);

		JLabel lblNewLabel_2 = new JLabel("Nhân viên bán: ");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 19));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.ipady = 20;
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.insets = new Insets(10, 20, 0, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		panel_east.add(lblNewLabel_2, gbc_lblNewLabel_2);

		lbmanv = new JLabel();
		lbmanv.setOpaque(true);
		lbmanv.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_lbmanv = new GridBagConstraints();
		gbc_lbmanv.insets = new Insets(10, 0, 5, 20);
		gbc_lbmanv.fill = GridBagConstraints.BOTH;
		gbc_lbmanv.gridx = 1;
		gbc_lbmanv.gridy = 3;
		panel_east.add(lbmanv, gbc_lbmanv);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(240, 240, 240));
		separator_1.setOpaque(true);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1.ipady = 10;
		gbc_separator_1.gridwidth = 3;
		gbc_separator_1.insets = new Insets(10, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 4;
		panel_east.add(separator_1, gbc_separator_1);

		JLabel lblNewLabel_4 = new JLabel("Ghi chú");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_4.insets = new Insets(0, 20, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 5;
		panel_east.add(lblNewLabel_4, gbc_lblNewLabel_4);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.ipady = 50;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 20, 5, 20);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 6;
		panel_east.add(scrollPane, gbc_scrollPane);

		textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.PLAIN, 18));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setOpaque(true);
		separator_1_1.setBackground(SystemColor.menu);
		GridBagConstraints gbc_separator_1_1 = new GridBagConstraints();
		gbc_separator_1_1.ipady = 10;
		gbc_separator_1_1.gridwidth = 3;
		gbc_separator_1_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1_1.insets = new Insets(10, 0, 5, 0);
		gbc_separator_1_1.gridx = 0;
		gbc_separator_1_1.gridy = 7;
		panel_east.add(separator_1_1, gbc_separator_1_1);

		JLabel lb_1 = new JLabel("Tổng tiền");
		lb_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_1.setFont(new Font("Arial", Font.PLAIN, 24));
		lb_1.setAlignmentX(1.0f);
		GridBagConstraints gbc_lb_1 = new GridBagConstraints();
		gbc_lb_1.anchor = GridBagConstraints.WEST;
		gbc_lb_1.fill = GridBagConstraints.VERTICAL;
		gbc_lb_1.insets = new Insets(10, 20, 5, 5);
		gbc_lb_1.gridx = 0;
		gbc_lb_1.gridy = 8;
		panel_east.add(lb_1, gbc_lb_1);

		lbtongtien = new JLabel("");
		lbtongtien.setHorizontalAlignment(SwingConstants.CENTER);
		lbtongtien.setFont(new Font("Arial", Font.PLAIN, 24));
		lbtongtien.setAlignmentX(1.0f);
		GridBagConstraints gbc_lbtiencongthem = new GridBagConstraints();
		gbc_lbtiencongthem.insets = new Insets(10, 20, 5, 5);
		gbc_lbtiencongthem.gridx = 1;
		gbc_lbtiencongthem.gridy = 8;
		panel_east.add(lbtongtien, gbc_lbtiencongthem);

		JLabel lblNewLabel_9_1 = new JLabel("Giảm giá(%)");
		lblNewLabel_9_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_9_1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblNewLabel_9_1.setAlignmentX(1.0f);
		GridBagConstraints gbc_lblNewLabel_9_1 = new GridBagConstraints();
		gbc_lblNewLabel_9_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_9_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_9_1.insets = new Insets(20, 20, 5, 5);
		gbc_lblNewLabel_9_1.gridx = 0;
		gbc_lblNewLabel_9_1.gridy = 9;
		panel_east.add(lblNewLabel_9_1, gbc_lblNewLabel_9_1);

		lbgiamgia = new JLabel("");
		lbgiamgia.setHorizontalAlignment(SwingConstants.CENTER);
		lbgiamgia.setFont(new Font("Arial", Font.PLAIN, 24));
		lbgiamgia.setAlignmentX(1.0f);
		GridBagConstraints gbc_lbtongtien_2 = new GridBagConstraints();
		gbc_lbtongtien_2.insets = new Insets(20, 20, 5, 5);
		gbc_lbtongtien_2.gridx = 1;
		gbc_lbtongtien_2.gridy = 9;
		panel_east.add(lbgiamgia, gbc_lbtongtien_2);

		JLabel lblNewLabel_9_1_1 = new JLabel("Tiền cộng thêm");
		lblNewLabel_9_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_9_1_1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblNewLabel_9_1_1.setAlignmentX(1.0f);
		GridBagConstraints gbc_lblNewLabel_9_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_9_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_9_1_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_9_1_1.insets = new Insets(20, 20, 5, 5);
		gbc_lblNewLabel_9_1_1.gridx = 0;
		gbc_lblNewLabel_9_1_1.gridy = 10;
		panel_east.add(lblNewLabel_9_1_1, gbc_lblNewLabel_9_1_1);

		lbtiencongthem = new JLabel("");
		lbtiencongthem.setHorizontalAlignment(SwingConstants.CENTER);
		lbtiencongthem.setFont(new Font("Arial", Font.PLAIN, 24));
		lbtiencongthem.setAlignmentX(1.0f);
		GridBagConstraints gbc_lbtiencongthem1 = new GridBagConstraints();
		gbc_lbtiencongthem1.fill = GridBagConstraints.BOTH;
		gbc_lbtiencongthem1.insets = new Insets(20, 20, 5, 5);
		gbc_lbtiencongthem1.gridx = 1;
		gbc_lbtiencongthem1.gridy = 10;
		panel_east.add(lbtiencongthem, gbc_lbtiencongthem1);

		JLabel lblNewLabel_10_1 = new JLabel("Khách phải trả");
		lblNewLabel_10_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_10_1.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_10_1.setAlignmentX(1.0f);
		GridBagConstraints gbc_lblNewLabel_10_1 = new GridBagConstraints();
		gbc_lblNewLabel_10_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_10_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_10_1.insets = new Insets(30, 20, 5, 5);
		gbc_lblNewLabel_10_1.gridx = 0;
		gbc_lblNewLabel_10_1.gridy = 11;
		panel_east.add(lblNewLabel_10_1, gbc_lblNewLabel_10_1);

		lbkhachphaitra = new JLabel("");
		lbkhachphaitra.setHorizontalAlignment(SwingConstants.CENTER);
		lbkhachphaitra.setFont(new Font("Arial", Font.BOLD, 32));
		lbkhachphaitra.setAlignmentX(1.0f);
		GridBagConstraints gbc_lbkhachphaitra_1 = new GridBagConstraints();
		gbc_lbkhachphaitra_1.insets = new Insets(30, 20, 5, 5);
		gbc_lbkhachphaitra_1.gridx = 1;
		gbc_lbkhachphaitra_1.gridy = 11;
		panel_east.add(lbkhachphaitra, gbc_lbkhachphaitra_1);

		/*
		 * 
		 * 
		 */
		getdata_hd(txt);
		getdata_chitietHD(txt);
		thanhtoanHD(txt);
		/*
		 * 
		 * 
		 */
	}

	private void hienthiSP(String i) {
		card.show(this, i);
	}

	private String DinhDangTienTe(double ob) {
		Locale locale = new Locale("en", "EN");
		String pattern = "###,###,###.##";

		DecimalFormat decimal = (DecimalFormat) NumberFormat.getNumberInstance(locale);
		decimal.applyPattern(pattern);
		return decimal.format(ob);
	}
}
