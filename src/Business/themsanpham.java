package Business;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class themsanpham extends JDialog implements KeyListener {

	private JPanel contentPane;
	private JTextField textField_tensp;
	private JTextField textField_masp;
	private JTextField textField_le;
	private JTextField textField_si;
	private JTextField textField_gianhap;
	private JTextArea textarea_ghichu;
	private JTextArea ta_diachifile;
	private JTextField textField_dvt;
	private ChooserFile chooser;

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

	public themsanpham() {

		FileNameExtensionFilter format = new FileNameExtensionFilter("file(.*)", "png", "jpg", "gif", "svg");
		chooser = new ChooserFile("Imager_products", format, false);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 852, 704);
		setResizable(false);
		// VO HIÊU HÓA FRAME KHÁC KHI JDIALOG NÀY ĐC MỞ
		setModal(true);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 66, 746, 2);
		contentPane.add(separator);

		JLabel lblNewLabel = new JLabel("Thêm Mới Sản Phẩm");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setBounds(32, 13, 373, 40);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Tên Sản Phẩm");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(429, 107, 173, 33);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Mã Sản Phẩm");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(53, 107, 173, 33);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Gía bán lẻ");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(619, 186, 109, 33);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_4 = new JLabel("Gía bán sỉ");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_4.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNewLabel_1_4.setBounds(429, 186, 173, 33);
		contentPane.add(lblNewLabel_1_4);

		textField_tensp = new JTextField();
		textField_tensp.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		textField_tensp.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_tensp.setBounds(480, 140, 330, 33);
		contentPane.add(textField_tensp);
		textField_tensp.setColumns(10);

		textField_masp = new JTextField();
		textField_masp.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		textField_masp.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_masp.setColumns(10);
		textField_masp.setBounds(100, 140, 291, 33);
		contentPane.add(textField_masp);

		textField_le = new JTextField("0");
		textField_le.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		textField_le.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_le.setColumns(10);
		textField_le.setBounds(652, 222, 158, 33);
		contentPane.add(textField_le);

		textField_si = new JTextField("0");
		textField_si.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		textField_si.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_si.setColumns(10);
		textField_si.setBounds(480, 222, 151, 33);
		contentPane.add(textField_si);

		JLabel lbhinhanh = new JLabel("");
		lbhinhanh.setBounds(426, 287, 360, 360);
		lbhinhanh.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(lbhinhanh);

		JLabel lblNewLabel_1_5_1 = new JLabel("Gía nhập");
		lblNewLabel_1_5_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_5_1.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNewLabel_1_5_1.setBounds(53, 186, 109, 33);
		contentPane.add(lblNewLabel_1_5_1);

		textField_gianhap = new JTextField("0");
		textField_gianhap.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		textField_gianhap.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_gianhap.setColumns(10);
		textField_gianhap.setBounds(100, 222, 136, 33);
		contentPane.add(textField_gianhap);

		JButton btnLuu = new JButton("Lưu");
		btnLuu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLuu.setSelected(true);
		btnLuu.setFont(new Font("Arial", Font.PLAIN, 17));
		btnLuu.setFocusable(false);
		btnLuu.setBounds(32, 614, 109, 33);
		contentPane.add(btnLuu);
		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				luuSanPham();
			}
		});

		JButton btnthoat = new JButton("Thoát");
		btnthoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnthoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnthoat.setFont(new Font("Arial", Font.PLAIN, 17));
		btnthoat.setFocusable(false);
		btnthoat.setBounds(193, 615, 115, 33);
		contentPane.add(btnthoat);

		ta_diachifile = new JTextArea();
		ta_diachifile.setLineWrap(true);
		ta_diachifile.setWrapStyleWord(true);
		ta_diachifile.setFont(new Font("Arial", Font.PLAIN, 14));
		ta_diachifile.setBounds(53, 537, 291, 49);
		ta_diachifile.setEditable(false);
		contentPane.add(ta_diachifile);

		textarea_ghichu = new JTextArea();
		textarea_ghichu.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		textarea_ghichu.setWrapStyleWord(true);
		textarea_ghichu.setLineWrap(true);
		textarea_ghichu.setFont(new Font("Arial", Font.PLAIN, 14));
		textarea_ghichu.setBounds(100, 388, 291, 78);
		contentPane.add(textarea_ghichu);

		JLabel lblNewLabel_1_2_1 = new JLabel("Ghi Chú");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2_1.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNewLabel_1_2_1.setBounds(53, 354, 109, 33);
		contentPane.add(lblNewLabel_1_2_1);

		JButton btnChonHinhAnh = new JButton("Chọn Hình ảnh");

		btnChonHinhAnh.setFont(new Font("Arial", Font.PLAIN, 17));
		btnChonHinhAnh.setBounds(53, 491, 151, 33);
		contentPane.add(btnChonHinhAnh);
		btnChonHinhAnh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChonHinhAnh.setFocusable(false);

		JLabel lblNewLabel_1_2_2 = new JLabel("DVT");
		lblNewLabel_1_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2_2.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNewLabel_1_2_2.setBounds(53, 272, 109, 33);
		contentPane.add(lblNewLabel_1_2_2);

		textField_dvt = new JTextField();
		textField_dvt.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		textField_dvt.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_dvt.setColumns(10);
		textField_dvt.setBounds(100, 308, 129, 33);
		contentPane.add(textField_dvt);
		btnChonHinhAnh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				chooser.show();
				String url = chooser.getUrl();
				Image icon = new ImageIcon(url).getImage().getScaledInstance(lbhinhanh.getWidth(),
						lbhinhanh.getHeight(), Image.SCALE_SMOOTH);
				lbhinhanh.setIcon(new ImageIcon(icon));
				ta_diachifile.setText(url);
			}
		});
	}

	private String ngaykhoitao() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date date = c.getTime();
		String s = simple.format(date);
		return s;
	}

	private void luuSanPham() {

		String sql = "INSERT INTO MatHang(MaHang , TenHang ,gianhap, giabanle , giabansi  ,DONVITINH ,GhiChu  ,Anh ,ngaykhoitao ) VALUES(? ,? ,? ,?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pStatement = DangNhap.con.prepareStatement(sql);

			ob_sanpham sp = new ob_sanpham();
			sp.setMahang(textField_masp.getText().trim());
			sp.setTenhang(textField_tensp.getText().trim());
			sp.setGianhap(Double.parseDouble(textField_gianhap.getText()));
			sp.setGiabanle(Double.parseDouble(textField_le.getText()));
			sp.setGiabansi(Double.parseDouble(textField_si.getText()));
			sp.setGhichu(textarea_ghichu.getText().trim());

			sp.setAnh(chooser.diachi_anh(ta_diachifile.getText()));
			sp.setDonvitinh(textField_dvt.getText().trim());
			sp.setNgaykhoitao(null);

			pStatement.setString(1, sp.getMahang());
			pStatement.setString(2, sp.getTenhang());
			pStatement.setDouble(3, sp.getGianhap());
			pStatement.setDouble(4, sp.getGiabanle());
			pStatement.setDouble(5, sp.getGiabansi());
			pStatement.setString(6, sp.getDonvitinh());
			pStatement.setString(7, sp.getGhichu());
			if(sp.getAnh() ==null )
				pStatement.setString(8, "");
			else {
				pStatement.setString(8, sp.getAnh());
			}
			pStatement.setString(9, sp.getNgaykhoitao());
			pStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "thêm thành công");

			dispose();
		} catch (NumberFormatException e1) {

			JOptionPane.showMessageDialog(null, "sai định dạng");
		} catch (SQLException e2) {

			JOptionPane.showMessageDialog(null, "trùng mã");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "sai " + e.getMessage());
		}

	}

	public void luuSanPham(ArrayList<ob_sanpham> ds) {

		String sql = "INSERT INTO MatHang(MaHang , TenHang ,gianhap, giabanle , giabansi ,DONVITINH ,GhiChu  ,Anh ,ngaykhoitao ) VALUES(? ,? ,? ,?, ?, ?, ?, ?, ?)";

		int size = ds.size();
		DefaultListModel<String> data = new DefaultListModel<String>();
		PreparedStatement pStatement = null;
		try {
			DangNhap.con.setAutoCommit(false);
			pStatement = DangNhap.con.prepareStatement(sql);

			for (int i = 0; i < size; i++) {
				ob_sanpham sp = ds.get(i);

				try {
					pStatement.setString(1, sp.getMahang());
					pStatement.setString(2, sp.getTenhang());
					pStatement.setDouble(3, sp.getGianhap());
					pStatement.setDouble(4, sp.getGiabanle());
					pStatement.setDouble(5, sp.getGiabansi());
					pStatement.setString(6, sp.getDonvitinh());
					pStatement.setString(7, sp.getGhichu());

					if (sp.getAnh() == null) {
						pStatement.setString(8, "");
					} else {

						new Check_er().kiemtraImager(sp.getAnh());
						pStatement.setString(8, chooser.diachi_anh(sp.getAnh()));
					}

					pStatement.setString(9, sp.getNgaykhoitao());

					pStatement.addBatch();
				} catch (Exception e1) {
					data.addElement("dòng: " + ds.get(i) + " ==>> lỗi: " + e1.getMessage());
				}
			}
			int size2 = data.getSize();

			String controller[] = { "tiếp tục thêm", "hủy" };

			JList<String> list = new JList<String>(data);
			list.setFont(new Font("arial", Font.PLAIN, 17));
			JScrollPane js = new JScrollPane(list);

			showOptiondialog s = new showOptiondialog(js, "danh sách lỗi: " + size2, JOptionPane.DEFAULT_OPTION,
					JOptionPane.ERROR_MESSAGE, null, controller, controller[0]);
			s.show();
			if (s.getChooser() == 0) {
				pStatement.executeBatch();
				JOptionPane.showMessageDialog(null, "cập nhật thành công: " + (size - size2));
				DangNhap.con.commit();
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "lỗi cập nhật");

			try {
				DangNhap.con.rollback();
			} catch (SQLException e1) {
				System.out.println("themsanpham.rollback: " + e.getMessage());
			}
		}

		try {
			DangNhap.con.setAutoCommit(true);
		} catch (SQLException e) {
			System.out.println("themsanpham.setAutoCommit: " + e.getMessage());
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 10) {
			luuSanPham();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
