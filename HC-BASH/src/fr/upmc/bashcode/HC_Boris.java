package fr.upmc.bashcode;

import java.util.ArrayList;

import fr.upmc.bashcode.model.*;

public class HC_Boris {

	public static void main(String[] args) {
		
		ArrayList<EndPoint> endpoint_list = new ArrayList<EndPoint>();
		
		double score_before = 0;
		double score_after = 0;
		
		for(EndPoint e: endpoint_list){
			score_before += e.getLatency();
		}
		
		for(EndPoint e: endpoint_list){
			for(Cache c: e.getCachesLatency().keySet()){
				score_after += c.getEndPointsLatency().get(e);
			}
		}
		
		System.out.println("Total de latence sans utiliser les caches : " + score_before);
		System.out.println("Total de latence en utilisant les caches : " + score_after);
	}
}
