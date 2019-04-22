package segment;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import base.rbg;

public class Img2 {
	final int length = 4096;
	int[][] mg = new int[length][2];
	int[] last = new int[length];
	int[] axn = new int[length];
	int x, y;
	protected rbg[] cent;
	int k = 1;
	Random rand = new Random();
	int type;
	BufferedImage dc;

	public Img2(BufferedImage inl, int ks) {
		dc = inl;
		k = ks;
		x = inl.getWidth();
		y = inl.getHeight();
		tts(inl);
		cent = new rbg[k];
		kt();
		type = inl.getType();
	}

	void tts(BufferedImage inl) {
		rbg[] bm = new rbg[length];
		for (int i = 0; i < length; i++) {
			bm[i] = new rbg();
		}
		int a;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				a = axthuan(inl.getRGB(i, j));
				mg[a][0]++;
				bm[a].add(inl.getRGB(i, j));
			}
		}
		for (int i = 0; i < length; i++) {
			if (mg[i][0] != 0) {
				// System.out.println(mg[i][0]);
				axn[i] = bm[i].div(mg[i][0]);
				// System.out.println(new rbg(axn[i]).getA());
			}
		}
	}

	public BufferedImage segment() {

		do {
			read();
		} while (update());
		BufferedImage res;
//		res = new BufferedImage(x, y, type);
//		int h;
//		for (int i = 0; i < x; i++) {
//			for (int j = 0; j < y; j++) {
//				h = axthuan(dc.getRGB(i, j));
//				res.setRGB(i, j, cent[mg[h][1]].get());
//			}
//		}
		 res = new Img(dc, k, cent).segment();
		return res;
	}

	private void read() {
		int i = 0, h;

		for (i = 0; i < 4096; i++) {
			{
				if (mg[i][0] != 0) {
					h = axnguoc(i);
					h = tam(h);
					mg[i][1] = h;
				}
			}
		}
	}

	boolean update() {

		long[][] cos = new long[k][4];
		int[] count = new int[k];
		int h = 0;
		long sum = 0;
		for (int i = 0; i < length; i++) {
			if (mg[i][0] != 0) {
				h = mg[i][1];
				if (h != last[i]) {
					sum++;
					last[i] = h;
				}
				rbg d = new rbg(axnguoc(i));
				cos[h][1] = cos[h][1] + d.getR();
				cos[h][2] = cos[h][2] + d.getB();
				cos[h][3] = cos[h][3] + d.getG();
				count[h]++;

			}
		}
		if (sum == 0)
			return false;
		// tinh tam cum
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

	void kt() {
		int[] means = new int[4];
		// Random rand = new Random();
		means[0] = 255;
		List<Integer> lp = new LinkedList<Integer>();
		for (int i = length - 1; i > -1; i--) {
			if (mg[i][0] != 0)
				lp.add(axnguoc(i));
		}
		System.out.println("so mau" + lp.size());
		int h = lp.size() / k;
		for (int i = 0; i < k; i++) {
			cent[i] = new rbg(lp.get(h * i));
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

	public static int axthuan(int a) {
		int k = ((a & 0xf0) >> 4) | ((a & 0xf000) >> 8) | ((a & 0xf00000) >> 12);
		return k;
	}

	public int axnguoc(int a) {
		//return ((a & 0xf) << 4) | ((a & 0xf0) << 8) | ((a & 0xf00) << 12);

		 return axn[a];
	}
}
