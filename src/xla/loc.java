package xla;

import java.awt.Color;
import java.awt.image.BufferedImage;

import base.rbg;

public class loc {
	// static int[][] ttc = { { 0, -1, 0 }, { -1, 4 , -1 }, { 0,-1, 0} };
	static int[][] ttc = { { -1, -2, -1 }, 
			{ -2, 19, -2 }, 
			{ -1, -2, -1 } };
	static int[][] lgs = { { 2, 4, 5, 4, 2 }, { 4, 9, 12, 9, 4 }, { 5, 12, 15, 12, 5 }, { 4, 9, 12, 9, 4 },
			{ 2, 4, 5, 4, 2 } };
	static int[][] ttb = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
	static int csl;
	static int r = 30, b = 50, g = 30;
	static int color = r + b + g;
	static int plag = -1;
	static int[][] cb = { { 0, 1, 0 }, { 1, 1, 1 }, { 0, 1, 0 } };

	static protected BufferedImage cv(BufferedImage in, int[][] H,int sc) {
		csl=sc;
		int x = in.getWidth();
		int y = in.getHeight();
		rbg l = new rbg(0);
		int v = H.length;
		int lv = v / 2;
		BufferedImage res = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				l.reset();
				for (int a = 0; a < v; a++)
					for (int b = 0; b < v; b++) {

						if ((a - lv + i < 0) || (b - lv + j < 0) || (a - lv + i >= x) || (b - lv + j >= y))
							l.add(in.getRGB(i, j), H[a][b]);
						else

							l.add(in.getRGB(a - lv + i, b - lv + j), H[a][b]);

					}
				res.setRGB(i, j, l.div(csl));
			}
		}
		return res;
	}

	static protected BufferedImage exb(BufferedImage in, int[][] H,int vv) {
		int  csl =vv;
		int x = in.getWidth();
		int y = in.getHeight();
		rbg l = new rbg(0);
		int v = H.length;
		int lv = v / 2;
		int bl = Color.red.getRGB();
		BufferedImage res = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				l.reset();
				int k=in.getRGB(i, j);
				for (int a = 0; a < v; a++) {
					for (int b = 0; b < v; b++) {

						if ((a - lv + i < 0) || (b - lv + j < 0) || (a - lv + i >= x) || (b - lv + j >= y))
							l.add(in.getRGB(i, j), H[a][b]);
						else

							l.add(in.getRGB(a - lv + i, b - lv + j), H[a][b]);
					}
				}
				
				if ( k!=l.div(csl))
				{
					res.setRGB(i, j, bl);
				}
				else res.setRGB(i, j, k);
			}
		}
		return res;
	}
	static protected BufferedImage exp(BufferedImage in,int s)
	{
		int x = in.getWidth()*s;
		int y = in.getHeight()*s;
		BufferedImage res = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		for(int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				res.setRGB(i, j, in.getRGB(i/s, j/s));
			}
		}
		return res;
	}
	protected static void imgiv(BufferedImage in) {
		int x = in.getWidth();
		int y = in.getHeight();
		int l = 0;

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				l = in.getRGB(i, j); // co the co loi
				l = ~l;
				// l = graycolor(l);
				in.setRGB(i, j, l);
			}
		}

	}

	protected static void imggray(BufferedImage in) {
		int x = in.getWidth();
		int y = in.getHeight();
		int l = 0;

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				l = in.getRGB(i, j); // co the co loi
				// =~l;
				l = graycolor(l);
				in.setRGB(i, j, l);
			}
		}

	}

	/**
	 * chuyen mau sang xan
	 * 
	 * @param a
	 * @return
	 */
	// static int[][] ttc = { {
		// 0,0,-1,0,0},{0,-1,-2,-1,0},{-1,-2,16,-2,-1},{0,-1,-2,-1,0},{
		// 0,0,-1,0,0}};
	static int graycolor(int a) {
		int i = 0;
		// int[] k = new int[5];
		rbg l = new rbg(a);
		i = (g * l.getG() + b * l.getB() + r * l.getR()) / color;
		i = i & plag;
		i = i * 0x10101;
		a = l.getA() << 24;
		a = a | i;
		// a=a&0xff0000ff;
		return i;

	}
	/**
	 * cong tat ca
	 */
	static private int ctc(int[][] H) {
		// TODO Auto-generated method stub
		int csl = 0;
		int v = H.length;
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++)
				//if (H[i][j] > 0)
					csl = csl + H[i][j];
		}
		if (csl < 1)
			csl = 1;
		//System.out.println(csl);
		return csl;
	}
	/**
	 * cong so duong
	 */
	static private int csd(int[][] H) {
		// TODO Auto-generated method stub
		int csl = 0;
		int v = H.length;
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++)
				if (H[i][j] > 0)
					csl = csl + H[i][j];
		}
		return csl;
		//System.out.println(csl);
		
	}
	public static BufferedImage tc(BufferedImage in) {
		// TODO Auto-generated method stub

		return cv(in, ttc,ctc(ttc));
	}

	/**
	 * loc trung binh
	 * 
	 * @param in
	 * @return
	 */
	public static BufferedImage tb(BufferedImage in) {
		// TODO Auto-generated method stub

		return cv(in, ttb,ctc(ttb));
	}

	public static BufferedImage hcb(BufferedImage in) {
		// TODO Auto-generated method stub

		return exb(in, cb,ctc(cb));
	}

	/**
	 * loc graussian
	 * 
	 * @param in
	 * @return
	 */
	public static BufferedImage tg(BufferedImage in) {
		// TODO Auto-generated method stub
		return cv(in, lgs,csd(lgs));
	}

	public static BufferedImage edge(BufferedImage in) {
		// TODO Auto-generated method stub
		int x = in.getWidth();
		int y = in.getHeight();
		int a, b;
		int s = Color.white.getRGB();
		BufferedImage eg = new BufferedImage(2 * x, 2 * y, BufferedImage.TYPE_INT_RGB);
		x--;
		y--;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				a = in.getRGB(i, j);
				b = in.getRGB(i + 1, j);
				if (a != b)
					eg.setRGB(2 * i + 1, 2 * j, s);
				b = in.getRGB(i, j + 1);
				if (a != b)
					eg.setRGB(2 * i, 2 * j + 1, s);

				
			}
			
		}
		return eg;
	}

}
