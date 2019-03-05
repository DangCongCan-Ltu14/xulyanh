package p2;

import java.util.LinkedList;
import java.util.List;

import base.point;

public class stack {
	List<point> p = new LinkedList<point>();

	protected void push(point pl) {
		p.add(pl);

	}

	protected point pop() {
		return p.remove(0);
	}

	protected boolean end() {
		return p.isEmpty();
	}

}
