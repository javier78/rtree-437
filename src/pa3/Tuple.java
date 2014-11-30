package pa3;

import java.io.Serializable;

public class Tuple implements Comparable<Tuple>, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8398170828309407041L;
	final int x;
	final int y;
	final long hilbert;
	NodeReference overflow;
	final String desc;

	public Tuple(int x, int y, long hilbert)
	{
		this.x = x;
		this.y = y;
		this.hilbert = hilbert;
		this.desc = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		//desc = "";
	}

	public int compareTo(Tuple o)
	{
		return (int)(hilbert - o.hilbert);
	}
	
	public boolean equals(Object o)
	{
		if(!(o instanceof Tuple))
			return false;
		
		Tuple t = (Tuple) o;
		
		return t.x == this.x && t.y == this.y;
	}
}
