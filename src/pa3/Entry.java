package pa3;

import java.io.Serializable;

public class Entry implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1864185831524829527L;
	Rectangle mbr = new Rectangle(0,0,0,0);
	Object[] containedDataRegions; //TODO: Calculate correct number of rectangles!!! Don't store this, get the array from another class.
	NodeReference child;
	
	public Rectangle calculateMBR()
	{
		containedDataRegions = new Tuple[2];
		int minX = Integer.MAX_VALUE;
		int maxX = 0;
		int minY = Integer.MAX_VALUE;
		int maxY = 0;
		for(Rectangle r : rectangles)
		{
			minX = r.getX1() < minX ? r.getX1() : minX;
			minX = r.getX2() < minX ? r.getX2() : minX;
			minY = r.getY1() < minY ? r.getY1() : minY;
			minY = r.getY2() < minY ? r.getY2() : minY;
			
			maxX = r.getX1() > maxX ? r.getX1() : maxX;
			maxX = r.getX2() > maxX ? r.getX2() : maxX;
			maxY = r.getY1() > maxY ? r.getY1() : maxY;
			maxY = r.getY2() > maxY ? r.getY2() : maxY;
		}
		
		mbr = new Rectangle(minX, minY, maxX, maxY);
		return mbr;
	}
	
	public Rectangle getMBR()
	{
		return mbr;
	}
}
