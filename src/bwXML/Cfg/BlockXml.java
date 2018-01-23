package bwXML.Cfg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bwXML.main.XMLUtil;

public class BlockXml {
	public String name;
	public int meta;
	public boolean hasUpgrades;
	public String facing;//up,down,north,south,east,west
	
	public ArrayList<Point> pos = new ArrayList();
	public HashMap<Integer,Integer> teirs_ticks = new HashMap();//tier,ticks
	public HashMap<Integer,Integer> teirs_stacksize = new HashMap();//tier, stacksize
	
	public String ingot;
	public int ingot_meta;
	public String ingot_nbt;
	
	public BlockXml(NodeList li) {
		for(int i=0;i<li.getLength();i++)
		{
			Node n = li.item(i);
			if(n.getNodeType() == Node.TEXT_NODE || n.getNodeType() == Node.COMMENT_NODE)
				continue;
			String str = n.getNodeName();
			String content = n.getTextContent();
			if(str.equals("name"))
				this.name = content.replaceAll("\"", "");
			
			else if(str.equals("meta"))
				this.meta = Integer.parseInt(content);
			else if(str.equals("ingot"))
				this.ingot = content;
			else if(str.equals("ingot_meta"))
				this.ingot_meta = Integer.parseInt(content);
			else if(str.equals("ingot_nbt"))
				this.ingot_nbt = content;
			else if(str.equals("hasUpgrades"))
				this.hasUpgrades = Boolean.parseBoolean(content);
			else if(str.equals("meta"))
				this.meta = Integer.parseInt(content);
			else if(str.equals("facing"))
			{
				this.facing = content.replaceAll("\"", "");
			}
			
			if(str.equals("pos"))
			{
				content = content.replaceAll("\\[", "");
				content = content.replaceAll("\\]", "");
				content = content.replaceAll("\\s+", "");//to whitespaced
				String[] poses = content.split("\"");
				if(!content.contains("\""))
					poses = new String[]{content};
				
				for(String s : poses)
				{
					if(s.equals(",") || s.equals(""))
						continue;
					String[] parts = s.split(",");
					double x = Double.parseDouble(parts[0]);
					double y = Double.parseDouble(parts[1]);
					double z = Double.parseDouble(parts[2]);
					pos.add(new Point(x,y,z) );
				}
			}
			int tier = XMLUtil.getIntExtension(str);
			if(tier > -1)
				str = str.split("_")[0];
			if(str.equals("ticksTier"))
				teirs_ticks.put(tier, Integer.parseInt(content));
			if(str.equals("amountTier"))
				teirs_stacksize.put(tier, Integer.parseInt(content));
		}
	}
	/**
	 * Massive String prepair to read a paragraph
	 */
	@Override
	public String toString()
	{
		String pos = "";
		String ticks = "";
		String stack = "";
		for(Point p : this.pos)
			pos += "\"" + p + "\",";
		Iterator<Integer> it1 = (Iterator<Integer>) teirs_ticks.keySet().iterator();
		Iterator<Integer> it2 = (Iterator<Integer>) teirs_stacksize.keySet().iterator();
		while(it1.hasNext())
		{
			Integer key = it1.next();
			Integer value = teirs_ticks.get(key);
			ticks += "\"tier: " + key + ",ticks:" + value + "\" "; 
		}
		while(it2.hasNext())
		{
			Integer key = it2.next();
			Integer value = teirs_stacksize.get(key);
			stack += "\"tier: " + key + ",stacksize:" + value + "\", "; 
		}
		return "name:" + this.name + " meta:" + this.meta + " hasUpgrades:" + this.hasUpgrades + " facing:" + this.facing + "\nPos:[" +
				pos + "] " + " ingot:" + this.ingot + " ingot_meta:" + this.ingot_meta + " ingot_nbt:" + this.ingot_nbt + 
		"\ntier_ticks:[" + ticks + "]\ntier_stack:[" + stack + "]";
	}
	

}
