package file;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class spng extends FileFilter {

	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String s = f.getName();

		return s.endsWith(".PNG") || s.endsWith(".png");
	}

	public String getDescription() {
		return list.png;
	}
	

}