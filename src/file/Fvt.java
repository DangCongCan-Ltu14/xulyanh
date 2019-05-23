package file;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class Fvt extends FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String s = f.getName();

		return s.endsWith(".vt") || s.endsWith(".VT");
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return list.vt;
	}

}
