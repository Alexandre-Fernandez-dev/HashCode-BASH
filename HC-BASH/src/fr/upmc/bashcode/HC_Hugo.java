package fr.upmc.bashcode;

import java.util.ArrayList;
import java.util.HashMap;

import fr.upmc.bashcode.model.*;

public class HC_Hugo {

	public static void main(String[] args) {
		
		Environnement env = new Environnement();
		GetData.getData(env, "inputs/me_at_the_zoo.in");
		
	
	
		for (Cache c:env.caches){
			HashMap<Video,Float> score=new HashMap<Video,Float>();
			
			for(EndPoint e: c.getEndPointsLatency().keySet() ){
				for (Video v: e.getVideoRequest().keySet()){
					if (!score.containsKey(v)){
						score.put(v, (float)0);
					}
					score.put(v, score.get(v)
							+(e.getVideoRequest().get(v)
									/(c.getEndPointsLatency().get(e)
											*v.getSize())
											*e.getLatency()));
				}
			}
			while(c.getCurrent()<c.getSizeMax()){
				Video vmax=null;
				Float max=(float)0;
				for (Video v:score.keySet()){
					if(score.get(v)>max){
						vmax=v;
						max=score.get(v);
					}
				}
				if (vmax==null)
					break;
								
				if((c.getCurrent()+vmax.getSize()<=c.getSizeMax())&&(!c.getInCacheVideos().contains(vmax))){
					c.addVideo(vmax);
					c.setCurrentFilling(c.getCurrent()+vmax.getSize());
					score.remove(vmax);
				}
				else
					break;
			}
			
		}
		
		
	}
	
}
