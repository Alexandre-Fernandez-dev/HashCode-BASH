package fr.upmc.bashcode;

import fr.upmc.bashcode.model.EndPoint;
import fr.upmc.bashcode.model.Environnement;
import fr.upmc.bashcode.model.Video;

public class HC_Sylvain {
	
    public static void main(String[] args) {
		
	Environnement env = new Environnement();
	GetData.getData(env, "inputs/me_at_the_zoo.in");
	
	for (Video v: env.videos)
	    System.out.println(v.getSize());
    }
	
}
