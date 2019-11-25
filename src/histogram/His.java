package histogram;

import java.awt.image.BufferedImage;
import java.io.IOException;

import base.rbg;
import base.show;
import xla.buff;
import xla.loc;

public class His {
	static String text = "/home/dccan/Pictures/b.jpeg";
	public static void main(String[] args) {
		try {
			BufferedImage in = buff.get(text);
			show.pr(loc.imggray(in));
			int x = in.getWidth(), y = in.getHeight();
			
			int p=rbg.getGray(in.getRGB(0,0));
			
			long k=0;
			int[][] mg=new int [x][y];
			for (int i = 00; i < x; i++)
				for (int j = 0; j < y; j++) {
					p=rbg.getGray(in.getRGB(i,j));
					k=k+p ;
					mg[i][j]=p;
				}
			System.out.println("do sang "+(k*1.0/(x*y)));
			Bac1 bb=new Bac1(60);
			printimage(bb,mg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void printimage(Line bb, int[][] mg) {
		// TODO Auto-generated method stub
		int y=mg[0].length,x=mg.length;
		BufferedImage in=new BufferedImage(x, y, BufferedImage.TYPE_3BYTE_BGR);
		for (int i = 00; i < x; i++)
			for (int j = 0; j < y; j++)
			{
				int c=bb.newValue((mg[i][j]));
				in.setRGB(i, j, rbg.pgray(c));
				
			}
		show.pr(in, "dkm atrix");
	}
}
