package base.svg;

import java.util.ArrayList;
import java.util.List;

import base.point;

public class polygon {
	protected List<point> p;
	public List<point> getP() {
		return p;
	}

	private int color;

	public polygon(int c) {
		color = c;
		p = new ArrayList<point>();
	}

	public polygon(int c,List<point> k) {
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
