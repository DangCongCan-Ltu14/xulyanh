import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import pv3.vector;

public class test {
	static final int length = 4096;

	public static void main(String[] args) {
		String f = "/home/amneiht/Desktop/anh/t2.png";
		try {
			BufferedImage bf = ImageIO.read(new File(f));
			 new vector(bf, 100).creat();
		} catch (Exception e) {
			System.out.println("cmn");
		}
	}

}
