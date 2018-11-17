package xla;

import java.util.Random;

import base.rbg;

public class t {
	rbg[] cent;
	int k = 20;

	public static void main(String[] args) {
		
		System.out.println(-191%2);
	}

	void test() {
		cent = new rbg[k + 1];
		kt();
		int i=0;
		for (rbg c : cent) {
			c.tpr(i);
			i++;
		}

		System.out.println(tam(cent[k]));
	}

	void kt() {
		int[][] means = new int[k + 1][4];
		for (int i = 0; i < k + 1; i++) {
			Random rand = new Random();
			means[i][0] = 255;
			means[i][1] = (int) (rand.nextDouble() * 255);
			means[i][2] = (int) (rand.nextDouble() * 255);
			means[i][3] = (int) (rand.nextDouble() * 255);
			cent[i] = new rbg(means[i]);

		}
		// System.out.println(x + " " + y);
	}

	int tam(rbg a) {
		System.out.println();
		int min = len(a, cent[0]);
		int p = 0;
		int c = 0;
		for (int i = 1; i < k; i++) {
			System.out.print(i+" ");
			c = len(a, cent[i]);
			if (c < min) {
				min = c;
				p = i;
			}
		}
		return p;
	}

	int len(rbg x, rbg y) {
		int a, b, c,d;
		a = x.getR() - y.getR();
		b = x.getB() - y.getB();
		c = x.getG() - y.getG();
		d=a * a + b * b + c * c;
		System.out.println(d);
		return d;
	}
}
