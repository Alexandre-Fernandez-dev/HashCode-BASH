package fr.upmc.bashcode.model;

import java.util.HashMap;

public class Cache {
    
    private int sizeMax;
    
    private HashMap<EndPoint, Integer> endPointsLatency = new HashMap<EndPoint,Integer>;

    public Cache(int sizeMax) {
        this.sizeMax = sizeMax;
    }

    public void addEndPoint(EndPoint ep, int latency) {
        endPointsLatency.put(ep, latency); 
    }

    public int getSizeMax() {
        return sizeMax;
    }
    
    public HashMap<EndPoint,Integer> getEndPointsLatency() {
        return endPointsLatency;
    }
    
    
}
