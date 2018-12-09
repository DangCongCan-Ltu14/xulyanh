package base.svg;

import java.awt.Polygon;
import java.util.LinkedList;
import java.util.List;

import base.point;

public class poly {
	protected List<point> p;

	public List<point> getP() {
		return p;
	}

	private int color;

	public poly(int c) {
		color = c;
		p = new LinkedList<point>();
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

}
