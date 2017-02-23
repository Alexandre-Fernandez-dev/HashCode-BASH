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
    
    public void optimizeCache(){
        HashMap<Video, Integer> totalVideoDataSize = new HashMap<Video, Integer>();
        
        HashMap<Video, ArrayList<EndPoint>> epRequiringVideo = new HashMap<Video, ArrayList<EndPoint>>();
        
        for(Video v : inCacheVideos) {
            epRequiringVideo.put(v, new ArrayList<EndPoint>()); 
            for(EndPoint e : endPointsLatency.keySet()) {
                if(e.getVideoRequest().keySet().contains(v)) {
                    epRequiringVideo.get(v).add(e);
                    if(totalVideoDataSize.get(v) != null)
                        totalVideoDataSize.put(v, totalVideoDataSize.get(v) + v.getSize()*e.getVideoRequest().get(v));
                    else
                        totalVideoDataSize.put(v, v.getSize()*e.getVideoRequest().get(v));
                }
            }
        
        }
        
        Video lowestVideo = null;
        int lowestSize = 0;

        for(Video v : totalVideoDataSize.keySet()) {
            if(lowestVideo == null || totalVideoDataSize.get(v)<lowestSize) {
                lowestVideo=v;
                
                lowestSize = totalVideoDataSize.get(v);
            }
        }

        HashMap<Video, Integer> videosNotInCache = new HashMap<Video, Integer>();
        for(EndPoint e : endPointsLatency.keySet()) {
            for(Video v : e.getVideoRequest().keySet()) {
                if(!inCacheVideos.contains(v)) {
                    if(videosNotInCache.containsKey(v))
                        videosNotInCache.put(v, videosNotInCache.get(v) + v.getSize()*e.getVideoRequest().get(v));
                    else
                        videosNotInCache.put(v, v.getSize()*e.getVideoRequest().get(v));
                }
            }
        }

        boolean cont = true;
        while(cont) {
            cont = false;
            Video replacement = null;
            for(Video v : videosNotInCache.keySet()) {
                if(videosNotInCache.get(v) > lowestSize) {
                	if(this.currentFilling - lowestVideo.getSize() + v.getSize() < sizeMax) {
                    	System.err.println("avant " + lowestSize + " après " + videosNotInCache.get(v));
	                    replacement = v;
	                    break;
                	} else {
                    	//System.err.println("Va dépasser");
                	}
                }
            }

            if(replacement != null) {
            	System.err.println("J AMELIORE");
                cont = true;
                inCacheVideos.remove(lowestVideo);
                inCacheVideos.add(replacement);
                totalVideoDataSize.put(replacement, videosNotInCache.get(replacement));
                videosNotInCache.remove(replacement);
                totalVideoDataSize.remove(lowestVideo);
                this.currentFilling -= lowestVideo.getSize();
                this.currentFilling += replacement.getSize();
                if(this.currentFilling>this.sizeMax)
					try {
						throw new Exception("Bordel");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
            }
        }

         
    }
    
}
