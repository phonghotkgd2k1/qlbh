package Business;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Check_er {

	public Check_er() {

	}

	public ImageIcon kiemtraImager(String pathname) throws IOException {

		Image i = null;
		File f = new File(pathname);
		i = ImageIO.read(f);
		return new ImageIcon(i);

	}

	public boolean kiemtraFileTonTai(String pathname) {
		File f = new File(pathname);
		if (f.exists()) {
			return true;
			
		} else {
			return false;
		}
	}

}
