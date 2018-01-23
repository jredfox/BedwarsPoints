package bwXML.Cfg;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ingot {
	public String name;
	public int meta;
	public String nbt;
	public int pointValue = 0;
	
	public Ingot(NodeList li) {
		for(int i=0;i<li.getLength();i++)
		{
			Node n = li.item(i);
			String str = n.getNodeName();
			String content = n.getTextContent();
			if(str.equals("name"))
				this.name = content.replaceAll("\"", "");
			else if(str.equals("meta"))
				this.meta = Integer.parseInt(content);
			else if(str.equals("nbt"))
				this.nbt = content;
			else if(str.equals("pointValue"))
				this.pointValue = Integer.parseInt(content);
		}
	}
	@Override
	public String toString()
	{
		return "IngotName: " + this.name + " meta:" + this.meta + " nbt:" + this.nbt + " pointValue:" + this.pointValue;
	}
	

}
