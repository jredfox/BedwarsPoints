package bwXML.Cfg.Interfaces;

import bwXML.Cfg.Point;

public interface BWEvents {
	
	public void rightClickItem();
	public void rightClickSpecial();
	public void breakBed();//you break bed
	public void breakBlock();//fired for any broken block
	public void bedBroken(Point p);//your bed broke
	public void placeBlock();
	public void killPlayer();
	public void openGUI();
	public void buyItem();
	public void pickupIngot();
	public void die();
	public void teleport();
    public void teamTick();

}
