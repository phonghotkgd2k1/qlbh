package Business;

import java.awt.BorderLayout;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class DonHang extends JPanel {
	private JTextField textField_timkiemKH;
	private JTextField textField_timkiemSP;
	private JTable table;
	private JTextField textField_nhanvien;
	private JTextField textField_mahd;
	private JTextField textField_giamgia;
	private JTextField textField_tienkhachdua;
	private JComboBox<Integer> comboBox_ngay;
	private JComboBox<Integer> comboBox_thang;
	private JComboBox<Integer> comboBox_nam;
	private JList list_SP;
	private JComboBox comboBox_DieuKien;
	private JPopupMenu popupMenu_SP;
	private JLabel lbKH;
	private JLabel lbsdt;
	private JLabel lbdiachi;
	private JLabel lbnhomKH;
	private JFormattedTextField textField_soluong;
	private JTextField textField_dongia;
	private jframe_banhang jframe;

	private void panelOpen() {
		try {
			textField_nhanvien.setText(
					DangNhap.rs_taikhoan.getString("manhanvien") + "-" + DangNhap.rs_taikhoan.getString("tennhanvien"));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void capnhatNgayThang() {
		NgayThang day = new NgayThang();

		String dateString[] = day.getDate().split("[ ]");

		day.Ngay();
		comboBox_ngay.setModel(day.getNgay());
		comboBox_ngay.setSelectedItem(Integer.parseInt(dateString[0]));

		day.Thang();
		comboBox_thang.setModel(day.getThang());
		comboBox_thang.setSelectedItem(Integer.parseInt(dateString[1]));

		day.Nam();
		comboBox_nam.setModel(day.getNam());
		comboBox_nam.setSelectedItem(Integer.parseInt(dateString[2]));
	}

	public DonHang(jframe_banhang jframe) throws ParseException, SQLException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and Feel not set");
		}
		this.jframe = jframe;
		/*
		 * 
		 */
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		add(scrollPane, BorderLayout.CENTER);

		JPanel panel_tong = new JPanel();
		scrollPane.setViewportView(panel_tong);
		GridBagLayout gbl_panel_tong = new GridBagLayout();
		gbl_panel_tong.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_tong.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_tong.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_tong.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		panel_tong.setLayout(gbl_panel_tong);

		JPanel panel_table = new JPanel();
		GridBagConstraints gbc_panel_table = new GridBagConstraints();
		gbc_panel_table.gridheight = 3;
		gbc_panel_table.insets = new Insets(25, 10, 5, 10);
		gbc_panel_table.fill = GridBagConstraints.BOTH;
		gbc_panel_table.gridx = 0;
		gbc_panel_table.gridy = 0;
		panel_tong.add(panel_table, gbc_panel_table);
		panel_table.setLayout(new BorderLayout(0, 10));

		JPanel panel_north = new JPanel();
		panel_north.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_north.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel_north.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_table.add(panel_north, BorderLayout.NORTH);

		JToolBar toolBar_north = new JToolBar();
		toolBar_north.setBorder(null);
		toolBar_north.setRollover(true);
		toolBar_north.setFloatable(false);
		panel_north.add(toolBar_north);

		JLabel lbicon_timkiem = new JLabel("");
		lbicon_timkiem.setOpaque(true);
		lbicon_timkiem.setIcon(new ImageIcon("Imager\\timkiem-25.png"));
		lbicon_timkiem.setBackground(Color.white);

		toolBar_north.add(lbicon_timkiem);

		textField_timkiemSP = new JTextField();
		textField_timkiemSP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				String txt = textField_timkiemSP.getText().trim();
				String dieukien = (String) comboBox_DieuKien.getSelectedItem();
				if (!txt.isEmpty()) {
					if (rdbtn_barcode.isSelected() && e.getKeyCode() == 10) {
						napthongtinSP(txt);
						popupMenu_SP.setVisible(false);
						textField_timkiemSP.selectAll();

					} else if (rdbtn_barcode.isSelected() == false) {
						list_SP.setModel(ds_timkiemSP(txt, dieukien));
						popupMenu_SP.show(textField_timkiemSP, 0, textField_timkiemSP.getHeight());
					}
				} else {
					popupMenu_SP.setVisible(false);
				}
			}
		});
		textField_timkiemSP.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField_timkiemSP.selectAll();
			}
		});

		textField_timkiemSP.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_timkiemSP.setFont(new Font("Arial", Font.PLAIN, 18));
		toolBar_north.add(textField_timkiemSP);
		textField_timkiemSP.setColumns(40);

		popupMenu_SP = new JPopupMenu();
		popupMenu_SP.setFocusable(false);
		popupMenu_SP.setBorder(null);
		popupMenu_SP.setPopupSize(new Dimension(350, 370));

		JScrollPane scrollPane_listSP = new JScrollPane();
		popupMenu_SP.add(scrollPane_listSP);

		list_SP = new JList();
		list_SP.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				list_SP.setSelectedIndex(list_SP.locationToIndex(e.getPoint()));
			}
		});
		list_SP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1 && list_SP.getSelectedIndex() != -1) {
					String temp = (String) list_SP.getSelectedValue();
					String data[] = temp.split("-");
					napthongtinSP(data[0]);
					popupMenu_SP.setVisible(false);
				}
			}
		});
		list_SP.setBorder(null);
		list_SP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		list_SP.setFixedCellHeight(30);
		list_SP.setFont(new Font("Arial", Font.PLAIN, 17));
		list_SP.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_listSP.setViewportView(list_SP);

		comboBox_DieuKien = new JComboBox();
		comboBox_DieuKien.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBox_DieuKien.setModel(new DefaultComboBoxModel(new String[] { "Mahang", "TenHang" }));
		toolBar_north.add(comboBox_DieuKien);

		JSeparator separator_kc1 = new JSeparator();
		separator_kc1.setOpaque(true);
		separator_kc1.setPreferredSize(new Dimension(30, 2));
		separator_kc1.setOrientation(SwingConstants.VERTICAL);
		toolBar_north.add(separator_kc1);

		rdbtn_barcode = new JRadioButton("Barcode");
		rdbtn_barcode.setBackground(Color.WHITE);
		rdbtn_barcode.setSelected(true);
		rdbtn_barcode.setFont(new Font("Tahoma", Font.BOLD, 14));
		toolBar_north.add(rdbtn_barcode);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setFont(new Font("Arial", Font.PLAIN, 17));
		panel_table.add(scrollPane_1, BorderLayout.CENTER);

		JPopupMenu popupMenu_table = new TablePopup();

		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(450, 390));
		table.setBorder(null);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {

					int row = table.rowAtPoint(e.getPoint());
					if (row > -1)
						table.setRowSelectionInterval(row, row);
					popupMenu_table.show(table, e.getX(), e.getY());
				}
			}
		});
		table.setRowHeight(50);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 22));
		/*
		 * default mode
		 */

		String header[] = { "Mã Sản phẩm", "Tên sản phẩm", "DVT", "Số lượng", "Đơn giá", "Thành tiền" };
		DefaultTableModel tableModel = new DefaultTableModel(header, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return (column == 3 || column == 4);
			}
		};
		table.setModel(tableModel);
		table.getColumn("DVT").setMaxWidth(70);
		table.getColumn("Số lượng").setMaxWidth(100);

		/*
		 * THEM JTextfield vao table cell
		 */
		TableColumn column_soluong = table.getColumnModel().getColumn(3);
		textField_soluong = new JFormattedTextField();
		textField_soluong.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_soluong.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				tongtien();
			}
		});
		column_soluong.setCellEditor(new DefaultCellEditor(textField_soluong));

		TableColumn column_dongia = table.getColumnModel().getColumn(4);
		textField_dongia = new JTextField();
		textField_dongia.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_dongia.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				tongtien();
			}
		});
		column_dongia.setCellEditor(new DefaultCellEditor(textField_dongia));
		/*
		 * 
		 */
		table.setModel(tableModel);
		table.getColumn("DVT").setMaxWidth(70);
		scrollPane_1.setViewportView(table);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 10);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 1;
		gbc_tabbedPane.gridy = 0;
		panel_tong.add(tabbedPane, gbc_tabbedPane);

		popupMenu_KH = new JPopupMenu();
		popupMenu_KH.setFocusable(false);
		popupMenu_KH.setPopupSize(new Dimension(350, 370));

		JScrollPane scrollPane_KH = new JScrollPane();
		popupMenu_KH.add(scrollPane_KH);

		list_KH = new JList();
		list_KH.setBorder(null);
		list_KH.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		list_KH.setFont(new Font("Tahoma", Font.PLAIN, 17));

		list_KH.setFixedCellHeight(30);
		list_KH.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				// lay row con tro đang trỏ tới
				list_KH.setSelectedIndex(list_KH.locationToIndex(e.getPoint()));
			}
		});
		list_KH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1 && list_KH.getSelectedIndex() != -1) {
					String temp = (String) list_KH.getSelectedValue();

					String data[] = temp.split("-");

					thongtinKH(data[0]);
					popupMenu_KH.setVisible(false);
				}
			}
		});
		scrollPane_KH.setViewportView(list_KH);

		JPanel panel_khachhang = new JPanel();
		panel_khachhang.setPreferredSize(new Dimension(400, 10));
		panel_khachhang.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_khachhang.setBackground(Color.WHITE);
		tabbedPane.addTab("Khách hàng", null, panel_khachhang, null);
		GridBagLayout gbl_panel_khachhang = new GridBagLayout();
		gbl_panel_khachhang.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_khachhang.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_khachhang.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_khachhang.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel_khachhang.setLayout(gbl_panel_khachhang);

		JLabel lblNewLabel_4 = new JLabel("Thông tin khách hàng");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.ipady = 20;
		gbc_lblNewLabel_4.gridwidth = 2;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 0;
		panel_khachhang.add(lblNewLabel_4, gbc_lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("Imager\\timkiem-25.png"));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 20, 5, 5);
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 1;
		panel_khachhang.add(lblNewLabel_5, gbc_lblNewLabel_5);

		textField_timkiemKH = new JTextField();
		textField_timkiemKH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				popupMenu_KH.setVisible(false);
				String txt = textField_timkiemKH.getText().trim();
				if (!txt.isEmpty()) {
					list_KH.setModel(ds_timkiemKH(txt));
					popupMenu_KH.show(separator_KH, 20, separator_KH.getHeight());
				} else {
					popupMenu_KH.setVisible(false);
				}
			}
		});
		textField_timkiemKH.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField_timkiemKH.selectAll();
			}
		});
		textField_timkiemKH.setBorder(null);
		textField_timkiemKH.setFont(new Font("Arial", Font.PLAIN, 18));

		GridBagConstraints gbc_textField_timkiemKH = new GridBagConstraints();
		gbc_textField_timkiemKH.insets = new Insets(0, 0, 5, 30);
		gbc_textField_timkiemKH.ipady = 10;
		gbc_textField_timkiemKH.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_timkiemKH.gridx = 1;
		gbc_textField_timkiemKH.gridy = 1;
		panel_khachhang.add(textField_timkiemKH, gbc_textField_timkiemKH);
		textField_timkiemKH.setColumns(10);

		separator_KH = new JSeparator();
		GridBagConstraints gbc_separator_KH = new GridBagConstraints();
		gbc_separator_KH.fill = GridBagConstraints.BOTH;
		gbc_separator_KH.insets = new Insets(0, 0, 5, 20);
		gbc_separator_KH.gridx = 1;
		gbc_separator_KH.gridy = 3;
		panel_khachhang.add(separator_KH, gbc_separator_KH);

		lbIcon_xoaKH = new JLabel("");
		lbIcon_xoaKH.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbIcon_xoaKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_timkiemKH.setText("");
				lbKH.setText("");
				lbdiachi.setText("");
				lbnhomKH.setText("");
				lbsdt.setText("");

			}
		});
		lbIcon_xoaKH.setIcon(new ImageIcon("Imager\\delete_20px.png"));
		GridBagConstraints gbc_lbIcon_xoa = new GridBagConstraints();
		gbc_lbIcon_xoa.fill = GridBagConstraints.VERTICAL;
		gbc_lbIcon_xoa.anchor = GridBagConstraints.EAST;
		gbc_lbIcon_xoa.insets = new Insets(0, 0, 5, 20);
		gbc_lbIcon_xoa.gridx = 1;
		gbc_lbIcon_xoa.gridy = 4;
		panel_khachhang.add(lbIcon_xoaKH, gbc_lbIcon_xoa);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("Imager\\user.png"));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_6.ipady = 10;
		gbc_lblNewLabel_6.insets = new Insets(20, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 5;
		panel_khachhang.add(lblNewLabel_6, gbc_lblNewLabel_6);

		lbKH = new JLabel();
		lbKH.setForeground(Color.BLUE);
		lbKH.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lbKH = new GridBagConstraints();
		gbc_lbKH.fill = GridBagConstraints.BOTH;
		gbc_lbKH.insets = new Insets(20, 30, 5, 0);
		gbc_lbKH.gridx = 1;
		gbc_lbKH.gridy = 5;
		panel_khachhang.add(lbKH, gbc_lbKH);

		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(0, 4));
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.ipady = 2;
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.insets = new Insets(0, 0, 5, 20);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 6;
		panel_khachhang.add(separator, gbc_separator);

		lbsdt = new JLabel();
		lbsdt.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lbsdt = new GridBagConstraints();
		gbc_lbsdt.fill = GridBagConstraints.BOTH;
		gbc_lbsdt.ipady = 20;
		gbc_lbsdt.insets = new Insets(0, 0, 5, 0);
		gbc_lbsdt.gridx = 1;
		gbc_lbsdt.gridy = 7;
		panel_khachhang.add(lbsdt, gbc_lbsdt);

		lbdiachi = new JLabel();
		lbdiachi.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lbdiachi = new GridBagConstraints();
		gbc_lbdiachi.fill = GridBagConstraints.BOTH;
		gbc_lbdiachi.ipady = 20;
		gbc_lbdiachi.insets = new Insets(0, 0, 5, 0);
		gbc_lbdiachi.gridx = 1;
		gbc_lbdiachi.gridy = 8;
		panel_khachhang.add(lbdiachi, gbc_lbdiachi);

		lbnhomKH = new JLabel();
		lbnhomKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lbnhomKH = new GridBagConstraints();
		gbc_lbnhomKH.insets = new Insets(0, 0, 5, 0);
		gbc_lbnhomKH.fill = GridBagConstraints.BOTH;
		gbc_lbnhomKH.ipady = 20;
		gbc_lbnhomKH.gridx = 1;
		gbc_lbnhomKH.gridy = 9;
		panel_khachhang.add(lbnhomKH, gbc_lbnhomKH);

		JPanel panel_morong = new JPanel();
		panel_morong.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_morong.setBackground(Color.WHITE);
		tabbedPane.addTab("Mở rộng", null, panel_morong, null);
		GridBagLayout gbl_panel_morong = new GridBagLayout();
		gbl_panel_morong.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_morong.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_morong.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_morong.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		panel_morong.setLayout(gbl_panel_morong);

		JLabel lblNewLabel_11 = new JLabel("Thông tin đơn hàng");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 17));
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_11.gridwidth = 2;
		gbc_lblNewLabel_11.ipadx = 20;
		gbc_lblNewLabel_11.ipady = 10;
		gbc_lblNewLabel_11.gridx = 0;
		gbc_lblNewLabel_11.gridy = 0;
		panel_morong.add(lblNewLabel_11, gbc_lblNewLabel_11);

		JLabel lblNewLabel_12_1 = new JLabel("Ngày tạo");
		lblNewLabel_12_1.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_12_1 = new GridBagConstraints();
		gbc_lblNewLabel_12_1.ipady = 10;
		gbc_lblNewLabel_12_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_12_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_12_1.insets = new Insets(10, 10, 5, 20);
		gbc_lblNewLabel_12_1.gridx = 1;
		gbc_lblNewLabel_12_1.gridy = 1;
		panel_morong.add(lblNewLabel_12_1, gbc_lblNewLabel_12_1);

		JToolBar toolBar = new JToolBar();
		toolBar.setBorder(UIManager.getBorder("TextField.border"));
		toolBar.setFont(new Font("Arial", Font.PLAIN, 16));
		toolBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.gridwidth = 2;
		gbc_toolBar.ipady = 10;
		gbc_toolBar.fill = GridBagConstraints.BOTH;
		gbc_toolBar.insets = new Insets(0, 10, 5, 10);
		gbc_toolBar.gridx = 1;
		gbc_toolBar.gridy = 2;
		panel_morong.add(toolBar, gbc_toolBar);

		comboBox_ngay = new JComboBox();
		comboBox_ngay.setBorder(null);
		comboBox_ngay.setFont(new Font("Arial", Font.PLAIN, 17));
		comboBox_ngay.setToolTipText("Ngày");
		toolBar.add(comboBox_ngay);

		comboBox_thang = new JComboBox<Integer>();
		comboBox_thang.setBorder(null);
		comboBox_thang.setFont(new Font("Arial", Font.PLAIN, 17));
		comboBox_thang.setToolTipText("Tháng");
		toolBar.add(comboBox_thang);

		comboBox_nam = new JComboBox<Integer>();
		comboBox_nam.setBorder(null);
		comboBox_nam.setFont(new Font("Arial", Font.PLAIN, 17));
		comboBox_nam.setToolTipText("Năm");
		toolBar.add(comboBox_nam);

		JLabel lblNewLabel_12_1_1 = new JLabel("Nhân viên");
		lblNewLabel_12_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_12_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_12_1_1.ipady = 10;
		gbc_lblNewLabel_12_1_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_12_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_12_1_1.insets = new Insets(10, 10, 5, 20);
		gbc_lblNewLabel_12_1_1.gridx = 1;
		gbc_lblNewLabel_12_1_1.gridy = 3;
		panel_morong.add(lblNewLabel_12_1_1, gbc_lblNewLabel_12_1_1);

		textField_nhanvien = new JTextField();
		panelOpen();
		textField_nhanvien.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String data = textField_nhanvien.getText().trim();
				if (!data.isEmpty()) {

					list_NV.setModel(ds_timkiemNV(data));
					popupMenu_NV.show(textField_nhanvien, 10, textField_nhanvien.getHeight());
				} else {
					popupMenu_NV.setVisible(false);
				}
			}
		});
		textField_nhanvien.setBorder(null);
		textField_nhanvien.setFont(new Font("Arial", Font.PLAIN, 18));

		popupMenu_NV = new JPopupMenu();
		popupMenu_NV.setFocusable(false);
		popupMenu_NV.setBorder(null);
		popupMenu_NV.setPopupSize(new Dimension(337, 300));

		JScrollPane scrollPane_NV = new JScrollPane();
		popupMenu_NV.add(scrollPane_NV);

		list_NV = new JList<String>();
		list_NV.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				list_NV.setSelectedIndex(list_NV.locationToIndex(e.getPoint()));
			}
		});
		list_NV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1 && list_NV.getSelectedIndex() != -1) {
					textField_nhanvien.setText(list_NV.getSelectedValue());
					popupMenu_NV.setVisible(false);
				}
			}
		});
		list_NV.setFont(new Font("Arial", Font.PLAIN, 18));
		list_NV.setBorder(null);
		scrollPane_NV.setViewportView(list_NV);

		lbIcon_xoaNV = new JLabel("");
		lbIcon_xoaNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					textField_nhanvien.setText("");
				}
			}
		});
		lbIcon_xoaNV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbIcon_xoaNV.setIcon(new ImageIcon("Imager\\delete_20px.png"));
		GridBagConstraints gbc_lbIcon_xoaNV = new GridBagConstraints();
		gbc_lbIcon_xoaNV.insets = new Insets(0, 0, 5, 10);
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lbIcon_xoaNV.gridx = 2;
		gbc_lbIcon_xoaNV.gridy = 6;
		panel_morong.add(lbIcon_xoaNV, gbc_lbIcon_xoaNV);
		GridBagConstraints gbc_textField_nhanvien = new GridBagConstraints();
		gbc_textField_nhanvien.ipady = 10;
		gbc_textField_nhanvien.insets = new Insets(0, 10, 5, 10);
		gbc_textField_nhanvien.fill = GridBagConstraints.BOTH;
		gbc_textField_nhanvien.gridx = 1;
		gbc_textField_nhanvien.gridy = 6;
		panel_morong.add(textField_nhanvien, gbc_textField_nhanvien);
		textField_nhanvien.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1.insets = new Insets(0, 10, 20, 0);
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 7;
		panel_morong.add(separator_1, gbc_separator_1);

		JToolBar toolBar_banggia = new JToolBar();
		toolBar_banggia.setBackground(Color.WHITE);
		toolBar_banggia.setBorder(null);
		GridBagConstraints gbc_toolBar_banggia = new GridBagConstraints();
		gbc_toolBar_banggia.fill = GridBagConstraints.BOTH;
		gbc_toolBar_banggia.insets = new Insets(0, 10, 10, 10);
		gbc_toolBar_banggia.gridx = 1;
		gbc_toolBar_banggia.gridy = 8;
		panel_morong.add(toolBar_banggia, gbc_toolBar_banggia);
