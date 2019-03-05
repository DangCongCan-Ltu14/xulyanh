package pv3;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;

class MedianFilter{
    public static void main(String[] a)throws Throwable{
        File f=new File("/home/amneiht/Desktop/anh/xly.png");                               //Input Photo File
        int[] pixel=new int[9];

        File output=new File("/home/amneiht/Desktop/anh/pi2.png");
        BufferedImage img=ImageIO.read(f);
        for(int i=1;i<img.getWidth()-1;i++)
            for(int j=1;j<img.getHeight()-1;j++)
            {
               pixel[0]=(img.getRGB(i-1,j-1));
               pixel[1]=(img.getRGB(i-1,j));
               pixel[2]=(img.getRGB(i-1,j+1));
               pixel[3]=(img.getRGB(i,j+1));
               pixel[4]=(img.getRGB(i+1,j+1));
               pixel[5]=(img.getRGB(i+1,j));
               pixel[6]=(img.getRGB(i+1,j-1));
               pixel[7]=(img.getRGB(i,j-1));
               pixel[8]=(img.getRGB(i,j));
               Arrays.sort(pixel);
               img.setRGB(i,j,pixel[4]);
            }
        ImageIO.write(img,"png",output);
    }
}