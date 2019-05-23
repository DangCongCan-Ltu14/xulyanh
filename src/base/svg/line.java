package base.svg;

import java.awt.Polygon;

import base.point;

public interface line {
	public void add(point a);

	public int getColor();
	public void setCor(int c);
	public Polygon topoly();
}
