package bwXML.Cfg.Gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bwXML.main.XMLUtil;

public class Item {
	
	public String name = null;
	public int meta = -1;
	public String nbt = null;
	public int slot = -1;
	public String click_gui = null;
	public int price = -1;
	public HashMap<Integer,String> lores = new HashMap();
	protected String display_Name = "";
	protected String display_NameInit = "";
	public int stacksize = 1;
	public boolean rndMeta = false;
	
	public String getDisplayName(){return this.display_Name;}
	
	@Override
	public String toString()
	{
		String s = "";
		Iterator<Integer> it = lores.keySet().iterator();
		while(it.hasNext())
		{
			Integer key = it.next();
			String value = lores.get(key);
			s += "\"key:" + key + ", value:" + value + "\", ";
		}
		return "name:" + this.name + " meta:" + this.meta + " nbt:" + this.nbt + " slot_index:" + this.slot + " gui:" + this.click_gui + "\nPrice:" + 
				this.price + " Lore:[" + s + "]" + " display_name:" + this.display_Name + " stackSize:" + this.stacksize;
	}
	
	public Item(NodeList li) {
		for(int i=0;i<li.getLength();i++)
		{
			Node n = li.item(i);
			if(n.getNodeType() == Node.TEXT_NODE || n.getNodeType() == Node.COMMENT_NODE)
				continue;
			String str = n.getNodeName();
			String c = n.getTextContent();
			c = c.replaceAll("\"", "");
			if(str.equals("name"))
				this.name = c;
			if(str.equals("meta"))
			{
				if(c.equals("rnd"))
				{
					c = "" + (int)(Math.random() * 16);
					this.rndMeta = true;
				}
				this.meta = Integer.parseInt(c);
			}
			
			if(str.equals("nbt"))
				this.nbt = c;
			if(str.equals("slot"))
			{
				String[] parts = c.split(",");
				this.slot = (Integer.parseInt(parts[0])) + (9*Integer.parseInt(parts[1]));
			}
			if(str.equals("click"))
				this.click_gui = c;
			if(str.equals("meta"))
				this.meta = Integer.parseInt(c);
			if(str.equals("price"))
				this.price = Integer.parseInt(c);
			if(str.equals("stacksize"))
				this.stacksize = Integer.parseInt(c);
			if(str.equals("Name"))
			{
				this.display_Name = c;
				display_NameInit = c;
			}
			int extension = XMLUtil.getIntExtension(str);
			if(extension != -1)
				str = str.split("_")[0];
			
			if(str.equals("lore"))
				lores.put(extension, c);
		}
	}
	public void upDateDisplayName()
	{
		
	}
	public void upDateLore()
	{
		
	}
}
