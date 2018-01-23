package bwXML.Cfg.Interfaces;

import java.util.ArrayList;

import org.w3c.dom.NodeList;

import bwXML.Cfg.Gui.Item;

public interface IParser {
	
	public void readXMLPreInit(NodeList li);
	public void readXMLPostInit(NodeList li);
	public void readXMLGUIPreInit(NodeList li);
	public void readXMLGUIPostInit(NodeList li);

}
