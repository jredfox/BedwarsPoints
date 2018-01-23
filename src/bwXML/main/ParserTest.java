package bwXML.main;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bwXML.Cfg.Interfaces.IParser;

public class ParserTest implements IParser{

	@Override
	public void readXMLPreInit(NodeList li) {
		System.out.println("READING MAP");
	}

	@Override
	public void readXMLGUIPreInit(NodeList li) {
		System.out.println("READING GUI");
	}

	@Override
	public void readXMLPostInit(NodeList li) {
//		System.out.println("post-map");
		
	}

	@Override
	public void readXMLGUIPostInit(NodeList li) {
//		System.out.println("post-gui");
		
	}

}
