package base.svg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import base.point;
import base.rbg;

public class img {
	private int x, y;
	static final String spe = File.separator;
	public int dem=0;
	public img(int dx, int dy) {
		x = dx;
		y = dy;
	}

	private ArrayList<polygon> p = new ArrayList<polygon>();

	public void add(polygon pd) {
		p.add(pd);
		dem++;
	}
	public polygon getpl(int i)
	{
		return p.get(i);
	}
	public void create(String file, String foder) {
		String cre = foder + spe + file + ".html";
		FileWriter out;
		try {
			out = new FileWriter(cre);
			System.out.println("aaa");
			String ts = String.format("<svg width=\"%d\" height=\"%d\">\n ", x, y);
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
		//System.gc();
		for (polygon d : p) {
			ArrayList<point> ps = d.p;
			point h = ps.get(0);
			out.write("<path d=\" M " + h.getx() + " " + h.gety() + " ");
			for (point k : ps) {
				out.write(String.format("L %d %d ", k.getx(), k.gety()));
			}
			out.write(String.format("Z \" fill=%s />\n", new rbg(d.getColor()).tohex()));

		}
		out.write("</svg>");
	}

	public BufferedImage paintimg() {

		BufferedImage res = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		Graphics g = res.getGraphics();
		for (polygon d : p) {
			ArrayList<point> arr = d.p;
			int s=arr.size();
			if(s<5) System.out.println("loi"+d.getColor());
			g.setColor(new Color(d.getColor()));
			Polygon pl = new Polygon();
			for (point i : arr) {
				// g.drawLine(p.getx(), p.gety(), i.getx(), i.gety());
				// p = i;
				pl.addPoint(i.getx(), i.gety());
				// System.out.print(mcat[i][j] + " ");
			}
			g.fillPolygon(pl);
		}
		g.dispose();
		return res;
	}
}
