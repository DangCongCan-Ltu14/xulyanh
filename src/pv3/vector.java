package pv3;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import base.point;
import base.stack;
import base.svg.img;
import base.svg.poly;

/**
 * @author amneiht
 *
 */
public class vector {
	int x, y;
	public int[][] img, sou;
	static final int black =0;//Color.black.getRGB();// mau nen
	static final int trai = 1, phai = 0b10, tren = 0b100, duoi = 0b1000;
	// public int dem = 0;

	public vector(BufferedImage in, int p) {
		x = in.getWidth() + 2;
		y = in.getHeight() + 2;
		img = new int[x][y];
		sou = new int[x][y];
		tdt(in);
		creline.setHalf(p);
	}

	public vector(int[][] in) {
		x = in.length + 2;
		y = in[0].length + 2;
		sou = new int[x][y];
		img = new int[x][y];
		tdt(in);
	}

	private void tdt(BufferedImage in) {
		for (int i = 1; i < x - 1; i++) {
			for (int j = 1; j < y - 1; j++) {
				img[i][j] = in.getRGB(i - 1, j - 1);
				sou[i][j] = in.getRGB(i - 1, j - 1);
			}
		}
	}

	private void tdt(int[][] in) {
		// TODO Auto-generated method stub
		for (int i = 1; i < x - 1; i++) {
			for (int j = 1; j < y - 1; j++) {
				img[i][j] = in[i - 1][j - 1];
				sou[i][j] = in[i - 1][j - 1];
			}
		}
	}

	public img creat() {
		// int dem = 0;
		System.gc();
		img sr = new img(x -2, y-2 );
		for (int i = 0; i < x - 1; i++) {
			for (int j = 0; j < y - 1; j++) {
				point a = new point(i, j);
				int color = img[i + 1][j + 1];
				int turn = check(a);
				if (turn > 0) {
					sr.add(new poly(color, getedge(a, color, turn)));
					clear(i + 1, j + 1,color);
				}
			}
		}
		return sr;
	}

	private boolean sp;

	public List<point> getedge(point a, int color, int t) {
		path p = new path();
		p.add(a);
		int turn = t;
		int site = 0;
		int x = a.getx(), y = a.gety();
		do {
			sp = false;
			turn = nextedge(p.get(site), color, turn);
			if (sp)
				p.note();
			switch (turn) {
			case trai:
				y = y - 1;
				// System.out.println("trai");
				break;
			case phai:
				y = y + 1;
				// System.out.println("phai");
				break;
			case tren:
				x = x - 1;
				// System.out.println("tren");
				break;
			case duoi:
				x = x + 1;
				break;
			}
			p.add(new point(x, y));
			site++;
		} while (end(p, site));
		//return grd(p);
		return makep(p);
		//return cong.taoPart2(p.getp());
	}
	
	@SuppressWarnings("unused")
	private List<point> grd(path p)
	{
		int l=5;
		List<point>d=p.getp();
		List<point>p2=new LinkedList<point>();
		for(point t:d)
		{
			if(t.getx()%l==0||t.gety()%l==0) p2.add(t);
		}
		return p2;
	}

	private List<point> makep(path p) {
		// TODO Auto-generated method stub
		int dau = p.getf();
		if (p.vtsize() < 1) {
			return cong.taoPart2(p.getp());
		}
		if (p.vtsize() < 2) {
			 return cong.creatp(p);
			//return cong.creatp(p);
		}
		int cuoi = p.getf();
		List<point> res = new LinkedList<point>();
		res.add(p.get(dau));
		while (cuoi != -1) {
			cong.ntaopart(res, p, dau, cuoi);
			dau = cuoi;
			cuoi = p.getf();
		}
		// if(res.size()<10) System.out.println("xcmn");
		return res;
	}

	protected boolean end(path p, int site) {
		int a = p.get(0).getx() - p.get(site).getx();
		int b = p.get(0).gety() - p.get(site).gety();
		return (a != 0 || b != 0);

	}

