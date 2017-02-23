package fr.upmc.bashcode;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.ws.Endpoint;

import fr.upmc.bashcode.model.*;

public class HC_Hugo {

	public static void main(String[] args) {
		ArrayList<Cache> listCache=new ArrayList<Cache>();
		for (Cache c:listCache){
			HashMap<Video,Float> score=new HashMap<Video,Float>();
			for(Endpoint e: c.getEndpointLatency().getKeySet() ){
				for (Video v: e.getvideoRequest().getKeySet()){
					if (!score.contains(v)){
						score.put(v, 0);
					}
					score.put(v, score.get(v)+(e.getvideoRequest().get(v)/(e.getCachesLatency().get(c));
				}
			}
			
			
		}
	}
	
}
