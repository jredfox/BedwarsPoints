package bwXML.Cfg.Interfaces;

import java.util.ArrayList;
import java.util.Map;

import bwXML.Cfg.BWMap;
import bwXML.Cfg.Gui.GUI;

public class Registry {
	
	public static ArrayList<BWMap> maps = new ArrayList();
	public static ArrayList<GUI> guis = new ArrayList();//global
	
	public static BWMap getMap(String id)
	{
		for(BWMap m : maps)
			if(m.bwMap.worldId.equals(id))
				return m;
		return null;
	}
	public static GUI getGUI(String id)
	{
		for(GUI g : guis)
			if(g.id.equals(id))
				return g;
		return null;
	}
}
