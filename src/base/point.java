package base;

public class point {
	private int x, y;

	public point(int a, int b) {
		x = a;
		y = b;
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}
	public static boolean cmp(point a,point b)
	{
		if(a.x==b.x&&a.y==b.y) return true;
		return false;
	}
	public void print()
	{
		System.out.println("x:"+x+" y:"+y);
	}
	public static boolean cmpl(point a,point b)
	{
		if(a.x<b.x) return true;
		if(a.x>b.x) return false;
		if(a.y<b.y) return true;
		return false;
	}
	public int kc()
	{
		return x+y;
	}
	
}
