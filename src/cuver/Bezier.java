package cuver;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JFrame;

import base.point;

public class Bezier {
	
public static void main(String[] args) {
	JFrame f=new JFrame();
	f.setVisible(true);
	f.setSize(400, 400);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	cv d=new cv();
	f.add(d);
}
}
class cv extends Canvas
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6268889905942626425L;
	static final int t=80;
	int ve=0;
	static ArrayList<point> arr = new ArrayList<point>();
	static int sx=400,sy=400;
	protected cv()
	{
		setSize(sx, sy);
		arr.add(new point(sx-200,sy-200));
		arr.add(new point(sx-200,sy-300));
		
		arr.add(new point(sx-300,sy-200));
		arr.add(new point(sx-300,sy-300));
	}
	public void paint(Graphics g) {
		 paintline();
		g.setColor(Color.BLUE);
       int [] mg=new int[4];
       int x1,y1,y2,x2;
       int v=t*t*t;
       x1=arr.get(ve).getx();
       y1=arr.get(ve).gety();
       for(int i=0;i<=t;i++)
       {
    	   mg[2]=3*(t-i)*i*i;
    	   mg[1]=3*(t-i)*(t-i)*i;
    	   mg[0]=(t-i); mg[0]=mg[0]*mg[0]*mg[0];
    	   mg[3]=i*i*i;
    	   x2=0;y2=0;
    	  for(int j=0;j<4;j++)
    	  {
    		  x2=x2+mg[j]*arr.get(ve+j).getx();
    		  y2=y2+mg[j]*arr.get(ve+j).gety();
    	  }
    	  x2=x2/v;
    	  y2=y2/v;
    	  g.drawLine(x1, y1, x2, y2);
    	  x1=x2;
    	  y1=y2;
       }
       
    }
	void paintline() {
		Graphics g = getGraphics();
		g.setColor(Color.GREEN);
		point p = arr.get(0);
		for (point i : arr) {
			g.drawLine(p.getx(), p.gety(), i.getx(), i.gety());
			p = i;
		}
		g.dispose();
	}
	
}