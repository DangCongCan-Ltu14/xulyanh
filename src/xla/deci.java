package xla;

import java.awt.image.BufferedImage;

public class deci {
	public static BufferedImage tach(BufferedImage in) {
		int x = in.getWidth();
		int y = in.getHeight();
		BufferedImage res = new BufferedImage(x, y,BufferedImage.TYPE_4BYTE_ABGR);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				int k = in.getRGB(i, j) | 0b0011110000111100001111;
				res.setRGB(i, j, k);
			}
		}
		return res;
	}
}
