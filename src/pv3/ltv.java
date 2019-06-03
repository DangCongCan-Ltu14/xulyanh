package pv3;

import java.awt.image.BufferedImage;

import base.rbg;

public class ltv {
	static int [] dem=new int[9];
	static int [] rdem=new int[9];
	public static BufferedImage tv(BufferedImage img) { // Input Photo File
		int[] pixel = new int[9];
		BufferedImage in = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		for (int i = 1; i < img.getWidth() - 1; i++)
			for (int j = 1; j < img.getHeight() - 1; j++) {
				pixel[0] = (img.getRGB(i - 1, j - 1));
				pixel[1] = (img.getRGB(i - 1, j));
				pixel[2] = (img.getRGB(i - 1, j + 1));
				pixel[3] = (img.getRGB(i, j + 1));
				pixel[4] = (img.getRGB(i + 1, j + 1));
				pixel[5] = (img.getRGB(i + 1, j));
				pixel[6] = (img.getRGB(i + 1, j - 1));
				pixel[7] = (img.getRGB(i, j - 1));
				pixel[8] = (img.getRGB(i, j));
				in.setRGB(i, j, sort(pixel));
			}
		int a = img.getWidth() - 1;
		int b = img.getHeight() - 1;
		for (int i = 0; i < img.getWidth(); i++) {
			in.setRGB(i, 0, img.getRGB(i, 0));
			in.setRGB(i, b, img.getRGB(i, b));
		}
		for (int i = 1; i < b; i++) {
			in.setRGB(0, i, img.getRGB(0, i));
			in.setRGB(a, i, img.getRGB(a,i));
		}
		return in;
	}

	private static int sort(int[] a) {
		// TODO Auto-generated method stub
		for(int i=0;i<9;i++)
			dem[i]=rbg.getGray(a[i]);
		int all=0,tr=0;
		int res=0;
		for(int i=0;i<9;i++)
		{
			all=0;tr=0;
			for(int j=0;j<9;j++)
			{
				if(dem[i]>dem[j]) all++;
				else if (dem[i]==dem[j]) tr++;
			}
			if(all<5)
			{
				if(all+tr>4) 
				{
					res=i;
					break;
				}
			}
		}
		return a[res];
	}
}