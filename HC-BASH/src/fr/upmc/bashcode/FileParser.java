package fr.upmc.bashcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileParser {

    private File f;
    private ArrayList<String> fileLines; 

    public FileParser(String fileName) {
        f = new File(fileName);
    }

    public void parseFile() throws IOException {
        BufferedReader r = new BufferedReader(new FileReader(f));
        String line = "";
        fileLines = new ArrayList<String>();
        while(true) {
            line = r.readLine();
            if(line == null)
                break;
            fileLines.add(line);
        }
        r.close();
    }

    public ArrayList<String> getfileLines() {
        return fileLines;
    }

    public void computeFile() {
        // TODO Ici on analysera le contenu du fichier
        
    }

}
