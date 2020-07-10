package Business;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

public class jframe_quanlyNhanVien extends JFrame {

	private JPanel panel;
	private JTextField textField_timkiem;
	private JTable table_nv;
	private JButton btnluu;
	private JMenuItem mntm_nv;
	private JMenuItem mntm_ctqh;
	private JMenuItem mntm_thoat;
	private JMenuItem mntm_themnv;
	private JMenuItem mntm_themqh;
	private executed ect;
	private JTable table;

	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private JMenuBar menubar() {
		ect = new executed(DangNhap.con);

		JMenuBar menuBar = new JMenuBar();

		JMenu mnView = new JMenu("Hiển thị");
		mnView.setFont(new Font("Arial", Font.BOLD, 15));
		menuBar.add(mnView);

		mntm_nv = new JMenuItem("Nhân viên");
		mntm_nv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				performed = e.getActionCommand();
				refresh();

			}
		});
		mntm_nv.setPreferredSize(new Dimension(250, 35));
		mntm_nv.setFont(new Font("Arial", Font.BOLD, 15));
		mnView.add(mntm_nv);

		mntm_ctqh = new JMenuItem("Chi Tiết Quyền hạn");
		mntm_ctqh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				performed = e.getActionCommand();
				refresh();

			}
		});

		JMenuItem mntm_qh = new JMenuItem("Quyền hạn");
		mntm_qh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				performed = e.getActionCommand();
				refresh();
			}
		});
		mntm_qh.setPreferredSize(new Dimension(250, 35));
		mntm_qh.setFont(new Font("Arial", Font.BOLD, 15));
		mnView.add(mntm_qh);
		mntm_ctqh.setPreferredSize(new Dimension(250, 35));
		mntm_ctqh.setFont(new Font("Arial", Font.BOLD, 15));
		mnView.add(mntm_ctqh);

		mntm_thoat = new JMenuItem("Thoát");
		mntm_thoat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		JSeparator separator = new JSeparator();
		mnView.add(separator);
		mntm_thoat.setPreferredSize(new Dimension(250, 35));
		mntm_thoat.setFont(new Font("Arial", Font.BOLD, 15));
		mnView.add(mntm_thoat);

		JMenu mnThem = new JMenu("Thêm");
		mnThem.setFont(new Font("Arial", Font.BOLD, 15));
		menuBar.add(mnThem);

		mntm_themnv = new JMenuItem("Thêm nhân viên");
		mntm_themnv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ThemNhanVien them = new ThemNhanVien();
				them.run();
			}
		});
		mntm_themnv.setPreferredSize(new Dimension(250, 35));
		mntm_themnv.setFont(new Font("Arial", Font.BOLD, 15));
		mnThem.add(mntm_themnv);

		mntm_themqh = new JMenuItem("Thêm quyền hạn");
		mntm_themqh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ThemQuyenHan them = new ThemQuyenHan();
				them.run();

			}
		});
		mntm_themqh.setFont(new Font("Arial", Font.BOLD, 15));
		mnThem.add(mntm_themqh);
		return menuBar;
	}

	public jframe_quanlyNhanVien() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1369, 756);

		setJMenuBar(menubar());

		panel = new JPanel(new BorderLayout());
		setContentPane(panel);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {

				// chỉ cho mở 1 trang frame thêm nhân viên 1 lần
				MenuChinh.check = false;
				MenuChinh.mntmQLNhanVien.setEnabled(MenuChinh.check);

				performed = "Nhân viên";
				refresh();
			}

			@Override
			public void windowClosing(WindowEvent e) {
				MenuChinh.check = true;
			}
		});

		north();
		Center();
		South();
	}

	private void north() {
		JPanel panel_north = new JPanel();
		panel_north.setBackground(Color.WHITE);
		getContentPane().add(panel_north, BorderLayout.NORTH);
		GridBagLayout gbl_panel_north = new GridBagLayout();
		gbl_panel_north.columnWidths = new int[] { 25, 461, 0, 0, 0 };
		gbl_panel_north.rowHeights = new int[] { 25, 0, 0 };
		gbl_panel_north.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_north.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_north.setLayout(gbl_panel_north);

		JLabel lbicon = new JLabel("");
		lbicon.setBackground(Color.WHITE);
		lbicon.setIcon(new ImageIcon("Imager\\timkiem-25.png"));
		GridBagConstraints gbc_lbicon = new GridBagConstraints();
		gbc_lbicon.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbicon.insets = new Insets(15, 20, 10, 5);
		gbc_lbicon.gridx = 0;
		gbc_lbicon.gridy = 0;
		panel_north.add(lbicon, gbc_lbicon);

		textField_timkiem = new JTextField();
		textField_timkiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (performed.equals("Nhân viên")) {
					textField_timkiem.setToolTipText("tìm kiếm theo tên nhân viên");
				} else if (performed.equals("Quyền hạn")) {
					textField_timkiem.setToolTipText("Tìm kiếm theo tên quyền hạn");
				} else if (performed.equals("Chi Tiết Quyền hạn")) {
					textField_timkiem.setToolTipText("Tìm kiếm theo  mã quyền hạn");
				}
				if (e.getKeyCode() == 10) {

					String h[] = null;
					String txt = textField_timkiem.getText();
					String sql = "";
					if (performed.equalsIgnoreCase("Nhân viên")) {

						h = new String[] { "nhanvien", "phanquyen" };
						String f = field(getColumnName(h));
						sql = "select n." + f
								+ " from nhanvien n  join phanquyen p on n.manhanvien = p.manhanvien where n.tennhanvien like N'%"
								+ txt + "%'";
					} else if (performed.equalsIgnoreCase("Quyền hạn")) {

						h = new String[] { "quyenhan" };
						String f = field(getColumnName(h));
						sql = "select " + f + " from quyenhan where tenquyenhan like N'%" + txt + "%'";
					} else if (performed.equalsIgnoreCase("chi tiết Quyền hạn")) {

						h = new String[] { "chitietquyenhan" };
						String f = field(getColumnName(h));
						sql = "select " + f + " from  chitietquyenhan  where maquyenhan like N'%" + txt + "%'";
					}

					timkiem(sql);
				}
			}

		});
		textField_timkiem.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_textField_timkiem = new GridBagConstraints();
		gbc_textField_timkiem.gridwidth = 3;
		gbc_textField_timkiem.insets = new Insets(15, 5, 10, 0);
		gbc_textField_timkiem.anchor = GridBagConstraints.NORTHWEST;
		gbc_textField_timkiem.gridx = 1;
		gbc_textField_timkiem.gridy = 0;
		panel_north.add(textField_timkiem, gbc_textField_timkiem);
		textField_timkiem.setColumns(35);

		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(Color.WHITE);
		toolBar.setBorder(null);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.fill = GridBagConstraints.BOTH;
		gbc_toolBar.insets = new Insets(0, 0, 10, 5);
		gbc_toolBar.gridx = 1;
		gbc_toolBar.gridy = 1;
		panel_north.add(toolBar, gbc_toolBar);

		JLabel lbrefresh = new JLabel("");
		lbrefresh.setToolTipText("tải lại bảng");
		lbrefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refresh();
			}
		});
		lbrefresh.setIcon(new ImageIcon("Imager\\tailai2-25.png"));
		toolBar.add(lbrefresh);

		toolBar.addSeparator(new Dimension(20, 5));

		/*
		 * phải xóa chitiethoadon - hoadonban - phanquyen - nhanvien lâu quá nên chưa
		 * làm
		 */
