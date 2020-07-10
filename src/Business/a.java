package Business;

import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class a extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					a frame = new a();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public a() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 615);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(96, 45, 386, 488);
		contentPane.add(scrollPane);

		Object h[] = { "id", "sd" };
		Object da[][] = { { new Boolean(false), "asd" }, { new Boolean(true), "addddd" } };
		DefaultTableModel model = new DefaultTableModel(h, 0) {
			public Class<?> getColumnClass(int column) {
				switch (column) {
					case 0:
						return Boolean.class;

					default:
						return String.class;
				}
			}
		};

		model.addRow(new Object[] { true, "asd" });
		model.addRow(new Object[] { false, "asd" });
		model.addRow(new Object[] { true, "asd" });
		model.addRow(new Object[] { true, "asd" });
		JTable table = new JTable(model);
		
		JCheckBox c = new JCheckBox("check");
		scrollPane.setViewportView(table);
	}

}
