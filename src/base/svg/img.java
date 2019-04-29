package base.svg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import base.point;
import base.rbg;
import base.stack;
import cuver.catmull;

public class img {
	private int x, y;
	static final String spe = File.separator;
	public int dem = 0;

	public img(int dx, int dy) {
		x = dx;
		y = dy;
	}

	private ArrayList<poly> p = new ArrayList<poly>();
	public void destroy()
	{
		p=null;
	}
	public void add(poly pd) {
		p.add(pd);
		dem++;
	}

	public poly getpl(int i) {
		return p.get(i);
	}

	public void save(String file) {
		String cre = file + ".vt";
		try {
			//DataOutputStream out = new DataOutputStream(new FileOutputStream(cre));
			FileWriter out=new FileWriter(cre);
			out.write(x);
			out.write(y);
			for (poly d : p) {
				List<point> ps = d.p;
				out.write(ps.size());
				rbg cl=new rbg(d.getColor());
				out.write(cl.getR());
				out.write(cl.getB());
				out.write(cl.getG());
				for (point c : ps) {
					out.write(c.getx());
					out.write(c.gety());
				}
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static img read(String file) {
		try {		
			FileReader in = new FileReader(file);
			int x = in.read();
			int y = in.read();
			img d = new img(x, y);
			int n = in.read();
			while(n>0) {
				
				int r = in.read();
				int g = in.read();
				int b = in.read();
				rbg cl = new rbg(255, r, g, b);
				poly p = new poly(cl.get());
				for (int j = 0; j < n; j++) {
					x = in.read();
					y = in.read();
					p.add(new point(x, y));
				}
				d.add(p);
				n = in.read();
			}
			in.close();
			return d;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void create(String file) {
		String cre = file + ".html";
		FileWriter out;
		try {
			out = new FileWriter(cre);
			System.out.println("aaa");
			String ts = String.format("<svg width=\"%d\" height=\"%d\">\n ", x - 2, y - 2);
			out.write(ts);
			printl(out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void printl(FileWriter out) throws IOException {
		// TODO Auto-generated method stub
		// System.gc();

		for (poly d : p) {
			List<point> ps = d.p;
			if (ps.size() > 3) {
				point h = ps.get(0);
				out.write("<path d=\" M " + (h.getx() - 1) + " " + (h.gety() - 1) + " ");
				for (point k : ps) {
					out.write(String.format("L %d %d ", (k.getx() - 1), (k.gety()) - 1));

				}
				out.write(String.format("Z \" fill=%s />\n", new rbg(d.getColor()).tohex()));
			}
		}
		out.write("</svg>");
	}

	public void khoiphuc() {
		for (poly d : p) {
			d.p = catmull.tcat(d.p);
		}
	}

	public BufferedImage kp(int col) {
		for (poly d : p) {
			d.p = catmull.tcat(d.p);
		}
		BufferedImage res = paintimg(col);
		for (int i = 1; i < x - 1; i++)
			for (int j = 1; j < y - 1; j++) {
				if (res.getRGB(i, j) == col) {
					fill(i, j, res, col);
				}
			}
		return res;
	}

	private void fill(int i, int j, BufferedImage res, int col) {
		// TODO Auto-generated method stub
		int d = col;
		if (res.getRGB(i + 1, j) != col)
			d = res.getRGB(i + 1, j);
		else if (res.getRGB(i - 1, j) != col)
			d = res.getRGB(i - 1, j);
		else if (res.getRGB(i, j - 1) != col)
			d = res.getRGB(i, j - 1);
		else if (res.getRGB(i, j + 1) != col)
			d = res.getRGB(i, j + 1);
		else
			return;
		stack st = new stack();
		st.push(new point(i, j));
		point p;
		int z = x - 1;
		int l = y - 1;
		res.setRGB(i, j, d);

		while (!st.end()) {
			p = st.pop();
			if (p.getx() > 1) {
				if (res.getRGB(p.getx() - 1, p.gety()) == col)
					st.push(new point(p.getx() - 1, p.gety()));
				res.setRGB(p.getx() - 1, p.gety(), d);
			}
			if (p.getx() < z) {
				if (res.getRGB(p.getx() + 1, p.gety()) == col)
					st.push(new point(p.getx() + 1, p.gety()));
				res.setRGB(p.getx() + 1, p.gety(), d);
			}
			if (p.gety() > 1) {
				if (res.getRGB(p.getx(), p.gety() - 1) == col)
					st.push(new point(p.getx(), p.gety() - 1));
				res.setRGB(p.getx(), p.gety() - 1, d);
			}
			if (p.gety() < l) {
				if (res.getRGB(p.getx(), p.gety() + 1) == col)
					st.push(new point(p.getx(), p.gety() + 1));
				res.setRGB(p.getx(), p.gety() + 1, d);
			}
		}

	}

	@SuppressWarnings("unused") // code sau
	private boolean check(point l, int[][] mg) {
		// TODO Auto-generated method stub
		int x = l.getx();
		int y = l.gety();

		return false;
	}

	BufferedImage paintimg(int col) {

		BufferedImage res = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		// BufferedImage res = new BufferedImage(
		Graphics g = res.getGraphics();
		g.setColor(new Color(col));
		g.fillRect(0, 0, x, y);
		for (poly d : p) {
			List<point> arr = d.p;
			g.setColor(new Color(d.getColor()));
			Polygon pl = new Polygon();
			for (point i : arr) {
				pl.addPoint(i.getx(), i.gety());
			}
			g.fillPolygon(pl);
		}
		g.dispose();
		return res;
	}

	public BufferedImage paintimg() {

		BufferedImage res = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		// BufferedImage res = new BufferedImage(
		Graphics g = res.getGraphics();
		for (poly d : p) {
			List<point> arr = d.p;
			// int s=arr.size();
			// if(s<5) System.out.println("loi"+d.getColor());
			g.setColor(new Color(d.getColor()));
			Polygon pl = new Polygon();
			for (point i : arr) {
				pl.addPoint(i.getx(), i.gety());
			}
			g.fillPolygon(pl);
		}
		g.dispose();
		return res;
	}
}
