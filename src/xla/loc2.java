package xla;

import java.awt.Color;
import java.awt.image.BufferedImage;

import base.rbg;

public class loc2 {
	static double o = 1.0;
	static double pi = 2 * Math.PI * (o * o);
	static double[][] Mgauss(int a) {
		int v = 2 * a + 1;
		double[][] res = new double[v][v];
		for (int i = 0; i <= a; i++)
			for (int j = i; j <= a; j++) {
				double d = gauss(i, j);
				res[a + i][a + j] = d;
				res[a - i][a + j] = d;
				res[a + i][a - j] = d;
				res[a - i][a - j] = d;
				res[a + j][a + i] = d;
				res[a - j][a + i] = d;
				res[a + j][a - i] = d;
				res[a - j][a - i] = d;
			}
		return res;
	}
	static double gauss(int a, int b) {
		return Math.exp(-(a * a + b * b) / 2.0) / pi;
	}
	public static BufferedImage tgex(BufferedImage in, int k) {
		// TODO Auto-generated method stub
		double[][] res = Mgauss(k);
		return cv(in, res, csd(res));
	}
	static protected BufferedImage cv(BufferedImage in, double[][] H, double csl) {
		//csl = sc;
		int x = in.getWidth();
		int y = in.getHeight();
		int v = H.length;
		int lv = v / 2;
		double red,blue,green;
		BufferedImage res = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_RGB);
		System.gc();
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				red=0;green=0;blue=0;
				for (int a = 0; a < v; a++)
					for (int b = 0; b < v; b++) {
					
						if ((a - lv + i < 0) || (b - lv + j < 0) || (a - lv + i >= x) || (b - lv + j >= y))
						{
							rbg l=new rbg(in.getRGB(i, j));
							red=red+l.getR()* H[a][b];
							blue=blue+l.getB()* H[a][b];
							green=green+l.getG()* H[a][b];
						}
						else
						{
							rbg l=new rbg(	in.getRGB(a - lv + i, b - lv + j));
							red=red+l.getR()* H[a][b];
							blue=blue+l.getB()* H[a][b];
							green=green+l.getG()* H[a][b];
							}

					}
				int r=(int)(red/csl);
				int b=(int)(blue/csl);
				int g=(int)(green/csl);
				res.setRGB(i, j, new Color(r, b, g).getRGB());
			}
		}
		return res;
	}
	static private double csd(double[][] H) {
		// TODO Auto-generated method stub
		double csl = 0;
		int v = H.length;
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++)
					csl = csl + H[i][j];
		}
		return csl;
		// System.out.println(csl);

	}
}
