package fr.upmc.bashcode;

import java.util.ArrayList;

import fr.upmc.bashcode.model.Cache;
import fr.upmc.bashcode.model.Environnement;
import fr.upmc.bashcode.model.Video;

public class HC_Boris_2 {

	public static void main(String[] args) {
		
		Environnement env = new Environnement();
		//GetData.getData(env, "inputs/me_at_the_zoo.in");
		
		/*** Donnees de tests ***/
		
		int taille_cache = 1500;
		int taille_video = 10;
		
		ArrayList<Cache> list_caches = new ArrayList<Cache>();
		ArrayList<Video> list_videos = new ArrayList<Video>();
		
		Cache c1 = new Cache(taille_cache);
		Cache c2 = new Cache(taille_cache);
		Cache c3 = new Cache(taille_cache);
		
		Video v1 = new Video(taille_video);
		Video v2 = new Video(taille_video);
		Video v3 = new Video(taille_video);
		Video v4 = new Video(taille_video);
		Video v5 = new Video(taille_video);
		Video v6 = new Video(taille_video);
		Video v7 = new Video(taille_video);
		Video v8 = new Video(taille_video);
		
		list_videos.add(v1);
		list_videos.add(v2);
		list_videos.add(v3);
		list_videos.add(v4);
		list_videos.add(v5);
		list_videos.add(v6);
		list_videos.add(v7);
		list_videos.add(v8);
		
		
		c1.addVideo(v1);
		c1.addVideo(v2);
		c1.addVideo(v3);
		c1.addVideo(v4);
		
		c2.addVideo(v3);
		c2.addVideo(v5);
		
		c3.addVideo(v1);
		c3.addVideo(v3);
		c3.addVideo(v4);
		c3.addVideo(v6);
		c3.addVideo(v7);
		c3.addVideo(v8);
		
		
		list_caches.add(c1);
		list_caches.add(c2);
		list_caches.add(c3);
		
		
		env.caches = list_caches;
		env.videos = list_videos;
		
		/************************/
		
		System.out.println(list_caches.size());
		for(Cache c : list_caches){
			System.out.print(env.caches.indexOf(c) + " ");
			for(Video v : c.getInCacheVideos()){
				System.out.print(env.videos.indexOf(v) + " ");
			}
			System.out.print("\n");
		}
		
	}
}