	public void clear(int dx, int dy, int col) {
		stack st = new stack();
		img[dx][dy] = black;
		st.push(new point(dx, dy));
		while (!st.end()) {
			point t = st.pop();

			if (img[t.getx() + 1][t.gety()] == col) {
				img[t.getx() + 1][t.gety()] = black;
				st.push(new point(t.getx() + 1, t.gety()));
			}

			if (img[t.getx() - 1][t.gety()] == col) {
				img[t.getx() - 1][t.gety()] = black;
				st.push(new point(t.getx() - 1, t.gety()));
			}
			if (img[t.getx()][t.gety() + 1] == col) {
				img[t.getx()][t.gety() + 1] = black;
				st.push(new point(t.getx(), t.gety() + 1));
			}
			if (img[t.getx()][t.gety() - 1] == col) {
				img[t.getx()][t.gety() - 1] = black;
				st.push(new point(t.getx(), t.gety() - 1));
			}

		}
	}

	int check(point a) {
		if (img[a.getx() + 1][a.gety() + 1] != black&&img[a.getx() + 1][a.gety() + 1]!=0)
			return trai;
		return 0;
	}

	protected int nextedge(point a, int color, int turn) {
		int k = 0;
		int d1, d2, d3, d4;
		d1 = sou[a.getx()][a.gety()];
		d2 = sou[a.getx()][a.gety() + 1];
		d3 = sou[a.getx() + 1][a.gety() + 1];
		d4 = sou[a.getx() + 1][a.gety()];// co the cai thien

		if (d1 == color)
			k = k | 0b1000;

		if (d2 == color)
			k = k | 0b100;

		if (d3 == color)
			k = k | 0b10;

		if (d4 == color)
			k = k | 0b1;

		// System.out.println(sp);
		switch (k) {

		case 0b1001:
			if (d2 != d3)
				sp = true;
			if (turn != tren && turn != duoi)
				return 0;
			k = turn;
			break;
		case 0b0110:
			// System.out.println("0b0110");
			if (d1 != d4)
				sp = true;
			if (turn != tren && turn != duoi)
				return 0;
			k = turn;
			break;
		case 0b1100:
			if (d3 != d4)
				sp = true;
			if (turn != trai && turn != phai)
				return 0;
			k = turn;

			break;
		case 0b0011:
			if (d1 != d2)
				sp = true;
			if (turn != trai && turn != phai)
				return 0;

			k = turn;
			break;
		case 0:
		case 0b1111:
			k = 0;
			break;
		case 0b0001:
			if (d1 != d2 || d2 != d3)
				sp = true;
			if (turn == phai)
				k = duoi;
			else if (turn == tren)
				k = trai;
			else
				return 0;
			break;
		case 0b1110:
			if (turn == phai)
				k = duoi;
			else if (turn == tren)
				k = trai;
			else
				return 0;
			break;
		case 0b0010:
			if (d1 != d2 || d2 != d4)
				sp = true;
			if (turn == trai)
				k = duoi;
			else if (turn == tren)
				k = phai;
			break;
		case 0b1101:
			if (turn == trai)
				k = duoi;
			else if (turn == tren)
				k = phai;
			break;
		case 0b0100:
			if (d1 != d3 || d3 != d4)
				sp = true;
			if (turn == duoi)
				k = phai;
			else if (turn == trai)
				k = tren;
			else
				return 0;
			break;
		case 0b1011:
			if (turn == duoi)
				k = phai;
			else if (turn == trai)
				k = tren;
			else
				return 0;
			break;
		case 0b1000:
			if (d2 != d3 || d3 != d4)
				sp = true;
			if (turn == duoi)
				k = trai;
			else if (turn == phai)
				k = tren;
			else
				return 0;
			break;
		case 0b0111:
			if (turn == duoi)
				k = trai;
			else if (turn == phai)
				k = tren;
			else
				return 0;
			break;
		case 0b1010:
			sp = true;
			switch (turn) {
			case trai:
				k = duoi;
				break;
			case phai:
				k = tren;
				break;
			case tren:
				k = phai;
				break;
			case duoi:
				k = trai;
				break;
			}
			break;
		case 0b0101:
			sp = true;
			switch (turn) {
			case trai:
				k = tren;
				break;
			case phai:
				k = duoi;
				break;
			case tren:
				k = trai;
				break;
			case duoi:
				k = phai;
				break;
			}
			break;
		}
		return k;
	}

	public int[][] getimg() {
		// TODO Auto-generated method stub
		int[][] res = new int[x - 2][y - 2];
		for (int i = 1; i < x - 1; i++) {
			for (int j = 1; j < y - 1; j++) {
				res[i - 1][j - 1] = img[i][j];
			}
		}
		return res;
	}

}