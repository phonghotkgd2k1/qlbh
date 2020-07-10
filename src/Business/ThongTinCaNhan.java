package Business;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.io.Serializable;
import javax.swing.JSeparator;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThongTinCaNhan extends JFrame implements  ActionListener {

	private JPanel contentPane;
	private JTextField tften;
	private JTextField tfsdt;
	private JTextField tfdiachi;
	private JTextField tftaikhoan;
	private JTextField tfemail;
	private JTextField tfngayvaolam;
	private JPasswordField passwordField;
	private JTextField tfluong;
	private JTextField tfphucap;
	private JButton btnthaydoi, btnluu;

	/**
	 * Launch the application.
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
 
	public ThongTinCaNhan() {
		try {
			GUI();
		} catch (SQLException e) {
			System.out.println(" ThongTinCaNhan - napdulieunv: " + e.getMessage());
		}
	}

	private void GUI() throws SQLException {

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1215, 699);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(233, 235, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setForeground(Color.BLACK);
		separator.setBounds(52, 68, 1076, 2);
		contentPane.add(separator);

		JLabel lblNewLabel = new JLabel("Thông Tin Nhân Viên");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel.setBounds(72, 23, 318, 41);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(264, 93, 864, 521);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbtennhanvien = new JLabel("Tên Nhân Viên");
		lbtennhanvien.setFont(new Font("Arial", Font.BOLD, 16));
		lbtennhanvien.setBounds(33, 13, 116, 28);
		panel.add(lbtennhanvien);

		tften = new JTextField(DangNhap.rs_taikhoan.getString("TenNhanVien"));
		tften.setEditable(false);
		tften.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.BLACK));
		tften.setFont(new Font("Arial", Font.PLAIN, 17));
		tften.setBounds(75, 50, 567, 31);
		panel.add(tften);
		tften.setColumns(10);

		JLabel lbsdt = new JLabel("Số Điện Thoại");
		lbsdt.setFont(new Font("Arial", Font.BOLD, 16));
		lbsdt.setBounds(33, 94, 116, 28);
		panel.add(lbsdt);

		tfsdt = new JTextField(DangNhap.rs_taikhoan.getString("DienThoai"));
		tfsdt.setEditable(false);
		tfsdt.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.BLACK));
		tfsdt.setFont(new Font("Arial", Font.PLAIN, 17));
		tfsdt.setBounds(75, 122, 322, 31);
		panel.add(tfsdt);
		tfsdt.setColumns(10);

		JLabel lbdiachi = new JLabel("Địa Chỉ");
		lbdiachi.setFont(new Font("Arial", Font.BOLD, 16));
		lbdiachi.setBounds(429, 94, 116, 28);
		panel.add(lbdiachi);

		tfdiachi = new JTextField(DangNhap.rs_taikhoan.getString("DiaChi"));
		tfdiachi.setEditable(false);
		tfdiachi.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.BLACK));
		tfdiachi.setFont(new Font("Arial", Font.PLAIN, 17));
		tfdiachi.setColumns(10);
		tfdiachi.setBounds(465, 122, 354, 31);
		panel.add(tfdiachi);

		JLabel lbtaikhoan = new JLabel("Tài Khoản");
		lbtaikhoan.setFont(new Font("Arial", Font.BOLD, 16));
		lbtaikhoan.setBounds(33, 179, 116, 28);
		panel.add(lbtaikhoan);

		tftaikhoan = new JTextField(DangNhap.rs_taikhoan.getString("manhanvien"));
		tftaikhoan.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.BLACK));
		tftaikhoan.setEditable(false);
		tftaikhoan.setFont(new Font("Arial", Font.PLAIN, 17));
		tftaikhoan.setColumns(10);
		tftaikhoan.setBounds(75, 208, 259, 31);
		panel.add(tftaikhoan);

		JLabel lbemail = new JLabel("Email");
		lbemail.setFont(new Font("Arial", Font.BOLD, 16));
		lbemail.setBounds(33, 258, 116, 28);
		panel.add(lbemail);

		tfemail = new JTextField(DangNhap.rs_taikhoan.getString("Email"));
		tfemail.setEditable(false);
		tfemail.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.BLACK));
		tfemail.setFont(new Font("Arial", Font.PLAIN, 17));
		tfemail.setColumns(10);
		tfemail.setBounds(75, 286, 322, 31);
		panel.add(tfemail);

		JLabel lbngayvaolam = new JLabel("Ngày Vào Làm");
		lbngayvaolam.setFont(new Font("Arial", Font.BOLD, 16));
		lbngayvaolam.setBounds(465, 258, 116, 28);
		panel.add(lbngayvaolam);

		tfngayvaolam = new JTextField(DangNhap.rs_taikhoan.getString("NgayLamViec"));
		tfngayvaolam.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.BLACK));
		tfngayvaolam.setEditable(false);
		tfngayvaolam.setFont(new Font("Arial", Font.PLAIN, 17));
		tfngayvaolam.setColumns(10);
		tfngayvaolam.setBounds(465, 286, 354, 31);
		panel.add(tfngayvaolam);

		JLabel lbmatkhau = new JLabel("Mật Khẩu");
		lbmatkhau.setFont(new Font("Arial", Font.BOLD, 16));
		lbmatkhau.setBounds(429, 179, 116, 28);
		panel.add(lbmatkhau);

		passwordField = new JPasswordField(DangNhap.rs_taikhoan.getString("matkhau"));
		passwordField.setFont(new Font("Arial", Font.PLAIN, 17));
		passwordField.setEditable(false);
		passwordField.setEchoChar('*');
		passwordField.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.BLACK));
		passwordField.setBounds(465, 208, 229, 31);
		panel.add(passwordField);

		tfluong = new JTextField(DangNhap.rs_taikhoan.getDouble("LuongCoBan") + "");
		tfluong.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.BLACK));
		tfluong.setEditable(false);
		tfluong.setFont(new Font("Arial", Font.PLAIN, 17));
		tfluong.setColumns(10);
		tfluong.setBounds(75, 358, 259, 31);
		panel.add(tfluong);

		JLabel lbluong = new JLabel("Lương Cơ Bản");
		lbluong.setFont(new Font("Arial", Font.BOLD, 16));
		lbluong.setBounds(33, 330, 116, 28);
		panel.add(lbluong);

		JLabel lbphucap = new JLabel("Phụ Cấp");
		lbphucap.setFont(new Font("Arial", Font.BOLD, 16));
		lbphucap.setBounds(429, 330, 116, 28);
		panel.add(lbphucap);

		tfphucap = new JTextField(DangNhap.rs_taikhoan.getDouble("PhuCap") + "");
		tfphucap.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.BLACK));
		tfphucap.setEditable(false);
		tfphucap.setFont(new Font("Arial", Font.PLAIN, 17));
		tfphucap.setColumns(10);
		tfphucap.setBounds(465, 358, 229, 31);
		panel.add(tfphucap);

		btnthaydoi = new JButton("Thay Đổi");

		btnthaydoi.addActionListener(this);

		btnthaydoi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnthaydoi.setFocusable(false);
		btnthaydoi.setBounds(581, 434, 113, 30);
		panel.add(btnthaydoi);

		btnluu = new JButton("Lưu");
		btnluu.setEnabled(false);

		btnluu.addActionListener(this);
		btnluu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnluu.setFocusable(false);
		btnluu.setBounds(722, 435, 97, 30);
		panel.add(btnluu);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String s = e.getActionCommand();
		if (s.equals("Thay Đổi")) {
			tften.setEditable(true);
			tfdiachi.setEditable(true);
			tfemail.setEditable(true);
			tfsdt.setEditable(true);
			passwordField.setEditable(true);
			if (btnluu.isEnabled() == false)
				btnluu.setEnabled(true);

		} else if (s.equals("Lưu")) {
			String sql_nv = "UPDATE NhanVien set TenNhanVien =?, DIACHI =? ,EMAIL =?, DienThoai =?  , matkhau = ? FROM NHANVIEN WHERE MANHANVIEN = ?";
			try {
				PreparedStatement ps = DangNhap.con.prepareStatement(sql_nv);
				ps.setString(1, tften.getText() );
				ps.setString(2, tfdiachi.getText());
				ps.setString(3, tfemail.getText());
				ps.setString(4, tfsdt.getText());
				ps.setString(5, passwordField.getText());
				ps.setString(6,  DangNhap.rs_taikhoan.getString("manhanvien"));
				int tc = ps.executeUpdate();
				if (tc == 1) {
					JOptionPane.showMessageDialog(this, "luu thanh cong");
					
				} else {
					JOptionPane.showMessageDialog(this, " loi sua nhan vien");
					
				}

			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}

		}
	}
}
