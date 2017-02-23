package fr.upmc.bashcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import fr.upmc.bashcode.model.Cache;
import fr.upmc.bashcode.model.EndPoint;
import fr.upmc.bashcode.model.Environnement;
import fr.upmc.bashcode.model.Video;

public class GetData {

    public static void getData(Environnement env, String path) {
	
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
	
	// data list
	ArrayList<Video> videos = new ArrayList<Video>();
	ArrayList<EndPoint> endPoints = new ArrayList<EndPoint>();
	HashMap<Integer, Cache> caches = new HashMap<Integer, Cache>();
	
	
	// videos
	data = iterator.next().split(" ");
	for (String s: data)
	    videos.add(new Video(Integer.parseInt(s)));
	    
	// endpoints
	for (long i = 0; i < nbE; i++) {
	    data = iterator.next().split(" ");
	    EndPoint newPoint = new EndPoint(Integer.parseInt(data[0]));
	    int nbCache = Integer.parseInt(data[1]);
	    for (int j = 0; j < nbCache; j++) {
		data = iterator.next().split(" ");
		caches.put(Integer.parseInt(data[0]), new Cache((int) nbX));
		newPoint.addEndCache(new Cache((int)nbX), Integer.parseInt(data[1]));
		caches.get(Integer.parseInt(data[0])).addEndPoint(newPoint, Integer.parseInt(data[1]));
	    }
	    endPoints.add(newPoint);
	}
	
	// requests
	for (long i = 0; i < nbR; i++) {
	    data = iterator.next().split(" ");
	    int idVideo = Integer.parseInt(data[0]);
	    int idPoint = Integer.parseInt(data[1]);
	    int nbReq = Integer.parseInt(data[2]);
	    endPoints.get(idPoint).getVideoRequest().put(videos.get(idVideo), nbReq);
	}
	
	env.endPoints = new ArrayList<EndPoint>(endPoints);
	ArrayList<Cache> cacheList = new ArrayList<Cache>();
	for (Integer i: caches.keySet())
	    cacheList.add(caches.get(i));
	env.caches = new ArrayList<Cache>(cacheList);
	env.videos = new ArrayList<Video>(videos);
	
    }
}
