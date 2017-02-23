package fr.upmc.bashcode.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Cache {
    
    private int sizeMax;
    private int currentFilling;
    private HashMap<EndPoint, Integer> endPointsLatency = new HashMap<EndPoint,Integer>();
    private ArrayList<Video> inCacheVideos=new ArrayList<Video>();
    
    public Cache(int sizeMax) {
        this.sizeMax = sizeMax;
        this.currentFilling=0;
    }

    public void addEndPoint(EndPoint ep, int latency) {
        endPointsLatency.put(ep, latency); 
    }

    public int getSizeMax() {
        return sizeMax;
    }
    
    public HashMap<EndPoint,Integer> getEndPointsLatency() {
        return endPointsLatency;
    }
    
    public int getCurrent(){
    	return currentFilling;
    }
    public ArrayList<Video> getInCacheVideos(){
    	return inCacheVideos;
    }
    
    public void setCurrentFilling(int v){
    	this.currentFilling=v;
    }
    
    public void addVideo(Video v){
    	inCacheVideos.add(v);
    }
    
}
