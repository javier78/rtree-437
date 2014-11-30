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
	
	public static Node ReadNode(NodeReference nr)
	{
		Node read = null;
		try {
			FileInputStream fis = new FileInputStream("db/" + nr.id +".ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			read = (Node)ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Couldn't find node file!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't open object input stream!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return read;
	}
}
