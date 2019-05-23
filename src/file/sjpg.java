package file;

import java.io.File;
//import java.io.FileFilter;

import javax.swing.filechooser.FileFilter;

public class sjpg extends FileFilter {

	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String s = f.getName();

		return s.endsWith(".jpg") || s.endsWith(".JPG");
	}

	public String getDescription() {
		return list.jpg;
	}
	

}