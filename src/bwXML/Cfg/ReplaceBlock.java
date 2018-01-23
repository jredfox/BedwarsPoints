package bwXML.Cfg;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReplaceBlock {
	public String name;
	public int meta;
	public int radius;
	
	public ReplaceBlock(NodeList li) {
		for(int i=0;i<li.getLength();i++)
		{
			Node n = li.item(i);
			String str = n.getNodeName();
			if(str.equals("name"))
				this.name = n.getTextContent().replaceAll("\"", "");
			else if(str.equals("meta"))
				this.meta = Integer.parseInt(n.getTextContent());
			else if(str.equals("radius"))
				this.radius = Integer.parseInt(n.getTextContent());
		}
	}
	@Override
	public String toString(){return "name:" + name + " meta:" + meta + " radius:" + radius;}

}
