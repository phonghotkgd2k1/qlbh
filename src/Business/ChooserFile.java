package Business;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ChooserFile {
	private JFileChooser fileChooser;
	private String url;
	private String FileName;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setFileName(String filename) {
		this.FileName = filename;
		fileChooser.setSelectedFile(new File(FileName));
	}

	/*
	 * 
	 */
	public ChooserFile() {
		fileChooser = new JFileChooser();
	}

	public ChooserFile(String path) {
		fileChooser = new JFileChooser(path);
	}

	public ChooserFile(FileNameExtensionFilter format, boolean b) {
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(format);
		fileChooser.setAcceptAllFileFilterUsed(b); // chon tat ca dinh dang
	}

	public ChooserFile(String path, FileNameExtensionFilter format, boolean b) {
		fileChooser = new JFileChooser(path);
		fileChooser.addChoosableFileFilter(format);
		fileChooser.setAcceptAllFileFilterUsed(b); // chon tat ca dinh dang
	}

	public void show() {
		int i = fileChooser.showDialog(null, "Open");

		if (i == JFileChooser.APPROVE_OPTION) {
			File f = fileChooser.getSelectedFile();
			url = f.getAbsolutePath();
		}
	}

	public String diachi_anh(String txt) {
		String url = txt.trim();
		int index = url.lastIndexOf("\\");

		int index2 = url.lastIndexOf("\\", index - 1);

		return url.substring(index2 + 1);
	}
}
