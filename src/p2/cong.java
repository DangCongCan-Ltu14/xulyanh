package p2;

import java.util.LinkedList;
import java.util.List;

import base.point;

public class cong {
	static final double str = Double.MAX_VALUE;
	public static void main(String[] args) {
		List<point> a=new LinkedList<point>();
		List<point> b=new LinkedList<point>();
		for(int i=0;i<5;i++)
		{
			a.add(new point(i,i));
		b.add(new point(4+i,4+i));
		}
		add(a,b);
		for(point d:a)
			System.out.println(d.getx());
			
	}
	public static void add(List<point> a, List<point> b) {
		int h = a.size();
		if (h == 0) {
			for (point p : b) {
				a.add(p);
			}
		} else {
			int i = 0;
			int l = b.size();
			if (point.cmp(a.get(h - 1), b.get(0)))
				i++;
			for (; i < l; i++)
				a.add(b.get(i));
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
		if (p.size()==0)return part;
		int dx = 0, dy = 0;
		int i = 0;
		int a, b;
		boolean con = true;

		double[] res = new double[2];
		boolean tl = false;
		res[1] = -str;
		res[0] = str;
		int k = p.size() - 1;
		point stack = p.get(0);
		for (i = 0; i < k; i++) {
			a = p.get(i + 1).getx() - p.get(i).getx();
			b = p.get(i + 1).getx() - p.get(i).getx();

			if (a != 0) {
				if (dx == 0)
					dx = a;
				else
					con = (dx == a) && con;
			}
			if (b != 0) {
				if (dy == 0)
					dy = b;
				else
					con = (dx == b) && con;
			}

			double[] sd = creline.region(stack, p.get(i));
			tl = change(res, sd);

			if ((!tl) || (!con)) {
				con = true;
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

	public static List<point> taoPart1(List<point> p) {
		int dis = 1;
		int ls = 4;
		List<point> path = new LinkedList<point>();
		int s = p.size();
		path.add(p.get(0));
		for (int i = 1; i < s; i = i + dis) {
			if ((p.get(i).getx() % ls == 0) || (p.get(i).gety() % ls == 0))
				path.add(p.get(i));
		}
		path.add(p.get(s - 1));
		return path;
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
}
