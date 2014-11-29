package pa3;

public class IndexNode extends Node 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4356587948255398892L;
	NodeReference[] refs;
	Rectangle[] mbr;
	private final int MAX_ENTRIES = 204;	//Found this value by summing the size of all fields in an index entry and multiplying each size by 2d, where d is the order of the tree.
	//size of a NodeReference = 4 bytes, size of a Rectangle is 16 bytes (contains 4 integers). Figure out how many entries also fit!!
	//So: 2d * (4 * 4) + 2d * (4) <= 4096
	//==>32d + 8d <= 4096
	//==>40d <= 4096
	//==>d <= floor(102.4) <= 2d
	//2d = 204
	public IndexNode()
	{
		refs = new NodeReference[MAX_ENTRIES];
	}
}
