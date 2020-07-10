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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class chitietKhachHang extends JPanel {
	private JTextField textField_tenKH;
	private JTextField textField_makh;
	private JTextField textField_dienthoai;
	private JTextField textField_diachi;
	private JTable table_lsKH;
	private JComboBox comboBox;
	private JPanel pn_chitietHD;
	private CardLayout card;
	private JLabel lbtitle;
	private int index;
	private JButton btnluukh;

	private void getdataKH(Object txt) {

		String sql = "select * from khachhang where maKH = N'" + txt + "'";
		Statement st;
		try {
			st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				lbtitle.setText(rs.getString("tenKH"));
				textField_tenKH.setText(rs.getString("tenKH"));
				textField_diachi.setText(rs.getString("diachi"));
				textField_dienthoai.setText(rs.getString("dienthoai"));
				textField_makh.setText(rs.getString("maKH"));
				comboBox.setSelectedItem(rs.getString("manhomkh"));
			}
		} catch (SQLException e) {
		}
	}

	public chitietKhachHang(Object txt) throws SQLException {
		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and Feel not set");
		}

		card = new CardLayout();
		setLayout(card);

		pn_chitietHD = new JPanel(new BorderLayout());
		JScrollPane js = new JScrollPane(pn_chitietHD);
		js.getVerticalScrollBar().setUnitIncrement(10);
		
		add(js, Integer.toString(index));
		index++;

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(70);
		flowLayout.setAlignment(FlowLayout.LEFT);
		pn_chitietHD.add(panel, BorderLayout.NORTH);

		lbtitle = new JLabel();
		lbtitle.setFont(new Font("Arial", Font.PLAIN, 27));
		panel.add(lbtitle);

		JPanel panel_1 = new JPanel();
		pn_chitietHD.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lbtenKH = new JLabel("Tên khách hàng");
		lbtenKH.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_lbtenKH = new GridBagConstraints();
		gbc_lbtenKH.fill = GridBagConstraints.VERTICAL;
		gbc_lbtenKH.anchor = GridBagConstraints.WEST;
		gbc_lbtenKH.insets = new Insets(20, 70, 5, 5);
		gbc_lbtenKH.gridx = 0;
		gbc_lbtenKH.gridy = 0;
		panel_1.add(lbtenKH, gbc_lbtenKH);

		textField_tenKH = new JTextField();
		textField_tenKH.setPreferredSize(new Dimension(6, 33));
		textField_tenKH.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_textField_tenKH = new GridBagConstraints();
		gbc_textField_tenKH.insets = new Insets(0, 90, 0, 5);
		gbc_textField_tenKH.fill = GridBagConstraints.BOTH;
		gbc_textField_tenKH.gridx = 0;
		gbc_textField_tenKH.gridy = 1;
		panel_1.add(textField_tenKH, gbc_textField_tenKH);
		textField_tenKH.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Mã khách hàng");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(20, 70, 5, 5);
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField_makh = new JTextField();
		textField_makh.setEditable(false);
		textField_makh.setPreferredSize(new Dimension(6, 33));
		textField_makh.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_textField_makh = new GridBagConstraints();
		gbc_textField_makh.insets = new Insets(0, 90, 5, 150);
		gbc_textField_makh.fill = GridBagConstraints.BOTH;
		gbc_textField_makh.gridx = 0;
		gbc_textField_makh.gridy = 3;
		panel_1.add(textField_makh, gbc_textField_makh);
		textField_makh.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Điện thoại");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.insets = new Insets(20, 70, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 4;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Địa chỉ");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_3.insets = new Insets(20, 70, 0, 0);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 4;
		panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);

		textField_dienthoai = new JTextField();
		textField_dienthoai.setPreferredSize(new Dimension(6, 33));
		textField_dienthoai.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_textField_dienthoai = new GridBagConstraints();
		gbc_textField_dienthoai.insets = new Insets(0, 90, 0, 270);
		gbc_textField_dienthoai.fill = GridBagConstraints.BOTH;
		gbc_textField_dienthoai.gridx = 0;
		gbc_textField_dienthoai.gridy = 5;
		panel_1.add(textField_dienthoai, gbc_textField_dienthoai);
		textField_dienthoai.setColumns(35);

		textField_diachi = new JTextField();
		textField_diachi.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_textField_diachi = new GridBagConstraints();
		gbc_textField_diachi.insets = new Insets(0, 90, 0, 350);
		gbc_textField_diachi.fill = GridBagConstraints.BOTH;
		gbc_textField_diachi.gridx = 1;
		gbc_textField_diachi.gridy = 5;
		panel_1.add(textField_diachi, gbc_textField_diachi);

		JLabel lblNewLabel_2_1 = new JLabel("Nhóm KH");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2_1.insets = new Insets(20, 70, 5, 5);
		gbc_lblNewLabel_2_1.gridx = 0;
		gbc_lblNewLabel_2_1.gridy = 6;
		panel_1.add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);

		comboBox = new JComboBox();
		comboBox.setModel(napdsNKH());
		comboBox.setPreferredSize(new Dimension(31, 33));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 90, 5, 270);
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 7;
		panel_1.add(comboBox, gbc_comboBox);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setBorder(new TitledBorder(null, "L\u1ECBch s\u1EED mua h\u00E0ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(30, 90, 5, 350);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 8;
		panel_1.add(scrollPane, gbc_scrollPane);

		table_lsKH = new JTable();
		table_lsKH.setFillsViewportHeight(true);
		table_lsKH.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_lsKH.setRowHeight(40);
		String header[] = { "Stt", "Mã hóa đơn", "Ngày tạo", "Giảm giá", "Thành tiền" };
		table_lsKH.setModel(new DefaultTableModel(header, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table_lsKH.getColumn("Stt").setMaxWidth(70);
		table_lsKH.getColumn("Mã hóa đơn").setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public void setForeground(Color c) {
				super.setForeground(Color.blue);
			}
		});
		table_lsKH.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				int column = table_lsKH.columnAtPoint(e.getPoint());
				if (column == 1) {
					table_lsKH.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				} else {
					table_lsKH.setCursor(null);
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {

			}
		});
		table_lsKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int mouse = e.getButton();
				int row = table_lsKH.rowAtPoint(e.getPoint());
				int column = table_lsKH.getSelectedColumn();

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
									hienthiHD(Integer.toString(0));
								}
							}
						});
						lbquaylai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						lbquaylai.setIcon(new ImageIcon("Imager\\back_25px.png"));
						lbquaylai.setToolTipText("Quay lại khách hàng");
						pn_them.add(lbquaylai);
						pn.add(new chitietDonHang(table_lsKH.getValueAt(row, column)), BorderLayout.CENTER);
						add(pn, Integer.toString(index));
						hienthiHD(Integer.toString(index));
						index++;
					} catch (Exception e2) {

					}
				}
			}
		});
		table_lsKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane.setViewportView(table_lsKH);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		flowLayout_1.setVgap(20);
		flowLayout_1.setHgap(70);
		pn_chitietHD.add(panel_2, BorderLayout.SOUTH);

		btnluukh = new JButton("Lưu");
		btnluukh.setEnabled(DangNhap.action_sua);
		btnluukh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "update khachhang set  tenkh =? , diachi =? , dienthoai =? , manhomkh = ? from khachhang where makh = ?";
					PreparedStatement pr = DangNhap.con.prepareStatement(sql);
					pr.setString(1, textField_tenKH.getText().trim());
					pr.setString(2, textField_diachi.getText().trim());
					pr.setString(3, textField_dienthoai.getText().trim());
					pr.setString(4, (String) comboBox.getSelectedItem());
					pr.setString(5, textField_makh.getText());
					pr.executeUpdate();

					JOptionPane.showMessageDialog(null, " luu khach hang thanh cong ");
					refresh();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "loi them khach hang ");
					System.out.println("chitietkhachhang - btnluuKH: " + e2.getMessage());
				}
			}
		});
		btnluukh.setPreferredSize(new Dimension(70, 33));
		btnluukh.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_2.add(btnluukh);

		/*
		 * 
		 * nap danh sach vao table
		 */
		lichsuKH(txt);
		getdataKH(txt);
	}

	private void refresh() {
		int index = cardpanel_khachhang.tabbedPane_chitietKH.getSelectedIndex();
		cardpanel_khachhang.removeTab(index);
	}

	private DefaultComboBoxModel<String> napdsNKH() {
		DefaultComboBoxModel<String> ds = new DefaultComboBoxModel<String>();
		try {
			String sql = "select manhomkh from nhomkh ";
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				ds.addElement(rs.getString(1));
			}
			return ds;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "loi: nap danh sach nhomKH ");
		}
		return null;
	}

	private void lichsuKH(Object txt) {
		
		String sql = "select hd.MaHD,hd.NgayTao , hd.GiamGia ,sum(cthd.ThanhTien * (100 - hd.GiamGia)/100) thanhtien from hoadonban hd left join ChiTietHoaDon cthd on hd.MaHD = cthd.MaHD WHERE MAKH = N'"
				+ txt + "' group by hd.MaHD ,hd.MaNV ,hd.NgayTao ,hd.GiamGia";

		DefaultTableModel datarow = (DefaultTableModel) table_lsKH.getModel();
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			for (int i = 1; rs.next(); i++) {
				String temp[] = { i + "", rs.getString("mahd"), rs.getString("ngaytao"), rs.getFloat("giamgia") + "",
						rs.getDouble("thanhtien") + "" };
				datarow.addRow(temp);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "loi: nap danh sach lich su mua hang ");
			System.out.println("chitietKhachHang lichsuKH: " + e.getMessage());
		}

	}

	private void hienthiHD(String i) {
		card.show(this, i);
	}
}
