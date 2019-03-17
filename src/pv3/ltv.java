package pv3;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class ltv {
	public static BufferedImage tv(BufferedImage img) { // Input Photo File
		int[] pixel = new int[9];
		BufferedImage in = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		for (int i = 1; i < img.getWidth() - 1; i++)
			for (int j = 1; j < img.getHeight() - 1; j++) {
				pixel[0] = (img.getRGB(i - 1, j - 1));
				pixel[1] = (img.getRGB(i - 1, j));
				pixel[2] = (img.getRGB(i - 1, j + 1));
				pixel[3] = (img.getRGB(i, j + 1));
				pixel[4] = (img.getRGB(i + 1, j + 1));
				pixel[5] = (img.getRGB(i + 1, j));
				pixel[6] = (img.getRGB(i + 1, j - 1));
				pixel[7] = (img.getRGB(i, j - 1));
				pixel[8] = (img.getRGB(i, j));
				Arrays.sort(pixel);
				in.setRGB(i, j, pixel[4]);
			}
		return in;
	}
}