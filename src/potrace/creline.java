package potrace;

import base.point;

public class creline {
	
	// static double tx, x0, ty, y0;
	private static int half =1;
	static final double str=Double.MAX_VALUE;
	public static double line(point a, point b) {
		double tx = b.getx() - a.getx();
		double ty = b.gety() - a.gety();
		if (tx == 0) {
			if (ty >= 0)
				return str;
			else
				return -str;
		}
		return ty / tx;
	}
	
	static double[] region(point a, point b) {
		int x = b.getx();
		int y = b.gety();
		// System.out.println(a.getx()+" "+a.gety()+" "+b.getx()+" "+b.gety());
		double[] res = new double[2];
		res[0] = -str;
		res[1] = str;
		if (!dmax(a, b)) {
			res[1] = -str;
			res[0] = str;
			return res;
		}
		double c = line(a, new point(x - half, y - half));
		if (c > res[0])
			res[0] = c;
		if (c < res[1])
			res[1] = c;
		//System.out.println(c);
		c = line(a, new point(x - half, y + half));
		if (c > res[0])
			res[0] = c;
		if (c < res[1])
			res[1] = c;
		//System.out.println(c);
		c = line(a, new point(x + half, y - half));
		if (c > res[0])
			res[0] = c;
		if (c < res[1])
			res[1] = c;
		//System.out.println(c);
		c = line(a, new point(x + half, y + half));
		if (c > res[0])
			res[0] = c;
		if (c < res[1])
			res[1] = c;
		//System.out.println(c);
		return res;

	}

	static boolean dmax(point a, point b) {
		int x = b.getx() - a.getx();
		int y = b.gety() - b.gety();
		
		return (Math.abs(x) >= half) || (Math.abs(y) >= half);
	}

}
