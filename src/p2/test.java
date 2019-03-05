package p2;

import base.point;

public class test {
	static final int trai = 1, phai = 0b10, tren = 0b100, duoi = 0b1000, trong = 0, ngoai = 0;
	static boolean sp=false;
	public static void main(String[] args) {
		cmn();
		System.out.println(sp);
	}
	public static int cmn()
	{
		System.out.println("cmn");
			int k = 0;
			int l = 0,color=0;;
			int d1,d2,d3,d4;
			int turn=0;
			point a=new point(0,0);
			int [][] img= {{1,1},{1,0}};
			d1=img[a.getx()][a.gety()];
			d2=img[a.getx()+1][a.gety()];
			d3=img[a.getx() + 1][a.gety() + 1];
			d4=img[a.getx() ][a.gety()+1];
			if (d1 == color)
				k = k | 0b1000;

			if (d2 == color)
				k = k | 0b100;
			
			if (d3 == color)
				k = k | 0b10;
			
			if (d4 == color)
				k = k | 0b1;
			
		System.out.println(k);
			
			switch (k) {

			case 0b1001:
				System.out.println("0b1001");
				if (turn != tren && turn != duoi)
					return 0;
				k = turn;
				if(d2!=d3)sp=true;
				break;
			case 0b0110:
				System.out.println("0b0110");
				if(d1!=d4)sp=true;
				if (turn != tren && turn != duoi)
					return 0;
				k = turn;
				break;
			case 0b1100:
				if(d3!=d4)sp=true;
				if (turn != trai && turn != phai)
					return 0;
				k = turn;
			
				break;
			case 0b0011:
				if(d1!=d2)sp=true;
				if (turn != trai && turn != phai)
					return 0;
				
				k = turn;
				break;
			case 0:
			case 0b1111:
				k = 0;
				break;
			case 0b0001:
				if(d1!=d2||d2!=d3)sp=true;
				if (turn == phai)
					k = duoi;
				else if (turn == tren)
					k = trai;
				else
					return 0;
				break;
			case 0b1110:
				if (turn == phai)
					k = duoi;
				else if (turn == tren)
					k = trai;
				else
					return 0;
				break;
			case 0b0010:
				if(d1!=d2||d2!=d4)sp=true;
				if (turn == trai)
					k = duoi;
				else if (turn == tren)
					k = phai;
				break;
			case 0b1101:
				if (turn == trai)
					k = duoi;
				else if (turn == tren)
					k = phai;
				break;
			case 0b0100:
				if(d1!=d3||d3!=d4)sp=true;
				if (turn == duoi)
					k = phai;
				else if (turn == trai)
					k = tren;
				else
					return 0;
				break;
			case 0b1011:
				if (turn == duoi)
					k = phai;
				else if (turn == trai)
					k = tren;
				else
					return 0;
				break;
			case 0b1000:
				if(d2!=d3||d3!=d4)sp=true;
				if (turn == duoi)
					k = trai;
				else if (turn == phai)
					k = tren;
				else
					return 0;
				break;
			case 0b0111:
				if (turn == duoi)
					k = trai;
				else if (turn == phai)
					k = tren;
				else
					return 0;
				break;
			case 0b1010:
				sp=true;
				switch (turn) {
				case trai:
					k = duoi;
					break;
				case phai:
					k = tren;
					break;
				case tren:
					k = phai;
					break;
				case duoi:
					k = trai;
					break;
				}
				break;
			case 0b0101:
				sp=true;
				switch (turn) {
				case trai:
					k = tren;
					break;
				case phai:
					k = duoi;
					break;
				case tren:
					k = trai;
					break;
				case duoi:
					k = phai;
					break;
				}
				break;
			}
			return k;
		}

	
}
