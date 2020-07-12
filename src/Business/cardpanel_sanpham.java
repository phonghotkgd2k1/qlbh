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
import java.io.IOException;
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
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class cardpanel_sanpham extends JPanel {

	private JTable table_sp;
	private JTextField textField_timkiem;
	private JComboBox comboBox;
	private JPopupMenu popupMenu_sp;
	static JTabbedPane tabbedPane;
	////// vi tri tab trong tabbedpane
	static int index;
	private JButton btnthem_sp;
	private JButton btnxoa_sp;
	private JLabel lbrefresh;

	public cardpanel_sanpham() {

		GUI();
		/*
		 * show san pham
		 */
		showSanpham();
	}

	private void GUI() {

		setPreferredSize(new Dimension(1120, 749));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

		table_sp = new JTable();
		popupMenu_sp = new Tablepopup(table_sp);
		popupMenu_sp.setFocusable(false);
		table_sp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					// LUU Y
					int row = table_sp.rowAtPoint(e.getPoint());
					if (row != -1) {
						table_sp.setRowSelectionInterval(row, row);
						///////
						popupMenu_sp.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			}
		});
		table_sp.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table_sp.setFont(new Font("Arial", Font.PLAIN, 22));
		table_sp.setPreferredScrollableViewportSize(new Dimension(350, 400));
		table_sp.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { " ", "Mã Hàng", "Tên Hàng", "DVT", "Ngày khởi tạo" }) {
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		});
		table_sp.getColumnModel().getColumn(0).setMaxWidth(70);
		table_sp.setRowHeight(45);
		table_sp.setFillsViewportHeight(true);

		JTableHeader header = table_sp.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 20));
		header.setBackground(new Color(240, 240, 240));
		header.setOpaque(false);
		header.setPreferredSize(new Dimension(100, 50));

		JScrollPane scrollPane = new JScrollPane(table_sp);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JPanel panel_SP = new JPanel(new BorderLayout());
		panel_SP.add(scrollPane, BorderLayout.CENTER);

		tabbedPane = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT, JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Arial", Font.PLAIN, 18));
		add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.add(panel_SP, "Sản Phẩm", index++);

		JPanel panel_timkiem = new JPanel();
		panel_timkiem.setBackground(Color.WHITE);
		FlowLayout fl_panel_timkiem = (FlowLayout) panel_timkiem.getLayout();
		fl_panel_timkiem.setVgap(10);
		fl_panel_timkiem.setAlignment(FlowLayout.LEFT);
		panel_SP.add(panel_timkiem, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Imager\\timkiem-25.png"));
		panel_timkiem.add(lblNewLabel);

		JSplitPane splitPane = new JSplitPane();
		panel_timkiem.add(splitPane);

		textField_timkiem = new JTextField();
		textField_timkiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String txt = textField_timkiem.getText().trim();
				String dieukien = (String) comboBox.getSelectedItem();
				if (e.getKeyCode() == 10) {
					ds_timkiemSP(txt, dieukien);
				}
			}

			private void ds_timkiemSP(String txt, String dieukien) {
				DefaultTableModel datarow = (DefaultTableModel) table_sp.getModel();
				datarow.setRowCount(0);
				String sql = "select * from MatHang where " + dieukien + " like N'%" + txt + "%' ";
				try {
					Statement st = DangNhap.con.createStatement();
					ResultSet rs = st.executeQuery(sql);

					for (int i = 1; rs.next(); i++) {
						String temp[] = { i + "", rs.getString("mahang"), rs.getString("tenhang"),
								rs.getString("donvitinh"), rs.getString("ngaykhoitao") };
						datarow.addRow(temp);
					}
				} catch (Exception e) {

					System.out.println("cardpanel_sanpham -ds_timkiemSP: " + e.getMessage());
				}
			}
		});
		textField_timkiem.setHorizontalAlignment(SwingConstants.LEFT);
		textField_timkiem.setFont(new Font("Arial", Font.PLAIN, 20));
		textField_timkiem.setColumns(30);
		splitPane.setLeftComponent(textField_timkiem);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "MaHang", "TenHang", "DonViTinh", "NgayKhoiTao" }));
		comboBox.setFont(new Font("Arial", Font.PLAIN, 17));
		splitPane.setRightComponent(comboBox);

		JPanel panel_bar = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_bar.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		flowLayout.setVgap(20);
		flowLayout.setHgap(30);
		add(panel_bar, BorderLayout.NORTH);

		lbrefresh = new JLabel("Refresh");
		lbrefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refresh();
			}
		});
		lbrefresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbrefresh.setIconTextGap(7);
		lbrefresh.setIcon(new ImageIcon("Imager\\refresh-25.png"));
		lbrefresh.setFont(new Font("Arial", Font.PLAIN, 22));
		panel_bar.add(lbrefresh);

		btnthem_sp = new JButton("Thêm");
		btnthem_sp.setEnabled(DangNhap.action_them);
		btnthem_sp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				themsanpham them = new themsanpham();
				them.run();
			}
		});
		btnthem_sp.setBorderPainted(false);
		btnthem_sp.setBorder(null);
		btnthem_sp.setContentAreaFilled(false);
		btnthem_sp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnthem_sp.setIconTextGap(7);
		btnthem_sp.setIcon(new ImageIcon("Imager\\themsp-25.png"));
		btnthem_sp.setFont(new Font("Arial", Font.PLAIN, 22));
		panel_bar.add(btnthem_sp);

		btnxoa_sp = new JButton("Xoá");
		btnxoa_sp.setEnabled(DangNhap.action_xoa);
		btnxoa_sp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int[] soluong = table_sp.getSelectedRows();
				int xacnhan = JOptionPane.showConfirmDialog(null, "xác nhận xóa " + soluong.length + " sản phẩm");
				if (xacnhan == JOptionPane.YES_OPTION)
					xoaSP(soluong);
			}
		});
		btnxoa_sp.setBorder(null);
		btnxoa_sp.setContentAreaFilled(false);
		btnxoa_sp.setBorderPainted(false);
		btnxoa_sp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnxoa_sp.setIconTextGap(7);
		btnxoa_sp.setIcon(new ImageIcon("Imager\\xoa-25.png"));
		btnxoa_sp.setFont(new Font("Arial", Font.PLAIN, 22));
		panel_bar.add(btnxoa_sp);

		JButton btnnhapfile = new JButton("Nhập File");
		btnnhapfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nhapFile();
			}
		});
		btnnhapfile.setEnabled(DangNhap.action_nhapfile);
		btnnhapfile.setBorder(null);
		btnnhapfile.setContentAreaFilled(false);
		btnnhapfile.setBorderPainted(false);
		btnnhapfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnnhapfile.setIconTextGap(5);
		btnnhapfile.setIcon(new ImageIcon("Imager\\nhapfile-30.png"));
		btnnhapfile.setFont(new Font("Arial", Font.PLAIN, 22));
		panel_bar.add(btnnhapfile);

		JButton btnxuatfile = new JButton("Xuất File");
		btnxuatfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				xuatfile();
			}
		});
		btnxuatfile.setEnabled(DangNhap.action_xuatfile);
		btnxuatfile.setBorder(null);
		btnxuatfile.setBorderPainted(false);
		btnxuatfile.setContentAreaFilled(false);
		btnxuatfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnxuatfile.setIconTextGap(5);
		btnxuatfile.setIcon(new ImageIcon("Imager\\xuatfile2-30.png"));
		btnxuatfile.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnxuatfile.setFont(new Font("Arial", Font.PLAIN, 22));
		panel_bar.add(btnxuatfile);

	}

	private void xoaSP(int[] soluong) {
		int size = soluong.length;
		Statement st = null;
		String MASP = null;
		try {
			for (int i = 0; i < size; i++) {
				MASP = (String) table_sp.getValueAt(soluong[i], 1);
				String sql_cthd = "DELETE FROM CHITIETHOADON WHERE MAHANG = N'" + MASP + "'";
				String sql_mh = " DELETE FROM  MATHANG WHERE MAHANG = N'" + MASP + "'";
				st = DangNhap.con.createStatement();
				st.executeUpdate(sql_cthd);
				st.executeUpdate(sql_mh);

			}
			refresh();
		} catch (Exception e) {
			System.out.println("cardpanel_sanpham -xoaSP: " + e.getMessage());
		}
	}

	private void refresh() {
		DefaultTableModel datarow = (DefaultTableModel) table_sp.getModel();
		datarow.setRowCount(0);
		showSanpham();
	}

	private void showSanpham() {
		DefaultTableModel datarow = (DefaultTableModel) table_sp.getModel();
		String sql = "select * from mathang ";
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			for (int i = 1; rs.next(); i++) {
				String temp[] = { i + "", rs.getString("mahang"), rs.getString("tenhang"), rs.getString("donvitinh"),
						rs.getString("ngaykhoitao") };
				datarow.addRow(temp);
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "cardpanel_sanpham - showdata: " + e.getMessage());
		}

	}

	private void nhapFile() {
		FileNameExtensionFilter format = new FileNameExtensionFilter("file(.*)", "txt");
		ChooserFile chooser = new ChooserFile(null, format, false);
		chooser.show();
		String url = chooser.getUrl();
		ArrayList<ob_sanpham> ds = new ArrayList<ob_sanpham>();
		if (url != null) {
			try {

				themsanpham them = new themsanpham();
				FileInputStream f = new FileInputStream(url);
				BufferedReader br = new BufferedReader(new InputStreamReader(f ,"UTF-8") );
				while (true) {
					String txt = br.readLine();
					if (txt == null || txt.equals("")) {
						break;
					}
					String o[] = txt.split("[;|]");
					
					
					ob_sanpham sp = new ob_sanpham();
					sp.setMahang( o[0].trim());
					sp.setTenhang(o[1].trim());
					sp.setGianhap( Double.parseDouble(o[2]));
					sp.setGiabanle(Double.parseDouble(o[3]));
					sp.setGiabansi(Double.parseDouble(o[4]));
					sp.setDonvitinh( o[5].trim());
					sp.setGhichu( o[6].trim());
					sp.setAnh(o[7].trim());
					sp.setNgaykhoitao(o[8].trim());
					
					ds.add(sp);
				}
				them.luuSanPham(ds);

				br.close();
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "lỗi dữ liệu =>"+e.getMessage());
				
			}
		}
	}

	private void xuatfile() {
		String tableName = "MATHANG";
		String mahang = "MAHANG";
		xuatfile xuat = new xuatfile(table_sp, tableName, mahang);
		xuat.run();
	}

	class Tablepopup extends JPopupMenu {

		private JMenuItem item_xem;
		private JMenuItem item_sua;
		JTable table;

		public Tablepopup(JTable table) {
			setFocusable(false);
			this.table = table;
			item_xem = new JMenuItem("Xem", new ImageIcon("Imager\\eyes-18.png"));
			item_xem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String event = e.getActionCommand();
					if (event.equalsIgnoreCase("xem") || event.equalsIgnoreCase("sua")) {
						themTab();
					}
				}
			});
			item_xem.setPreferredSize(new Dimension(250, 30));
			item_xem.setFont(new Font("Arial", Font.PLAIN, 14));
			add(item_xem);

			add(new Separator());
			item_sua = new JMenuItem("Sửa");
			item_sua.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					themTab();
				}
			});
			item_sua.setPreferredSize(new Dimension(250, 30));
			item_sua.setFont(new Font("Arial", Font.PLAIN, 14));
			add(item_sua);
		}
	}

	private void themTab() {
		try {
			int row = table_sp.getSelectedRow();
			chitietSanPham chitiet = new chitietSanPham((String) table_sp.getValueAt(row, 1));
			tabbedPane.add(chitiet, "san pham " + (index), index);
			tabbedPane.setTabComponentAt(index, new customJTabbedpane(this));
			tabbedPane.setSelectedIndex(index);
			index++;
		} catch (Exception e1) {
			System.out.println("cardpanel_sanpham themtab: " + e1.getMessage());
		}
	}

	static void removeTab(int i) {

		tabbedPane.remove(i);
		index--;
	}

	class customJTabbedpane extends JPanel {

		private cardpanel_sanpham customTabbed;

		public customJTabbedpane(cardpanel_sanpham tabbed) {
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
					int i = customTabbed.tabbedPane.indexOfTabComponent(customJTabbedpane.this);
					if (i != -1)
						return customTabbed.tabbedPane.getTitleAt(i);

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
					int i = customTabbed.tabbedPane.indexOfTabComponent(customJTabbedpane.this);
					removeTab(i);
				}
			});

			bt.setIcon(new ImageIcon("Imager\\delete_15px.png"));
			bt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			add(bt);
		}

	}

}
