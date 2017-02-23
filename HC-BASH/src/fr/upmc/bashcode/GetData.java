package fr.upmc.bashcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class GetData {

    public static void getData(String path) {
	
	FileParser file = new FileParser(path);
	try {
	    file.parseFile();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	ArrayList<String> allLines = file.getfileLines();
	Iterator<String> iterator = allLines.iterator();
	String current;
	String[] data;
	
	long nbV, nbE, nbR, nbC, nbX;
	
	data = iterator.next().split(" ");
	nbV = Long.parseLong(data[0]);
	nbE = Long.parseLong(data[1]);
	nbR = Long.parseLong(data[2]);
	nbC = Long.parseLong(data[3]);
	nbX = Long.parseLong(data[4]);
	
	// videos
	data = iterator.next().split(" ");
	
	// endpoints
	
	
    }
}