//
//		buttongroup = new ButtonGroup();
//
//		rdbtngiale = new JRadioButton("Giá bán lẻ");
//		rdbtngiale.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try {
//					giabanle();
//				} catch (SQLException e1) {
//
//					System.out.println("DONHANG - rdbtngiale: " + e1.getMessage());
//				}
//			}
//		});
//		rdbtngiale.setBackground(Color.WHITE);
//		rdbtngiale.setSelected(true);
//		rdbtngiale.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		rdbtngiale.setFont(new Font("Arial", Font.BOLD, 16));
//		toolBar_banggia.add(rdbtngiale);
//		buttongroup.add(rdbtngiale);
//
//		rdbtngiasi = new JRadioButton("Gía bán sỉ");
//		rdbtngiasi.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try {
//					giabansi();
//				} catch (SQLException e1) {
//					System.out.println("DONHANG - rdbtngiasi: " + e1.getMessage());
//				}
//			}
//		});
//		rdbtngiasi.setBackground(Color.WHITE);
//		rdbtngiasi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		rdbtngiasi.setFont(new Font("Arial", Font.BOLD, 16));
//		toolBar_banggia.add(rdbtngiasi);
//		buttongroup.add(rdbtngiasi);

		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.ipady = 20;
		gbc_scrollPane_2.gridwidth = 2;
		gbc_scrollPane_2.insets = new Insets(0, 10, 5, 10);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 1;
		gbc_scrollPane_2.gridy = 9;
		panel_morong.add(scrollPane_2, gbc_scrollPane_2);

		JTextArea textArea_ghichu = new JTextArea();
		textArea_ghichu.setTabSize(9);
		textArea_ghichu.setWrapStyleWord(true);
		textArea_ghichu.setLineWrap(true);
		textArea_ghichu.setFont(new Font("Arial", Font.PLAIN, 16));
		textArea_ghichu
				.setBorder(new TitledBorder(null, "Ghi ch\u00FA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_2.setViewportView(textArea_ghichu);

		JPanel panel_tinhtien = new JPanel();
		panel_tinhtien.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_tinhtien.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_tinhtien = new GridBagConstraints();
		gbc_panel_tinhtien.insets = new Insets(0, 0, 5, 10);
		gbc_panel_tinhtien.fill = GridBagConstraints.BOTH;
		gbc_panel_tinhtien.gridx = 1;
		gbc_panel_tinhtien.gridy = 1;
		panel_tong.add(panel_tinhtien, gbc_panel_tinhtien);
		GridBagLayout gbl_panel_tinhtien = new GridBagLayout();
		gbl_panel_tinhtien.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_tinhtien.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_tinhtien.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_tinhtien.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_tinhtien.setLayout(gbl_panel_tinhtien);

		JLabel lblNewLabel = new JLabel("Thành tiền:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(10, 20, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_tinhtien.add(lblNewLabel, gbc_lblNewLabel);

		lbthanhtien = new JLabel("0.0");
		lbthanhtien.setFont(new Font("Arial", Font.PLAIN, 27));
		GridBagConstraints gbc_lbthanhtien = new GridBagConstraints();
		gbc_lbthanhtien.ipady = 10;
		gbc_lbthanhtien.fill = GridBagConstraints.BOTH;
		gbc_lbthanhtien.insets = new Insets(10, 20, 5, 30);
		gbc_lbthanhtien.gridx = 1;
		gbc_lbthanhtien.gridy = 0;
		panel_tinhtien.add(lbthanhtien, gbc_lbthanhtien);

		JLabel lblNewLabel_2 = new JLabel("Giảm giá(%):");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.insets = new Insets(20, 20, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		panel_tinhtien.add(lblNewLabel_2, gbc_lblNewLabel_2);

		textField_giamgia = new JTextField();
		textField_giamgia.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				kiemtra_giamgia();
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
		textField_giamgia.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10 && textField_giamgia != null)
					kiemtra_giamgia();

			}

			@Override
			public void keyPressed(KeyEvent e) {
				//

			}
		});
		textField_giamgia.setBorder(new LineBorder(new Color(171, 173, 179), 1, true));
		textField_giamgia.setText("0.0");
		textField_giamgia.setFont(new Font("Arial", Font.PLAIN, 27));
		GridBagConstraints gbc_textField_giamgia = new GridBagConstraints();
		gbc_textField_giamgia.ipady = 10;
		gbc_textField_giamgia.insets = new Insets(20, 20, 5, 30);
		gbc_textField_giamgia.fill = GridBagConstraints.BOTH;
		gbc_textField_giamgia.gridx = 1;
		gbc_textField_giamgia.gridy = 1;
		panel_tinhtien.add(textField_giamgia, gbc_textField_giamgia);
		textField_giamgia.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Tiền cộng thêm");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2_1.insets = new Insets(15, 20, 5, 5);
		gbc_lblNewLabel_2_1.gridx = 0;
		gbc_lblNewLabel_2_1.gridy = 2;
		panel_tinhtien.add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);

		textField_tiencongthem = new JTextField();
		textField_tiencongthem.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					kiemtra_tiencongthem();
				}
			}
		});
		textField_tiencongthem.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				kiemtra_tiencongthem();
			}

			@Override
			public void focusGained(FocusEvent e) {
				textField_tiencongthem.selectAll();
			}
		});
		textField_tiencongthem.setText("0.0");
		textField_tiencongthem.setFont(new Font("Arial", Font.PLAIN, 27));
		textField_tiencongthem.setColumns(10);
		textField_tiencongthem.setBorder(new LineBorder(new Color(171, 173, 179), 1, true));
		GridBagConstraints gbc_textField_tiencongthem = new GridBagConstraints();
		gbc_textField_tiencongthem.insets = new Insets(15, 20, 5, 30);
		gbc_textField_tiencongthem.fill = GridBagConstraints.BOTH;
		gbc_textField_tiencongthem.gridx = 1;
		gbc_textField_tiencongthem.gridy = 2;
		panel_tinhtien.add(textField_tiencongthem, gbc_textField_tiencongthem);

		JLabel lblNewLabel_13 = new JLabel("Khách phải trả:");
		lblNewLabel_13.setFont(new Font("Arial", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
		gbc_lblNewLabel_13.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_13.insets = new Insets(20, 20, 5, 5);
		gbc_lblNewLabel_13.gridx = 0;
		gbc_lblNewLabel_13.gridy = 3;
		panel_tinhtien.add(lblNewLabel_13, gbc_lblNewLabel_13);

		lbkhachphaitra = new JLabel("0");
		lbkhachphaitra.setFont(new Font("Arial", Font.BOLD, 32));
		GridBagConstraints gbc_lbkhachphaitra = new GridBagConstraints();
		gbc_lbkhachphaitra.ipady = 10;
		gbc_lbkhachphaitra.fill = GridBagConstraints.BOTH;
		gbc_lbkhachphaitra.insets = new Insets(20, 20, 5, 30);
		gbc_lbkhachphaitra.gridx = 1;
		gbc_lbkhachphaitra.gridy = 3;
		panel_tinhtien.add(lbkhachphaitra, gbc_lbkhachphaitra);

		JLabel lblNewLabel_15 = new JLabel("Tiền khách đưa:");
		lblNewLabel_15.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_15 = new GridBagConstraints();
		gbc_lblNewLabel_15.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_15.insets = new Insets(20, 20, 5, 5);
		gbc_lblNewLabel_15.gridx = 0;
		gbc_lblNewLabel_15.gridy = 4;
		panel_tinhtien.add(lblNewLabel_15, gbc_lblNewLabel_15);

		textField_tienkhachdua = new JTextField("0");
		textField_tienkhachdua.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					tienkhachdua();
			}
		});
		textField_tienkhachdua.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				tienkhachdua();
			}

			@Override
			public void focusGained(FocusEvent e) {
				textField_tienkhachdua.selectAll();

			}
		});
		textField_tienkhachdua.setBorder(new LineBorder(new Color(171, 173, 179), 1, true));
		textField_tienkhachdua.setText("0.0");
		textField_tienkhachdua.setFont(new Font("Arial", Font.PLAIN, 27));
		GridBagConstraints gbc_textField_tienkhachdua = new GridBagConstraints();
		gbc_textField_tienkhachdua.ipady = 10;
		gbc_textField_tienkhachdua.insets = new Insets(20, 20, 5, 30);
		gbc_textField_tienkhachdua.fill = GridBagConstraints.BOTH;
		gbc_textField_tienkhachdua.gridx = 1;
		gbc_textField_tienkhachdua.gridy = 4;
		panel_tinhtien.add(textField_tienkhachdua, gbc_textField_tienkhachdua);
		textField_tienkhachdua.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("Tiền thừa");
		lblNewLabel_16.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_16 = new GridBagConstraints();
		gbc_lblNewLabel_16.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_16.insets = new Insets(20, 20, 5, 5);
		gbc_lblNewLabel_16.gridx = 0;
		gbc_lblNewLabel_16.gridy = 5;
		panel_tinhtien.add(lblNewLabel_16, gbc_lblNewLabel_16);

		lbtienthua = new JLabel("0");
		lbtienthua.setFont(new Font("Arial", Font.PLAIN, 30));
		GridBagConstraints gbc_lbtienthua = new GridBagConstraints();
		gbc_lbtienthua.ipady = 10;
		gbc_lbtienthua.fill = GridBagConstraints.BOTH;
		gbc_lbtienthua.insets = new Insets(20, 20, 5, 30);
		gbc_lbtienthua.gridx = 1;
		gbc_lbtienthua.gridy = 5;
		panel_tinhtien.add(lbtienthua, gbc_lbtienthua);

		JButton btnTao = new JButton("Thanh toán(alt + f)");
		btnTao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql_HD = "INSERT INTO HoaDonBan(MaHD ,MaNV, MaKH , NgayTao , GhiChu , GiamGia ,tiencongthem) VALUES(? ,? ,? ,? ,? ,? ,?)";
				String mahd = DonHang.this.jframe.maHoaDonTuDong();
				try {
					DangNhap.con.setAutoCommit(false);
					PreparedStatement ps = DangNhap.con.prepareStatement(sql_HD);
					ps.setString(1, mahd);

					String dataNV[] = textField_nhanvien.getText().split("-");
					if (dataNV[0].isEmpty()) {
						dataNV[0] = null;
					}
					ps.setString(2, dataNV[0]);

					String dataKH[] = lbKH.getText().split("-");
					if (dataKH[0].isEmpty()) {
						dataKH[0] = null;
					}
					ps.setString(3, dataKH[0]);
					ps.setString(4, XulyNgayThang());
					ps.setString(5, textArea_ghichu.getText());
					ps.setFloat(6, Float.parseFloat(textField_giamgia.getText()));
					ps.setDouble(7, Double.parseDouble(chinhsua(textField_tiencongthem.getText())));
					ps.executeUpdate();

					int rowcount = table.getRowCount();
					PreparedStatement ps2 = DangNhap.con.prepareStatement(
							"INSERT INTO ChiTietHoaDon(MaHD ,MaHang , SoLuong , DonGia , ThanhTien)  VALUES(? ,? ,? ,? ,?)");
					for (int i = 0; i < rowcount; i++) {

						ps2.setString(1, mahd);
						ps2.setString(2, (String) table.getValueAt(i, 0));
						ps2.setInt(3, Integer.parseInt((String) table.getValueAt(i, 3)));
						ps2.setDouble(4, Double.parseDouble((String) table.getValueAt(i, 4)));
						ps2.setDouble(5, Double.parseDouble(chinhsua((String) table.getValueAt(i, 5))));

						ps2.addBatch();
					}
					ps2.executeBatch();
					ps2.close();
					ps.close();
					JOptionPane.showMessageDialog(null, "tạo thành công đơn hàng: " + mahd);
					int i = jframe.tabbedPane.getSelectedIndex();
					jframe.xoaTab(i);

					DangNhap.con.commit();

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "lỗi ", e1.getMessage(), JOptionPane.ERROR_MESSAGE);
					System.err.println("DonHang - thanh toan: " + e1.getMessage());
					try {
						DangNhap.con.rollback();

					} catch (SQLException e2) {
						System.err.println("DonHang - thanh toan -rollback: " + e1.getMessage());
					}
				}
				try {
					DangNhap.con.setAutoCommit(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});
		btnTao.setMnemonic('f');
		btnTao.setForeground(Color.blue);
		btnTao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTao.setFocusPainted(false);
		btnTao.setFont(new Font("Arial", Font.BOLD, 21));
		GridBagConstraints gbc_btnTao = new GridBagConstraints();
		gbc_btnTao.anchor = GridBagConstraints.SOUTH;
		gbc_btnTao.ipady = 15;
		gbc_btnTao.fill = GridBagConstraints.BOTH;
		gbc_btnTao.gridwidth = 2;
		gbc_btnTao.insets = new Insets(30, 30, 10, 30);
		gbc_btnTao.gridx = 0;
		gbc_btnTao.gridy = 6;
		panel_tinhtien.add(btnTao, gbc_btnTao);

		/*
		 * 
		 * cap nhat ngay thang
		 */
		capnhatNgayThang();
	}

	private void thongtinKH(String data) {
		String sql = "select * from khachhang where maKH = N'" + data + "'";
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				lbKH.setText(rs.getString("makh") + "-" + rs.getString("tenkh"));
				lbsdt.setText(rs.getString("dienthoai"));
				lbdiachi.setText(rs.getString("diachi"));
				lbnhomKH.setText(rs.getString("manhomkh"));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("DonHang - thongtinKH: " + e.getMessage());
		}
	}

	private DefaultListModel<String> ds_timkiemKH(String data) {
		String sql = "SELECT MAKH , TENKH FROM KHACHHANG WHERE TENKH like N'%" + data + "%'";
		DefaultListModel<String> ds = new DefaultListModel<String>();
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String datarow = rs.getString("makh") + "-" + rs.getString("tenkh");

				ds.addElement(datarow);
			}
			rs.close();
			return ds;
		} catch (Exception e) {
			System.out.println("DonHang - ds_timkiemKH: " + e.getMessage());
		}
		return null;

	}

	private void napthongtinSP(String data) {
		String sql = "select * from mathang where mahang = N'" + data + "'";
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			DefaultTableModel datarow = (DefaultTableModel) table.getModel();
			if (rs.next()) {
				int giatriTrung = kiemtraTrungSP(rs.getString("mahang"));
				if (giatriTrung > -1) {
					int sl = Integer.parseInt((String) table.getValueAt(giatriTrung, 3));
					datarow.setValueAt((sl + 1) + "", giatriTrung, 3);
				} else {
					String[] temp = { rs.getString("mahang"), rs.getString("tenhang"), rs.getString("DONVITINH"), "1",
							rs.getDouble("GiaBanLe") + "", rs.getDouble("GiaBanLe") + "" };
					datarow.addRow(temp);
				}
				tongtien();
				rs.close();
			}
		} catch (Exception e) {
			System.out.println("DonHang - thongtinSP: " + e.getMessage());
		}
	}

