package cuver;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import base.point;

public class catmull {

	public void paintline(Graphics g,List<point> arr) {
		// Graphics g = getGraphics();
		g.setColor(Color.GREEN);
		point p = arr.get(0);
		for (point i : arr) {
			g.drawLine(p.getx(), p.gety(), i.getx(), i.gety());
			p = i;
		}
		g.dispose();
	}

	static int[][] mcat = { { -1, 3, -3, 1 }, { 2, -5, 4, -1 }, { -1, 0, 1, 0 }, { 0, 2, 0, 0 } };
//	public static BufferedImage draw2(int x, int y,poly p ) {
//		List<point> arr=p.getP();
//		BufferedImage res = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
//		Graphics g = res.getGraphics();
//		int k = arr.size();
//		for (int i = 0; i < k; i++)
//			pcat(i, arr, g);
//		g.dispose();
//		return res;
//	}

//	private static void pcat(int l, List<point> arr, Graphics g) {
//		g.setColor(Color.RED);
//		int p, k = arr.size();
//		int[][] cat = new int[4][2];// a,b,c,d
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 4; j++) {
//				p = (l + j ) % k;
//				cat[i][0] = cat[i][0] + arr.get(p).getx() * mcat[i][j];
//				cat[i][1] = cat[i][1] + arr.get(p).gety() * mcat[i][j];
//			}
//		}
//		
//		double t = 0.01;
//		double t3, t2;
//		int x0, y0, x1, y1;
//		p = (l + 1 ) % k;
//		x0 = arr.get(p).getx();
//		y0 = arr.get(p).gety();
//		while (t <= 1) {
//			t2 = t * t;
//			t3 = t2 * t;
//			x1 = (int) (t3 * cat[0][0] + t2 * cat[1][0] + t * cat[2][0] + cat[3][0]) / 2;
//			y1 = (int) (t3 * cat[0][1] + t2 * cat[1][1] + t * cat[2][1] + cat[3][1]) / 2;
//			g.drawLine(x0, y0, x1, y1);
//			x0 = x1;
//			y0 = y1;
//			t = t + 0.01;
//		}
//		//System.out.println("done");
//
//	}
	public static void dcat(List<point> brr, List<point> arr,int l) 
	{
		int p, k = arr.size();
		int[][] cat = new int[4][2];// a,b,c,d
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				p = (l + j ) % k;
				cat[i][0] = cat[i][0] + arr.get(p).getx() * mcat[i][j];
				cat[i][1] = cat[i][1] + arr.get(p).gety() * mcat[i][j];
			}
		}
		int a=(l + 1 ) % k,b=(l+2)%k;
		int h=(int)(Math.sqrt(Math.abs(arr.get(a).getx()-arr.get(b).getx())+Math.abs(arr.get(a).gety()-arr.get(b).gety()))+1);
		double dt=1.0/h;
		double t = dt;//.0/Math.sqrt(a);
		double t3, t2;
		int x0, y0, x1, y1;
		p = (l + 1 ) % k;
		x0 = arr.get(p).getx();
		y0 = arr.get(p).gety();
		while (t < 1) {
			brr.add(new point(x0,y0));
			t2 = t * t;
			t3 = t2 * t;
			x1 = (int) ((t3 * cat[0][0] + t2 * cat[1][0] + t * cat[2][0] + cat[3][0]) / 2+0.25);
			y1 = (int) ((t3 * cat[0][1] + t2 * cat[1][1] + t * cat[2][1] + cat[3][1]) / 2+0.25);
			x0 = x1;
			y0 = y1;
			t = t + dt;
		}
		brr.add(arr.get(b));

	}
	
	@SuppressWarnings("unused")
	private static void adcat(List<point> brr, List<point> arr,int l)// backup of dcat 
	{
		int p, k = arr.size();
		int[][] cat = new int[4][2];// a,b,c,d
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				p = (l + j ) % k;
				cat[i][0] = cat[i][0] + arr.get(p).getx() * mcat[i][j];
				cat[i][1] = cat[i][1] + arr.get(p).gety() * mcat[i][j];
			}
		}
		double t = 0.01;//.0/Math.sqrt(a);
		double t3, t2;
		int x0, y0, x1, y1;
		p = (l + 1 ) % k;
		x0 = arr.get(p).getx();
		y0 = arr.get(p).gety();
		while (t <= 1) {
			brr.add(new point(x0,y0));
			t2 = t * t;
			t3 = t2 * t;
			x1 = (int) ((t3 * cat[0][0] + t2 * cat[1][0] + t * cat[2][0] + cat[3][0]) / 2+0.25);
			y1 = (int) ((t3 * cat[0][1] + t2 * cat[1][1] + t * cat[2][1] + cat[3][1]) / 2+0.25);
			x0 = x1;
			y0 = y1;
			t = t + 0.01;
		}


	}
	public static List<point>  tcat( List<point> arr)
	{
		List<point>brr=new LinkedList<point>();
		int l=arr.size();
		for(int i=0;i<l;i++)
		{
			dcat(brr, arr, i);
		}
		return brr;
	}
}
