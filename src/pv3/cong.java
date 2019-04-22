package pv3;

import java.util.LinkedList;
import java.util.List;

import base.point;

public class cong {

	static final double str = Double.MAX_VALUE;
	static final int lg = 12;// bien so toi thieu

	public static void ntaopart(List<point> ret, path p, int dau, int cuoi) {
		double[] res = new double[2];
		res[1] = -str;
		res[0] = str;
		int h = p.site();
		int z = h - 1;
		boolean bres = false;
		point a = p.get(dau);
		point b = p.get(cuoi);
		int stack, node, dx = 0, dy = 0, da = 0, db = 0;
		int k;
		boolean tl;
		if (point.cmpl(a, b)) {
			stack = dau;
			node = dau;
			
			while (dau != cuoi) {
				k = dau;
				dau = (dau + 1) % h;
				da = Math.abs(p.get(dau).getx() - p.get(k).getx());
				db = Math.abs(p.get(dau).gety() - p.get(k).gety());
				dx = dx + da;
				dy = dy + db;
				if (dx * dy != 0) {
					if (dx > lg || dy > lg) {
						bres = true;
					} else {
						node = k;
						dx = da;
						dy = db;
					}
				}
				bres=false;
				if (bres) {
					if (node != stack)
						ret.add(p.get(node));
					ret.add(p.get(k));
					stack = k;
					node = k;
					res[1] = -str;
					res[0] = str;
					dx = da;
					dy = db;
					bres = false;
				} else {
					double[] sd = creline.region(a, p.get(dau));
					tl = change(res, sd);
					if (!tl) {
						ret.add(p.get(k));
						res[1] = -str;
						res[0] = str;
						a = p.get(k);
						stack = k;
					}
				}
			}

			ret.add(p.get(cuoi));
		} else {
			int neo = ret.size();
			ret.add(p.get(cuoi));
			stack = cuoi;
			node = cuoi;
			while (cuoi != dau) {
				k = cuoi;
				cuoi = (cuoi + z) % h;
				da = Math.abs(p.get(cuoi).getx() - p.get(k).getx());
				db = Math.abs(p.get(cuoi).gety() - p.get(k).gety());
				dx = dx + da;
				dy = dy + db;
				if (dx * dy != 0) {
					if (dx > lg || dy > lg) {
						bres = true;
					} else {
						node = k;
						dx = da;
						dy = db;
					}
				}
				bres=false;
				if (bres) {
					if (node != stack)
						ret.add(neo, p.get(node));
					ret.add(neo, p.get(k));
					stack = k;
					node = k;
					res[1] = -str;
					res[0] = str;
					dx = da;
					dy = db;
					bres = false;
				} else {
					double[] sd = creline.region(b, p.get(cuoi));
					tl = change(res, sd);
					if (!tl) {
						ret.add(neo, p.get(k));
						res[1] = -str;
						res[0] = str;
						b = p.get(k);
					}
				}
			}
		}

	}

	public static void ntaopartx(List<point> ret, path p, int dau, int cuoi) {
		double[] res = new double[2];
		res[1] = -str;
		res[0] = str;
		int h = p.site();
		int z = h - 1;
		boolean bres = false;
		point a = p.get(dau);
		point b = p.get(cuoi);
		int stack, node, dx = 0, dy = 0, da = 0, db = 0;
		int k;
		boolean tl;
		if (point.cmpl(a, b)) {
			stack = dau;
			node = dau;

			while (dau != cuoi) {
				k = dau;
				dau = (dau + 1) % h;
				da = Math.abs(p.get(dau).getx() - p.get(k).getx());
				db = Math.abs(p.get(dau).gety() - p.get(k).gety());
				dx = dx + da;
				dy = dy + db;
				if (dx * dy != 0) {
					if (dx > lg || dy > lg) {
						bres = true;
					} else {
						node = k;
						dx = da;
						dy = db;
					}
				}
				bres=false;
				if (bres) {
					if (node != stack)
						ret.add(p.get(node));
					ret.add(p.get(k));
					stack = k;
					node = k;
					res[1] = -str;
					res[0] = str;
					dx = da;
					dy = db;
					bres = false;
				} else {
					double[] sd = creline.region(a, p.get(dau));
					tl = change(res, sd);
					if (!tl) {
						ret.add(p.get(k));
						res[1] = -str;
						res[0] = str;
						a = p.get(k);
						stack = k;
					}
				}
			}

			ret.add(p.get(cuoi));
		} else {
			int neo = ret.size();
			ret.add(p.get(cuoi));
			stack = cuoi;
			node = cuoi;
			while (cuoi != dau) {
				k = cuoi;
				cuoi = (cuoi + z) % h;
				da = Math.abs(p.get(cuoi).getx() - p.get(k).getx());
				db = Math.abs(p.get(cuoi).gety() - p.get(k).gety());
				dx = dx + da;
				dy = dy + db;
				if (dx * dy != 0) {
					if (dx > lg || dy > lg) {
						bres = true;
					} else {
						node = k;
						dx = da;
						dy = db;
					}
				}
				bres=false;
				if (bres) {
					if (node != stack)
						ret.add(neo,p.get(node));
					ret.add(neo,p.get(k));
					stack = k;
					node = k;
					res[1] = -str;
					res[0] = str;
					dx = da;
					dy = db;
					bres = false;
				} 
				double[] sd = creline.region(b, p.get(cuoi));
				tl = change(res, sd);
				if (!tl) {
					ret.add(neo, p.get(k));
					res[1] = -str;
					res[0] = str;
					b = p.get(k);
				}
			}
		}

	}
	public static List<point> taoPart2(List<point> p) {
		List<point> part = new LinkedList<point>();
		if (p.size() < 1)
			return part;
		int dx = 0, dy = 0, a=0, b=0;
		boolean tl, bres = false;
		int stack = 0, node = 0;
		int k = p.size() - 1;
		part.add(p.get(0));
		double[] res = new double[2];
		res[1] = -str;
		res[0] = str;
		for (int i = 1; i < k; i++) {
			a = Math.abs(p.get(i + 1).getx() - p.get(i).getx());
			b = Math.abs(p.get(i + 1).gety() - p.get(i).gety());
			dx = dx + a;
			dy = dy + b;
			if (dx * dy != 0) {
				if (dx > lg || dy > lg) {
					bres = true;
				} else {
					node = i;
					dx = a;
					dy = b;
				}
			}
			 bres=false;
			if (bres) {
				if (node < stack)
					part.remove(part.size() - 1);
				if (node != stack)
					part.add(p.get(node));
				part.add(p.get(i));
				stack = i;
				node = i;
				res[1] = -str;
				res[0] = str;
				dx = a;
				dy = b;
				bres = false;
			} else {
				double[] sd = creline.region(p.get(stack), p.get(i + 1));
				tl = change(res, sd);
				if (!tl) {
					res[1] = -str;
					res[0] = str;
					part.add(p.get(i));
					stack = i;
				}
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
				b = p.get(a);
				res[1] = -str;
				res[0] = str;
			} else
				a = (a + 1) % h;
		}
		return ret;
	}
}
