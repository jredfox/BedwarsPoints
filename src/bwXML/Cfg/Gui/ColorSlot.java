package bwXML.Cfg.Gui;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bwXML.Cfg.BWMap;
import bwXML.Cfg.Team;
import bwXML.Cfg.Interfaces.Registry;
import bwXML.main.XMLUtil;

public class ColorSlot extends Item{
	
	public String style;//rnd for random team and colored if boolean,voting for voting for the slot,and fixed for a fixed team
	public boolean nonColorVoting;
	public boolean vote;
	public Team team;
	public String teamId;
	
	public String getDisplayName(){
		return this.display_NameInit.replaceAll("`color`", this.team.color).replaceAll("`teamId`", this.team.id);
	}
	
	public ColorSlot(NodeList li,ArrayList<Team> t) {
		super(li);
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
			}
			if(str.equals("nonColorVoting"))
				this.nonColorVoting = Boolean.parseBoolean(c);
			if(str.equals("team"))
				this.teamId = c.replaceAll("\"", "");
		}
		upDateWool(t);
	}
	public void upDateWool(ArrayList<Team> teams)
	{
		if(this.style.equals("rnd"))
		{
			int size = teams.size();
			int index_team = (int)(Math.random() * (0 + size) );
			Team t = teams.get(index_team);
			t.rndMeta();
			this.teamId = t.id;
			this.team = t;
			this.name = t.teamBlock;
			this.meta = t.teammeta;
		}
		if(this.team == null)
			this.team = Team.getTeam(teams, this.teamId);
		
		if(this.style.equals("fixed") && this.meta == -1)
			this.meta = this.team.teammeta;
		
		//Future Code for updatng voting
		if(this.style.equals("voting")){
			this.meta = 0;
		}
		
		this.stacksize = getStackSize();
		
		for(int i=0;i<stacksize-1;i++)
			this.lores.put(i, getPlayer(i) );
		upDateLore();
		upDateDisplayName();
	}
	private String getPlayer(int i) {return null;}

	private int getStackSize() {
		int players = 1;
		if(players == 0)
			return 1;
		return players;
	}

	@Override
	public void upDateDisplayName(){this.display_Name = this.getDisplayName();}
	
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
