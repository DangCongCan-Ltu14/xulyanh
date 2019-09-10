package histogram;

import base.point;

public class Bac1 implements Line {
	double x1, x2, y1, y2;
	double t = 0;

	public Bac1(point dau, point cuoi) {
		x2 = dau.getx();
		y2 = dau.gety();
		x1 = cuoi.getx() - x2;
		y1 = cuoi.gety() - y2;

	}

	public Bac1(int anpha) {
		x2 = 0;
		y2 = 0;
		x1 = 255;
		y1 = (int) (255 * Math.tan((anpha / 180.0) * Math.PI));
		System.out.println("y1 =" + y1);
	}

	public Bac1(int min, int max) {
		x2 = min;
		y2 = 0;
		x1 = max - x2;
		y1 = 255;

	}

	public int newValue(int a) {
		if (x1 != 0) {
			t = (a - x2) / x1;

			t = (y1 * t + y2);
			// System.out.println(t);
			int c = (int) t;
			if (c < 0)
				c = 0;
			if (c > 255)
				c = 255;
			return (int) c;
		}
		return a;
	}

	@Override
	public point newValue(point a) {
		// TODO Auto-generated method stub
		return null;
	}

}
