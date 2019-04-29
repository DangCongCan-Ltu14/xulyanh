import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;

import javax.imageio.ImageIO;

import base.point;
import base.rbg;
import base.show;
import base.svg.img;
import base.svg.poly;
import pv3.vector;
import segment.Kmean2;

//import potrace.vector;
public class test {
	static final int length = 4096;

	// @SuppressWarnings("unused")
	public static void main(String[] args) {
		 System.gc();
		 String s = "/home/amneiht/Desktop/anh/test.vt";
		 vt(1);
//		 img k = op(s);
//		 show.pr(k.paintimg(), "kp");
		//tt();
	}

	public static img op(String file) {
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

	public static void vt(double n) {
		String f = "/home/amneiht/Desktop/anh/rg.jpg";
		try {
			// BufferedImage bf =new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);
			BufferedImage res = ImageIO.read(new File(f));
			// Kmean2 sm=new Kmean2(res,Math.PI);
			Kmean2 sm = new Kmean2(res, n);
			res = sm.segment();
			// show.pr(res,"kp");
			img km = new vector(res, 20).creat();
			show.pr(km.paintimg(), "ve lai");
			// System.gc();
	//		km.save("/home/amneiht/Desktop/anh/test");
			//
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
