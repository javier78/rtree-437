package pa3;

import java.io.Serializable;

public abstract class Entry implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1864185831524829527L;
	Rectangle mbr = new Rectangle(0,0,0,0);
	Tuple[] tuples = new Tuple[10]; //TODO: Calculate correct number of tuples!!!
	
	public abstract Rectangle calculateMBR();
	
	public Rectangle getMBR()
	{
		return mbr;
	}
}
