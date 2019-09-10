package base;

public class rbg {
	private int a, r, b, g;

	public int getA() {
		return a;
	}

	public int getR() {
		return r;
	}

	public int getB() {
		return b;
	}

	public int getG() {
		return g;
	}

	public rbg(int sl) {

		add(sl);

	}
	public rbg() {

		add(0);

	}
	public rbg(int la, int lr, int lb, int lg) {

		a = la;
		r = lr;
		g = lg;
		b = lb;

	}

	public rbg(int[] is) {
		// TODO Auto-generated constructor stub
		a = is[0];
		r = is[1];
		g = is[2];
		b = is[3];
	}

	public void reset() {
		// TODO Auto-generated method stub
		a = 255;
		r = 0;
		g = 0;
		b = 0;
	}
	public int tds(int s)
	{
		r=(r+s);
		g=(g+s);
		b=(b+s);
		if(r>255) r=255;
		if(b>255) b=255;
		if(g>255) g=255;
		return get();
	}
	public void add(int sl) {
		//a = a + ((sl & 0xff000000) >> 24);
		r = r + ((sl & 0xff0000) >> 16);
		b = b + ((sl & 0xff00) >> 8);
		g = g + (sl & 0xff);
		// System.out.println(r+" "+b+" "+g);
	}

	public void add(int sl, int k) {
		//a = a + ((sl & 0xff000000) >> 24) * k;
		r = r + ((sl & 0xff0000) >> 16) * k;
		b = b + ((sl & 0xff00) >> 8) * k;
		g = g + (sl & 0xff) * k;
		// System.out.println(r+" "+b+" "+g);
	}

	public int div(int d) {
		if (d == 0)
			return get();
		b = b / d;
		r = r / d;
		g = g / d;
		return get();
	}

	public int get() {
		int res = 0;
		res = (r << 16) | (b << 8) | g | (255 << 24);
		return res;
	}

	public void tpr(int i) {
		System.out.println(i + " " + a + " " + r + " " + b + " " + g + " ");
	}

	public static boolean cmp(rbg a, rbg b) {
		return (a.a == b.a) && (a.r == b.r) && (a.g == b.g) && (a.b == b.b);
	}
	public String tohex() {
		return String.format("#%02x%02x%02x", r,b,g).toUpperCase();
	}
	public static int getGray(int sl)
	{
		int r,g,b;
		r =  ((sl & 0xff0000) >> 16);
		b =  ((sl & 0xff00) >> 8);
		g =  (sl & 0xff);
		return (r+g+b)/3;
	}
	public static int pgray(int c)
	{
		rbg k=new rbg(255,c,c,c);
		return k.get();
	}
}
