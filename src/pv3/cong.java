package pv3;

import java.util.LinkedList;
import java.util.List;

import base.point;

public class cong {

	static final double str = Double.MAX_VALUE;
	static final int lg = 6;

	public static void ntaopart(List<point> ret, path p, int dau, int cuoi) {
		double[] res = new double[2];
		res[1] = -str;
		res[0] = str;

		int h = p.site();
		int z = h - 1;
		point a = p.get(dau);
		point b = p.get(cuoi);
		boolean tl;
		if (point.cmpl(a, b)) {
			// ret.add(p.get(dau));
			while (dau != cuoi) {
				double[] sd = creline.region(a, p.get(dau));
				tl = change(res, sd);
				if (!tl) {
					dau = (dau + z) % h;
					ret.add(p.get(dau));
					res[1] = -str;
					res[0] = str;
				} else
					dau = (dau + 1) % h;
			}

			ret.add(p.get(cuoi));
		} else {
			int neo = ret.size();
			ret.add(p.get(cuoi));

			while (cuoi != dau) {

				double[] sd = creline.region(b, p.get(cuoi));
				tl = change(res, sd);
				if (!tl) {
					cuoi = (cuoi + 1) % h;
					ret.add(neo, p.get(cuoi));
					res[1] = -str;
					res[0] = str;
				} else
					cuoi = (cuoi + z) % h;
			}
		}

	}

	public static List<point> taoPart(List<point> p) {
		List<point> part = new LinkedList<point>();
		point stack = p.get(0);
		int i = 1;
		double[] res = new double[2];
		res[1] = -str;
		res[0] = str;
		int k = p.size() - 1;

		for (i = 1; i < k; i++) {

			double[] sd = creline.region(stack, p.get(i));
			boolean tl = change(res, sd);

			if (!tl) {

				part.add(p.get(i - 1));
				stack = p.get(i - 1);

				res[1] = -str;
				res[0] = str;
				i = i - 1;
			}

		}
		part.add(p.get(k));
		return part;
	}

	public static List<point> taoPart2(List<point> p) {
		List<point> part = new LinkedList<point>();
		if (p.size() == 0)
			return part;
		int dx = 0, dy = 0;
		int i = 0;
		boolean a = false, b = false;
		boolean con = true;// contince
		boolean bres = false;
		double[] res = new double[2];
		boolean tl = false;
		res[1] = -str;
		res[0] = str;
		int k = p.size() - 1;
		point stack = p.get(0);
		int dem = 0;
		for (i = 0; i < k; i++) {
			if (con) {
				if (dem < lg) {
					dx = dx + Math.abs(p.get(i + 1).getx() - p.get(i).getx());
					dy = dy + Math.abs(p.get(i + 1).getx() - p.get(i).getx());
				} else {
					a = (dx == 0);
					b = (dy == 0);
					con = false;
				}
			} else {
				dx = (p.get(i + 1).getx() - p.get(i).getx());
				dy = (p.get(i + 1).getx() - p.get(i).getx());
				if (a && dx != 0)
					bres = true;
				if (b && dy != 0)
					bres = true;

			}
			if (bres)
				tl = false;
			else {
				double[] sd = creline.region(stack, p.get(i));
				tl = change(res, sd);
			}
			if ((!tl)) {
				con = true;
				bres = false;
				part.add(p.get(i - 1));
				stack = p.get(i - 1);
				dx = 0;
				dy = 0;
				res[1] = -str;
				res[0] = str;
				i = i - 1;
			}

		}

		part.add(p.get(k));
		return part;
	}

	static boolean change(double[] a, double[] b) {
		if (b[0] < a[1])
			return false;
		if (b[1] > a[0])
			return false;
		if (b[0] < a[0]) {
			a[0] = b[0];
		}
		if (b[1] >= a[1]) {
			a[1] = b[1];
		}
		return true;
	}

	public static List<point> creatp(path p) {
		// TODO Auto-generated method stub
		int a = p.getf();
		int dau = a;
		int h = p.getp().size();
		a = (a + 1) % h;
		double[] res = new double[2];
		res[1] = -str;
		res[0] = str;
		List<point> ret = new LinkedList<point>();
		ret.add(p.get(dau));
		boolean tl;
		point b = p.get(dau);
		while (a != dau) {
			double[] sd = creline.region(b, p.get(a));
			tl = change(res, sd);
			if (!tl) {
				a = (a - 1) % h;
				ret.add(p.get(a));
				res[1] = -str;
				res[0] = str;
			} else
				a = (a + 1) % h;
		}
		return ret;
	}
}
