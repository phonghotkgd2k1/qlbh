package Business;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;

public class themnhomkhachhang extends JDialog {

	private JPanel contentPane;
	private JTextField textField_maNKH;
	private JTextField textField_tenNKH;
	private JTextArea textArea;
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
	 * Create the frame.
	 */
	
	public themnhomkhachhang() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 804,575);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbtitle = new JLabel("Thêm Nhóm Khách Hàng");
		lbtitle.setFont(new Font("Arial", Font.PLAIN, 30));
		lbtitle.setBounds(32, 20, 400, 33);
		contentPane.add(lbtitle);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(30, 66, 750, 4);
		contentPane.add(separator);
		
		JLabel lbmaNKH = new JLabel("Mã Nhóm KH");
		lbmaNKH.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbmaNKH.setBounds(32, 106, 155, 33);
		contentPane.add(lbmaNKH);
		
		JLabel lblTnNhmKh = new JLabel("Tên Nhóm KH");
		lblTnNhmKh.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTnNhmKh.setBounds(422, 106, 155, 33);
		contentPane.add(lblTnNhmKh);
		
		JLabel lblGhiChu = new JLabel("Ghi Chú");
		lblGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblGhiChu.setBounds(32, 232, 155, 33);
		contentPane.add(lblGhiChu);
		
		textField_maNKH = new JTextField();
		textField_maNKH.setFont(new Font("Arial", Font.PLAIN, 16));
		textField_maNKH.setBounds(91, 141, 257, 33);
		contentPane.add(textField_maNKH);
		textField_maNKH.setColumns(10);
		
		textField_tenNKH = new JTextField();
		textField_tenNKH.setFont(new Font("Arial", Font.PLAIN, 16));
		textField_tenNKH.setColumns(10);
		textField_tenNKH.setBounds(476, 141, 257, 33);
		contentPane.add(textField_tenNKH);
		
		textArea = new JTextArea();
		textArea.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Arial", Font.PLAIN, 15));
		textArea.setBounds(91, 266, 411, 206);
		contentPane.add(textArea);
		
		JButton btnluu = new JButton("Lưu");
		btnluu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnluu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String sql =" insert into NhomKH( Manhomkh , Tennhomkh , Ghichu) values(? ,? ,?)";
				try {
					PreparedStatement ps = DangNhap.con.prepareStatement(sql);
					ps.setString(1, textField_maNKH.getText().trim() );
					ps.setString(2, textField_tenNKH.getText().trim()  );
					ps.setString(3,  textArea.getText().trim());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "them thanh cong");
					dispose();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"Trùng mã");
					System.err.println("them nhom khach hang - luu: "+e2.getMessage()  );
				}
			}
		});
		btnluu.setFocusable(false);
		btnluu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnluu.setBounds(32, 494, 114, 33);
		contentPane.add(btnluu);
		
		JButton btnthoat = new JButton("Thoát");
		btnthoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnthoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnthoat.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnthoat.setFocusable(false);
		btnthoat.setBounds(198, 494, 114, 33);
		contentPane.add(btnthoat);
	}
	
	
}
