package bwXML.Cfg.Interfaces;

import java.util.ArrayList;

import org.w3c.dom.NodeList;

import bwXML.Cfg.Gui.Item;

public interface IParser {
	
	public void readXML(NodeList li);
	public void readXMLGUI(NodeList li);

}
