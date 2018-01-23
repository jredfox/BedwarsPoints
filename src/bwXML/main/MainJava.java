package bwXML.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import bwXML.Cfg.BWMap;
import bwXML.Cfg.BlockXml;
import bwXML.Cfg.Team;
import bwXML.Cfg.Gui.ColorSlot;
import bwXML.Cfg.Gui.GUI;
import bwXML.Cfg.Interfaces.InterFacialRegistry;
import bwXML.Cfg.Interfaces.Registry;

public class MainJava {
	
	public static void main(String[] args)
	{
		AppUtil.moveFiles();
		AppUtil.updateXMLFileList();
		InterFacialRegistry.registerParser(new ParserTest());
		try {
			for(File f : AppUtil.filemaps)
				XMLUtil.parseMapFile(new FileInputStream(f), "Map");
			for(File f : AppUtil.fileguis)
				XMLUtil.parseGUIFile(new FileInputStream(f),"GUIS" );
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		BWMap map = Registry.maps.get(0);
		GUI g = map.teamSelectors;
		ColorSlot s = (ColorSlot) g.items.get(0);
		System.out.println("Name: " + s.getDisplayName() + " meta:" + s.meta);
		g.guiOpen(map.teams);
		System.out.println("Name: " + s.getDisplayName() + " meta:" + s.meta);
	}
	
}
