package p2;

import java.util.LinkedList;
import java.util.List;

import base.svg.poly;

public class store {
	private List<poly> vecto = new LinkedList<poly>(); // cacs
	private List<poly> sp = new LinkedList<poly>();// toa do diem dac biet

	void addst(poly a, poly b) {
		vecto.add(a);
		sp.add(b);
	}

	public poly check(poly d) {
		int p = sp.size();
		for (int i = 0; i < p; i++) {
			if (poly.cmp(sp.get(i), d)) {
				sp.remove(i);
				return vecto.remove(i);
			}
		}
		return null;
	}
}
