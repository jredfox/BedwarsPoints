package bwXML.Cfg.Gui;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bwXML.Cfg.Team;

public class GUI {
	
	public String id;
	public String name;
	public int rows = 3;
	public ArrayList<Item> items = new ArrayList();
	
	/**
	 * use if and only if gu isn't the team selector
	 */
	public void guiOpen()
	{
		guiOpen(null);
	}
	/**
	 * pass null if gui isn't a team selector
	 * @param teams
	 */
	public void guiOpen(ArrayList<Team> teams)
	{
		for(Item i : items)
		{
			if(i.rndMeta)
				i.meta = (int)(Math.random() * 16);
			if(i instanceof ColorSlot && teams != null)
			{
				ColorSlot slot = (ColorSlot) i;
				slot.upDateWool(teams);
			}
		}
	}
	
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
	public GUI(NodeList li,ArrayList<Team> teams) {
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
				ColorSlot slot = new ColorSlot(n.getChildNodes(), teams );
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
