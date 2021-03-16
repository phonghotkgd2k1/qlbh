package Business;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class cardpanel_baocao extends JPanel {

	private JLabel lblNewLabel;
	private JPanel panel_baocaohangngay;
	private JToolBar toolBar;
	static JComboBox<Integer> comboBox_ngaybd;
	static JComboBox<Integer> comboBox_thangbd;
	static JComboBox<Integer> comboBox_nambd;
	private JLabel lblNewLabel_1;
	private JToolBar toolBar_1;
	static JComboBox<Integer> comboBox_ngaykt;
	static JComboBox<Integer> comboBox_thangkt;
	static JComboBox<Integer> comboBox_namkt;
	private JButton btnxacnhan;
	private thongkedoanhthu thongke;
	private bieudocot cot;
	private JPanel panel_South;
	private bieudoduong bieudoDuong;
	private TopNhanVienKinhDoanh topnhanvien;
	private sanphambanchay pnsanphambanchay;
	static int d1, d2, m1, m2, y1, y2;

	private boolean action() {
		d1 = (int) comboBox_ngaybd.getSelectedItem();
		m1 = (int) comboBox_thangbd.getSelectedItem();
		y1 = (int) comboBox_nambd.getSelectedItem();
		d2 = (int) comboBox_ngaykt.getSelectedItem();
		m2 = (int) comboBox_thangkt.getSelectedItem();
		y2 = (int) comboBox_namkt.getSelectedItem();
		SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date start = simple.parse(d1 + "-" + m1 + "-" + y1);
			Date end = simple.parse(d2 + "-" + m2 + "-" + y2);
			long time = end.getTime() - start.getTime();
			if ((time / 1000 / 24 / 60 / 60) > 365 * 2) {
				JOptionPane.showMessageDialog(null, "Chỉ thống kê trong thời gian 2 năm.");
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
		return true;
	}

	private JPanel panel_north() {
		JPanel panel_tongquanbaocao = new JPanel();
		panel_tongquanbaocao.setPreferredSize(new Dimension(50, 100));

		GridBagLayout gbl_panel_tongquanbaocao = new GridBagLayout();
		gbl_panel_tongquanbaocao.columnWidths = new int[] { 34, 238, 0, 0, 0 };
		gbl_panel_tongquanbaocao.rowHeights = new int[] { 29, 0, 0, 0, 0 };
		gbl_panel_tongquanbaocao.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_tongquanbaocao.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_tongquanbaocao.setLayout(gbl_panel_tongquanbaocao);

		lblNewLabel = new JLabel("Tổng Quan Báo Cáo");
		lblNewLabel.setIconTextGap(0);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 29));
		lblNewLabel.setAlignmentX(1.0f);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.ipadx = 50;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel_tongquanbaocao.add(lblNewLabel, gbc_lblNewLabel);

		toolBar = new JToolBar();
		toolBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		toolBar.setBorder(null);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.ipadx = 90;
		gbc_toolBar.fill = GridBagConstraints.BOTH;
		gbc_toolBar.insets = new Insets(10, 30, 20, 5);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 2;
		panel_tongquanbaocao.add(toolBar, gbc_toolBar);

		comboBox_ngaybd = new JComboBox<Integer>();
		comboBox_ngaybd.setBackground(Color.WHITE);
		comboBox_ngaybd.setFont(new Font("Arial", Font.PLAIN, 16));
		toolBar.add(comboBox_ngaybd);

		comboBox_thangbd = new JComboBox<Integer>();
		comboBox_thangbd.setBackground(Color.WHITE);
		comboBox_thangbd.setFont(new Font("Arial", Font.PLAIN, 16));
		toolBar.add(comboBox_thangbd);

		comboBox_nambd = new JComboBox<Integer>();
		comboBox_nambd.setBackground(Color.WHITE);
		comboBox_nambd.setFont(new Font("Arial", Font.PLAIN, 16));
		toolBar.add(comboBox_nambd);

		lblNewLabel_1 = new JLabel("Đến ngày");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(10, 0, 20, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panel_tongquanbaocao.add(lblNewLabel_1, gbc_lblNewLabel_1);

		toolBar_1 = new JToolBar();
		toolBar_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		toolBar_1.setBorder(null);
		GridBagConstraints gbc_toolBar_1 = new GridBagConstraints();
		gbc_toolBar_1.ipadx = 90;
		gbc_toolBar_1.fill = GridBagConstraints.BOTH;
		gbc_toolBar_1.insets = new Insets(10, 0, 20, 5);
		gbc_toolBar_1.gridx = 2;
		gbc_toolBar_1.gridy = 2;
		panel_tongquanbaocao.add(toolBar_1, gbc_toolBar_1);

		comboBox_ngaykt = new JComboBox<Integer>();
		comboBox_ngaykt.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBox_ngaykt.setBackground(Color.WHITE);
		toolBar_1.add(comboBox_ngaykt);

		comboBox_thangkt = new JComboBox<Integer>();
		comboBox_thangkt.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBox_thangkt.setBackground(Color.WHITE);
		toolBar_1.add(comboBox_thangkt);

		comboBox_namkt = new JComboBox<Integer>();
		comboBox_namkt.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBox_namkt.setBackground(Color.WHITE);
		toolBar_1.add(comboBox_namkt);

		btnxacnhan = new JButton("Xác nhận");
		btnxacnhan.setOpaque(false);
		btnxacnhan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (action()) {
					capnhat_thongkedoanhthu();
					capnhat_bieudocot();
					capnhat_topnhanvien();
					capnhat_bieudoduong();
					capnhat_sanphambanchay();
				}
			}
		});
		btnxacnhan.setBackground(Color.WHITE);
		btnxacnhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnxacnhan.setForeground(Color.BLACK);
		btnxacnhan.setFont(new Font("Arial", Font.BOLD, 16));
		GridBagConstraints gbc_btnxacnhan = new GridBagConstraints();
		gbc_btnxacnhan.ipady = 2;
		gbc_btnxacnhan.fill = GridBagConstraints.BOTH;
		gbc_btnxacnhan.insets = new Insets(10, 20, 20, 0);
		gbc_btnxacnhan.gridx = 3;
		gbc_btnxacnhan.gridy = 2;
		panel_tongquanbaocao.add(btnxacnhan, gbc_btnxacnhan);

		return panel_tongquanbaocao;
	}

	private JPanel panel_center() {
		thongke = new thongkedoanhthu(d1, m1, y1, d2, m2, y2);
		thongke.hienthibaocao();
		JPanel Center = thongke;
		return Center;
	}

	private JPanel panel_south() {

		panel_South = new JPanel();

		GridBagLayout gbl_panel_center = new GridBagLayout();
		gbl_panel_center.columnWidths = new int[] { 453, 453, 0 };
		gbl_panel_center.rowHeights = new int[] { 249, 249, 0, 0 };
		gbl_panel_center.columnWeights = new double[] { 1.0, 0.0, 1.0 };
		gbl_panel_center.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		panel_South.setLayout(gbl_panel_center);

		// ***********************************************************************

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 3;
		gbc.ipady = 5;
		gbc.ipadx = 5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 5, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		bieudoDuong = new bieudoduong();
		panel_South.add(bieudoDuong, gbc);

		panel_baocaohangngay = new baocaohangngay();
		panel_baocaohangngay.setPreferredSize(new Dimension(40, 265));
		GridBagConstraints gbc_panel_baocaohangngay = new GridBagConstraints();
		gbc_panel_baocaohangngay.fill = GridBagConstraints.BOTH;
		gbc_panel_baocaohangngay.ipady = 5;
		gbc_panel_baocaohangngay.ipadx = 5;
		gbc_panel_baocaohangngay.insets = new Insets(0, 0, 5, 5);
		gbc_panel_baocaohangngay.gridx = 1;
		gbc_panel_baocaohangngay.gridy = 1;
		panel_South.add(panel_baocaohangngay, gbc_panel_baocaohangngay);

		// them bieu do cot
		GridBagConstraints gbc_1 = new GridBagConstraints();
		gbc_1.fill = GridBagConstraints.BOTH;
		gbc_1.insets = new Insets(0, 0, 5, 5);
		gbc_1.gridx = 0;
		gbc_1.gridy = 1;
		panel_South.add(cot, gbc_1);

//		GridBagConstraints gbc_2 = new GridBagConstraints();
//		gbc_2.fill = GridBagConstraints.BOTH;
//		gbc_2.insets = new Insets(0, 0, 0, 5);
//		gbc_2.gridx = 1;
//		gbc_2.gridy = 1;
//		tysuatloinhuan tysuat = new tysuatloinhuan();
//		 panel_center.add( tysuat, gbc_2);

		/**
		 * them san pham ban chạy
		 */
		GridBagConstraints gbc_panel_table = new GridBagConstraints();
		gbc_panel_table.gridwidth = 2;
		gbc_panel_table.insets = new Insets(5, 5, 5, 5);
		gbc_panel_table.fill = GridBagConstraints.BOTH;
		gbc_panel_table.gridx = 1;
		gbc_panel_table.gridy = 2;
		panel_South.add(pnsanphambanchay, gbc_panel_table);

		/*
		 * them top nhan vien
		 */
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(5, 5, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		panel_South.add(topnhanvien, gbc_panel);

		return panel_South;
	}

	public cardpanel_baocao() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and Feel not set");
		}
		setLayout(new BorderLayout());

		JPanel pn = new JPanel(new BorderLayout(10, 10));

		JScrollPane js = new JScrollPane(pn);
		js.getVerticalScrollBar().setUnitIncrement(10);

		pn.add(panel_north(), BorderLayout.NORTH);
		addMode();
		action();

		pn.add(panel_center(), BorderLayout.CENTER);

		topnhanvien = new TopNhanVienKinhDoanh(d1, d2, m1, m2, y1, y2);
		pnsanphambanchay = new sanphambanchay(d1, d2, m1, m2, y1, y2);
		cot = new bieudocot();
		pn.add(panel_south(), BorderLayout.SOUTH);

		add(js, BorderLayout.CENTER);
	}

	private void addMode() {
		NgayThang day = new NgayThang();

		day.Ngay();
		comboBox_ngaybd.setModel(day.getNgay());

		day.Thang();
		comboBox_thangbd.setModel(day.getThang());

		day.Nam();
		comboBox_nambd.setModel(day.getNam());

		NgayThang day2 = new NgayThang();
		String dateString2[] = day2.getDate().split("[ ]");

		day2.Ngay();
		comboBox_ngaykt.setModel(day2.getNgay());
		comboBox_ngaykt.setSelectedItem(Integer.parseInt(dateString2[0]));

		day2.Thang();
		comboBox_thangkt.setModel(day2.getThang());
		comboBox_thangkt.setSelectedItem(Integer.parseInt(dateString2[1]));

		day2.Nam();
		comboBox_namkt.setModel(day2.getNam());
		comboBox_namkt.setSelectedItem(Integer.parseInt(dateString2[2]));

		Calendar cal = Calendar.getInstance();
		cal.add(cal.DATE, -7);
		comboBox_ngaybd.setSelectedItem(cal.get(cal.DATE));
		comboBox_thangbd.setSelectedItem((cal.get(cal.MONTH) + 1));
		comboBox_nambd.setSelectedItem(cal.get(cal.YEAR));
	}

	private void capnhat_bieudocot() {
		panel_South.remove(cot);

		GridBagConstraints gbc_1 = new GridBagConstraints();
		gbc_1.fill = GridBagConstraints.BOTH;
		gbc_1.insets = new Insets(0, 0, 0, 5);
		gbc_1.gridx = 0;
		gbc_1.gridy = 1;

		cot = new bieudocot();
		panel_South.add(cot, gbc_1);
	}

	private void capnhat_bieudoduong() {
		panel_South.remove(bieudoDuong);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 3;
		gbc.ipady = 5;
		gbc.ipadx = 5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		bieudoDuong = new bieudoduong();
		panel_South.add(bieudoDuong, gbc);
	}

	private void capnhat_topnhanvien() {
		panel_South.remove(topnhanvien);

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(5, 5, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		topnhanvien = new TopNhanVienKinhDoanh(d1, d2, m1, m2, y1, y2);
		panel_South.add(topnhanvien, gbc_panel);
	}

	private void capnhat_thongkedoanhthu() {
		thongke.setNgay1(y1, m1, d1);
		thongke.setNgay2(y2, m2, d2);
		thongke.hienthibaocao();
	}

	private void capnhat_sanphambanchay() {
		panel_South.remove(pnsanphambanchay);

		GridBagConstraints gbc_panel_table = new GridBagConstraints();
		gbc_panel_table.insets = new Insets(5, 5, 5, 5);
		gbc_panel_table.fill = GridBagConstraints.BOTH;
		gbc_panel_table.gridx = 2;
		gbc_panel_table.gridy = 2;
		pnsanphambanchay = new sanphambanchay(d1, d2, m1, m2, y1, y2);
		panel_South.add(pnsanphambanchay, gbc_panel_table);
	}
}
