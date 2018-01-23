package bwXML.Cfg;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Potion {
	
	public String name;
	public int value;
	public int ticks;
	
	public Potion(NodeList li) {
		for(int i=0;i<li.getLength();i++)
		{
			Node n = li.item(i);
			if(n.getNodeType() == Node.TEXT_NODE || n.getNodeType() == Node.COMMENT_NODE)
				continue;
			String str = n.getNodeName();
			String content = n.getTextContent();
			if(str.equals("name"))
				this.name = content;
			if(str.equals("value"))
				this.value = Integer.parseInt(content);
			if(str.equals("ticks"))
				this.ticks = Integer.parseInt(content);
		}
	}
	@Override
	public String toString()
	{
		return "name:" + this.name + " value:" + this.value + " ticks:" + this.ticks;
	}
}
