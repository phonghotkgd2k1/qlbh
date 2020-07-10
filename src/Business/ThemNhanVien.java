package Business;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class ThemNhanVien extends JDialog {

	private JButton btnxacnhan;
	private JTextField textField_manhanvien;
	private JTextField textField_tennhanvien;
	private JTextField textField_diachi;
	private JTextField textField_email;
	private JComboBox comboBox_ngay;
	private JComboBox comboBox_thang;
	private JComboBox comboBox_nam;
	private JTextField textField_sdt;
	private JTextField textField_luongcoban;
	private JTextField textField_phucap;
	private JPasswordField textField_matkhau;
	private JComboBox comboBox_quyenhan;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private NgayThang day;
	private JPanel panel_center;
	private CardLayout card;
	private ButtonGroup group;
	private JRadioButton rdbtn_chophep;
	private JRadioButton rdbtn_khongchophep;

	/**
	 * .
	 */
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

	private void south() {
		JPanel panel_south = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_south.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel_south, BorderLayout.SOUTH);

		btnxacnhan = new JButton("Xác nhận");
		btnxacnhan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				them();
			}
		});
		btnxacnhan.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_south.add(btnxacnhan);
	}

	private void center() {
		card = new CardLayout();
		panel_center = new JPanel();
		getContentPane().add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(card);

		JPanel panel_thongtin = new JPanel();
		panel_center.add(panel_thongtin, "thongtin");
		panel_thongtin.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Mã nhân viên");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4.setBounds(51, 40, 155, 22);
		panel_thongtin.add(lblNewLabel_4);

		textField_manhanvien = new JTextField();
		textField_manhanvien.setFont(new Font("Arial", Font.PLAIN, 15));
		textField_manhanvien.setBounds(61, 66, 323, 27);
		panel_thongtin.add(textField_manhanvien);
		textField_manhanvien.setColumns(10);

		JLabel lblNewLabel_4_1 = new JLabel("Tên nhân viên");
		lblNewLabel_4_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4_1.setBounds(51, 117, 155, 22);
		panel_thongtin.add(lblNewLabel_4_1);

		textField_tennhanvien = new JTextField();
		textField_tennhanvien.setFont(new Font("Arial", Font.PLAIN, 15));
		textField_tennhanvien.setColumns(10);
		textField_tennhanvien.setBounds(61, 144, 323, 27);
		panel_thongtin.add(textField_tennhanvien);

		JLabel lblNewLabel_4_2 = new JLabel("Địa chỉ ");
		lblNewLabel_4_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4_2.setBounds(51, 193, 155, 22);
		panel_thongtin.add(lblNewLabel_4_2);

		textField_diachi = new JTextField();
		textField_diachi.setFont(new Font("Arial", Font.PLAIN, 15));
		textField_diachi.setColumns(10);
		textField_diachi.setBounds(61, 218, 353, 27);
		panel_thongtin.add(textField_diachi);

		JLabel lblNewLabel_4_3 = new JLabel("Email");
		lblNewLabel_4_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4_3.setBounds(51, 266, 155, 22);
		panel_thongtin.add(lblNewLabel_4_3);

		textField_email = new JTextField();
		textField_email.setFont(new Font("Arial", Font.PLAIN, 15));
		textField_email.setColumns(10);
		textField_email.setBounds(61, 292, 353, 27);
		panel_thongtin.add(textField_email);

		JToolBar toolBar = new JToolBar();
		toolBar.setBorder(UIManager.getBorder("Button.border"));
		toolBar.setBounds(530, 66, 316, 27);
		panel_thongtin.add(toolBar);

		comboBox_ngay = new JComboBox();
		toolBar.add(comboBox_ngay);

		comboBox_thang = new JComboBox();
		toolBar.add(comboBox_thang);

		comboBox_nam = new JComboBox();
		toolBar.add(comboBox_nam);

		JLabel lblNewLabel_4_4 = new JLabel("Ngày làm việc");
		lblNewLabel_4_4.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4_4.setBounds(508, 40, 155, 22);
		panel_thongtin.add(lblNewLabel_4_4);

		JLabel lblNewLabel_4_5 = new JLabel("Lương cơ bản ");
		lblNewLabel_4_5.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4_5.setBounds(508, 117, 155, 22);
		panel_thongtin.add(lblNewLabel_4_5);

		JLabel lblNewLabel_4_6 = new JLabel("Phụ cấp");
		lblNewLabel_4_6.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4_6.setBounds(508, 196, 155, 22);
		panel_thongtin.add(lblNewLabel_4_6);

		JLabel lblNewLabel_4_7 = new JLabel("Điện thoại");
		lblNewLabel_4_7.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4_7.setBounds(51, 341, 155, 22);
		panel_thongtin.add(lblNewLabel_4_7);

		textField_sdt = new JTextField();
		textField_sdt.setFont(new Font("Arial", Font.PLAIN, 15));
		textField_sdt.setColumns(10);
		textField_sdt.setBounds(61, 365, 285, 27);
		panel_thongtin.add(textField_sdt);

		textField_luongcoban = new JTextField("0.0");
		textField_luongcoban.setFont(new Font("Arial", Font.PLAIN, 15));
		textField_luongcoban.setColumns(10);
		textField_luongcoban.setBounds(530, 146, 249, 27);
		panel_thongtin.add(textField_luongcoban);

		textField_phucap = new JTextField("0.0");
		textField_phucap.setFont(new Font("Arial", Font.PLAIN, 15));
		textField_phucap.setColumns(10);
		textField_phucap.setBounds(530, 220, 209, 27);
		panel_thongtin.add(textField_phucap);

		textField_matkhau = new JPasswordField();
		textField_matkhau.setFont(new Font("Arial", Font.PLAIN, 15));
		textField_matkhau.setColumns(10);
		textField_matkhau.setBounds(61, 435, 227, 27);
		panel_thongtin.add(textField_matkhau);

		JLabel lblNewLabel_4_7_1 = new JLabel("Mật khẩu");
		lblNewLabel_4_7_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4_7_1.setBounds(51, 408, 155, 22);
		panel_thongtin.add(lblNewLabel_4_7_1);

		JPanel panel_phanquyen = new JPanel();
		panel_center.add(panel_phanquyen, "phanquyen");
		panel_phanquyen.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Quyền hạn:");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5.setBounds(51, 40, 222, 22);
		panel_phanquyen.add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel("Cho phép hoạt động: ");
		lblNewLabel_5_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_1.setBounds(51, 174, 235, 22);
		panel_phanquyen.add(lblNewLabel_5_1);

		comboBox_quyenhan = new JComboBox();
		comboBox_quyenhan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox_quyenhan.setBounds(74, 75, 192, 27);
		panel_phanquyen.add(comboBox_quyenhan);

		JSeparator separator = new JSeparator();
		separator.setBounds(51, 159, 394, 2);
		panel_phanquyen.add(separator);

		group = new ButtonGroup();
		rdbtn_chophep = new JRadioButton("1");
		rdbtn_chophep.setSelected(true);
		group.add(rdbtn_chophep);
		rdbtn_chophep.setBounds(74, 221, 127, 25);
		panel_phanquyen.add(rdbtn_chophep);

		rdbtn_khongchophep = new JRadioButton("0");
		group.add(rdbtn_khongchophep);
		rdbtn_khongchophep.setBounds(74, 272, 127, 25);
		panel_phanquyen.add(rdbtn_khongchophep);

	}

	private void west() {
		JPanel panel_west = new JPanel();
		panel_west.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_west.setBackground(Color.WHITE);
		getContentPane().add(panel_west, BorderLayout.WEST);
		GridBagLayout gbl_panel_west = new GridBagLayout();
		gbl_panel_west.columnWidths = new int[] { 97, 97, 0 };
		gbl_panel_west.rowHeights = new int[] { 25, 0, 0, 0 };
		gbl_panel_west.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_west.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_west.setLayout(gbl_panel_west);

		JButton btn_thongtin = new JButton("Thong tin");
		btn_thongtin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showLayout("thongtin");
			}
		});
		buttonGroup_1.add(btn_thongtin);
		btn_thongtin.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_btn_thongtin = new GridBagConstraints();
		gbc_btn_thongtin.gridwidth = 2;
		gbc_btn_thongtin.fill = GridBagConstraints.BOTH;
		gbc_btn_thongtin.insets = new Insets(30, 0, 5, 0);
		gbc_btn_thongtin.gridx = 0;
		gbc_btn_thongtin.gridy = 0;
		panel_west.add(btn_thongtin, gbc_btn_thongtin);

		JButton btn_phanquyen = new JButton("Phan quyen");
		btn_phanquyen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showLayout("phanquyen");

			}
		});
		buttonGroup_1.add(btn_phanquyen);
		btn_phanquyen.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_btn_phanquyen = new GridBagConstraints();
		gbc_btn_phanquyen.gridwidth = 2;
		gbc_btn_phanquyen.insets = new Insets(30, 0, 5, 0);
		gbc_btn_phanquyen.fill = GridBagConstraints.BOTH;
		gbc_btn_phanquyen.gridx = 0;
		gbc_btn_phanquyen.gridy = 1;
		panel_west.add(btn_phanquyen, gbc_btn_phanquyen);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 2;
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.insets = new Insets(10, 0, 0, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		panel_west.add(separator, gbc_separator);
	}

	public ThemNhanVien() {
		setTitle("Thêm nhân viên mới");
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 1254, 743);
		
		center();
		south();
		west();

		controller();
		napQuyenHan();
	}

	private void controller() {
		day = new NgayThang();
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

	private void napQuyenHan() {
		String sql = "select * from quyenhan order by maquyenhan desc";
		DefaultComboBoxModel<String> data = new DefaultComboBoxModel<String>();
		try {
			ResultSet rs = DangNhap.con.createStatement().executeQuery(sql);
			while (rs.next()) {
				data.addElement(rs.getString("maquyenhan"));
			}
			comboBox_quyenhan.setModel(data);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "loi nap quyen han ");
			System.out.println("themnhanvien - napQuyenHan: " + e.getMessage());
		}
	}

	private void showLayout(String pn) {
		card.show(panel_center, pn);
	}

	private void them() {
		String sql_nv = "insert into nhanvien values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = DangNhap.con.prepareStatement(sql_nv);
			ps.setString(1, textField_manhanvien.getText().trim());
			ps.setString(2, textField_tennhanvien.getText().trim());
			ps.setString(3, textField_diachi.getText().trim());
			ps.setString(4, textField_email.getText().trim());
			ps.setString(5, XulyNgayThang());
			ps.setDouble(6, Double.parseDouble(textField_luongcoban.getText()));
			ps.setDouble(7, Double.parseDouble(textField_phucap.getText()));
			ps.setString(8, textField_matkhau.getText());
			ps.setString(9, textField_sdt.getText().trim());
			ps.executeUpdate();

			String sql_pq = "insert into phanquyen values(?,?,?)";
			ps = DangNhap.con.prepareStatement(sql_pq);
			ps.setString(1, textField_manhanvien.getText().trim());
			ps.setString(2, (String) comboBox_quyenhan.getSelectedItem());
			ps.setInt(3, getHoatDong());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "them thanh cong!");

			// jframe_ThemNhanVien.contentPane_tabbed.remove(this);
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "lỗi thêm nhân viên");
			System.out.println("them " + e.getMessage());
		}
	}

	private String XulyNgayThang() {
		String ngaythang = comboBox_nam.getSelectedItem() + "-" + comboBox_thang.getSelectedItem() + "-"
				+ comboBox_ngay.getSelectedItem();
		return ngaythang;
	}

	private int getHoatDong() {
		if (rdbtn_chophep.isSelected())
			return 1;
		return 0;
	}
}
