package bwXML.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import bwXML.Cfg.BWMap;
import bwXML.Cfg.BlockXml;
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
				Registry.maps.add(XMLUtil.parseMapFile(new FileInputStream(f), "Map") );
			for(File f : AppUtil.fileguis)
				XMLUtil.parseGUIFile(new FileInputStream(f),"GUIS" );
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}
	
}
