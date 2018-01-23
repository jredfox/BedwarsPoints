package bwXML.Cfg;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Special {
	public int slimeTicks;
	public boolean slimeInfinate;
	public int staircase_up;
	public int staircase_down;
	public Trap trap;
	
	public Special(NodeList li) {
		for(int i=0;i<li.getLength();i++)
		{
			Node n = li.item(i);
			if(n.getNodeType() == Node.TEXT_NODE || n.getNodeType() == Node.COMMENT_NODE)
				continue;
			String str = n.getNodeName();
			String content = n.getTextContent();
			if(str.equals("slimeTicks"))
				this.slimeTicks = Integer.parseInt(content);
			else if(str.equals("slimeInfinate"))
				this.slimeInfinate = Boolean.parseBoolean(content);
			else if(str.equals("staircase_up"))
				this.staircase_up = Integer.parseInt(content);
			else if(str.equals("staircase_down"))
				this.staircase_down = Integer.parseInt(content);
			else if(str.equals("String"))
				this.trap = new Trap(n.getChildNodes());
			
		}
	}
	@Override
	public String toString()
	{
		return "Slime:" + this.slimeTicks + " Slimeinfinate:" + slimeInfinate + " staircase_up:" + staircase_up + "staircase_down:" + this.staircase_down + 
				"\nTrap: " + this.trap;
	}
}
