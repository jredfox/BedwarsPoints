package bwXML.Cfg;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Trap {
	public ArrayList<Potion> potions = new ArrayList();
	public int fuse = 0;
	public int power = 0;
	
	public Trap(NodeList li) {
		for(int i=0;i<li.getLength();i++)
		{
			Node n = li.item(i);
			if(n.getNodeType() == Node.TEXT_NODE || n.getNodeType() == Node.COMMENT_NODE)
				continue;
			String str = n.getNodeName();
			String content = n.getTextContent();
			if(str.equals("fuse"))
				this.fuse = Integer.parseInt(content);
			else if(str.equals("power"))
				this.power = Integer.parseInt(content);
			else if(str.equals("potion"))
				potions.add(new Potion(n.getChildNodes() ) );
		}
	}
	@Override
	public String toString()
	{
		String s = "";
		for(Potion p : potions)
			s += p.toString() + ",";
		return "fuse:" + this.fuse + " power:" + this.power + " Potions:[" + s + "]";
	}

}
