package fr.upmc.bashcode;

import fr.upmc.bashcode.model.Environnement;

public class HC_Sylvain {
	
    public static void main(String[] args) {
		
	Environnement env = new Environnement();
	GetData.getData(env, "inputs/me_at_the_zoo.in");
	
	System.out.println("NB ENDPOINTS " + env.endPoints.size());
	System.out.println("NB CACHES " + env.caches.size());
    }
	
}
