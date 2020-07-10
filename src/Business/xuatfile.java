package Business;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class xuatfile extends JDialog {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_url;
	private String tableName;
	private String id;
	private JLabel lbtruongxuat;
	private CardLayout card;
	private JPanel panel_Center;
	private JLabel lbicon;
	private JRadioButton rdbtn_trenbang;
	private JRadioButton rdbtn_duocchon;
	private JTable table;
	private JCheckBox checkBoxs[];

	public void run() {
		try {

			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	private void South() {
		JPanel panel_South = new JPanel();
		panel_South.setBackground(new Color(240, 240, 240));
		FlowLayout fl_panel_South = (FlowLayout) panel_South.getLayout();
		fl_panel_South.setHgap(10);
		fl_panel_South.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel_South, BorderLayout.SOUTH);

		JButton btnxuatfile = new JButton("Xuất file");
		btnxuatfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				executed ect = new executed(DangNhap.con);

				ArrayList<String> getRows = getRows();
				if (rdbtn_duocchon.isSelected() ) {
					if (getRows.size() <= 0) {
						JOptionPane.showMessageDialog(null, "không có hàng nào được chọn");
					} else {
						ect.ghiFile(getField(), getRows, tableName, id, textField_url.getText());
					}

				} else if (rdbtn_trenbang.isSelected()) {
					ect.ghiFile(getField(), table, tableName, id, textField_url.getText());
				} else {
					JOptionPane.showMessageDialog(null, "lựa chọn điều kiện xuất");
				}
				dispose();
			}

		});
		btnxuatfile.setForeground(Color.BLUE);
		btnxuatfile.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_South.add(btnxuatfile);

		JButton btnthoat = new JButton("Thoát");
		btnthoat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		btnthoat.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_South.add(btnthoat);
	}

	private void North() {
		JPanel panel_North = new JPanel();
		panel_North.setBackground(new Color(240, 240, 240));
		FlowLayout flowLayout = (FlowLayout) panel_North.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setHgap(20);
		getContentPane().add(panel_North, BorderLayout.NORTH);

		lbicon = new JLabel("");
		lbicon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showlayout("dieukienxuat");
				lbicon.setVisible(false);
			}
		});
		lbicon.setIcon(new ImageIcon("Imager\\back_25px.png"));
		lbicon.setVisible(false);
		panel_North.add(lbicon);

		JLabel lbtitle = new JLabel("Xuất file danh sách");
		lbtitle.setFont(new Font("Arial", Font.PLAIN, 33));
		panel_North.add(lbtitle);
	}

	private JPanel dieukienxuat() {
		JPanel panel_dieukienxuat = new JPanel();
		panel_dieukienxuat.setBackground(Color.WHITE);

		panel_dieukienxuat.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 0, 830, 2);
		panel_dieukienxuat.add(separator);

		JLabel lblNewLabel_1 = new JLabel("Lựa chọn kết quả xuất ");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(30, 41, 222, 22);
		panel_dieukienxuat.add(lblNewLabel_1);

		rdbtn_trenbang = new JRadioButton("Dữ liệu trên bảng");
		rdbtn_trenbang.setOpaque(false);
		buttonGroup.add(rdbtn_trenbang);
		rdbtn_trenbang.setFont(new Font("Arial", Font.PLAIN, 18));
		rdbtn_trenbang.setBounds(30, 104, 206, 25);
		panel_dieukienxuat.add(rdbtn_trenbang);

		rdbtn_duocchon = new JRadioButton("Hàng được chọn ");
		rdbtn_duocchon.setOpaque(false);
		buttonGroup.add(rdbtn_duocchon);
		rdbtn_duocchon.setFont(new Font("Arial", Font.PLAIN, 18));
		rdbtn_duocchon.setBounds(30, 157, 206, 25);
		panel_dieukienxuat.add(rdbtn_duocchon);

		lbtruongxuat = new JLabel("lựa chọn trường xuất");
		lbtruongxuat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showlayout("truongxuat");
				lbicon.setVisible(true);
			}
		});
		lbtruongxuat.setForeground(Color.BLUE);
		lbtruongxuat.setFont(new Font("Arial", Font.BOLD, 20));
		lbtruongxuat.setBounds(30, 221, 206, 25);
		panel_dieukienxuat.add(lbtruongxuat);

		JLabel lbfilename = new JLabel("File name :");
		lbfilename.setFont(new Font("Arial", Font.PLAIN, 18));
		lbfilename.setBounds(30, 293, 95, 28);
		panel_dieukienxuat.add(lbfilename);

		textField_url = new JTextField();
		textField_url.setFont(new Font("Arial", Font.PLAIN, 18));
		textField_url.setBounds(127, 294, 565, 25);
		panel_dieukienxuat.add(textField_url);
		textField_url.setColumns(10);

		JButton btnThaydoiFile = new JButton("Thay đổi");
		btnThaydoiFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter format = new FileNameExtensionFilter("file(.*)", "txt");
				ChooserFile chooser = new ChooserFile(format, false);
				chooser.setFileName(textField_url.getText());
				chooser.show();
				String url = chooser.getUrl();
				if (url != null)
					textField_url.setText(url);

			}
		});
		btnThaydoiFile.setFont(new Font("Arial", Font.PLAIN, 18));
		btnThaydoiFile.setBounds(704, 295, 115, 25);
		panel_dieukienxuat.add(btnThaydoiFile);

		return panel_dieukienxuat;
	}

	private JPanel truongxuat() {
		Font f = new Font("Arial", Font.PLAIN, 18);

		JPanel panel_truongxuat = new JPanel();
		panel_truongxuat.setBackground(Color.WHITE);
		panel_truongxuat.setLayout(new GridLayout(0, 3, 0, 0));

		executed ect = new executed(DangNhap.con);
		ArrayList<String> dscot = ect.getTenCot(tableName);
		int size = dscot.size();

		/*
		 * chuyen arraylist sang string[]
		 */
		String dString[] = new String[size];
		dString = dscot.toArray(dString);

		checkBoxs = new JCheckBox[size];
		for (int i = 0; i < size; i++) {
			checkBoxs[i] = new JCheckBox(dString[i], true);
			checkBoxs[i].setFont(f);
			checkBoxs[i].setOpaque(false);
			panel_truongxuat.add(checkBoxs[i]);
		}
		return panel_truongxuat;
	}

	private void Center() {
		card = new CardLayout(0, 0);
		panel_Center = new JPanel();
		getContentPane().add(panel_Center, BorderLayout.CENTER);
		panel_Center.setLayout(card);

		panel_Center.add(dieukienxuat(), "dieukienxuat");
		panel_Center.add(truongxuat(), "truongxuat");

		JPanel panel_w = new JPanel();
		panel_w.setPreferredSize(new Dimension(15, 10));
		panel_w.setBackground(Color.WHITE);
		getContentPane().add(panel_w, BorderLayout.WEST);

		JPanel panel_e = new JPanel();
		panel_e.setPreferredSize(new Dimension(15, 10));
		panel_e.setBackground(Color.WHITE);
		getContentPane().add(panel_e, BorderLayout.EAST);
	}

	public xuatfile(JTable table, String tableName, String id) {
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowOpened(WindowEvent e) {
				int i = 1;
				String url = System.getProperty("user.dir") + "\\FileXuat";
				Check_er check = new Check_er();
				while (check.kiemtraFileTonTai(url + i + ".txt") == true) {
					i++;
				}

				textField_url.setText(url + i + ".txt");
			}
		});
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 875, 493);
		getContentPane().setLayout(new BorderLayout());

		this.table = table;
		this.id = id;
		this.tableName = tableName;
		South();
		North();
		Center();

	}

	private ArrayList<String> getRows() {
		int rows[] = table.getSelectedRows();
		int size = rows.length;
		ArrayList<String> ds_row = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			ds_row.add((String) table.getValueAt(rows[i], 1));
		}
		return ds_row;
	}

	private ArrayList<String> getField() {
		ArrayList<String> ds = new ArrayList<String>();
		int size = checkBoxs.length;
		for (int i = 0; i < size; i++) {
			if (checkBoxs[i].isSelected()) {
				ds.add(checkBoxs[i].getText());
			}
		}
		return ds;
	}

	private void showlayout(String layout) {
		card.show(panel_Center, layout);
	}
}
