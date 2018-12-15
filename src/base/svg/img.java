package base.svg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import base.point;
import base.rbg;
import cuver.catmull;

public class img {
	private int x, y;
	static final String spe = File.separator;
	public int dem=0;

	public img(int dx, int dy) {
		x = dx;
		y = dy;
	}

	private ArrayList<poly> p = new ArrayList<poly>();

	public void add(poly pd) {
		p.add(pd);
		dem++;
	}
	public poly getpl(int i)
	{
		return p.get(i);
	}
	public void create(String file) {
		String cre = file + ".html";
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
		for (poly d : p) {
			List<point> ps = d.p;
			point h = ps.get(0);
			out.write("<path d=\" M " + h.getx() + " " + h.gety() + " ");
			for (point k : ps) {
				out.write(String.format("L %d %d ", k.getx(), k.gety()));
			}
			out.write(String.format("Z \" fill=%s />\n", new rbg(d.getColor()).tohex()));

		}
		out.write("</svg>");
	}
	public void khoiphuc()
	{
		for(poly d:p)
		{
			d.p=catmull.tcat(d.p);
		}
	}
	
	public BufferedImage paintimg() {

		BufferedImage res = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		Graphics g = res.getGraphics();
		for (poly d : p) {
			List<point> arr = d.p;
		//	int s=arr.size();
		//	if(s<5) System.out.println("loi"+d.getColor());
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
