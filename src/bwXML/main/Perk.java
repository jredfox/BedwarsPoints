package bwXML.main;

public class Perk {
	
	public String id;
	public String display_name;
	public String itemId;
	public int max_tier;
	
	public Perk(String id, String display,String itemId,int max_teir)
	{
		this.id = id;
		this.display_name = display;
		this.itemId = itemId;
		this.max_tier = max_teir;
	}
	
	public int getTeirCost(int tier)
	{
		return 0;
	}
}
