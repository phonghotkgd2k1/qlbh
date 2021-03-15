package Business;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.Font;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Cursor;

public class baocaohangngay extends JPanel {
	private Calendar calendar;
	private int ngay, thang, nam;
	private JLabel lbdonhang;
	private JLabel lbnhaphang;
	private JLabel lbxuathang;
	private JLabel lbkhachhang;

	private void soKhachHang() {
		String sql = "select  count(makh) from HoaDonBan where DAY(NgayTao) = ? and MONTH(NgayTao ) = ?   and YEAR(NgayTao ) = ? ";
		int sl = 0;
		try {
			PreparedStatement pr = DangNhap.con.prepareStatement(sql);
			pr.setInt(1, ngay);
			pr.setInt(2, thang);
			pr.setInt(3, nam);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				sl = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("baocaohangngay - sokhachhang: " + e.getMessage());
		}
		lbkhachhang.setText(sl + "");
	}

	private void soDonHang() {
		String sql = "select  count(mahd) from HoaDonBan where DAY(NgayTao) = ? and MONTH(NgayTao ) = ?   and YEAR(NgayTao ) = ? ";
		int sl = 0;
		try {
			PreparedStatement pr = DangNhap.con.prepareStatement(sql);
			pr.setInt(1, ngay);
			pr.setInt(2, thang);
			pr.setInt(3, nam);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				sl = rs.getInt(1);

			}
		} catch (Exception e) {
			System.out.println("baocaohangngay - sokhachhang: " + e.getMessage());
		}
		lbdonhang.setText(sl + "");
	}

	private void getNgayThang() {
		calendar = calendar.getInstance();
		ngay = calendar.get(Calendar.DATE);

		// THÁNG BẮT ĐẦU TỪ O ???
		thang = calendar.get(Calendar.MONTH) + 1;
		nam = calendar.get(Calendar.YEAR);
	}

	public baocaohangngay() {
		GUI();
		getNgayThang();
		get_baocao();
	}

	public void get_baocao() {
		soDonHang();
		soKhachHang();
	}

	private void GUI() {

		setBackground(new Color(243, 243, 243));
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Th\u00F4ng Tin Kinh Doanh H\u00F4m Nay", TitledBorder.LEADING, TitledBorder.TOP,
				new Font("Arial", Font.PLAIN, 16), null));
		setLayout(new BorderLayout(0, 0));

		JPanel panel_baocaohangngay = new JPanel();
		panel_baocaohangngay.setToolTipText("");
		panel_baocaohangngay.setBackground(Color.WHITE);

		add(panel_baocaohangngay);
		GridBagLayout gbl_panel_baocaohangngay = new GridBagLayout();
		gbl_panel_baocaohangngay.columnWidths = new int[] { 56, 0, 0 };
		gbl_panel_baocaohangngay.rowHeights = new int[] { 0, 16, 0, 16, 0, 16, 0, 16, 0, 0, 0, 0 };
		gbl_panel_baocaohangngay.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_baocaohangngay.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel_baocaohangngay.setLayout(gbl_panel_baocaohangngay);

		JLabel lbtailai = new JLabel("Tải lại");
		lbtailai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					get_baocao();
				}
			}
		});
		lbtailai.setIcon(new ImageIcon("Imager\\tailai2-25.png"));
		lbtailai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbtailai.setForeground(Color.BLUE);
		lbtailai.setIconTextGap(10);
		lbtailai.setFont(new Font("Arial", Font.BOLD, 17));
		GridBagConstraints gbc_lbtailai = new GridBagConstraints();
		gbc_lbtailai.ipady = 10;
		gbc_lbtailai.anchor = GridBagConstraints.EAST;
		gbc_lbtailai.fill = GridBagConstraints.VERTICAL;
		gbc_lbtailai.insets = new Insets(10, 0, 5, 20);
		gbc_lbtailai.gridx = 1;
		gbc_lbtailai.gridy = 0;
		panel_baocaohangngay.add(lbtailai, gbc_lbtailai);

		JLabel lblNewLabel = new JLabel("Khách Hàng");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setMinimumSize(new Dimension(56, 16));
		lblNewLabel.setMaximumSize(new Dimension(56, 16));
		lblNewLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewLabel.setIconTextGap(10);
		lblNewLabel.setIcon(new ImageIcon("Imager\\khachhang-40.png"));
		lblNewLabel.setPreferredSize(new Dimension(56, 45));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 23));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(10, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel_baocaohangngay.add(lblNewLabel, gbc_lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setOpaque(true);
		separator.setForeground(Color.CYAN);
		separator.setBackground(Color.WHITE);
		separator.setPreferredSize(new Dimension(5, 1));
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 2;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		gbc_separator.weightx = 1;
		panel_baocaohangngay.add(separator, gbc_separator);

		JLabel lblNewLabel_1 = new JLabel(" Đơn Hàng");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewLabel_1.setIcon(new ImageIcon("Imager\\donhang2-40.png"));
		lblNewLabel_1.setIconTextGap(5);
		lblNewLabel_1.setPreferredSize(new Dimension(56, 45));
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 23));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(20, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 3;
		panel_baocaohangngay.add(lblNewLabel_1, gbc_lblNewLabel_1);

		lbdonhang = new JLabel("0");
		lbdonhang.setOpaque(true);
		lbdonhang.setBackground(Color.WHITE);
		lbdonhang.setHorizontalAlignment(SwingConstants.CENTER);
		lbdonhang.setVerticalAlignment(SwingConstants.BOTTOM);
		lbdonhang.setFont(new Font("Arial", Font.BOLD, 20));
		GridBagConstraints gbc_lbdonhang = new GridBagConstraints();
		gbc_lbdonhang.fill = GridBagConstraints.BOTH;
		gbc_lbdonhang.insets = new Insets(20, 0, 5, 5);
		gbc_lbdonhang.gridx = 0;
		gbc_lbdonhang.gridy = 3;
		panel_baocaohangngay.add(lbdonhang, gbc_lbdonhang);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOpaque(true);
		separator_1.setForeground(new Color(0, 255, 255));
		separator_1.setBackground(Color.WHITE);
		separator_1.setPreferredSize(new Dimension(5, 1));
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 2;
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 4;
		panel_baocaohangngay.add(separator_1, gbc_separator_1);

		JLabel lblNewLabel_3 = new JLabel("Nhập Hàng");
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setIcon(new ImageIcon("Imager\\nhaphang-40.png"));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_3.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNewLabel_3.setIconTextGap(10);
		lblNewLabel_3.setPreferredSize(new Dimension(56, 45));
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 23));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_3.insets = new Insets(20, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 5;
		panel_baocaohangngay.add(lblNewLabel_3, gbc_lblNewLabel_3);

		lbnhaphang = new JLabel("0");
		lbnhaphang.setOpaque(true);
		lbnhaphang.setBackground(Color.WHITE);
		lbnhaphang.setHorizontalAlignment(SwingConstants.CENTER);
		lbnhaphang.setVerticalAlignment(SwingConstants.BOTTOM);
		lbnhaphang.setFont(new Font("Arial", Font.BOLD, 20));
		GridBagConstraints gbc_lbnhaphang = new GridBagConstraints();
		gbc_lbnhaphang.fill = GridBagConstraints.BOTH;
		gbc_lbnhaphang.insets = new Insets(20, 0, 5, 5);
		gbc_lbnhaphang.gridx = 0;
		gbc_lbnhaphang.gridy = 5;
		panel_baocaohangngay.add(lbnhaphang, gbc_lbnhaphang);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOpaque(true);
		separator_2.setForeground(Color.CYAN);
		separator_2.setBackground(Color.WHITE);
		separator_2.setPreferredSize(new Dimension(5, 1));
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.gridwidth = 2;
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 6;
		panel_baocaohangngay.add(separator_2, gbc_separator_2);

		JLabel lblNewLabel_2 = new JLabel("Xuất Hàng");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setIcon(new ImageIcon("Imager\\xuathang-40.png"));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewLabel_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setIconTextGap(10);
		lblNewLabel_2.setPreferredSize(new Dimension(56, 45));
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 23));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(20, 0, 5, 0);
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 7;
		panel_baocaohangngay.add(lblNewLabel_2, gbc_lblNewLabel_2);

		lbxuathang = new JLabel("0");
		lbxuathang.setOpaque(true);
		lbxuathang.setBackground(Color.WHITE);
		lbxuathang.setHorizontalAlignment(SwingConstants.CENTER);
		lbxuathang.setVerticalAlignment(SwingConstants.BOTTOM);
		lbxuathang.setFont(new Font("Arial", Font.BOLD, 20));
		GridBagConstraints gbc_lbxuathang = new GridBagConstraints();
		gbc_lbxuathang.fill = GridBagConstraints.BOTH;
		gbc_lbxuathang.insets = new Insets(20, 0, 5, 5);
		gbc_lbxuathang.gridx = 0;
		gbc_lbxuathang.gridy = 7;
		panel_baocaohangngay.add(lbxuathang, gbc_lbxuathang);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOpaque(true);
		separator_3.setPreferredSize(new Dimension(5, 1));
		separator_3.setForeground(Color.CYAN);
		separator_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.gridwidth = 2;
		gbc_separator_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_3.insets = new Insets(0, 0, 5, 0);
		gbc_separator_3.gridx = 0;
		gbc_separator_3.gridy = 8;
		panel_baocaohangngay.add(separator_3, gbc_separator_3);

		lbkhachhang = new JLabel("0");
		lbkhachhang.setOpaque(true);
		lbkhachhang.setBackground(Color.WHITE);
		lbkhachhang.setHorizontalAlignment(SwingConstants.CENTER);
		lbkhachhang.setVerticalAlignment(SwingConstants.BOTTOM);
		lbkhachhang.setFont(new Font("Arial", Font.BOLD, 20));
		GridBagConstraints gbc_lbkhachhang = new GridBagConstraints();
		gbc_lbkhachhang.fill = GridBagConstraints.BOTH;
		gbc_lbkhachhang.insets = new Insets(10, 0, 5, 5);
		gbc_lbkhachhang.gridx = 0;
		gbc_lbkhachhang.gridy = 1;
		panel_baocaohangngay.add(lbkhachhang, gbc_lbkhachhang);

	}
}
