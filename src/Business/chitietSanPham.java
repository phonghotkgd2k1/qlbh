package Business;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class chitietSanPham extends JPanel {
	private JTextField textField_tensp;
	private JTextField textField_masp;
	private JTextField textField_DVT;
	private JTextField textField_le;
	private JLabel lbhinhanh;
	private JTextField textField_url;
	private JTextArea textArea;
	private JLabel lbtitle;
	private Image icon;
	private JButton btnluu_sp;
	private JButton btnxoa_ctsp;
	private JLabel lbgianhap;
	private JTextField textField_nhap;
	private ChooserFile chooser;
	private Check_er check;

	private void getdataSP(String txt) {
		try {
			String sql = "select * from mathang where mahang = N'" + txt + "'";
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {

				lbtitle.setText(rs.getString("tenhang"));
				textField_url.setText(System.getProperty("user.dir") + "\\" + rs.getString("anh"));
				textField_le.setText(rs.getDouble("giabanle") + "");
				textField_DVT.setText(rs.getString("donvitinh"));
				textField_nhap.setText(rs.getDouble("gianhap") + "");
				textField_tensp.setText(rs.getString("tenhang"));
				textField_masp.setText(rs.getString("mahang"));
				textArea.setText(rs.getString("ghichu"));

				icon = check.kiemtraImager(rs.getString("anh")).getImage();
				lbhinhanh.setIcon(new ImageIcon(icon.getScaledInstance(250, 250, Image.SCALE_SMOOTH)));

			}
		} catch (IOException e) {
			lbhinhanh.setText("ảnh không có sẵn");
			textField_url.setText("");
		} catch (Exception e) {
			System.out.println("chi tiet san pham - getdataSP : " + e.getMessage());
		}
	}

	private JPanel panel_north() {
		JPanel panel_north = new JPanel();
		FlowLayout fl_panel_north = (FlowLayout) panel_north.getLayout();
		fl_panel_north.setVgap(20);
		fl_panel_north.setHgap(70);
		fl_panel_north.setAlignment(FlowLayout.LEFT);

		lbtitle = new JLabel();
		lbtitle.setFont(new Font("Tahoma", Font.PLAIN, 27));
		panel_north.add(lbtitle);

		return panel_north;
	}

	private JPanel panel_center() {
		JPanel panel_center = new JPanel();

		GridBagLayout gbl_panel_center = new GridBagLayout();
		gbl_panel_center.columnWidths = new int[] { 468, 0, 0, 0, 0, 56, 0 };
		gbl_panel_center.rowHeights = new int[] { 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_center.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_center.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0 };
		panel_center.setLayout(gbl_panel_center);

		JLabel lbten = new JLabel("Tên sản phẩm");
		lbten.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_lbten = new GridBagConstraints();
		gbc_lbten.insets = new Insets(20, 70, 5, 5);
		gbc_lbten.anchor = GridBagConstraints.WEST;
		gbc_lbten.gridx = 0;
		gbc_lbten.gridy = 0;
		panel_center.add(lbten, gbc_lbten);

		lbgianhap = new JLabel("Gía nhập");
		lbgianhap.setVisible(DangNhap.action_sua);
		lbgianhap.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_lbgianhap = new GridBagConstraints();
		gbc_lbgianhap.anchor = GridBagConstraints.WEST;
		gbc_lbgianhap.fill = GridBagConstraints.VERTICAL;
		gbc_lbgianhap.insets = new Insets(20, 50, 5, 5);
		gbc_lbgianhap.gridx = 1;
		gbc_lbgianhap.gridy = 4;
		panel_center.add(lbgianhap, gbc_lbgianhap);

		textField_nhap = new JTextField("0");
		textField_nhap.setVisible(DangNhap.action_sua);
		textField_nhap.setPreferredSize(new Dimension(6, 33));
		textField_nhap.setMinimumSize(new Dimension(6, 33));
		textField_nhap.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_nhap.setColumns(15);
		GridBagConstraints gbc_textField_nhap = new GridBagConstraints();
		gbc_textField_nhap.insets = new Insets(0, 60, 5, 10);
		gbc_textField_nhap.fill = GridBagConstraints.BOTH;
		gbc_textField_nhap.gridx = 1;
		gbc_textField_nhap.gridy = 5;
		panel_center.add(textField_nhap, gbc_textField_nhap);

		textField_url = new JTextField();
		textField_url.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_url.setEditable(false);
		GridBagConstraints gbc_textField_url = new GridBagConstraints();
		gbc_textField_url.anchor = GridBagConstraints.NORTH;
		gbc_textField_url.insets = new Insets(20, 70, 5, 5);
		gbc_textField_url.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_url.gridx = 2;
		gbc_textField_url.gridy = 8;
		panel_center.add(textField_url, gbc_textField_url);
		textField_url.setColumns(25);
		textField_url.setBorder(null);

		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setBorder(new TitledBorder(null, "Ghi Ch\u00FA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setPreferredSize(new Dimension(350, 40));
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(30, 90, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 8;
		panel_center.add(textArea, gbc_textArea);

		textField_le = new JTextField("0");
		textField_le.setPreferredSize(new Dimension(6, 33));
		textField_le.setMinimumSize(new Dimension(6, 33));
		textField_le.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_le.setColumns(15);
		GridBagConstraints gbc_textField_le = new GridBagConstraints();
		gbc_textField_le.insets = new Insets(0, 60, 5, 10);
		gbc_textField_le.fill = GridBagConstraints.BOTH;
		gbc_textField_le.gridx = 1;
		gbc_textField_le.gridy = 3;
		panel_center.add(textField_le, gbc_textField_le);

		textField_DVT = new JTextField();
		textField_DVT.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_textField_DVT = new GridBagConstraints();
		gbc_textField_DVT.fill = GridBagConstraints.BOTH;
		gbc_textField_DVT.insets = new Insets(0, 90, 5, 250);
		gbc_textField_DVT.gridx = 0;
		gbc_textField_DVT.gridy = 5;
		panel_center.add(textField_DVT, gbc_textField_DVT);
		textField_DVT.setColumns(15);

		lbhinhanh = new JLabel("");
		lbhinhanh.setSize(new Dimension(250, 250));
		GridBagConstraints gbc_lbhinhanh = new GridBagConstraints();
		gbc_lbhinhanh.gridwidth = 1;
		gbc_lbhinhanh.gridheight = 6;
		gbc_lbhinhanh.insets = new Insets(20, 70, 5, 5);
		gbc_lbhinhanh.anchor = GridBagConstraints.WEST;
		gbc_lbhinhanh.gridx = 2;
		gbc_lbhinhanh.gridy = 1;
		panel_center.add(lbhinhanh, gbc_lbhinhanh);

		JLabel lbmasp = new JLabel("Mã sản phẩm");
		lbmasp.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_lbmasp = new GridBagConstraints();
		gbc_lbmasp.gridwidth = 2;
		gbc_lbmasp.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lbmasp.insets = new Insets(20, 70, 5, 5);
		gbc_lbmasp.gridx = 0;
		gbc_lbmasp.gridy = 2;
		panel_center.add(lbmasp, gbc_lbmasp);

		textField_tensp = new JTextField();
		textField_tensp.setPreferredSize(new Dimension(6, 33));
		textField_tensp.setMinimumSize(new Dimension(6, 33));
		textField_tensp.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_textField_tensp = new GridBagConstraints();
		gbc_textField_tensp.fill = GridBagConstraints.BOTH;
		gbc_textField_tensp.insets = new Insets(0, 90, 5, 5);
		gbc_textField_tensp.gridx = 0;
		gbc_textField_tensp.gridy = 1;
		panel_center.add(textField_tensp, gbc_textField_tensp);
		textField_tensp.setColumns(35);

		JLabel lbgiabanle = new JLabel("Gía bán lẻ");
		lbgiabanle.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_lbgiabanle = new GridBagConstraints();
		gbc_lbgiabanle.insets = new Insets(20, 50, 5, 5);
		gbc_lbgiabanle.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lbgiabanle.gridx = 1;
		gbc_lbgiabanle.gridy = 2;
		panel_center.add(lbgiabanle, gbc_lbgiabanle);

		textField_masp = new JTextField();
		textField_masp.setEditable(false);
		textField_masp.setPreferredSize(new Dimension(6, 33));
		textField_masp.setMinimumSize(new Dimension(6, 33));
		textField_masp.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_textField_masp = new GridBagConstraints();
		gbc_textField_masp.fill = GridBagConstraints.BOTH;
		gbc_textField_masp.insets = new Insets(5, 90, 5, 100);
		gbc_textField_masp.gridx = 0;
		gbc_textField_masp.gridy = 3;
		panel_center.add(textField_masp, gbc_textField_masp);
		textField_masp.setColumns(20);

		JLabel lbDVT = new JLabel("DVT");
		lbDVT.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_lbDVT = new GridBagConstraints();
		gbc_lbDVT.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbDVT.gridwidth = 2;
		gbc_lbDVT.anchor = GridBagConstraints.SOUTH;
		gbc_lbDVT.insets = new Insets(20, 70, 5, 5);
		gbc_lbDVT.gridx = 0;
		gbc_lbDVT.gridy = 4;
		panel_center.add(lbDVT, gbc_lbDVT);

		JLabel lbmota = new JLabel("Thay đổi hình ảnh");
		lbmota.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					chooser.show();
					String url = chooser.getUrl();

					Image icon = new ImageIcon(url).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
					lbhinhanh.setIcon(new ImageIcon(icon));
					lbhinhanh.setText("");
					textField_url.setText(url);
				}
			}
		});
		lbmota.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbmota.setForeground(Color.BLUE);
		lbmota.setFont(new Font("Arial", Font.PLAIN, 17));
		GridBagConstraints gbc_lbmota = new GridBagConstraints();
		gbc_lbmota.anchor = GridBagConstraints.WEST;
		gbc_lbmota.insets = new Insets(0, 70, 5, 5);
		gbc_lbmota.gridx = 2;
		gbc_lbmota.gridy = 0;
		panel_center.add(lbmota, gbc_lbmota);

		return panel_center;
	}

	private JPanel panel_south() {
		JPanel panel_south = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_south.getLayout();
		flowLayout_1.setVgap(50);
		flowLayout_1.setHgap(70);
		flowLayout_1.setAlignment(FlowLayout.LEFT);

		btnluu_sp = new JButton("Lưu");
		btnluu_sp.setEnabled(DangNhap.action_sua);
		btnluu_sp.setToolTipText("Alt + s");
		btnluu_sp.setMnemonic(KeyEvent.VK_S);
		btnluu_sp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				luuSanPham();
				refresh();
			}
		});
		btnluu_sp.setPreferredSize(new Dimension(100, 40));
		btnluu_sp.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnluu_sp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_south.add(btnluu_sp);

		btnxoa_ctsp = new JButton("Xóa");
		btnxoa_ctsp.setEnabled(DangNhap.action_xoa);
		btnxoa_ctsp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sql_chitietHD = " DELETE FROM CHITIETHOADON WHERE MAHANG = ?"; // XOA BẢNG CON CHỨA MÃ HÀNG CẦN
																						// xoa

				String sql_mathang = " DELETE FROM  MATHANG WHERE MAHANG = ? "; // XÓA MÃ HÀNG
				try {

					PreparedStatement pr = DangNhap.con.prepareStatement(sql_chitietHD);
					pr.setString(1, textField_masp.getText());
					pr.addBatch();
					pr = DangNhap.con.prepareStatement(sql_mathang);
					pr.setString(1, textField_masp.getText());
					pr.addBatch();
					int xacnhan = JOptionPane.showConfirmDialog(null, "Xác nhận xóa sản phẩm ? ", null,
							JOptionPane.YES_NO_OPTION);
					if (xacnhan == JOptionPane.YES_OPTION) {
						pr.executeBatch();
						refresh();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Lỗi xóa sản phẩm ");
					System.out.println("chi tiet san pham - btnxoa: " + e2.getMessage());
				}
			}
		});
		btnxoa_ctsp.setFocusable(false);
		btnxoa_ctsp.setForeground(Color.black);
		btnxoa_ctsp.setBackground(new Color(240, 240, 240));
		btnxoa_ctsp.setPreferredSize(new Dimension(150, 40));
		btnxoa_ctsp.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnxoa_ctsp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_south.add(btnxoa_ctsp);

		return panel_south;
	}

	public chitietSanPham(String txt) throws SQLException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and Feel not set");
		}
		setLayout(new BorderLayout());

		FileNameExtensionFilter format = new FileNameExtensionFilter("file(.*)", "png", "jpg", "gif", "svg");
		chooser = new ChooserFile("Imager_products", format, false);

		check = new Check_er();

		JPanel pn_tong = new JPanel(new BorderLayout());
		pn_tong.add(panel_north(), BorderLayout.NORTH);
		pn_tong.add(panel_center(), BorderLayout.CENTER);
		pn_tong.add(panel_south(), BorderLayout.SOUTH);

		JScrollPane js = new JScrollPane(pn_tong);
		js.getVerticalScrollBar().setUnitIncrement(10);
		add(js, BorderLayout.CENTER);
		/*
		 * 
		 * 
		 */
		getdataSP(txt);
	}

	private void luuSanPham() {
		String sql = "UPDATE MATHANG SET TenHang = ? ,GIABANLE = ? , DonViTinh = ? ,GhiChu = ? ,ANH = ? ,GIANHAP =? FROM MATHANG WHERE MAHANG = ? ";
		try {
			PreparedStatement ps = DangNhap.con.prepareStatement(sql);
			ps.setString(1, textField_tensp.getText().trim());
			ps.setDouble(2, Double.parseDouble(textField_le.getText()));
			ps.setString(3, textField_DVT.getText().trim());
			ps.setString(4, textArea.getText().trim());
			ps.setString(5, chooser.diachi_anh(textField_url.getText()));
			ps.setDouble(6, Double.parseDouble(textField_nhap.getText()));
			ps.setString(7, textField_masp.getText());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "update thanh cong");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "update khong thanh cong", null, JOptionPane.ERROR_MESSAGE);
			System.out.println("chi tiet san pham -btn luu: " + e.getMessage());
		}
	}

	private void refresh() {
		int index = cardpanel_sanpham.tabbedPane.getSelectedIndex();
		cardpanel_sanpham.removeTab(index);
	}
}
