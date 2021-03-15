package Business;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class cardpanel_khachhang extends JPanel {
	private JTable table_KH;
	private JPanel panel_cardlayout;
	private JTextField txt_timkiemNKH;
	private JTextField txt_timkiem;
	private JTable table_NKH;
	private JComboBox comboBox;
	private JComboBox comboBoxNKH;
	private JPopupMenu popupMenu;
	static int index;
	static JTabbedPane tabbedPane_chitietKH;
	private JTabbedPane tabbedPane;
	private JButton btnthem_kh_nkh;
	private JLabel lblRefresh;
	private JButton btnxoa_kh;

	public cardpanel_khachhang() {
		GUI();
		showKH();
		showNKH();
	}

	private void GUI() {

		setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT, JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Arial", Font.PLAIN, 18));
		add(tabbedPane, BorderLayout.CENTER);

		JPanel panel_bar = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_bar.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		flowLayout.setVgap(20);
		flowLayout.setHgap(30);
		add(panel_bar, BorderLayout.NORTH);

		lblRefresh = new JLabel("Refresh");
		lblRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refresh();
			}
		});
		lblRefresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRefresh.setIcon(new ImageIcon("Imager\\refresh-25.png"));
		lblRefresh.setIconTextGap(7);
		lblRefresh.setFont(new Font("Arial", Font.PLAIN, 22));
		panel_bar.add(lblRefresh);

		btnthem_kh_nkh = new JButton("Thêm");
		btnthem_kh_nkh.setEnabled(DangNhap.action_them);
		btnthem_kh_nkh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabbedPane.getSelectedIndex() == 0) {
					themkhachhang them = new themkhachhang();
					them.run();
				} else if (tabbedPane.getSelectedIndex() == 1) {
					themnhomkhachhang them = new themnhomkhachhang();
					them.run();
				}
			}
		});
		btnthem_kh_nkh.setBorder(null);
		btnthem_kh_nkh.setBorderPainted(false);
		btnthem_kh_nkh.setContentAreaFilled(false);
		btnthem_kh_nkh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnthem_kh_nkh.setIconTextGap(7);
		btnthem_kh_nkh.setIcon(new ImageIcon("Imager\\themsp-25.png"));
		btnthem_kh_nkh.setFont(new Font("Arial", Font.PLAIN, 22));
		panel_bar.add(btnthem_kh_nkh);

		btnxoa_kh = new JButton("Xóa");
		btnxoa_kh.setEnabled(DangNhap.action_xoa);
		btnxoa_kh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabbedPane.getSelectedIndex() == 0) {
					int[] soluong = table_KH.getSelectedRows();
					if (soluong.length > 0) {
						int xacnhan = JOptionPane.showConfirmDialog(null,
								"xác nhận xóa " + soluong.length + "khách hàng");
						if (xacnhan == JOptionPane.YES_OPTION)
							xoaKH(soluong);
					}
				} else if (tabbedPane.getSelectedIndex() == 1) {
					int[] soluong = table_NKH.getSelectedRows();
					if (soluong.length > 0) {
						int xacnhan = JOptionPane.showConfirmDialog(null,
								"xác nhận xóa " + soluong.length + " Nhóm khách hàng");
						if (xacnhan == JOptionPane.YES_OPTION)
							xoaNKH(soluong);
					}
				}
			}
		});
		btnxoa_kh.setBorder(null);
		btnxoa_kh.setBorderPainted(false);
		btnxoa_kh.setContentAreaFilled(false);
		btnxoa_kh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnxoa_kh.setIconTextGap(7);
		btnxoa_kh.setIcon(new ImageIcon("Imager\\xoa-25.png"));
		btnxoa_kh.setFont(new Font("Arial", Font.PLAIN, 22));
		panel_bar.add(btnxoa_kh);

