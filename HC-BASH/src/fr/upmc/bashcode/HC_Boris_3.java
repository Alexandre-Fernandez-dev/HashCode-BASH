package fr.upmc.bashcode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import fr.upmc.bashcode.model.Cache;
import fr.upmc.bashcode.model.EndPoint;
import fr.upmc.bashcode.model.Environnement;
import fr.upmc.bashcode.model.Video;

public class HC_Boris_3 {

	public static void main(String[] args) throws IOException {
		
		Environnement env = new Environnement();
		GetData.getData(env, "inputs/kittens.in");
		
		float factor_req_latency = (float) 10;
		float factor_taille_vid = (float) 80000;
		float factor_datacenter_latency = (float) 0.1;
		
		for (Cache c:env.caches){
			HashMap<Video,Float> score=new HashMap<Video,Float>();
			
			for(EndPoint e: c.getEndPointsLatency().keySet() ){
				for (Video v: e.getVideoRequest().keySet()){
					if (!score.containsKey(v)){
						score.put(v, (float)0);
					}
					/*System.out.println(factor_req_latency * (e.getVideoRequest().get(v)/(c.getEndPointsLatency().get(e))));
					System.out.println((factor_taille_vid * 1/v.getSize()));
					System.out.println((factor_datacenter_latency * e.getLatency()));
					System.out.println("////////////////");*/
					
					score.put(v, score.get(v)
							+ factor_req_latency * (e.getVideoRequest().get(v)/(c.getEndPointsLatency().get(e)))
							+ (factor_taille_vid * 1/v.getSize())
							+ (factor_datacenter_latency * e.getLatency()));
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
		ArrayList<Cache> list_caches=new ArrayList<Cache>();
		/*for (Cache c:env.caches){
			if (!c.getInCacheVideos().isEmpty())
				list_caches.add(c);
		}
		
		System.out.println(list_caches.size());
		for(Cache c : list_caches){
			System.out.print(env.caches.indexOf(c) + " ");
			for(Video v : c.getInCacheVideos()){
				System.out.print(env.videos.indexOf(v) + " ");
			}
			System.out.print("\n");
		}*/
		
		File f = new File("kittensBoris.txt");
		FileWriter fw = new FileWriter(f);
		
		
		for (Cache c:env.caches){
			if (!c.getInCacheVideos().isEmpty())
				list_caches.add(c);
		}

		fw.write(list_caches.size()+ "\n");
		for(Cache c : list_caches){
			fw.write(env.caches.indexOf(c) + " ");
			for(Video v : c.getInCacheVideos()){
				fw.write(env.videos.indexOf(v) + " ");
			}
			fw.write("\n");
		}
		fw.close();
		
	}
}

