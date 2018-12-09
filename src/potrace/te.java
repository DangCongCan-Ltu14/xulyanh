package potrace;

import java.util.ArrayList;

import base.point;
import base.svg.poly;

public class te {
	static int[][] ps= {
//						{0,0,1,1,0,0,1,0,1,1,1},
//						{0,1,0,1,1,1,1,0,1,1,0},
//						{0,0,1,1,0,0,1,1,1,0,0},
//						{0,0,1,1,1,1,1,1,1,1,0},
//						{0,0,0,0,1,0,0,0,1,1,1},
//						{1,0,0,1,1,1,1,1,1,1,1},
						{0,0,0,0,0,0,1,1,1,1,1},
						{0,0,0,0,0,0,1,1,0,0,1},
						{0,0,0,1,0,0,1,1,1,0,1},
						{0,0,0,0,0,0,1,1,1,0,0}
	};
	static final int trai = 1, phai = 0b10, tren = 0b100, duoi = 0b1000, trong = 0, ngoai = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//pr(ps);
		vectorbackup p=new vectorbackup(ps);
		ArrayList<point> arr;
		point a=new point (3,9);
		int color =1;
		int turn = p.check(a, color);
		if (turn > 0) {
			// System.out.println("point"+i+" "+j);
		arr= p.getedge(a, color, turn);
		pr(tomg(arr));
		}
		
	}
	static int[][] tomg(ArrayList<point> arr)
	{
		int x=ps.length , y=ps[0].length;
		int[][] res=new int[x+2][y+2];
		for(point d:arr)
		{
			res[d.getx()][d.gety()]=4;
		}
		return res;
				
	}
	public static void pr(int[][] stn) {
		// TODO Auto-generated method stub
		int a = stn.length;
		int b = stn[0].length;
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++)
				System.out.print(stn[i][j] + " ");

			System.out.println();
		}

	}
}
