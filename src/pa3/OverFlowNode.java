package pa3;

public class OverFlowNode extends LeafNode
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4221881330423156522L;
	NodeReference overflow;	//Reference to next overflow node! If null, this is the last overflow node in the list.
	
	public OverFlowNode(int id)
	{
		super(id);
	}
	
	public NodeReference getOverflowReference()
	{
		return overflow;
	}

	public boolean addTuple(Tuple t)
	{
		if(overflow == null)
		{
			for(int x = 0; x < tuples.length; x++)
			{
				if(tuples[x] == null)
				{
					tuples[x] = t;
					if(x == tuples.length - 1)
					{
						overflow = new NodeReference(RTree.getNewID());
						OverFlowNode ofn = new OverFlowNode(overflow.id);
						Node.writeNode(ofn, overflow);
					}
					Node.writeNode(this, new NodeReference(id));
					return true;
				}
			}
		}
		else
		{
			OverFlowNode ofn = (OverFlowNode)Node.ReadNode(overflow);
			ofn.addTuple(t);
			return true;
		}
		return true;
	}
}
