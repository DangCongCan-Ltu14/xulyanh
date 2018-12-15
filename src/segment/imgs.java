package segment;

import java.awt.image.BufferedImage;
import java.util.Random;

import base.rbg;

public class imgs {
	int max, min;
	int x, y;
	int[][] mg;
	int[][] last;
	int[][] img;
	protected rbg[] cent;
	int k = 1;
	Random rand = new Random();
	int type;

	public imgs(BufferedImage inl, int ks) {
		k = ks;
		x = inl.getWidth();
		y = inl.getHeight();
		mg = new int[x][y];
		img = new int[x][y];
		last = new int[x][y];
		tts(inl);
		cent = new rbg[k];
		kt();
		type = inl.getType();
	}

	public BufferedImage segment() {
		BufferedImage res = new BufferedImage(x, y, type);
		do {
			read();
		} while (update());
		int h;

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				h = mg[i][j];
				res.setRGB(i, j, cent[h].get());
			}
		}
		return res;
	}
/**
 * tinh lai trong tam
 * @return
 */
	
	boolean update() {

		long[][] cos = new long[k][4];
		int[] count = new int[k];
		int h = 0;
		long sum = 0;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				h = mg[i][j];
				if (h != last[i][j]) {
					sum++;
					last[i][j] = h;
				}
				rbg d = new rbg(img[i][j]);
				cos[h][1] = cos[h][1] + d.getR();
				cos[h][2] = cos[h][2] + d.getB();
				cos[h][3] = cos[h][3] + d.getG();
				count[h]++;
			}
		}
		if (sum == 0)
			return false;
		for (h = 0; h < k; h++) {
			cos[h][1] = cos[h][1] + cent[h].getR();
			cos[h][2] = cos[h][2] + cent[h].getB();
			cos[h][3] = cos[h][3] + cent[h].getG();
			count[h]++;
		}

		int a = 255, r, b, g;
		for (int i = 0; i < k; i++) {

			r = (int) (cos[i][1] / count[i]);
			b = (int) (cos[i][2] / count[i]);
			g = (int) (cos[i][3] / count[i]);
			rbg d = new rbg(a, r, b, g);
			cent[i] = d;

		}

		return true;
	}

	private void read() {
		int j = 0, i = 0, h;

		for (i = 0; i < x; i++) {
			for (j = 0; j < y; j++) {
				h = img[i][j];
				h = tam(h);
				mg[i][j] = h;
			}
		}
	}

	void tts(BufferedImage inl) {
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		// int h;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				img[i][j] = inl.getRGB(i, j);

				if (img[i][j] > max) {
					max = img[i][j];
				} else if (img[i][j] < min) {
					min = img[i][j];
				}
			}
		}
		max = max - min;
	}

//	void kt1() {
//		for (int i = 0; i < k; i++) {
//			cent[i] = rand();
//		}
//	}

	void kt() {
		int[][] means = new int[k][4];
		for (int i = 0; i < k; i++) {
			Random rand = new Random();
			means[i][0] = 255;
			means[i][1] = (int) (rand.nextDouble() * 255);
			means[i][2] = (int) (rand.nextDouble() * 255);
			means[i][3] = (int) (rand.nextDouble() * 255);
			cent[i] = new rbg(means[i]);
//			cent[i] = new rbg(img[4][4]);
		}
	}

	int tam(int a) {
		int min = len(a, cent[0]);
		int p = 0;
		int c = 0;
		for (int i = 1; i < k; i++) {
			c = len(a, cent[i]);
			if (c < min) {
				min = c;
				p = i;
			}
		}
		return p;
	}

	int len(int x, rbg y) {
		int a, b, c, d;
		a = ((x & 0xff0000) >> 16) - y.getR();
		b = ((x & 0xff00) >> 8) - y.getB();
		c = (x & 0xff) - y.getG();
		d = a * a + b * b + c * c;
		return d;
	}
}
