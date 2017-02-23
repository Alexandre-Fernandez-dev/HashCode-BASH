package fr.upmc.bashcode.model;

import java.util.HashMap;

public class EndPoint {

    int dataCenterLatency;
    HashMap<Cache, Integer> cachesLatency;
    
    public EndPoint(int dataCenterLatency) {
        this.dataCenterLatency = dataCenterLatency; 
    }

    public void addEndCache(Cache c, int latency) {
        cachesLatency.put(c, latency); 
    }
}
