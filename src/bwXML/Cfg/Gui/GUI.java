package bwXML.Cfg.Gui;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GUI {
	
	public String id;
	public String name;
	public int rows = 3;
	public ArrayList<Item> items = new ArrayList();
	
	public GUI(NodeList li) {
		for(int i=0;i<li.getLength();i++)
		{
			Node n = li.item(i);
			String str = n.getNodeName();
			String c = n.getTextContent();
			if(str.equals("Item"))
				items.add(new Item(n.getChildNodes()));
			if(str.equals("id"))
				this.id = c.replaceAll("\"", "");
			if(str.equals("name"))
				this.name = c.replaceAll("\"", "");
			if(str.equals("rows"))
				this.rows = Integer.parseInt(c);
		}
	}
	/**
	 * parse gui with mapName info
	 */
	public GUI(NodeList li,String mapName) {
		for(int i=0;i<li.getLength();i++)
		{
			Node n = li.item(i);
			String str = n.getNodeName();
			String c = n.getTextContent();
			if(str.equals("Item"))
				items.add(new Item(n.getChildNodes()));
			if(str.equals("id"))
				this.id = c.replaceAll("\"", "");
			if(str.equals("name"))
				this.name = c.replaceAll("\"", "");
			if(str.equals("rows"))
				this.rows = Integer.parseInt(c);
			if(str.equals("ColorSlot"))
			{
				ColorSlot slot = new ColorSlot(n.getChildNodes(), mapName );
				items.add(slot);
			}
		}
	}
	@Override
	public String toString()
	{
		String s = "";
		for(Item i : items)
			s += "\n" + i + "\n ";
		return "id:" + this.id + " name:" + this.name + " rows:" + this.rows + "\nItems:[" + s + "]\n";
	}

}
