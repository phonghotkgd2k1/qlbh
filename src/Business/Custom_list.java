package Business;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Custom_list extends JList<String> {
	private DefaultListModel<String> datarow;
	private String url;
	private ArrayList<String> ds;

	public Custom_list(String url) {
		this.url = url;
		ds = new ArrayList<String>();
		datarow = new DefaultListModel<String>();
		/*
		 * tránh lỗi : JList$4 cannot be cast to javax.swing.DefaultListMode
		 */
		this.setModel(datarow);

		addAction();
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void getdata() {
		try {
			FileInputStream f = new FileInputStream(url);
			BufferedReader b = new BufferedReader(new InputStreamReader(f));
			while (true) {
				String temp = b.readLine();
				if (temp == null || temp =="\n") {
					break;
				}
				
				datarow.addElement(temp);
				ds.add(temp);
			}
			b.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "lỗi nạp serverName", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setdata(String txt) {
		try {
			FileOutputStream f = new FileOutputStream(url);
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(f));
			if (kiemtraServer(txt) ==false) {
				datarow.addElement(txt);
			}
			int size = datarow.getSize();
			for (int i = 0; i < size; i++) {
				pw.println(datarow.getElementAt(i));
			}
			pw.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "lỗi ghi serverName", JOptionPane.ERROR_MESSAGE);
		}

	}

	private boolean kiemtraServer(String txt) {
		int size = ds.size();
		for (int i = 0; i < size; i++) {
			if (ds.get(i).equals(txt)) {
				return true;
			}
		}
		return false;
	}

	public DefaultListModel<String> getDefault() {
		return datarow;
	}

	private void addAction() {
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				setSelectedIndex(locationToIndex(e.getPoint()));
			}
		});
	}
}
