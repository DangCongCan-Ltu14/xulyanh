package base.svg;

import java.util.ArrayList;

import base.point;

public class polygon {
	protected ArrayList<point> p;
	public ArrayList<point> getP() {
		return p;
	}

	private int color;

	public polygon(int c) {
		color = c;
		p = new ArrayList<point>();
	}

	public polygon(int c, ArrayList<point> k) {
		color = c;
		p = k;
	}

	public void add(point a) {
		p.add(a);
	}

	public int getColor() {
		return color;
	}
}
