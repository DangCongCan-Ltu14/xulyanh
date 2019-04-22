import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import base.show;
import base.svg.img;
import pv3.vector;

public class test {
	static final int length = 4096;

	public static void main(String[] args) {
		String f = "/home/amneiht/Desktop/anh/pi0.png";
		String s="/home/amneiht/Desktop/anh/test";
		String extend="png";
		try {
			// BufferedImage bf =new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);
			BufferedImage bf = ImageIO.read(new File(f));
			show.pr(bf);
//			gird(bf);
			img is=new vector(bf, 1).creat();	
			BufferedImage in =is.paintimg();
			show.pr(in);
//			File outputfile = new File(s+"."+extend);
//			ImageIO.write(in, extend, outputfile);
		} catch (Exception e) {
			System.out.println("cmn");
		}
	}
	private static void gird(BufferedImage bf) {
		// TODO Auto-generated method stub
		int l=20;
		Graphics g=bf.getGraphics();
		int x=bf.getHeight();
		int y=bf.getWidth();
		for(int i=l;i<y;i=i+l)
		{
			g.drawLine(i, 0, i,x);
			
		}
		for(int i=l;i<x;i=i+l)
		{
			g.drawLine(0, i, y,i);
			
		}
		g.dispose();
	}
	void print(BufferedImage in)
	{
		
	}
}
