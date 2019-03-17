package segment;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class bina {
	public static BufferedImage segment(BufferedImage in) {
		Img p = new Img(in, 2);
		BufferedImage res = p.segment();
		int a = p.cent[0].get(), b = p.cent[1].get();
		if(a<b) a=b;
		return cv(res, a);
	}
	// binary image

	private static BufferedImage cv(BufferedImage inl, int a) {
		// TODO Auto-generated method stub
		int x = inl.getWidth();
		int y = inl.getHeight();
		int black = Color.BLACK.getRGB();
		int white = Color.white.getRGB();
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (inl.getRGB(i, j) == a) {
					inl.setRGB(i, j, white);
				} else
					inl.setRGB(i, j, black);
			}
		}
		return inl;
	}
}
