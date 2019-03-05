package p2;

import java.awt.Polygon;
import java.util.LinkedList;
import java.util.List;

import base.point;

public class path {
	private List<point> lp = new LinkedList<point>(); // toa do diem
	public List<Integer> vt = new LinkedList<Integer>();//// vi tri diem dac biet
	int color;

	public void add(point g, boolean d) {
		lp.add(g);
		if (d)
			vt.add(lp.size() - 1);

	}
	public  List<point> gettvt()
	{
		List<point> lwp = new LinkedList<point>();
		for(int a:vt)
			lwp.add(lp.get(a));
		return lwp;
	}
	protected List<point> getp() {
		return lp;
	}

	/**
	 * get next point in list
	 * 
	 * @return
	 */
	public point getnext(int i) // get next point
	{
		int a = (i + 1 + lp.size()) % lp.size();
		return lp.get(a);
	}

	public point getback(int i) // get next point
	{
		int a = (i - 1 + lp.size()) % lp.size();
		return lp.get(a);
	}

	public List<point> getPath(int d, int c) {
		List<point> p = new LinkedList<point>();
		int h = lp.size();
		while (true) {
			if (d == c)
				break;
			p.add(lp.get(d));
			d = (d + 1) % h;
		}
		return p;
	}

	/**
	 * set start point
	 * 
	 * @param a
	 */

	/**
	 * lay toa do diem dac biet
	 * 
	 * @param a
	 */
	int gvt = -1;// bien luu vi tri

	public int getf() {
		gvt++;
		if (vt.size() == 0)
			return -1;
		if (gvt > vt.size())
			return -1;
		if (gvt == vt.size())
			return vt.get(0);
		return vt.get(gvt);
	}

	// @Override
	public int getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	// @Override
	public Polygon topoly() {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	public void setCor(int c) {
		// TODO Auto-generated method stub
		color = c;
	}

	public point get(int site) {
		// TODO Auto-generated method stub
		return lp.get(site);
	}

}
