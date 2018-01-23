package bwXML.Cfg.Gui;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bwXML.Cfg.BWMap;
import bwXML.Cfg.Team;
import bwXML.Cfg.Interfaces.Registry;
import bwXML.main.XMLUtil;

public class ColorSlot extends Item{
	
	public String team;
	public String style;//rnd for random team and colored if boolean,voting for voting for the slot,and fixed for a fixed team
	public boolean nonColorVoting;
	public boolean vote;
	public BWMap bwMap;
	
	public ColorSlot(NodeList li,String bwMap) {
		super(li);
		this.bwMap = Registry.getMap(bwMap);
		for(int i=0;i<li.getLength();i++)
		{
			Node n = li.item(i);
			String str = n.getNodeName();
			String c = n.getTextContent();
			c.replaceAll("\"", "");
			if(str.equals("style"))
			{
				this.style = c.replaceAll("\"", "");
				this.vote = this.canVote();
				upDateWool();
			}
			if(str.equals("nonColorVoting"))
				this.nonColorVoting = Boolean.parseBoolean(c);
			if(str.equals("team"))
				this.team = c.replaceAll("\"", "");
		}
		//If you don't specify name it will grab it from the team
		if(this.name == null && this.team != null)
		{
			Team t = Team.getTeam(this.bwMap,this.team);
			this.name = t.teamBlock;
			if(this.meta == -1)
				this.meta = t.teammeta;
		}
		if(this.meta == -1)
			this.meta = 0;
	}
	public void upDateWool()
	{
		if(this.style.equals("rnd"))
		{
			int size = bwMap.teams.size();
			int index_team = (int)(Math.random() * (0 + size) );
			Team t = bwMap.teams.get(index_team);
			this.team = t.id;
		}
		//Future Code for updatng voting
		if(this.style.equals("voting"))
		{
			
		}
		//checks for errors and fixes them
		if(this.name == null && this.team != null)
		{
			Team t = Team.getTeam(this.bwMap,this.team);
			this.name = t.teamBlock;
			if(this.meta == -1)
				this.meta = t.teammeta;
		}
		if(this.meta == -1)
			this.meta = 0;
		this.stacksize = getPlayers();
		for(int i=0;i<stacksize-1;i++)
			this.lores.put(i, getPlayer(i) );
		upDateLore();
	}

	private String getPlayer(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getPlayers() {
		return 1;
	}
	@Override
	public String toString()
	{
		return super.toString() + "\nColor:" + this.team + " style:" + this.style + " noncolorVoting:" + this.nonColorVoting + " vote:" + this.vote;
	}
	
	public boolean canVote() {
		return this.style.equals("voting");
	}
}
