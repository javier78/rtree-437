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
	static IndexNode root;
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
		System.out.println(hilbertValues.size());
		sc.close();
		bulkLoad();
		//System.out.println(pointSearch(2563,8967, root).size());
		System.out.println(regionSearch(new Rectangle(5649,7415,5649,7415), root).size());
	}
	
	public static void bulkLoad()
	{
		Queue<NodeReference> childrenToBe = new LinkedList<NodeReference>();
		while(!hilbertValues.isEmpty())
		{
			int id = RTree.getNewID();
			NodeReference nr = new NodeReference(id);
			childrenToBe.add(nr);
			LeafNode lf = new LeafNode(id);
			while(!lf.isFull())
			{
				if(hilbertValues.isEmpty())
					break;
				lf.addTuple(hilbertValues.poll());
			}
			Node.writeNode(lf, nr);
		}
		//Leaf nodes now generated, start generating index nodes.
		boolean complete = false;
		while(!complete)
		{
			Queue<NodeReference> currentLevel = new LinkedList<NodeReference>();	//This should actually be childrenToBe, maybe reassign childrenToBe to this when it's empty!
			while(!childrenToBe.isEmpty())
			{
				int id = RTree.getNewID();
				NodeReference nr = new NodeReference(id);
				currentLevel.add(nr);
				IndexNode in = new IndexNode(id);
				while(!in.isFull())
				{
					if(childrenToBe.isEmpty())
					{
						if(currentLevel.size() == 1)
							complete = true;	//This is only set when a node that is currently being filled (but isn't full yet) and the children list is empty and there is only one node in the current level. This isn't the only place we should be checking.
						break;
					}
					//Next line takes care of entry creation and calculates the MBR for this node.
					in.addIndex(Node.ReadNode(childrenToBe.poll()));	//During this time, in could've become full, AND childrenToBe could be empty. Next conditional accounts for this.
				}
				Node.writeNode(in, nr);
				if(currentLevel.size() == 1 && childrenToBe.isEmpty())	//This means that only one node has been written in this level. That would be the root.
					complete = true;
			}
			childrenToBe = currentLevel;
			System.out.println("Size of childrenToBe: "+childrenToBe.size());
		}
		System.out.println("Done loading nodes!");
		IndexNode root = (IndexNode) Node.ReadNode(childrenToBe.poll());
		System.out.println(root.isFull());
		RTree.root = root;
	}
	
	public static ArrayList<Tuple> pointSearch(int x, int y, Node toSearch)
	{
		ArrayList<Tuple> resultSet = new ArrayList<Tuple>();
		if(toSearch instanceof IndexNode)
		{
			IndexNode in = (IndexNode) toSearch;
			
			for(Entry e : in.entries)
			{
				if(e == null)
					break;
				if(e.mbr.containsPoint(x, y))
				{
					resultSet.addAll(pointSearch(x, y, Node.ReadNode(e.child)));	//Overlapping boxes can be a huge problem. We need a way to distinguish whether the point has been added or not.
				}
			}
		}
		
		else if(toSearch instanceof LeafNode)
		{
			LeafNode ln = (LeafNode) toSearch;
			
			for(Tuple t : ln.tuples)
			{
				if(t == null)
					break;
				if(t.x == x && t.y == y)
				{
					resultSet.add(t);
					if(t.overflow != null)
						resultSet.addAll(t.getAllOverFlowTuples());
				}
			}
		}
		
		return resultSet;
	}
	
	public static ArrayList<Tuple> regionSearch(Rectangle searchBox, Node toSearch)
	{
		ArrayList<Tuple> resultSet = new ArrayList<Tuple>();
		if(toSearch instanceof IndexNode)
		{
			IndexNode in = (IndexNode) toSearch;
			
			for(Entry e : in.entries)
			{
				if(e == null)
					break;
				if(e.mbr.intersects(searchBox))
				{
					resultSet.addAll(regionSearch(searchBox, Node.ReadNode(e.child)));
				}
			}
		}
		else if(toSearch instanceof LeafNode)
		{
			LeafNode ln = (LeafNode) toSearch;
			
			for(Tuple t : ln.tuples)
			{
				if(t == null)
					break;
				if(searchBox.containsPoint(t.x, t.y))
				{
					resultSet.add(t);
					if(t.overflow != null)
						resultSet.addAll(t.getAllOverFlowTuples());
				}
			}
		}
		return resultSet;
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
