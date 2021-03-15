package Business;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class themkhachhang extends JDialog {

	private JPanel contentPane;
	private JTextField textField_tenkh;
	private JTextField textField_makh;
	private JTextField textField_sdt;
	private JTextField textField_diachi;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and Feel not set");
		}

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
	public themkhachhang() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 804, 575);
		setResizable(false);
		setModal(true);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 66, 746, 2);
		contentPane.add(separator);

		JLabel lblNewLabel = new JLabel("Thêm Mới Khách Hàng");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setBounds(32, 20, 374, 33);
		contentPane.add(lblNewLabel);

		JLabel lbtenkhachhang = new JLabel("Tên Khách Hàng");
		lbtenkhachhang.setHorizontalAlignment(SwingConstants.CENTER);
		lbtenkhachhang.setFont(new Font("Arial", Font.PLAIN, 17));
		lbtenkhachhang.setBounds(12, 97, 173, 33);
		contentPane.add(lbtenkhachhang);

		JLabel lbmakhachhang = new JLabel("Mã Khách Hàng");
		lbmakhachhang.setHorizontalAlignment(SwingConstants.CENTER);
		lbmakhachhang.setFont(new Font("Arial", Font.PLAIN, 17));
		lbmakhachhang.setBounds(403, 97, 173, 33);
		contentPane.add(lbmakhachhang);

		JLabel lbsdt = new JLabel("Số Điện Thoai");
		lbsdt.setHorizontalAlignment(SwingConstants.LEFT);
		lbsdt.setFont(new Font("Arial", Font.PLAIN, 17));
		lbsdt.setBounds(53, 186, 109, 33);
		contentPane.add(lbsdt);

		JLabel lblNewLabel_1_4 = new JLabel("Địa chỉ");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNewLabel_1_4.setBounds(391, 186, 173, 33);
		contentPane.add(lblNewLabel_1_4);

		textField_tenkh = new JTextField();
		textField_tenkh.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_tenkh.setBounds(100, 130, 244, 33);
		contentPane.add(textField_tenkh);
		textField_tenkh.setColumns(10);

		textField_makh = new JTextField();
		textField_makh.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_makh.setColumns(10);
		textField_makh.setBounds(480, 130, 244, 33);
		contentPane.add(textField_makh);

		textField_sdt = new JTextField();
		textField_sdt.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_sdt.setColumns(10);
		textField_sdt.setBounds(100, 222, 208, 33);
		contentPane.add(textField_sdt);

		textField_diachi = new JTextField();
		textField_diachi.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_diachi.setColumns(20);
		textField_diachi.setBounds(480, 232, 278, 33);
		contentPane.add(textField_diachi);

		JLabel lblNewLabel_1_5_1 = new JLabel("Nhóm Khách Hàng");
		lblNewLabel_1_5_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_5_1.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNewLabel_1_5_1.setBounds(53, 275, 164, 33);
		contentPane.add(lblNewLabel_1_5_1);

		JButton btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themkhachhang();
			}
		});
		btnLuu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLuu.setSelected(true);
		btnLuu.setFont(new Font("Arial", Font.PLAIN, 17));
		btnLuu.setFocusable(false);
		btnLuu.setBounds(53, 467, 109, 33);
		contentPane.add(btnLuu);

		JButton btnthoat = new JButton("Thoát");
		btnthoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnthoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnthoat.setFont(new Font("Arial", Font.PLAIN, 17));
		btnthoat.setFocusable(false);
		btnthoat.setBounds(193, 467, 115, 33);
		contentPane.add(btnthoat);

		JTextArea ta_diachifile = new JTextArea();
		ta_diachifile.setLineWrap(true);
		ta_diachifile.setWrapStyleWord(true);
		ta_diachifile.setFont(new Font("Arial", Font.PLAIN, 14));
		ta_diachifile.setBounds(53, 537, 291, 49);
		ta_diachifile.setEditable(false);
		contentPane.add(ta_diachifile);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 15));
		comboBox.setModel(addnhomKH());
		comboBox.setBounds(100, 310, 208, 33);
		contentPane.add(comboBox);
	}

	private DefaultComboBoxModel<String> addnhomKH() {
		DefaultComboBoxModel<String> ds = new DefaultComboBoxModel<String>();
		String sql = "select * from nhomkh";
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				ds.addElement(rs.getString("manhomkh"));
			}
			return ds;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, " lỗi cập nhật nhóm KH ", e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public void themkhachhang() {

		String sql = "insert into KhachHang(MaKH ,TenKH ,DiaChi ,DienThoai , MaNhomKH) values(? ,? ,? ,? ,?) ";
		try {
			PreparedStatement pStatement = DangNhap.con.prepareStatement(sql);
			pStatement.setString(1, textField_makh.getText());
			pStatement.setString(2, textField_tenkh.getText());
			pStatement.setString(3, textField_diachi.getText());
			pStatement.setString(4, textField_sdt.getText());
			pStatement.setString(5, comboBox.getSelectedItem().toString());
			pStatement.executeUpdate();

			JOptionPane.showMessageDialog(null, "thêm thành công");
			dispose();
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "trùng mã KH", JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException e3) {
			JOptionPane.showMessageDialog(null, e3.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void themkhachhang(ArrayList<String> ds) {

		int size = ds.size();
		String sql = "insert into KhachHang(MaKH ,TenKH ,DiaChi ,DienThoai , MaNhomKH) values(? ,? ,? ,? ,?) ";
		String temp[];
		DefaultListModel<String> dsER = new DefaultListModel<String>();
		try {
			DangNhap.con.setAutoCommit(false);
			PreparedStatement pr = DangNhap.con.prepareStatement(sql);

			for (int i = 0; i < size; i++) {
				temp = ds.get(i).split("[;|]");
				try {
					pr.setString(1, temp[0].trim());
					pr.setString(2, temp[1].trim());
					pr.setString(3, temp[2].trim());
					pr.setString(4, temp[3].trim());
					pr.setString(5, kiemtraMaNKH(temp[4].trim()));
					pr.addBatch();
				} catch (Exception e) {
					dsER.addElement("Dòng: " + ds.get(i) + " ==>>lỗi: " + e.getMessage());
				}
			}

			JList<String> list = new JList<String>(dsER);
			list.setFont(new Font("arial", Font.PLAIN, 17));
			JScrollPane js = new JScrollPane(list);

			String controller[] = { "tiếp tục thêm", "hủy" };

			int size2 = dsER.getSize();
			showOptiondialog s = new showOptiondialog(js, "danh sách lỗi: " + size2, JOptionPane.DEFAULT_OPTION,
					JOptionPane.ERROR_MESSAGE, null, controller, controller[0]);
			s.show();
			if (s.getChooser() == 0) {
				pr.executeBatch();
				JOptionPane.showMessageDialog(null, "cập nhật thành công: " + (size - size2));

				DangNhap.con.commit();
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "lỗi dữ liệu");

			try {
				DangNhap.con.rollback();
			} catch (SQLException e1) {
				System.out.println("themkhachhang.rollback: " + e1.getMessage());
			}
		}

		try {
			DangNhap.con.setAutoCommit(true);
		} catch (SQLException e) {
			System.out.println("themkhachhang.setautocommit: " + e.getMessage());
		}
	}

	private String kiemtraMaNKH(String mankh) throws Exception {
		DefaultComboBoxModel<String> ds = addnhomKH();
		int x = 0;
		for (int i = 0; i < ds.getSize(); i++) {
			if (mankh.equalsIgnoreCase(ds.getElementAt(i))) {
				x++;
			}
		}
		if (x == 0) {
			throw new Exception("lỗi mã nhóm KH");
		} else {
			return mankh;
		}
	}
}
