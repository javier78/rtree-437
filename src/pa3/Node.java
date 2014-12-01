package pa3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Node implements Serializable
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6812172449270115395L;
	int id;
	transient static int readCount = 0;
	transient static int writeCount = 0;
	public Node(int id)
	{
		this.id = id;
	}

	public static void writeNode(Node n, NodeReference nr)
	{
		try {
			FileOutputStream fos = new FileOutputStream("db/" + nr.id + ".ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(n);
			oos.close();
			fos.close();
			writeCount++;
		} catch (FileNotFoundException e) {
			System.out.println("Can't write to file!");
			e.printStackTrace();
		}
		catch (IOException ioe)
		{
			System.out.println("Can't write object to file!");
			ioe.printStackTrace();
		}
	}
	
	public static Node ReadNode(NodeReference nr)
	{
		Node read = null;
		try {
			FileInputStream fis = new FileInputStream("db/" + nr.id +".ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			read = (Node)ois.readObject();
			ois.close();
			readCount++;
		} catch (FileNotFoundException e) 
		{
			System.out.println("Couldn't find node file!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Couldn't open object input stream!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return read;
	}
	
	public static void resetCount()
	{
		readCount = 0;
		writeCount = 0;
	}
}
