package pa3;

public class LeafNode extends Node 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7112258851814916861L;
	Tuple[] tuples;
	private final int MAX_ENTRIES = 7;	//derived this number by taking every field in a tuple into account, and making sure that it doesn't go above the page size (4096 bytes).
	//Each tuple contains two 4-byte ints: x and y. Each tuple contains a hilbert value of type long (8 bytes), and a 500-byte string.
	//Let d be the order of the tree: 2d(2*4) + 2d(8) + 2d(500) <= 4096
	//==>516 * 2d <= 4096
	//==>2d <= 7.938
	//Taking the floor, the max tuples in a node is 7.
	public LeafNode()
	{
		tuples = new Tuple[MAX_ENTRIES];
		
	}
}
