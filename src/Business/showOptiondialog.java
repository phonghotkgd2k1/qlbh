package Business;

import java.awt.Component;

import javax.management.loading.PrivateClassLoader;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class showOptiondialog {
	private JOptionPane dialog;
	private Component component;
	private String title;
	private int optionType;
	private int messageType;
	private ImageIcon icon;
	private Object options[];
	private Object initialValue;
	private int chooser;

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getOptionType() {
		return optionType;
	}

	public void setOptionType(int optionType) {
		this.optionType = optionType;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public Object[] getOptions() {
		return options;
	}

	public void setOptions(Object[] options) {
		this.options = options;
	}

	public Object getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(Object initialValue) {
		this.initialValue = initialValue;
	}

	public int getChooser() {
		return chooser;
	}

	public showOptiondialog(Component component, String title, int optionType, int messageType, ImageIcon icon,
			Object[] options, Object initialValue) {

		this.component = component;
		this.title = title;
		this.optionType = optionType;
		this.messageType = messageType;
		this.icon = icon;
		this.options = options;
		this.initialValue = initialValue;
	}

	public void show() {
		chooser = dialog.showOptionDialog(null, component, title, optionType, messageType, icon, options, initialValue);

	}
}
