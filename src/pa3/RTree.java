package pa3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class RTree
{
	final private static int BITS_PER_DIM = 16;
	static PriorityQueue<Tuple> hilbertValues;
	private static int currentID;
	public static void main(String[] args) throws FileNotFoundException
	{
		hilbertValues = new PriorityQueue<Tuple>();
		Scanner sc = new Scanner(new File("project3dataset30K-1.txt"));
		sc.useDelimiter(",\\s*|\\s+");
		while(sc.hasNext())
		{
			int x = sc.nextInt();
			int y = sc.nextInt();
			hilbertValues.add(new Tuple(x, y, getHilbertValue(x, y)));
		}
		currentID = -1;
		System.out.println("Done sorting!");
		System.out.println(hilbertValues.size());
		sc.close();
		bulkLoad();
	}
	
	public static void bulkLoad()
	{
		ArrayList<NodeReference> leaves = new ArrayList<NodeReference>(30000);	//Giant array that will be used for next level up.
		while(hilbertValues.size() > 0)
		{
			int id = RTree.getNewID();
			leaves.add(new NodeReference(id));
			LeafNode lf = new LeafNode(id);
			while(!lf.isFull())
			{
				if(hilbertValues.size() == 0)
					break;
				lf.addTuple(hilbertValues.poll());
			}
			Node.writeNode(lf, leaves.get(leaves.size() - 1));
		}
		System.out.println("Leaf references: "+leaves.size());
	}
	
	public static long getHilbertValue(int x1, int x2) {
		long res = 0;
		
		for (int ix = BITS_PER_DIM - 1; ix >= 0; ix--) {
			long h = 0;
			long b1 = (x1 & (1 << ix)) >> ix;
			long b2 = (x2 & (1 << ix)) >> ix;
			
			if (b1 == 0 && b2 == 0) {
				h = 0;
			} else if (b1 == 0 && b2 == 1) {
				h = 1;
			} else if (b1 == 1 && b2 == 0) {
				h = 3;
			} else if (b1 == 1 && b2 == 1) {
				h = 2;
			}
			res += h << (2 * ix);
		}
		return res;
	}
	
	public static int getNewID()
	{
		currentID++;
		return currentID;
	}
}
