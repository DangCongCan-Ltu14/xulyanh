package xla;

import java.awt.image.BufferedImage;

public class canny {
	private final static int[][] gx = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
	private final static int[][] gy = { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } };
	static final int[][] lgs = { { 2, 4, 5, 4, 2 }, { 4, 9, 12, 9, 4 }, { 5, 12, 15, 12, 5 }, { 4, 9, 12, 9, 4 },
			{ 2, 4, 5, 4, 2 } };
	final static double tan = Math.PI / 4;
	final static double tan2 = tan / 2;
	double[][] mg, mm;
	static int csl = 1;

//	static protected BufferedImage loccanny(BufferedImage in) {
//		loc.imgiv(in);// chuyen sang anh xam
//		in = loc.tg(in);// lam mo anh
//		
//	}
/**
 * tính gradien va các gia tri goc 45
 * @param in
 */
	protected void cv(BufferedImage in) {

		int x = in.getWidth();
		int y = in.getHeight();
		mm = new double[x][y];
		mg = new double[x][y];
		int lx;
		int ly;
		int v = 3;
		int lv = v / 2;
		int p;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				lx = 0;
				ly = 0;
				for (int a = 0; a < v; a++)
					for (int b = 0; b < v; b++) {

						if ((a - lv + i < 0) || (b - lv + j < 0) || (a - lv + i >= x) || (b - lv + j >= y)) {
							p = (in.getRGB(i, j) & 0xff);
							lx = lx + p * gx[a][b];
							ly = ly + p * gy[a][b];
						} else {
							p = (in.getRGB(a - lv + i, b - lv + j) & 0xff);
							lx = lx + p * gx[a][b];
							ly = ly + p * gy[a][b];
						}

					}
				mm[i][j] = Math.abs(lx) + Math.abs(ly);
				if(lx!=0)
				{
				mg[i][j] = Math.atan(ly / ((double) lx));// toi uu sau
				if (mg[i][j] < 0)
					mg[i][j] = mg[i][j] + Math.PI;
				mg[i][j] = (mg[i][j] + tan2) / tan;
				}
				else
				{
					 mg[i][j]=2;
				}
			}
		}

	}

	protected void dobien()
	{
		
	}
}
