package base;

import java.awt.image.BufferedImage;

public class convert {
	public static BufferedImage run(int[][] mg) {
		int x = mg.length, y = mg[0].length;
		BufferedImage res = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {

				res.setRGB(i, j, mg[i][j]);
			}
		}
		return res;
	}

	public static int[][] rv(BufferedImage in) {
		int[][] mg;
		int y = in.getHeight(), x = in.getWidth();
		// BufferedImage res = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		mg = new int[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				mg[i][j] = in.getRGB(i, j);
			}
		}
		return mg;
	}
}
