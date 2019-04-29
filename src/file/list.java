package file;

public class list {
	static String jpg = "*.jpg,*.JPG", png = "*.png,*.PNG",vt="*.vt";

	public static String getex(String s) {
		if (s.equals(jpg))
			return "jpg";
		else if (s.equals(png))
			return "png";
		else if (s.equals(vt))
			return "vt";
		return null;
	}
}
