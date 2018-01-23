package bwXML.Cfg;

import java.util.ArrayList;

import bwXML.Cfg.Gui.GUI;

public class BWMap {
	public MapVar bwMap;
	public ReplaceBlock bwReplaceBlock;
	public Special bwSpecial;
	public Points bwPoints;
	public MobHolder bwVMobs;
	public MobHolder bwCMobs;
	public ArrayList<BlockXml> blocks = new ArrayList();
	public ArrayList<Team> teams = new ArrayList();
	public GUI teamSelectors;
	public GUI voting_weather;
	public GUI voting_time;
	
	@SuppressWarnings("unchecked")
	public BWMap(Object[] obj)
	{
		bwMap = (MapVar) obj[0];
		bwReplaceBlock = (ReplaceBlock) obj[1];
		bwSpecial = (Special) obj[2];
		bwPoints = (Points) obj[3];
		bwVMobs = (MobHolder) obj[4];
		bwCMobs = (MobHolder) obj[5];
		blocks = (ArrayList<BlockXml>) obj[6];
		teams = (ArrayList<Team>) obj[7];
		teamSelectors = (GUI) obj[8];
		voting_weather = (GUI) obj[9];
		voting_time = (GUI) obj[10];
	}

}
