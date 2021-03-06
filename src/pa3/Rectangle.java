package pa3;

import java.io.Serializable;

public class Rectangle implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4559993740503751530L;
	int x1, x2, y1, y2;
	
	public Rectangle(int x1, int y1, int x2, int y2)
	{
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	public boolean containsPoint(int x, int y)
	{
		return x1 <= x && x <= x2 && y1 <= y && y <= y2;
	}
	
	public boolean intersects(Rectangle r)
	{
		java.awt.Rectangle rect1 = new java.awt.Rectangle(r.x1, r.y1, r.x2, r.y2);
		java.awt.Rectangle rect2 = new java.awt.Rectangle(this.x1, this.y1, this.x2, this.y2);
		return rect1.intersects(rect2);
	}

}
