package bwXML.Cfg.Interfaces;

import java.util.ArrayList;

import bwXML.Cfg.Gui.Item;

public class InterFacialRegistry {
	public static ArrayList<IParser> parsers = new ArrayList();
	public static ArrayList<BWEvents> classes = new ArrayList();
	public static ArrayList<Item> specialItems = new ArrayList();
	
	public static void registerParser(IParser i){parsers.add(i);}//parse your own xml objects here for configurations
	public static void registerEventClass(BWEvents c){classes.add(c);}//get events fired to do stuff
	public static void registerSpecial(Item i){specialItems.add(i);}//get Item to tab and have item registered for the events

}
