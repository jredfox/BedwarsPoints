package bwXML.Cfg;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MobHolder {
	public ArrayList<Mob> mobs = new ArrayList();
	
	public MobHolder(NodeList li)
	{
		for(int i=0;i<li.getLength();i++)
		{
			Node n = li.item(i);
			if(n.getNodeType() == Node.TEXT_NODE || n.getNodeType() == Node.COMMENT_NODE)
				continue;
			String str = n.getNodeName();
			String content = n.getTextContent();
			if(str.equals("Mob"))
				mobs.add(new Mob(n.getChildNodes() ));
		}
	}
	@Override
	public String toString()
	{
		String s = "";
		for(Mob b : mobs)
			s += "Mob:" + b + "\n";
		return s;
	}

}
