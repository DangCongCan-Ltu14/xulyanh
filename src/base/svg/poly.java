package base.svg;

import java.awt.Polygon;
import java.util.LinkedList;
import java.util.List;

import base.point;

public class poly implements line {
	protected List<point> p;

	public List<point> getP() {
		return p;
	}

	private int color;

	public poly(int c) {
		color = c;
		p = new LinkedList<point>();
	}

	public poly() {
		p = new LinkedList<point>();
	}

	public poly(List<point> d) {
		p = d;
	}

	public poly(int c, List<point> k) {
		color = c;
		p = k;
	}

	public void add(point a) {
		p.add(a);
	}

	public int getColor() {
		return color;
	}

	public Polygon topoly() {
		Polygon pl = new Polygon();
		for (point d : p)
			pl.addPoint(d.getx(), d.gety());
		return pl;
	}

	public static boolean cmp(poly a, poly b) {
		int l = a.p.size();
		if (l != b.p.size())
			return false;
		boolean kq = true;
		for (int i = 0; i < l; i++) {
			if (!point.cmp(a.p.get(i), b.p.get(i))) {
				kq = false;
				break;
			}

		}
		if (kq)
			return true;
		kq = true;
		for (int i = 0; i < l; i++) {
			if (!point.cmp(a.p.get(i), b.p.get(l - i - 1))) {
				kq = false;
				break;
			}

		}
		return kq;
	}

	@Override
	public void setCor(int c) {
		// TODO Auto-generated method stub
		color = c;
	}

}
