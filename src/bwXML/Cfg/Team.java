package bwXML.Cfg;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bwXML.main.XMLUtil;

public class Team {
	public String id;
	public Point teamSpawnInit;
	public Point teamSpawn;
	public ArrayList<Point> blockBedPos = new ArrayList();
	public String teamBlock;
	public int teammeta;
	public String color;
	public String shop_mobid;
	public String shop_gui;
	public boolean rndMeta;
	
	public void mapRestart()
	{
		rndMeta();
	}
	
	public void rndMeta() {
		if(this.rndMeta)
			this.teammeta = (int)(Math.random() * 16);	
	}

	public Team(NodeList li) {
		for(int i=0;i<li.getLength();i++)
		{
			Node n = li.item(i);
			String str = n.getNodeName();
			String c = n.getTextContent();
			if(str.equals("id"))
				this.id = c.replaceAll("\"", "");
			if(str.equals("teamInitialSpawn"))
			{
				c = c.replaceAll("\\[", "");
				c = c.replaceAll("\\]", "");
				c = c.replaceAll("\"", "");
				c = c.replaceAll("\\s+", "");//to whitespaced
				String[] parts = c.split(",");
				double x = Double.parseDouble(parts[0]);
				double y = Double.parseDouble(parts[1]);
				double z = Double.parseDouble(parts[2]);
				teamSpawnInit = new Point(x,y,z);
			}
			if(str.equals("teamSpawn"))
			{
				c = c.replaceAll("\\[", "");
				c = c.replaceAll("\\]", "");
				c = c.replaceAll("\"", "");
				c = c.replaceAll("\\s+", "");//to whitespaced
				String[] parts = c.split(",");
	
				double x = Double.parseDouble(parts[0]);
				double y = Double.parseDouble(parts[1]);
				double z = Double.parseDouble(parts[2]);
				teamSpawn = new Point(x,y,z);
			}
			if(str.equals("blockbedPos"))
			{
				c = c.replaceAll("\\[", "");
				c = c.replaceAll("\\]", "");
				c = c.replaceAll("\\s+", "");//to whitespaced
				String[] poses = c.split("\"");
				for(String s : poses)
				{
					if(s.equals(",") || s.equals(""))
						continue;
					String[] parts = s.split(",");
					double x = Double.parseDouble(parts[0]);
					double y = Double.parseDouble(parts[1]);
					double z = Double.parseDouble(parts[2]);
					blockBedPos.add(new Point(x,y,z) );
				}
			}
			if(str.equals("teamblock"))
				this.teamBlock = c.replaceAll("\"", "");
			if(str.equals("teammeta"))
			{
				if(c.equals("rnd"))
				{
					c = "" + (int)(Math.random() * 16);
					this.rndMeta = true;
				}
				this.teammeta = Integer.parseInt(c);
			}
			if(str.equals("color"))
				this.color = c.replaceAll("\"", "");
			if(str.equals("Shop"))
			{
				Element e = (Element)n;
				this.shop_gui = e.getElementsByTagName("gui").item(0).getTextContent().replaceAll("\"", "");
				this.shop_mobid = e.getElementsByTagName("MobClickedUpon").item(0).getTextContent().replaceAll("\"", "");
			}
		}
	}
	
	@Override
	public String toString()
	{
		String points = "";
		for(Point p : blockBedPos)
			points += "\"" + p + "\", ";
		return "id:" + this.id + " InitSpawn:" + this.teamSpawnInit + " Spawn:" + this.teamSpawn + "\nPoints:[" + points + "]\n" + 
					"TeamBlock:" + this.teamBlock + " teamMeta:" + this.teammeta + " color:" + this.color + " shop_mobId:" + this.shop_mobid + " shop_gui:" + this.shop_gui;
	}
	
	public static Team getTeam(BWMap map,String id){
		for(Team t : map.teams)
			if(id.equals(t.id))
				return t;
		return null;
	}
	public static Team getTeam(ArrayList<Team> teams,String id){
		for(Team t : teams)
			if(id.equals(t.id))
				return t;
		return null;
	}
	

}
