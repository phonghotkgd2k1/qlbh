package Business;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ThemQuyenHan extends JDialog {
	private JTextField textField_maqh;
	private JTextField textField_tenqh;
	private JTextField textField_mactqh;
	private JTable table;

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

	/**
	 * Create the dialog.
	 */
	WindowListener window = new WindowAdapter() {
		public void windowOpened(java.awt.event.WindowEvent e) {
			napChucNang();
		};
	};

	public ThemQuyenHan() {
		setTitle("Thêm quyền hạn mới");

		addWindowListener(window);
		setResizable(false);
		setModal(true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1254, 743);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel_Center = new JPanel();
		getContentPane().add(panel_Center, BorderLayout.CENTER);
		panel_Center.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 279, 611);
		panel_Center.add(scrollPane);

		table = new JTable(customTable());
		table.setFillsViewportHeight(true);
		table.setRowHeight(30);
		table.setFont(new Font("Arial", Font.PLAIN, 17));

		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Mã quyền hạn:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(324, 129, 233, 30);
		panel_Center.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Thêm quyền hạn");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(324, 23, 663, 35);
		panel_Center.add(lblNewLabel_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(334, 71, 300, 3);
		panel_Center.add(separator);

		textField_maqh = new JTextField();
		textField_maqh.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_maqh.setBounds(345, 161, 331, 27);
		panel_Center.add(textField_maqh);
		textField_maqh.setColumns(10);

		JLabel lblTenQuyenhan = new JLabel("Tên quyền hạn:");
		lblTenQuyenhan.setFont(new Font("Arial", Font.BOLD, 16));
		lblTenQuyenhan.setBounds(324, 224, 233, 30);
		panel_Center.add(lblTenQuyenhan);

		textField_tenqh = new JTextField();
		textField_tenqh.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_tenqh.setColumns(10);
		textField_tenqh.setBounds(345, 256, 331, 27);
		panel_Center.add(textField_tenqh);

		JLabel lblMactqh = new JLabel("Mã chi tiết quyền hạn:");
		lblMactqh.setFont(new Font("Arial", Font.BOLD, 16));
		lblMactqh.setBounds(820, 129, 233, 30);
		panel_Center.add(lblMactqh);

		textField_mactqh = new JTextField();
		textField_mactqh.setEditable(false);
		textField_mactqh.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_mactqh.setColumns(10);
		textField_mactqh.setBounds(845, 161, 331, 27);
		panel_Center.add(textField_mactqh);

		JPanel panel_South = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_South.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(25);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel_South, BorderLayout.SOUTH);

		JButton btnxacnhan = new JButton("Xác nhận");
		btnxacnhan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DangNhap.con.setAutoCommit(false);
					ThemQuyenHan();
					JOptionPane.showMessageDialog(null, "thêm thành công");
					DangNhap.con.commit();
					dispose();
				} catch (Exception e1) {

					JOptionPane.showMessageDialog(null, "lỗi: " + e1.getMessage());
					try {
						DangNhap.con.rollback();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}

				try {
					DangNhap.con.setAutoCommit(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnxacnhan.setFont(new Font("Arial", Font.BOLD, 17));
		panel_South.add(btnxacnhan);
	}

	private DefaultTableModel customTable() {
		Object header[] = { "Cho phép", "Chức năng" };
		DefaultTableModel custom = new DefaultTableModel(header, 0) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 0) {
					return Boolean.class;
				}
				return String.class;
			}
		};
		return custom;
	}

	private void napChucNang() {
		DefaultTableModel data = (DefaultTableModel) table.getModel();
		Vector<Object> chucnang = new Vector<Object>();
		
		data.addRow(new Object[] { true, "bán hàng" });
		data.addRow(new Object[] { false, "xóa" });
		data.addRow(new Object[] { false, "thêm" });
		data.addRow(new Object[] { true, "sửa" });
		data.addRow(new Object[] { true, "xuất file" });
		data.addRow(new Object[] { true, "nhập file" });
		data.addRow(new Object[] { false, "quản lý nhân viên" });
	}

	private String randomMaCTQH() {
		Random r = new Random();
		double d = r.nextDouble() * 10;
		return Double.toString(d);
	}

	private void ThemQuyenHan() throws SQLException {

		String sql = "insert into quyenhan values(?,?)";
		PreparedStatement pr = DangNhap.con.prepareStatement(sql);

		pr.setString(1, textField_maqh.getText().trim());
		pr.setString(2, textField_tenqh.getText().trim());
		pr.addBatch();
		pr.executeBatch();

		sql = "insert into chitietquyenhan values(?,?,?,?)";

		pr = DangNhap.con.prepareStatement(sql);
		String mactqh = null;

		System.out.println(mactqh);
		System.out.println(textField_maqh.getText().trim());
		for (int i = 0; i < table.getRowCount(); i++) {
			pr.setString(1, randomMaCTQH());
			pr.setString(2, textField_maqh.getText().trim());
			pr.setString(3, (String) table.getValueAt(i, table.getColumn("Chức năng").getModelIndex()));
			pr.setBoolean(4, (Boolean) table.getValueAt(i, table.getColumn("Cho phép").getModelIndex()));
			pr.addBatch();
		}
		pr.executeBatch();
	}
}
