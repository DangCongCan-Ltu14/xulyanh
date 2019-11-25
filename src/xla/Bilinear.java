package xla;

import java.awt.image.BufferedImage;

import base.rbg;
import base.show;

public class Bilinear {
	static String text = "/home/dccan/Pictures/s.jpg";

	public static void main(String[] args) {
		try {
			BufferedImage in = buff.get(text);
			// in =loc.imggray(in);
			int z = 3;
			BufferedImage p = phong(in, z);
			BufferedImage s = near(in, z);
			show.pr(in, "anh goc");
			show.pr(p, "Bilinear");
			show.pr(s, "doan xem");
//			double[][] t = { { 5, 8, 4 }, { 5, 10, 7 }, { 6, 4, 1 }, { 1, 2, 6 } };
//			dshow(t);
//			System.out.println();
//			ph2(t, 5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void dshow(double[][] a) {
		for (double[] b : a) {
			for (double c : b) {
				String d = String.format("%10.4f ", c);
				System.out.print(d);
			}
			System.out.println();
		}
	}

	public static void ph2(double[][] in, int p) {
		int w = in.length;
		int h = in[0].length;
		double z = p;
		double[][] res = new double[w * p][h * p];
		for (int i = 0; i < w - 1; i++)
			for (int j = 0; j < h - 1; j++) {
				for (int a = 0; a < p; a++) {
					for (int b = 0; b < p; b++) {
						double[][] mg = create(a / z, b / z);
						res[i * p + a][j * p + b] = (in[i][j] * mg[0][0]) + (in[i][j + 1] * mg[0][1])
								+ in[i + 1][j + 1] * mg[1][1] + in[i + 1][j] * mg[1][0];
					}
				}

			}
		dshow(res);

	}

	public static BufferedImage phong(BufferedImage in, int p) {
		int h = in.getHeight(), w = in.getWidth();
		BufferedImage res = new BufferedImage(w * p, h * p, in.getType());
		double z = p;
		for (int i = 0; i < w; i++)
			for (int j = 0; j < h; j++) {
				rbg i00, i01, i10, i11;
				i00 = new rbg(in.getRGB(i, j));
				if (j != h - 1)
					i01 = new rbg(in.getRGB(i, j + 1));
				else
					i01 = new rbg(in.getRGB(i, j));
				if (i != w - 1)
					i10 = new rbg(in.getRGB(i + 1, j));
				else
					i10 = new rbg(in.getRGB(i, j));

				if (j != h - 1 && i != w - 1)
					i11 = new rbg(in.getRGB(i + 1, j + 1));
				else
					i11 = new rbg(in.getRGB(i, j));
				for (int a = 0; a < p; a++) {
					for (int b = 0; b < p; b++) {
						// System.out.println(a/z);

						double[][] mg = create(a / z, b / z);
						int r = (int) (mg[0][0] * i00.getR() + mg[0][1] * i01.getR() + i10.getR() * mg[1][0]
								+ i11.getR() * mg[1][1]);
						int g = (int) (mg[0][0] * i00.getG() + mg[0][1] * i01.getG() + i10.getG() * mg[1][0]
								+ i11.getG() * mg[1][1]);
						int cb = (int) (mg[0][0] * i00.getB() + mg[0][1] * i01.getB() + i10.getB() * mg[1][0]
								+ i11.getB() * mg[1][1]);
						res.setRGB(i * p + a, j * p + b, new rbg(255, r, cb, g).get());
						// res.setRGB(i * p + a, j * p + b,in.getRGB(i, j));

					}
				}
				res.setRGB(i * p, j * p, in.getRGB(i, j));
			}
		return res;
	}

	public static BufferedImage near(BufferedImage in, int p) {
		int h = in.getHeight(), w = in.getWidth();
		BufferedImage res = new BufferedImage(w * p, h * p, in.getType());
		for (int i = 0; i < w; i++)
			for (int j = 0; j < h; j++) {

				for (int a = 0; a < p; a++) {
					for (int b = 0; b < p; b++) {
						res.setRGB(i * p + a, j * p + b, in.getRGB(i, j));

					}
				}

			}
		return res;
	}

	public static int cv(int p) {
		if (p < 0)
			return 0;
		if (p > 255)
			return 255;
		return p;
	}

	public static double[][] create(double a, double b) {
		double[][] res = new double[2][2];
		res[0][0] = (1 - a) * (1 - b);
		res[0][1] = (1 - a) * (b);
		res[1][0] = (a) * (1 - b);
		res[1][1] = a * b;
		return res;
	}
}