//		JButton btnnhapfile = new JButton("Nhập File");
//		btnnhapfile.setEnabled(DangNhap.action_nhapfile);
//		btnnhapfile.setBorder(null);
//		btnnhapfile.setBorderPainted(false);
//		btnnhapfile.setContentAreaFilled(false);
//		btnnhapfile.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				FileNameExtensionFilter format = new FileNameExtensionFilter("file(.*)", "txt");
//				ChooserFile chooser = new ChooserFile(format, false);
//				chooser.show();
//
//				String url = chooser.getUrl();
//				if (url != null) {
//					ArrayList<String> ds = new ArrayList<String>();
//					String data = null;
//					try {
//						FileInputStream f = new FileInputStream(url);
//						BufferedReader b = new BufferedReader(new InputStreamReader(f, "UTF-8"));
//
//						while (true) {
//							data = b.readLine();
//							if (data == null || data.equals("")) {
//								break;
//							}
//							ds.add(data);
//						}
//						themkhachhang them = new themkhachhang();
//						them.themkhachhang(ds);
//						b.close();
//					} catch (Exception e2) {
//						JOptionPane.showMessageDialog(null, "lỗi đọc file: " + e2.getMessage());
//					}
//
//				}
//			}
//		});
//		btnnhapfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		btnnhapfile.setIconTextGap(5);
//		btnnhapfile.setIcon(new ImageIcon("Imager\\nhapfile-30.png"));
//		btnnhapfile.setFont(new Font("Arial", Font.PLAIN, 22));
//		panel_bar.add(btnnhapfile);
//
//		JButton btnxuatfile = new JButton("Xuất File");
//		btnxuatfile.setEnabled(DangNhap.action_xuatfile);
//		btnxuatfile.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String tableName = "khachhang";
//				String id = "makh";
//				xuatfile xuat = new xuatfile(table_KH, tableName, id);
//				xuat.run();
//			}
//		});
//		btnxuatfile.setBorder(null);
//		btnxuatfile.setBorderPainted(false);
//		btnxuatfile.setContentAreaFilled(false);
//		btnxuatfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		btnxuatfile.setIconTextGap(5);
//		btnxuatfile.setIcon(new ImageIcon("Imager\\xuatfile2-30.png"));
//		btnxuatfile.setFont(new Font("Arial", Font.PLAIN, 22));
//		panel_bar.add(btnxuatfile);

		JPanel panel_KH = new JPanel();
		tabbedPane.addTab("Khách Hàng", panel_KH);
		tabbedPane.setEnabledAt(0, true);
		panel_KH.setLayout(new BorderLayout(0, 0));

		JPanel panel_timkiem = new JPanel();
		panel_timkiem.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) panel_timkiem.getLayout();
		flowLayout_1.setVgap(10);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_KH.add(panel_timkiem, BorderLayout.NORTH);

		JLabel lbicon = new JLabel("");
		lbicon.setIcon(new ImageIcon("Imager\\timkiem-25.png"));
		panel_timkiem.add(lbicon);

		JSplitPane splitPane = new JSplitPane();
		panel_timkiem.add(splitPane);

		txt_timkiem = new JTextField();
		txt_timkiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				String txt = txt_timkiem.getText().trim();
				String dieukien = (String) comboBox.getSelectedItem();
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {

					ds_timkiemKH(txt, dieukien);
				}
			}
		});
		txt_timkiem.setFont(new Font("Arial", Font.PLAIN, 20));
		splitPane.setLeftComponent(txt_timkiem);
		txt_timkiem.setColumns(30);

		comboBox = new JComboBox();
		comboBox.setFocusable(false);
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "MaKH", "Tenkh", "DiaChi", "DienThoai", "MaNhomKH" }));
		comboBox.setFont(new Font("Arial", Font.PLAIN, 15));
		comboBox.setMaximumRowCount(15);
		splitPane.setRightComponent(comboBox);

		table_KH = new JTable();
		table_KH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {

					////// LUU Ý
					int row = table_KH.rowAtPoint(e.getPoint());
					table_KH.setRowSelectionInterval(row, row);
					//////
					popupMenu.show(e.getComponent(), e.getX(), e.getY());

				}

			}
		});
		table_KH.setFont(new Font("Arial", Font.PLAIN, 19));
		table_KH.setFillsViewportHeight(true);
		table_KH.setRowHeight(40);
		table_KH.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "", "M\u00E3 Kh\u00E1ch H\u00E0ng", "T\u00EAn Kh\u00E1ch H\u00E0ng",
						"\u0110\u1ECBa Ch\u1EC9", "\u0110i\u1EC7n Tho\u1EA1i", "M\u00E3 Nh\u00F3m KH" }) {
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		});
		table_KH.getColumn("").setMaxWidth(70);

		JTableHeader header = table_KH.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 20));
		header.setBackground(new Color(240, 240, 240));
		header.setOpaque(false);
		header.setPreferredSize(new Dimension(100, 50));

		tabbedPane_chitietKH = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT, JTabbedPane.TOP);

		popupMenu = new Tablepopup(table_KH);
		table_KH.add(popupMenu);
		JScrollPane scrollPane = new JScrollPane(table_KH);
		scrollPane.setBackground(Color.white);
		tabbedPane_chitietKH.add(scrollPane, "Danh Sách", index++);
		// panel_KH.add(scrollPane, BorderLayout.CENTER);
		panel_KH.add(tabbedPane_chitietKH, BorderLayout.CENTER);
		// panel 2
		//
		//
		//
		JPanel panel_NKH = new JPanel();
		tabbedPane.addTab("Nhóm Khách Hàng", panel_NKH);
		tabbedPane.setEnabledAt(1, true);
		panel_NKH.setLayout(new BorderLayout(0, 0));

		JPanel panel_timkiem1 = new JPanel();
		panel_timkiem1.setBackground(Color.WHITE);
		FlowLayout flowLayout2 = (FlowLayout) panel_timkiem1.getLayout();
		flowLayout2.setVgap(10);
		flowLayout2.setAlignment(FlowLayout.LEFT);
		panel_NKH.add(panel_timkiem1, BorderLayout.NORTH);

		JLabel lbicon1 = new JLabel("");
		lbicon1.setIcon(new ImageIcon("Imager\\timkiem-25.png"));
		panel_timkiem1.add(lbicon1);

		JSplitPane splitPane1 = new JSplitPane();
		panel_timkiem1.add(splitPane1);

		txt_timkiemNKH = new JTextField();
		txt_timkiemNKH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				String txt = txt_timkiemNKH.getText().trim();
				String dieukien = (String) comboBoxNKH.getSelectedItem();
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {

					ds_timkiemNKH(txt, dieukien);
				}
			}
		});
		txt_timkiemNKH.setFont(new Font("Arial", Font.PLAIN, 20));
		splitPane1.setLeftComponent(txt_timkiemNKH);
		txt_timkiemNKH.setColumns(30);

		comboBoxNKH = new JComboBox();
		comboBoxNKH.setFocusable(false);
		comboBoxNKH.setModel(new DefaultComboBoxModel(new String[] { "MaNhomKH", "TenNhomKH" }));
		comboBoxNKH.setFont(new Font("Arial", Font.PLAIN, 15));
		comboBoxNKH.setMaximumRowCount(15);
		splitPane1.setRightComponent(comboBoxNKH);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_NKH.add(scrollPane_1, BorderLayout.CENTER);

		table_NKH = new JTable();
		table_NKH.setFont(new Font("Arial", Font.PLAIN, 17));
		table_NKH.setFillsViewportHeight(true);
		table_NKH.setRowHeight(30);
		table_NKH.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "", "M\u00E3 Nh\u00F3m KH", "T\u00EAn Nh\u00F3m KH", "Ghi Ch\u00FA" }));
		table_NKH.getColumn("").setMaxWidth(70);
		scrollPane_1.setViewportView(table_NKH);
		JTableHeader header_NKH = table_NKH.getTableHeader();
		header_NKH.setFont(new Font("Arial", Font.PLAIN, 20));
		header_NKH.setBackground(new Color(177, 209, 245));

	}

	private void showKH() {
		int index = 1;
		String sql = " select * from khachhang";
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			DefaultTableModel row = (DefaultTableModel) table_KH.getModel();
			while (rs.next()) {
				String data[] = { index++ + "", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5) };
				row.addRow(data);
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), " lỗi kết nối bảng khách hàng",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void showNKH() {
		int index = 1;
		String sql = " select * from nhomkh";
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			DefaultTableModel row = (DefaultTableModel) table_NKH.getModel();
			while (rs.next()) {
				String data[] = { index++ + "", rs.getString(1), rs.getString(2), rs.getString(3) };
				row.addRow(data);
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), " lỗi kết nối bảng mã khách hàng",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void ds_timkiemKH(String txt, String dieukien) {
		String sql = "SELECT * FROM KhachHang WHERE " + dieukien + " LIKE N'%" + txt + "%'";
		DefaultTableModel datarow = (DefaultTableModel) table_KH.getModel();
		datarow.setRowCount(0);
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			for (int i = 1; rs.next(); i++) {

				String temp[] = { i + "", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5) };
				datarow.addRow(temp);
			}

		} catch (Exception e) {
			System.out.println("cardpanel_khachhang -ds tim kiem kh: " + e.getMessage());
		}
	}

	private void ds_timkiemNKH(String txt, String dieukien) {
		String sql = "SELECT * FROM NhomKH WHERE " + dieukien + " LIKE N'%" + txt + "%'";
		DefaultTableModel datarow = (DefaultTableModel) table_NKH.getModel();
		datarow.setRowCount(0);
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			for (int i = 1; rs.next(); i++) {

				String temp[] = { i + "", rs.getString(1), rs.getString(2), rs.getString(3) };
				datarow.addRow(temp);
			}

		} catch (Exception e) {
			System.out.println("cardpanel_khachhang  -ds tim kiem nkh: " + e.getMessage());
		}
	}

	private void xoaKH(int[] soluong) {
		int size = soluong.length;
		Statement st = null;
		try {
			String sql = "(";
			st = DangNhap.con.createStatement();
			for (int i = 0; i < size; i++)
				sql += "N'" + (String) table_KH.getValueAt(soluong[i], 1) + "',";
			// xoa dau , cuoi cung
			sql = sql.substring(0, sql.length() - 1);
			sql += ")";
			ResultSet rs = st.executeQuery("select count(*) from hoadonban where makh in " + sql);
			if (rs.next()) {
				if (rs.getInt(1) == 0)
					st.executeUpdate("delete from khachhang where makh in " + sql);
				else
					JOptionPane.showMessageDialog(null,
							"các Khách hàng đang tồn tại trong hóa đơn,bạn phải xóa các đơn hàng của khách hàng cần xóa trước.");
			}
			refresh();
		} catch (Exception e) {
			System.out.println("cardpanel_donhang -xoaDH: " + e.getMessage());
		}
	}
	
	private void xoaNKH(int[] soluong) {
		int size = soluong.length;
		Statement st = null;
		try {
			String sql = "(";
			st = DangNhap.con.createStatement();
			for (int i = 0; i < size; i++)
				sql += "N'" + (String) table_NKH.getValueAt(soluong[i], 1) + "',";
			// xoa dau , cuoi cung
			sql = sql.substring(0, sql.length() - 1);
			sql += ")";
			ResultSet rs = st.executeQuery("select count(*) from khachhang where MaNhomkh in " + sql);
			if (rs.next()) {
				if (rs.getInt(1) == 0)
					st.executeUpdate("delete from nhomkh where MaNhomKh in " + sql);
				else
					JOptionPane.showMessageDialog(null,
							"không thể xóa, trước tiên phải xóa các khách hàng có mã nhóm khách hàng muốn xóa.");
			}
			refresh();
		} catch (Exception e) {
			System.out.println("cardpanel_donhang -xoaNKH: " + e.getMessage());
		}
	}
	
	private void refresh() {

		// lấy chỉ mục đang chọn
		int i = tabbedPane.getSelectedIndex();

		if (i == 0) {
			DefaultTableModel datarow = (DefaultTableModel) table_KH.getModel();
			datarow.setRowCount(0);
			showKH();
		} else if (i == 1) {
			DefaultTableModel datarow = (DefaultTableModel) table_NKH.getModel();
			datarow.setRowCount(0);
			showNKH();
		}

	}

	class Tablepopup extends JPopupMenu {

		JMenuItem item_xem;

		public Tablepopup(JTable table) {

			item_xem = new JMenuItem("Xem", new ImageIcon("Imager\\eyes-18.png"));
			item_xem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					themTab();
				}
			});
			item_xem.setPreferredSize(new Dimension(250, 30));
			item_xem.setFont(new Font("Arial", Font.PLAIN, 14));
			add(item_xem);

		}
	}

	private void themTab() {
		try {
			int row = table_KH.getSelectedRow();
			chitietKhachHang chitiet = new chitietKhachHang(table_KH.getValueAt(row, 1));
			tabbedPane_chitietKH.add(chitiet, "san pham " + (index), index);
			tabbedPane_chitietKH.setTabComponentAt(index, new customJTabbedpane(this));
			tabbedPane_chitietKH.setSelectedIndex(index);
			index++;
		} catch (Exception e1) {
			System.out.println("cardpanel_khachhang: " + e1.getMessage());
		}
	}

	static void removeTab(int i) {
		tabbedPane_chitietKH.remove(i);
		index--;
	}

	class customJTabbedpane extends JPanel {

		private cardpanel_khachhang customTabbed;

		public customJTabbedpane(cardpanel_khachhang tabbed) {
			this.customTabbed = tabbed;
			setLayout(new FlowLayout(FlowLayout.LEFT, 7, 5));
			setOpaque(false);
			setBorder(null);
			addlabel();
			addbutton();
		}

		private void addlabel() {
			JLabel lb = new JLabel() {
				public String getText() { // GHI DE PHUONG THUC getText();
					int i = customTabbed.tabbedPane_chitietKH.indexOfTabComponent(customJTabbedpane.this);
					if (i != -1)
						return customTabbed.tabbedPane_chitietKH.getTitleAt(i);
					return null;
				}
			};
			add(lb);
		}

		private void addbutton() {
			JButton bt = new JButton();
			// duong vien
			bt.setBorderPainted(false);

			// noi dung
			bt.setContentAreaFilled(false);
			bt.setPreferredSize(new Dimension(20, 20));
			bt.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					int i = customTabbed.tabbedPane_chitietKH.indexOfTabComponent(customJTabbedpane.this);
					removeTab(i);
				}
			});

			bt.setIcon(new ImageIcon("Imager\\delete_15px.png"));
			bt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			add(bt);
		}
	}

}
