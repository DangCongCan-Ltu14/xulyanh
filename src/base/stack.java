package base;

import java.util.LinkedList;
import java.util.List;

 public class stack {
	List<point> p = new LinkedList<point>();

	public void push(point pl) {
		p.add(pl);

	}

	public point pop() {
		return p.remove(0);
	}

	public boolean end() {
		return p.isEmpty();
	}

}
