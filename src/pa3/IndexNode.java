package pa3;

public class IndexNode extends Node 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4356587948255398892L;
	//NodeReference[] refs;
	Entry[] entries;	//Can access NodeReferences from here, as well as all MBRs, no need to store them again.
	
	final transient int MAX_ENTRIES = 204;	//Found this value by summing the size of all fields in an index entry and multiplying each size by 2d, where d is the order of the tree.
	//size of a NodeReference = 4 bytes, size of a Rectangle is 16 bytes (contains 4 integers). Figure out how many entries also fit!!
	//So: 2d * (4 * 4) + 2d * (4) <= 4096
	//==>32d + 8d <= 4096
	//==>40d <= 4096
	//==>d <= floor(102.4) <= 2d
	//2d = 204
	public IndexNode(int id)
	{
		super(id);
		entries = new Entry[MAX_ENTRIES];
	}
	
	public boolean addIndex(Node child)
	{
		 for(int x = 0; x < entries.length; x++)
		 {
			 if(entries[x] == null)
			 {
				 entries[x] = new Entry(new NodeReference(child.id));
				 entries[x].calculateMBR(child);
				 return true;
			 }
		 }
		 return false;
	}
	
	public Rectangle[] getRectangles()
	{
		Rectangle[] r = new Rectangle[entries.length];	//TODO: This assumes that all entries have rectangles. This is not always the case.
		for(int x = 0; x < entries.length; x++)
		{
			r[x] = entries[x].mbr;
		}
		return r;
	}
	
	public boolean isFull()
	{
		for(int x = 0; x < entries.length; x++)
		{
			if(entries[x] == null)
				return false;
		}
		return true;
	}
}
