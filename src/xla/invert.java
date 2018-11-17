package xla;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import base.point;
import base.svg.img;
import base.svg.polygon;
import cuver.catmull;
import potrace.vector;
import segment.imgs;

public class invert extends JFrame {
	/**
		 * 
		 */

	static String file = "/home/amneiht/sdf/02.jpg";

	private static final long serialVersionUID = 1L;
	static int csl = 0;

	public invert() {
		// this.setVisible(true);
		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	BufferedImage in;

	public void paint(Graphics g) {
		super.paint(g);

		if (in != null) {
			g.drawImage(in, 50 ,50, null);
		}

	}

	public static void main(String[] args) {
		try {
			invert c = new invert();
			c.cs();
			c.setVisible(true);
			c.repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void cs() throws IOException {
		in = get(file);
//		System.out.println(in.getWidth() + " " + in.getHeight());
//		in = loc.tg(in);
//		in = new imgs(in, 10).segment();
//		vector pk = new vector(in);
//		img is=pk.creat();
//		polygon p=is.getpl(210);
//		in=catmull.draw(in.getWidth(), in.getHeight(), p.getP());
//		is.create("xcmn", "/home/amneiht/sdf");
		//in=loc.exp(in, 3);
		in=loc.tg(in);
		in=loc.tc(in);
		
	}
	public static void pr(int[][] stn) {
		// TODO Auto-generated method stub
		int a = stn.length;
		int b = stn[0].length;
		for (int i = 0; i < a; i++) {
			System.out.print(i + "  ");
			for (int j = 0; j < b; j++)
			{
				
				switch(stn[i][j])
				{
				case 0:
					System.out.print(0 + " ");
					break;
				case -15916254:
					System.out.print(1 + " ");
				break;
				case -13415877:
					System.out.print(2 + " ");
					break;
				case -11439249:
					System.out.print(3 + " ");
					break;
					
				default:
					System.out.print(4 + " ");
					break;
				}
			}
			System.out.println();
		}

	}
	BufferedImage paintline(ArrayList<point> arr, int col) {

		int x = in.getWidth(), y = in.getHeight();
		BufferedImage res = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		Graphics g = res.getGraphics();
		g.setColor(new Color(col));
		point p = arr.get(0);
		Polygon pl = new Polygon();
		for (point i : arr) {
			pl.addPoint(i.getx(), i.gety());
		}
		g.fillPolygon(pl);
		g.dispose();
		return res;
	}

	/**
	 * lay thong tin anh
	 * 
	 * @param f
	 * @return
	 * @throws IOException
	 */
	private static BufferedImage get(String f) throws IOException {
		BufferedImage in = ImageIO.read(new File(f));

		BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_RGB);
		// System.out.println(in.getWidth());
		Graphics2D g = newImage.createGraphics();
		g.drawImage(in, 0, 0, null);
		g.dispose();
		return newImage;
	}

	public void update(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		paintComponents(g);
	}

	public void repaint() {
		paint(getGraphics());
	}
	/**
	 * chuyen anh sang den trang
	 * 
	 * @param in
	 */

}