package bwXML.Cfg;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Points {
	public ArrayList<Ingot> ingots = new ArrayList();
	
	public Points(NodeList li) {
		for(int i=0;i<li.getLength();i++)
		{
			Node n = li.item(i);
			String str = n.getNodeName();
			String content = n.getTextContent();
			if(str.equals("Ingot"))
				ingots.add(new Ingot(n.getChildNodes()) );
		}
	}
	@Override
	public String toString()
	{
		String s = "";
		for(Ingot i : ingots)
			s += i.toString() + "\n";
		return s;
	}

}
