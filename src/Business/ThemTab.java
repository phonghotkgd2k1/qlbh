package Business;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ThemTab extends JPanel {
	jframe_banhang customTabbed;

	public ThemTab(jframe_banhang tabbed) {
		this.customTabbed = tabbed;
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		setBorder(new EmptyBorder(5, 2, 2, 2));
		setOpaque(false);
		add(addLable());
		add(addButton());
	}

	private JLabel addLable() {
		JLabel lb = new JLabel() {
			@Override
			public String getText() {
				int index = customTabbed.tabbedPane.indexOfTabComponent(ThemTab.this);
				if (index != -1) {
					return customTabbed.tabbedPane.getTitleAt(index);
				}
				return null;
			}
		};
		 lb.setBorder(new EmptyBorder(0, 0, 0, 10));
		return lb;
	}

	private JButton addButton() {
		JButton bt = new JButton();
		bt.setBorderPainted(false);
		bt.setContentAreaFilled(false);
		bt.setPreferredSize(new Dimension(20, 20));
		bt.setIcon(new ImageIcon("Imager\\delete_15px.png"));
		bt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bt.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {

				bt.setBorderPainted(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				bt.setBorderPainted(true);

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getButton() == MouseEvent.BUTTON1) {
					int i = customTabbed.tabbedPane.indexOfTabComponent(ThemTab.this);
					customTabbed.xoaTab(i);
				}
			}
		});
		return bt;
	}
}