//	private String kiemtraGiaBan() {
//		String giaban = null;
//		if (rdbtngiale.isSelected()) {
//			giaban = "GiaBanLe";
//		} else if (rdbtngiasi.isSelected()) {
//			giaban = "GiaBanSi";
//		}
//		return giaban;
//	}

	private int kiemtraTrungSP(String id) {
		int size = table.getRowCount();
		for (int i = 0; i < size; i++) {
			if (table.getValueAt(i, 0).equals(id))
				return i;
		}
		return -1;
	}

	private DefaultListModel<String> ds_timkiemSP(String data, String dieukien) {
		String sql = "SELECT * FROM MATHANG WHERE " + dieukien + " like N'%" + data + "%'";
		DefaultListModel<String> ds = new DefaultListModel<String>();
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String datarow = rs.getString("MAHANG") + "-" + rs.getString("TENHANG");
				ds.addElement(datarow);
			}
			rs.close();
			return ds;
		} catch (Exception e) {
			System.out.println("DonHang - ds_timkiemSP: " + e.getMessage());
		}
		return null;
	}

	private DefaultListModel<String> ds_timkiemNV(String data) {
		String sql = "select * from nhanvien where tennhanvien like N'%" + data + "%'";
		DefaultListModel<String> ds = new DefaultListModel<String>();
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String datarow = rs.getString("manhanvien") + "-" + rs.getString("tennhanvien");
				ds.addElement(datarow);
			}
			rs.close();
			return ds;
		} catch (Exception e) {
			System.out.println("DonHang - ds_timkiemNV: " + e.getMessage());
		}
		return null;
	}

	private String XulyNgayThang() {

		SimpleDateFormat simple = new SimpleDateFormat("hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		String giay = simple.format(cal.getTime());

		String ngaythang = comboBox_nam.getSelectedItem() + "-" + comboBox_thang.getSelectedItem() + "-"
				+ comboBox_ngay.getSelectedItem() + " " + giay;

		return ngaythang;

	}

	private void kiemtra_tiencongthem() {
		try {
			double tiencongthem = Double.parseDouble(chinhsua(textField_tiencongthem.getText()));
			if (tiencongthem < 0) {
				textField_tiencongthem.setText("0");
			}
			tongtien();
		} catch (Exception e) {
			textField_tiencongthem.setText("0");
		}
	}

	private void tienkhachdua() {

		try {
			Locale locale = new Locale("en", "EN");
			String pattern = "###,###,###.##";
			DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
			dcf.applyPattern(pattern); // áp dụng mẫu pattern = "###.##" cho dcf
			// ====================================================//

			double tienkhachdua = Double.parseDouble(chinhsua(textField_tienkhachdua.getText()));
			double khachphaitra = Double.parseDouble(chinhsua(lbkhachphaitra.getText()));
			textField_tienkhachdua.setText(dcf.format(tienkhachdua));

			double tienthua = tienkhachdua - khachphaitra;

			lbtienthua.setText(dcf.format(tienthua));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Tiền khách đưa không hợp lệ" + e.getMessage());
			System.out.println("DonHang -tienkhachdua " + e.getMessage());
			textField_tienkhachdua.setText("0");
			textField_tienkhachdua.requestFocus();
		}
	}

	private void kiemtra_giamgia() {
		try {
			Double so = Double.parseDouble(textField_giamgia.getText());
			if (so < 0 || so > 100)
				textField_giamgia.setText("0");

			tongtien();

		} catch (Exception e1) {
			textField_giamgia.requestFocus();
			textField_giamgia.setText("0");
		}
	}

	static String chinhsua(String s) {

		String temp[] = s.split(",");
		s = "";
		for (int i = 0; i < temp.length; i++) {
			s += temp[i];
		}
		return s;
	}

	private void tongtien() {
		double tong = 0.0;
		int rowcount = table.getRowCount();
		int sl = 0;
		double dongia = 0.0;
		double tongtiensanpham = 0.0;
		// ====================================
		Locale locale = new Locale("en", "EN");
		String pattern = "###,###,###.##";
		DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
		dcf.applyPattern(pattern); // áp dụng mẫu pattern = "###.##" cho dcf
		// =======================================

		double tiencongthem = Double.parseDouble(chinhsua(textField_tiencongthem.getText()));

		if (rowcount == 0) {
			lbthanhtien.setText("0.0");
			lbkhachphaitra.setText("0.0");

			double tienthua = Double.parseDouble(chinhsua(textField_tienkhachdua.getText())) - tiencongthem;
			lbtienthua.setText(dcf.format(tienthua));
		}
		for (int i = 0; i < rowcount; i++) {
			try {
				sl = Integer.parseInt((String) table.getValueAt(i, 3));
				dongia = Double.parseDouble((String) table.getValueAt(i, 4));

				tongtiensanpham = sl * dongia;

				tong += tongtiensanpham;

				table.setValueAt(dcf.format(tongtiensanpham), i, 5);

				lbthanhtien.setText(dcf.format(tong));

				double so = Double.parseDouble(textField_giamgia.getText().trim());

				double khachphaitra = Double.parseDouble(chinhsua(lbthanhtien.getText())) * (100 - so) / 100
						+ tiencongthem;

				textField_tiencongthem.setText(dcf.format(tiencongthem));
				lbkhachphaitra.setText(dcf.format(khachphaitra));

				tienkhachdua();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "sai định dạng" + e.getMessage());
				System.out.println("DonHang - tongtien: " + e.getMessage());
			}
		}

	}

	private ResultSet sanpham(String mahang) {
		String sql = "SELECT * FROM MATHANG WHERE MAHANG = N'" + mahang + "'";
		ResultSet rs = null;
		try {
			Statement st = DangNhap.con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next())
				return rs;
		} catch (Exception e) {

		}
		return rs;
	}

	private void giabanle() throws SQLException {
		DefaultTableModel datarow = (DefaultTableModel) table.getModel();
		ResultSet rs = null;
		int size = datarow.getRowCount();
		for (int i = 0; i < size; i++) {
			rs = sanpham((String) datarow.getValueAt(i, 0));
			datarow.setValueAt(rs.getFloat("GiaBanLe") + "", i, 4);
		}
		tongtien();
	}

	private void giabansi() throws SQLException {
		DefaultTableModel datarow = (DefaultTableModel) table.getModel();
		ResultSet rs = null;
		int size = datarow.getRowCount();
		for (int i = 0; i < size; i++) {
			rs = sanpham((String) datarow.getValueAt(i, 0));
			datarow.setValueAt(rs.getFloat("GiaBanSi") + "", i, 4);
		}
		tongtien();
	}

	private JLabel lbkhachphaitra;
	private JLabel lbtienthua;
	private JPopupMenu popupMenu_KH;
	private JList list_KH;
	private JSeparator separator_KH;
	private JPopupMenu popupMenu_NV;
	private JList<String> list_NV;
	private JLabel lbthanhtien;
	private ButtonGroup buttongroup;
//	private JRadioButton rdbtngiale;
//	private JRadioButton rdbtngiasi;
	private JRadioButton rdbtn_barcode;
	private JLabel lbIcon_xoaKH;
	private JLabel lbIcon_xoaNV;
	private JTextField textField_tiencongthem;

	class TablePopup extends JPopupMenu {

		private JMenuItem xoaItem;

		public TablePopup() {

			setFocusable(false);
			xoaItem = new JMenuItem("Xóa");
			xoaItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int row = table.getSelectedRow();
					System.err.println(row);
					DefaultTableModel datarow = (DefaultTableModel) table.getModel();
					datarow.removeRow(row);
					tongtien();
				}
			});
			add(xoaItem);
		}
	}

}
