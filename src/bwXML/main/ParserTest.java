package bwXML.main;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bwXML.Cfg.Interfaces.IParser;

public class ParserTest implements IParser{

	@Override
	public void readXML(NodeList li) {
		System.out.println("READING MAP");
	}

	@Override
	public void readXMLGUI(NodeList li) {
		System.out.println("READING GUI");
	}

}
