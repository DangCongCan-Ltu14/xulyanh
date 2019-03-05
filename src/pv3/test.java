package pv3;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class test {
	static final int trai = 1, phai = 0b10, tren = 0b100, duoi = 0b1000, trong = 0, ngoai = 0;
	static boolean sp=false;
	public static void main(String[] args) {
		  try {
		File f=new File("/home/amneiht/Desktop/anh/pi2.png");                               //Input Photo File   
			BufferedImage img=ImageIO.read(f);
			new vector(img,1).creat();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
