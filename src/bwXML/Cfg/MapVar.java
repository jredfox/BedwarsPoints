package bwXML.Cfg;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MapVar {
	private String resourceMessege = "\u273D `int` resources \u273D";
	public String worldId = "worldId";
	public String worldName = "name";
	
	public boolean daytimeVoting = true;
	public boolean weatherVoting = true;
	public boolean tierUpgrades = false;
	public boolean hasVMobs = true;

	public MapVar(NodeList li){
		for(int i=0;i<li.getLength();i++)
		{
			Node n = li.item(i);
			if(n.getNodeName().equals("worldId"))
				this.worldId = n.getTextContent().replaceAll("\"", "");
			
			else if(n.getNodeName().equals("worldName"))
				this.worldName = n.getTextContent().replaceAll("\"","");
			
			else if(n.getNodeName().equals("tierUpgrades"))
				this.tierUpgrades = Boolean.parseBoolean(n.getTextContent());
			
			else if(n.getNodeName().equals("daytimeVoting"))
				this.daytimeVoting = Boolean.parseBoolean(n.getTextContent());
			
			else if(n.getNodeName().equals("weatherVoting"))
				this.weatherVoting = Boolean.parseBoolean(n.getTextContent());
			
			else if(n.getNodeName().equals("resourceMessege"))
				this.resourceMessege = n.getTextContent();
			
			else if(n.getNodeName().equals("hasVMobs"))
				hasVMobs = Boolean.parseBoolean(n.getTextContent());
		}
	}
	
	public void setResourceMessege(String s){this.resourceMessege = s;}
	
	public String getResourceMessege(int resource)
	{
		return resourceMessege.replaceAll("`int`", "" + resource);
	}
	@Override
	public String toString(){return "id:" + this.worldId + " Name:" + this.worldName + " resourceMsg: " + this.resourceMessege + "\n" 
			+ "DayVote:" + this.daytimeVoting + " RainVoting:" + this.weatherVoting + " Upgrades:" + this.tierUpgrades + " hasVMobs:" + this.hasVMobs;}

}
