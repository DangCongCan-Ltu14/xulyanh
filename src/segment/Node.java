package segment;

public class Node {
	private int color, sl = 0, cluster;

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getSl() {
		return sl;
	}

	public void addSl(int sl) {
		sl++;
	}

	public int getCluster() {
		return cluster;
	}

	public void setCluster(int cluster) {
		this.cluster = cluster;
	}

	public static int axthuan(int a) {
		int k = ((a & 0xf0) >> 4) | ((a & 0xf000) >> 8) | ((a & 0xf00000) >> 12);
		return k;
	}
	public static int axnguoc(int a) {
		int k = ((a & 0xf) << 4) | ((a & 0xf0) << 8) | ((a & 0xf00) << 12);
		return k;
	}
}