//		JLabel lbxoa = new JLabel("");
//		lbxoa.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//
//				int ds[] = table_nv.getSelectedRows();
//				int size = ds.length;
//				int lc = JOptionPane.showConfirmDialog(null, "xác nhận xóa: " + size);
//				if (lc == JOptionPane.YES_OPTION) {
//					String sql;
//					ArrayList<String> dsID = new ArrayList<String>();
//					try {
//						DangNhap.con.setAutoCommit(false);
//						if (performed.equals("Nhân viên")) {
//							for (int i = 0; i < size; i++) {
//								dsID.add((String) table.getValueAt(ds[i],
//										table.getColumn("MaNhanVien").getModelIndex()));
//
//							}
//							sql = "delete from phanquyen where manhanvien = ?";
//							xoa(sql, dsID);
//							sql = "delete from nhanvien where manhanvien =? ";
//							xoa(sql, dsID);
//						} else if (performed.equals("Quyền hạn")) {
//							for (int i = 0; i < size; i++) {
//								// dsID.add((String) table.getValueAt(ds[i],
//								// table.getColumn("MaNhanVien").getModelIndex()));
//
//							}
//						}
//						
//						DangNhap.con.commit();
//						
//					} catch (Exception e1) {
//						JOptionPane.showMessageDialog(null,"jframe_ThemNhanVien - xoaNV: " + e1.getMessage());
//						try {
//							DangNhap.con.rollback();
//						} catch (SQLException e2) {
//							e2.printStackTrace();
//						}
//					}
//					
//					try {
//						DangNhap.con.setAutoCommit(true);
//					} catch (SQLException e1) {
//						e1.printStackTrace();
//					}
//				}
//			}
//		});
//		lbxoa.setToolTipText("xóa nhân viên");
//		lbxoa.setVisible(DangNhap.action_xoa);
//		lbxoa.setIcon(new ImageIcon("Imager\\xoa-25.png"));
//		toolBar.add(lbxoa);

	}

	private void Center() {
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setRowHeight(35);

		table.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
	}

	private void South() {
		JPanel panel_South = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_South.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_South, BorderLayout.SOUTH);

		btnluu = new JButton("Lưu thay đổi");
		btnluu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String field[] = null;
				String sql = null;

				try {

					DangNhap.con.setAutoCommit(false);

					if (performed.equalsIgnoreCase("Nhân viên")) {

						field = getColumnName(new String[] { "nhanvien" });
						sql = "update nhanvien set tennhanvien = ?, diachi = ?, email = ?, ngaylamviec =? ,luongcoban =?, phucap =? , matkhau =? ,dienthoai=? where manhanvien = ?";

						LuuThayDoi(field, sql, "MaNhanVien");

						field = getColumnName(new String[] { "phanquyen" });
						sql = "update phanquyen set maquyenhan =?, hoatdong=? where manhanvien =?";

						LuuThayDoi(field, sql, "MaNhanVien");

					} else if (performed.equalsIgnoreCase("Quyền hạn")) {

						field = getColumnName(new String[] { "quyenhan" });

						sql = "update quyenhan set tenquyenhan = ? where maquyenhan = ?";
						LuuThayDoi(field, sql, "MaQuyenHan");

					} else if (performed.equalsIgnoreCase("Chi tiết quyền hạn")) {

						field = getColumnName(new String[] { "chitietquyenhan" });
						sql = "update chitietquyenhan set  maquyenhan = ? , chucnang =? , chophep = ? where machitietQH = ? ";
						LuuThayDoi(field, sql, "MaChiTietQH");
					}

					DangNhap.con.commit();
					JOptionPane.showMessageDialog(null, "chỉnh sửa thành công");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Sai định dạng", JOptionPane.ERROR_MESSAGE);
					System.out.println("jframe_themnhanvien - luuthaydoi: " + e.getMessage());
					try {
						DangNhap.con.rollback();
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
					}
				}
				try {
					DangNhap.con.setAutoCommit(true);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		});
		btnluu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_South.add(btnluu);
	}

	private void napDanhSach(String sql) {

		try {

			ResultSet rs = DangNhap.con.createStatement().executeQuery(sql);
			DefaultTableModel datarow = (DefaultTableModel) table.getModel();
			int columnCount = datarow.getColumnCount();
			Vector<String> vector = null;
			while (rs.next()) {
				vector = new Vector<String>();
				for (int k = 0; k < columnCount; k++) {

					vector.addElement(rs.getString(datarow.getColumnName(k)));
				}
				datarow.addRow(vector);
			}
			rs.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "lỗi nạp danh sách");
			System.out.println("jframe_themnhanvien napDanhSach: " + e.getMessage());
		}
	}

	private void timkiem(String sql) {

		DefaultTableModel datarow = (DefaultTableModel) table.getModel();
		datarow.setRowCount(0);
		Vector<String> vector = null;
		int columnCount = datarow.getColumnCount();

		try {

			ResultSet rs = DangNhap.con.createStatement().executeQuery(sql);
			while (rs.next()) {
				vector = new Vector<String>();
				for (int k = 0; k < columnCount; k++) {

					vector.add(rs.getString(datarow.getColumnName(k)));
				}
				datarow.addRow(vector);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("jframe_themnhanvien timkiemnv: " + e.getMessage());
		}
	}

	private void xoa(String sql, ArrayList<String> dsID) throws SQLException {

		int size = dsID.size();

		PreparedStatement pr = DangNhap.con.prepareStatement(sql);
		for (int i = 0; i < size; i++) {

			pr.setString(1, dsID.get(i));
			pr.addBatch();
		}
		pr.executeBatch();
		pr.close();
		/*
		 * refresh
		 */
	}

	private void setTable() {

		if (performed.equalsIgnoreCase("Nhân viên")) {
			String data[] = new String[napMaQuyenHan().size()];
			data = napMaQuyenHan().toArray(data);
			JComboBox<String> cell = new JComboBox<String>();
			cell.setFont(new Font("Arial", Font.PLAIN, 17));
			cell.setModel(new DefaultComboBoxModel<String>(data));
			table.getColumn("MaQuyenHan").setCellEditor(new DefaultCellEditor(cell));
			JTextField txt = new JTextField();
			txt.setToolTipText("định dạng: năm-tháng-ngày");
			table.getColumn("NgayLamViec").setCellEditor(new DefaultCellEditor(txt));
		}
	}

	private JTable xulyColumnnName(JTable tab, String field[]) {
		JTable tableTemp = new JTable();
		tableTemp.setModel(tab.getModel());
		int size = tableTemp.getColumnCount();
		ArrayList<String> ds = new ArrayList<String>();

		for (int i = 0; i < size; i++) {
			ds.add(tableTemp.getColumnName(i));
		}

		for (int i = 0; i < field.length; i++) {
			/*
			 * new tần số xuất hiện >=1 thì xóa
			 */
			if (Collections.frequency(ds, field[i]) >= 1) {
				ds.remove(field[i]);
			}
		}

		for (int k = 0; k < ds.size(); k++) {

			for (int i = 0; i < tableTemp.getColumnCount(); i++) {

				if (tableTemp.getColumnName(i).equalsIgnoreCase(ds.get(k))) {
					tableTemp.removeColumn(tableTemp.getColumnModel().getColumn(i));
				}
			}
		}
		return tableTemp;
	}

	private ArrayList<String> napMaQuyenHan() {
		ArrayList<String> data = new ArrayList<String>();
		String sql = "select maquyenhan from quyenhan ";
		try {
			ResultSet rs = DangNhap.con.createStatement().executeQuery(sql);
			while (rs.next()) {
				data.add(rs.getString(1));
			}
			rs.close();
		} catch (Exception e) {

		}
		return data;
	}

	private void LuuThayDoi(String[] field, String sql, String key) throws Exception {

		int columnCount = field.length;
		JTable tableT = xulyColumnnName(table, field);
		int size = tableT.getRowCount();

		PreparedStatement pr = DangNhap.con.prepareStatement(sql);
		for (int i = 0; i < size; i++) {
			int k = 1;
			for (; k < columnCount; k++) {
				String name = tableT.getColumnName(k);

				if (name.equalsIgnoreCase("LuongCoBan") || name.equalsIgnoreCase("PhuCap")) {

					pr.setDouble(k, Double.parseDouble((String) tableT.getValueAt(i, k)));
				} else if (name.equalsIgnoreCase("HoatDong")) {

					pr.setInt(k, Integer.parseInt((String) tableT.getValueAt(i, k)));
				} else if (name.equalsIgnoreCase("ngaylamviec")) {

					pr.setString(k, ngaylamviec((String) tableT.getValueAt(i, k)));
				} else {

					pr.setString(k, (String) tableT.getValueAt(i, k));
				}
			}

			pr.setString(k, (String) tableT.getValueAt(i, tableT.getColumn(key).getModelIndex()));
			pr.executeUpdate();
		}

	}

	private void refresh() {

		String h[] = null;
		String sql = "";
		if (performed.equals("Nhân viên")) {
			h = new String[] { "nhanvien", "phanquyen" };
			table.setModel(CustomDefault(h));
			String f = field(getColumnName(h));
			sql = "select P." + f + " from nhanvien N  join phanquyen P on N.manhanvien = P.manhanvien ";

		} else if (performed.equals("Quyền hạn")) {
			h = new String[] { "quyenhan" };
			table.setModel(CustomDefault(h));
			String f = field(getColumnName(h));

			sql = "select " + f + " from  QUYENHAN  ";

		} else if (performed.equals("Chi Tiết Quyền hạn")) {
			h = new String[] { "chitietquyenhan" };
			table.setModel(CustomDefault(h));
			String f = field(getColumnName(h));
			sql = "select " + f + " from chitietquyenhan ";

		}
		napDanhSach(sql);
		setTable();
	}

	private String ngaylamviec(String d) throws Exception {

		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = simple.parse(d);
		return simple.format(d1);
	}

	private String[] getColumnName(String h[]) {
		/*
		 * nap ten cot
		 */
		LinkedHashSet<String> linkedHashSet = new LinkedHashSet<String>();
		ArrayList<String> name = new ArrayList<String>();
		int size = h.length;
		for (int i = 0; i < size; i++) {
			name = ect.getTenCot(h[i]);
			linkedHashSet.addAll(name);
		}

		String header[] = new String[linkedHashSet.size()];
		header = linkedHashSet.toArray(header);
		return header;

	}

	private DefaultTableModel CustomDefault(String[] column) {
		DefaultTableModel d = new DefaultTableModel(getColumnName(column), 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (performed.equalsIgnoreCase("nhân viên")) {
					if (column == 0)
						return false;
				} else if (performed.equalsIgnoreCase("quyền hạn")) {
					if (column == 0)
						return false;
				} else if (performed.equalsIgnoreCase("chi tiết quyền hạn")) {
					if (column != 3)
						return false;
				}

				return true;
			}
		};
		return d;
	}

	private String field(String[] f) {
		String tempString = "";
		int size = f.length;
		for (int i = 0; i < size; i++) {
			tempString += f[i];
			if (i == (size - 1))
				break;
			tempString += ",";
		}
		return tempString;
	}

	private String performed;

}
