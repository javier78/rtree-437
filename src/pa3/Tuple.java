package pa3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
	final String desc;

	public Tuple(int x, int y, long hilbert)
	{
		this.x = x;
		this.y = y;
		this.hilbert = hilbert;
		//this.desc = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		desc = "";
	}

	public int compareTo(Tuple o)
	{
		return (int)(hilbert - o.hilbert);
	}
	/*
	public void writeTuple(Tuple t, NodeReference tr)
	{
		try {
			FileOutputStream fos = new FileOutputStream("db/" + tr.id + ".ser");
			ObjectOutputStream ois = new ObjectOutputStream(fos);
			ois.writeObject(t);
			ois.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't write to file!");
			e.printStackTrace();
		}
		catch (IOException ioe)
		{
			System.out.println("Can't write object to file!");
			ioe.printStackTrace();
		}
		
	}
	*/
	
	
}
