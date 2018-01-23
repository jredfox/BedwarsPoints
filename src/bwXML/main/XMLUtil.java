package bwXML.main;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bwXML.Cfg.BWMap;
import bwXML.Cfg.BlockXml;
import bwXML.Cfg.MapVar;
import bwXML.Cfg.Mob;
import bwXML.Cfg.MobHolder;
import bwXML.Cfg.Points;
import bwXML.Cfg.ReplaceBlock;
import bwXML.Cfg.Special;
import bwXML.Cfg.Team;
import bwXML.Cfg.Gui.GUI;
import bwXML.Cfg.Interfaces.IParser;
import bwXML.Cfg.Interfaces.InterFacialRegistry;
import bwXML.Cfg.Interfaces.Registry;

public class XMLUtil {
	
	private static MapVar bwMap;
	private static ReplaceBlock bwReplaceBlock;
	
	private static Special bwSpecial;
	private static Points bwPoints;
	private static MobHolder bwVMobs;
	private static MobHolder bwCMobs;
	private static ArrayList<BlockXml> blocks = new ArrayList();
	private static ArrayList<Team> teams = new ArrayList();
	private static GUI teamSelector;
	private static GUI voting_weather;
	private static GUI voting_time;
	

	public static void parseMapFile(InputStream in,String rootnode) {
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			Document document = db.parse(in);
			document.normalize();//checks for errors
			
			NodeList rootNodes = (NodeList) document.getElementsByTagName(rootnode);
			NodeList map = rootNodes.item(0).getChildNodes();
			bwMap = new MapVar(map);
			
			//parse addons
			for(IParser parser : InterFacialRegistry.parsers)
				parser.readXMLPreInit(rootNodes.item(0).getChildNodes());
			
			for(int i = 0; i < map.getLength();i++)
			{
				Node n = map.item(i);
				String name = n.getNodeName();
				if(n.getNodeType() == Node.TEXT_NODE || n.getNodeType() == Node.COMMENT_NODE)
					continue;
				if(name.equals("ReplaceBlock"))
					bwReplaceBlock = new ReplaceBlock(n.getChildNodes());
				if(name.equals("Special"))
					bwSpecial = new Special(n.getChildNodes());
				if(name.equals("Points"))
					bwPoints = new Points(n.getChildNodes());
				if(name.equals("VMob"))
					bwVMobs = new MobHolder(n.getChildNodes());
				if(name.equals("CMob"))
					bwCMobs = new MobHolder(n.getChildNodes());
				if(name.equals("Block"))
					blocks.add(new BlockXml(n.getChildNodes()) );
				if(name.equals("Team"))
					teams.add(new Team(n.getChildNodes() ));
				if(name.equals("TeamSelectorGUI"))
					teamSelector = new GUI(n.getChildNodes(),teams);
				if(name.equals("GUI"))
				{
					GUI g = new GUI(n.getChildNodes(),teams);
					if(g.id.equals("voting_time"))
						voting_time = g;
					if(g.id.equals("voting_weather"))
						voting_weather = g;
				}
			}
			
			Object[] obj = new Object[11];
			obj[0] = bwMap;
		    obj[1] = bwReplaceBlock;
			obj[2] = bwSpecial;
			obj[3] = bwPoints;
			obj[4] = bwVMobs;
			obj[5] = bwCMobs;
			obj[6] = blocks;
			obj[7] = teams;
			obj[8] = teamSelector;
			obj[9] = voting_weather;
			obj[10] = voting_time;
			Registry.maps.add(new BWMap(obj));
			
			//parse addons
			for(IParser parser : InterFacialRegistry.parsers)
				parser.readXMLPostInit(rootNodes.item(0).getChildNodes());
			
			in.close();
			
			}catch(Exception e){e.printStackTrace();}
	}
	/**
	 * Adds guis to the regsitry from file
	 */
	public static void parseGUIFile(InputStream in,String rootnode) {
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			Document document = db.parse(in);
			document.normalize();//checks for errors
			
			NodeList rootNodes = (NodeList) document.getElementsByTagName(rootnode);
			NodeList root = rootNodes.item(0).getChildNodes();
			
			//parse addons
			for(IParser parser : InterFacialRegistry.parsers)
				parser.readXMLGUIPreInit(rootNodes.item(0).getChildNodes());
			
			for(int i = 0; i < root.getLength();i++)
			{
				Node n = root.item(i);
				if(n.getNodeType() == Node.TEXT_NODE || n.getNodeType() == Node.COMMENT_NODE)
					continue;
				String name = n.getNodeName();
				if(name.equals("GUI"))
					Registry.guis.add(new GUI(n.getChildNodes()) );
			}
			//parse addons
			for(IParser parser : InterFacialRegistry.parsers)
				parser.readXMLGUIPostInit(rootNodes.item(0).getChildNodes());
			in.close();
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static int getIntExtension(String s){
		if(!s.contains("_0") && !s.contains("_1") && !s.contains("_2") && !s.contains("_3") && !s.contains("_4") && !s.contains("_5") && !s.contains("_6") && !s.contains("_7") && !s.contains("_8") && !s.contains("_9") )
			return -1;
		return Integer.parseInt(s.split("_")[1]);
	}

}
