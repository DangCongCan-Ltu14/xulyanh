package potrace;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import base.point;
import base.svg.img;
import base.svg.polygon;

/**
 * @author amneiht
 *
 */
public class vector {
	static final double str = Double.MAX_VALUE;
	int x, y;
	public int[][] img;
	int black = 0;// mau nen
	static final int trai = 1, phai = 0b10, tren = 0b100, duoi = 0b1000, trong = 0, ngoai = 0;
	public int dem = 0;

	public vector(BufferedImage in) {
		x = in.getWidth() + 2;
		y = in.getHeight() + 2;
		img = new int[x][y];
		tdt(in);
	}

	public vector(int[][] in) {
		x = in.length + 2;
		y = in[0].length + 2;
		img = new int[x][y];
		tdt(in);
	}

	private void tdt(BufferedImage in) {
		for (int i = 1; i < x - 1; i++) {
			for (int j = 1; j < y - 1; j++) {
				img[i][j] = in.getRGB(i - 1, j - 1);
			}
		}
	}

	private void tdt(int[][] in) {
		// TODO Auto-generated method stub
		for (int i = 1; i < x - 1; i++) {
			for (int j = 1; j < y - 1; j++) {
				img[i][j] = in[i - 1][j - 1];
			}
		}
	}

	public ArrayList<point> getedge(point a, int color, int t) {
		ArrayList<point> p = new ArrayList<point>();
		p.add(a);
		int turn = t;
		int site = 0;
		int x = a.getx(), y = a.gety();
		do {
			turn = nextedge(p.get(site), color, turn);
			// if(turn==0 )System.out.println("aa");
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
				// System.out.println("duoi");
				break;
			}
			p.add(new point(x, y));
			site++;
		} while (end(p, site));
		 return taoPart(p);
		//return p;
	}

	protected boolean end(ArrayList<point> p, int site) {
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

	public img creat() {
		// int dem = 0;
		img sr = new img(x, y);
		for (int i = 0; i < x - 1; i++) {
			for (int j = 0; j < y - 1; j++) {
				point a = new point(i, j);
				int color = img[i + 1][j + 1];
				int turn = check(a);
				if (turn > 0) {
					
					sr.add(new polygon(color, getedge(a, color, turn)));
					clear(i + 1, j + 1, color);
				}
			}
		}
		return sr;
	}

	int check(point a) {
		if (img[a.getx() + 1][a.gety() + 1] != black)
			return trai;
		return 0;
	}

	protected int nextedge(point a, int color, int turn) {
		int k = 0;

		if (img[a.getx()][a.gety()] == color)
			k = k | 0b1000;

		if (img[a.getx()][a.gety() + 1] == color)
			k = k | 0b100;

		if (img[a.getx() + 1][a.gety() + 1] == color)
			k = k | 0b10;

		if (img[a.getx() + 1][a.gety()] == color)
			k = k | 0b1;

		switch (k) {

		case 0b1001:
		case 0b0110:
			if (turn != tren && turn != duoi)
				return 0;
			k = turn;
			break;
		case 0b1100:
		case 0b0011:
			if (turn != trai && turn != phai)
				return 0;
			k = turn;
			break;
		case 0:
		case 0b1111:
			k = 0;
			break;
		case 0b0001:
		case 0b1110:
			if (turn == phai)
				k = duoi;
			else if (turn == tren)
				k = trai;
			else
				return 0;
			break;
		case 0b0010:
		case 0b1101:
			if (turn == trai)
				k = duoi;
			else if (turn == tren)
				k = phai;
			break;
		case 0b0100:
		case 0b1011:
			if (turn == duoi)
				k = phai;
			else if (turn == trai)
				k = tren;
			else
				return 0;
			break;
		case 0b1000:
		case 0b0111:
			if (turn == duoi)
				k = trai;
			else if (turn == phai)
				k = tren;
			else
				return 0;
			break;
		case 0b1010:
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

	public static ArrayList<point> taoPart(ArrayList<point> p) {
		ArrayList<point> part = new ArrayList<point>();
		point stack = p.get(0);
		int i=1;
		double[] res = new double[2];
		res[1] = -str;
		res[0] = str;
		int k = p.size() - 1;

		for (i=1; i < k; i++) {

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
	public static ArrayList<point> taoPart2(ArrayList<point> p)
	{
		//System.out.print("ssss");
		ArrayList<point> part = new ArrayList<point>();
		int dx=0,dy=0;
		int i=0;int dem=0;
		int a,b;
		boolean con=true;
		// tim su khac biet
//		while(con)
//		{
//			a=p.get(i+1).getx()-p.get(i).getx();
//			b=p.get(i+1).getx()-p.get(i).getx();
//			if(a!=0)
//			{
//				if(dx==0) dx=a;
//				else con=(dx==a);
//			}
//			if(b!=0)
//			{
//				if(dy==0) dy=b;
//				else con=(dx==b);
//			}
//			i++;
//			
//		}
		double[] res = new double[2];
		boolean tl=false;
		res[1] = -str;
		res[0] = str;
		int k = p.size() - 1;
		point stack = p.get(0);
		for (i=0; i < k; i++) {
			a=p.get(i+1).getx()-p.get(i).getx();
			b=p.get(i+1).getx()-p.get(i).getx();
			
			if(a!=0)
			{
				if(dx==0) dx=a;
				else con=(dx==a)&&con;
			}
			if(b!=0)
			{
				if(dy==0) dy=b;
				else con=(dx==b)&&con;
			}
			
			double[] sd = creline.region(stack, p.get(i));
			tl = change(res, sd);
			
			
			
			if ((!tl)||(!con)) {
				con=true;
				part.add(p.get(i - 1));
				stack = p.get(i - 1);
				dx=0;
				dy=0;
				res[1] = -str;
				res[0] = str;
				i = i - 1;
			}

		}
		
		part.add(p.get(k));
		return part;
	}
	public static ArrayList<point> taoPart1(ArrayList<point> p)
	{
		int dis=1;
		int ls=4;
		ArrayList<point> path=new ArrayList<point> ();
		int s=p.size();
		path.add(p.get(0));
		for(int i=1;i<s;i=i+dis)
		{
			if((p.get(i).getx()%ls==0)||(p.get(i).gety()%ls==0))
			path.add(p.get(i));
		}
		path.add(p.get(s-1));
		return path ;
	}
}
