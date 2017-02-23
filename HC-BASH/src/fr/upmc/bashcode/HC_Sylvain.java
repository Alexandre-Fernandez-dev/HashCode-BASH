package fr.upmc.bashcode;

import fr.upmc.bashcode.model.Cache;
import fr.upmc.bashcode.model.EndPoint;
import fr.upmc.bashcode.model.Environnement;
import fr.upmc.bashcode.model.Video;

public class HC_Sylvain {
	
    public static void main(String[] args) {
		
		Environnement env = new Environnement();
		GetData.getData(env, "inputs/trending_today.in");
		
		int maxsize;
		Video toAdd;
		
		for (Cache c: env.caches) {
			if (c.getCurrent() < c.getSizeMax()) {
				for (EndPoint e: c.getEndPointsLatency().keySet()) {
					while (true) {
						maxsize = 0;
						toAdd = null;
						for (Video v: e.getVideoRequest().keySet()) {
							if (v.getSize() + c.getCurrent() < c.getSizeMax() && v.getSize() > maxsize) {
								maxsize = v.getSize();
								toAdd = v;
							}
						}
						if (toAdd == null) break;
						c.setCurrentFilling(c.getCurrent() + toAdd.getSize());
						c.addVideo(toAdd);
					}
				}
			}
		}
		
		System.out.println(env.caches.size());
		for(Cache c : env.caches){
			System.out.print(env.caches.indexOf(c) + " ");
			for(Video v : c.getInCacheVideos()){
				System.out.print(env.videos.indexOf(v) + " ");
			}
			System.out.print("\n");
		}
	
    }
	
}
