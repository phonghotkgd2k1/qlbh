package Business;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class jframe_banhang extends JFrame implements ActionListener {
	private int index;
	JTabbedPane tabbedPane;
	private int indexString;

	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setTitle("1.4.0");
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private boolean kiemtraNULL() {
		String sql = " select count(mahd) from hoadonban ";
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				if (rs.getInt(1) == 0)
					return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	String maHoaDonTuDong() {
		String sql = null;
		if (kiemtraNULL() == false)
			sql = "select top 1 MaHD from HoaDonBan where MaHD like N'07KPTQ/%' order  by MaHD desc ";
		else {
			return "07KPTQ/0000001";
		}
		int HD = 0;
		try {
			Statement st = DangNhap.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				String mahd = rs.getString(1);
				String temp[] = mahd.split("/");
				HD = Integer.parseInt(temp[1]);
				HD++;
				temp[0] += "/";
				String hdString = Integer.toString(HD);
				for (int i = 0; i < (7 - hdString.length()); i++) {
					temp[0] += "0";
				}
				temp[0] += hdString;
				return temp[0];
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "lỗi cập nhật mã hóa đơn");
			System.out.println("jframe_banhang - maHoaDonTuDong(): " + e.getMessage());
		}
		return null;
	}

	public jframe_banhang() throws SQLException, ParseException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1383, 773);
		getContentPane().setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		tabbedPane.add(new DonHang(this), "Đơn " + index, index++);
		tabbedPane.setTabComponentAt(0, new ThemTab(this));
		// them dau cong
		tabbedPane.add(null, "+", index++);
		indexString++;

		// LUU Y : ĐẶT EVENT Ở CUỐI CÙNG
		tabbedPane.addChangeListener(changelistener);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Thêm");
		mnNewMenu.setFont(new Font("Arial", Font.PLAIN, 16));
		mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.setPreferredSize(new Dimension(65, 24));
		menuBar.add(mnNewMenu);

		mnthemSP = new JMenuItem("Thêm sản phẩm");
		mnthemSP.addActionListener(this);
		mnthemSP.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
		mnthemSP.setIconTextGap(7);
		mnthemSP.setPreferredSize(new Dimension(300, 30));
		mnthemSP.setIcon(new ImageIcon("Imager\\themsp-25.png"));
		mnthemSP.setFont(new Font("Arial", Font.PLAIN, 17));
		mnNewMenu.add(mnthemSP);

		mnthemKH = new JMenuItem("Thêm khách hàng");
		mnthemKH.addActionListener(this);
		mnthemKH.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.ALT_MASK));
		mnthemKH.setIconTextGap(7);
		mnthemKH.setPreferredSize(new Dimension(250, 30));
		mnthemKH.setIcon(new ImageIcon("Imager\\themkhachhang-25.png"));
		mnthemKH.setFont(new Font("Arial", Font.PLAIN, 17));
		mnNewMenu.add(mnthemKH);

		mnthemNKH = new JMenuItem("Thêm nhóm KH");
		mnthemNKH.addActionListener(this);
		mnthemNKH.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK));
		mnthemNKH.setIconTextGap(7);
		mnthemNKH.setPreferredSize(new Dimension(250, 30));
		mnthemNKH.setIcon(new ImageIcon("Imager\\themnhomkhachhang-25.png"));
		mnthemNKH.setFont(new Font("Arial", Font.PLAIN, 17));
		mnNewMenu.add(mnthemNKH);

		mnthemDH = new JMenuItem("Thêm đơn hàng");
		mnthemDH.addActionListener(this);
		mnthemDH.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
		mnthemDH.setPreferredSize(new Dimension(250, 30));
		mnthemDH.setIconTextGap(7);
		mnthemDH.setFont(new Font("Arial", Font.PLAIN, 17));
		mnNewMenu.add(mnthemDH);

		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(250, 3));
		mnNewMenu.add(separator);

		mnthoat = new JMenuItem("Thoát");
		mnthoat.addActionListener(this);
		mnthoat.setPreferredSize(new Dimension(250, 30));
		mnthoat.setHorizontalAlignment(SwingConstants.LEFT);
		mnthoat.setFont(new Font("Arial", Font.PLAIN, 17));
		mnNewMenu.add(mnthoat);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				boolean action_them = DangNhap.action_them;
				mnthemKH.setEnabled(action_them);
				mnthemNKH.setEnabled(action_them);
				mnthemSP.setEnabled(action_them);
			}
		});
	}

	ChangeListener changelistener = new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent e) {
			themtab();
		}
	};
	private JMenuItem mnthemSP;
	private JMenuItem mnthemKH;
	private JMenuItem mnthemNKH;
	private JMenuItem mnthemDH;
	private JMenuItem mnthoat;

	public void xoaTab(int i) {
		if (i > -1) {
			tabbedPane.remove(i);
			index--;
			if (i == index - 1 && i > 0) {
				tabbedPane.setSelectedIndex(index - 2);
			} else
				tabbedPane.setSelectedIndex(i);
			if (index == 1) {
				indexString = 1;
				themtab();
			}
		}
	}

	public void themtab() {
		try {
			int i = index - 1;
			if (i == tabbedPane.getSelectedIndex()) {
				tabbedPane.add(new DonHang(this), "Đơn " + indexString, i);
				tabbedPane.setTabComponentAt(i, new ThemTab(this));
				tabbedPane.removeChangeListener(changelistener);
				tabbedPane.setSelectedIndex(i);
				tabbedPane.addChangeListener(changelistener);
				index++;
				indexString++;
			}
		} catch (ParseException | SQLException e) {
			System.out.println("jframe_banhang - themtab: " + e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object action = e.getSource();
		if (action == mnthemDH) {
			tabbedPane.setSelectedIndex(index - 1);
			themtab();
		} else if (action == mnthemSP) {
			themsanpham them = new themsanpham();
			them.run();
		} else if (action == mnthemKH) {
			themkhachhang them = new themkhachhang();
			them.run();
		} else if (action == mnthemNKH) {
			themnhomkhachhang them = new themnhomkhachhang();
			them.run();
		} else if (action == mnthoat) {
			dispose();
		}
	}
}
