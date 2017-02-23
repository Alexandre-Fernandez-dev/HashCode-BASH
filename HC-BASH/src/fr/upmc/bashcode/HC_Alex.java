package fr.upmc.bashcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import fr.upmc.bashcode.model.EndPoint;
import fr.upmc.bashcode.model.Video;

public class HC_Alex {
	
	public static void main(String[] args) {
	    System.out.println("Yop !");
        FileParser fp = new FileParser("inputs/file.test");    
        
        try {
            fp.parseFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ArrayList<String> content = fp.getfileLines();
        
        System.out.println("> Contenu du fichier :");
        
        for(String s : content) {
            System.out.println(s);
        }
        
        System.out.println("> Le fichier est fini !");
    }

    public void optimizeCache(HashMap<EndPoint, Integer> endPointsLatency, ArrayList<Video> inCacheVideos){
        HashMap<Video, Integer> totalVideoDataSize = new HashMap<Video, Integer>();
        
        HashMap<Video, ArrayList<EndPoint>> epRequiringVideo = new HashMap<Video, ArrayList<EndPoint>>();
        
        for(Video v : inCacheVideos) {
            epRequiringVideo.put(v, new ArrayList<EndPoint>()); 
            for(EndPoint e : endPointsLatency.keySet()) {
                if(e.getVideoRequest().keySet().contains(v)) {
                    epRequiringVideo.get(v).add(e);
                    if(totalVideoDataSize.get(v) != null)
                        totalVideoDataSize.put(v, totalVideoDataSize.get(v) + e.getCachesLatency().get(v));
                    else
                        totalVideoDataSize.put(v, e.getCachesLatency().get(v));
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
                        videosNotInCache.put(v, videosNotInCache.get(v) + e.getVideoRequest().get(v));
                    else
                        videosNotInCache.put(v, e.getVideoRequest().get(v));
                }
            }
        }
        boolean cont = false;
        while(cont) {
            cont = false;
            Video replacement = null;
            for(Video v : videosNotInCache.keySet()) {
                if(videosNotInCache.get(v) > lowestSize) {
                    replacement = v;
                    break;
                }
            }

            if(replacement != null) {
                cont = true;
                inCacheVideos.remove(lowestVideo);
                inCacheVideos.add(replacement);
                totalVideoDataSize.put(replacement, videosNotInCache.get(replacement));
                videosNotInCache.remove(replacement);
                totalVideoDataSize.remove(lowestVideo);
            }
        }

         
    }

}
