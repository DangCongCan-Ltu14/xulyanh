package set;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Setting {
	int mode = 0;
	String gd = "GTK+";

	private Setting(int a, String b) {
		mode = a;
		gd = b;
	}

	static String pname(int a) {
		switch (a) {
		case 0:
			return "GTK+";
		case 1:
			return "Nimbus";
		case 2:
			return "Metal";

		}
		return "GTK+";
	}

	static int rname(String a) {
		if (a.equals("GTK+"))
			return 0;
		if (a.equals("Nimbus"))
			return 1;
		if (a.equals("Metak"))
			return 2;
		return 0;
	}

	public static Setting openSetting(String ten) {
		try {
			FileReader fl = new FileReader(new File(ten));
			int a = fl.read();
			String b = pname(fl.read());
			fl.close();
			return new Setting(a, b);
		} catch (Exception e) {
			return new Setting(0, "GTK+");
		}
	}

	public void saveSetting(String ten) {
		try {
			FileWriter fl = new FileWriter(new File(ten));
			fl.write(mode);
			fl.write(rname(gd));
			fl.close();
		} catch (Exception e) {

		}
	}
}
