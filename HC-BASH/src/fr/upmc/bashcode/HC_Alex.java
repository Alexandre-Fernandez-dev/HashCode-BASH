package fr.upmc.bashcode;

import java.io.IOException;
import java.util.ArrayList;

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

}
