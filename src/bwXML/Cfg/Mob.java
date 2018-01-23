package bwXML.Cfg;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Mob {
	public ArrayList<Point> pos = new ArrayList();
	public String entityId;
	public String nbt;
	public int minWave;
	public int maxWave;
	public int delay;
	
	public boolean hasWaveLimit;
	public boolean hasTimeLimit;
	public long timeLimit;
	public int lastWave;
	
	public Mob(NodeList li) {
		for(int i=0;i<li.getLength();i++)
		{
			Node n = li.item(i);
			if(n.getNodeType() == Node.TEXT_NODE || n.getNodeType() == Node.COMMENT_NODE)
				continue;
			String str = n.getNodeName();
			String content = n.getTextContent();
			if(str.equals("pos"))
			{
				if(content.contains("["))
				{
					content = content.replaceAll("\\[", "");
					content = content.replaceAll("\\]", "");
				}
				
				content = content.replaceAll("\\s+", "");//to whitespaced
				String[] poses = content.split("\"");
				if(!content.contains("\""))
					poses = new String[]{content};
				
				for(String s : poses)
				{
					if(s.equals(",") || s.equals(""))
						continue;
					String[] parts = s.split(",");
					double x = Double.parseDouble(parts[0]);
					double y = Double.parseDouble(parts[1]);
					double z = Double.parseDouble(parts[2]);
					pos.add(new Point(x,y,z) );
				}
			}
			else if(str.equals("delay"))
				this.delay = Integer.parseInt(content);
			else if(str.equals("entityId"))
				this.entityId = content;
			else if(str.equals("nbt"))
				this.nbt = content;
			else if(str.equals("minWave"))
				this.minWave = Integer.parseInt(content);
			else if(str.equals("maxWave"))
				this.maxWave = Integer.parseInt(content);
			else if(str.equals("hasWaveLimit"))
				this.hasWaveLimit = Boolean.parseBoolean(content);
			else if(str.equals("hasTimeLimit"))
				this.hasTimeLimit = Boolean.parseBoolean(content);
			else if(str.equals("timeLimit"))
				this.timeLimit = Integer.parseInt(content);
			else if(str.equals("lastWave"))
				this.lastWave = Integer.parseInt(content);
		}
	}
	@Override
	public String toString()
	{
		return this.entityId + " Pos:" + this.pos.get(0) + " nbt:" + this.nbt + " min:" + this.minWave + 
				" max:" + this.maxWave + " delay:" + this.delay + " haswave: " + hasWaveLimit + " hasTime:" + this.hasTimeLimit + " waves:" + lastWave; 
	}
}
