package bwXML.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;

public class AppUtil {
	public static File dir;
	public static File dirmaps;
	public static File dirguis;
	public static ArrayList<File> filemaps = new ArrayList();
	public static ArrayList<File> fileguis = new ArrayList();
	
	public static void moveFiles()
	{
		dir = new File(System.getProperty("user.dir"));
		dirmaps = new File(dir,"Maps");
		dirguis = new File(dir,"Guis");
		if(!dirmaps.exists())
			dirmaps.mkdirs();
		if(!dirguis.exists())
			dirguis.mkdirs();
		File invasion = new File(dirmaps,"invasion.xml");
		File gui = new File(dirguis,"shop.xml");
		moveFileFromJar(MainJava.class,"./bedwars-Beta-1.1.xml",invasion);
		moveFileFromJar(MainJava.class,"./bedwars_gui.xml",gui);
	}
	public static void updateXMLFileList() {
		filemaps.clear();
		fileguis.clear();
		getAllFilesFromDir(dirmaps,filemaps,".xml");
		getAllFilesFromDir(dirguis,fileguis,".xml");
	}
	public static void getAllFilesFromDir(File directory, ArrayList<File> files,String extension) {

	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile() && !files.contains(file) && file.getName().endsWith(extension)) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	        	getAllFilesFromDir(file, files,extension);
	        }
	    }
	}
	@SuppressWarnings("rawtypes")
	public static void moveFileFromJar(Class clazz,String input,File output) {
		try {
			if(output.exists())
				return;
			InputStream inputstream =  clazz.getResourceAsStream(input);
			FileOutputStream outputstream = new FileOutputStream(output);
			output.createNewFile();
			IOUtils.copy(inputstream,outputstream);
			inputstream.close();
			outputstream.close();
		} catch (Exception io) {io.printStackTrace();}
		
	}
	
}
