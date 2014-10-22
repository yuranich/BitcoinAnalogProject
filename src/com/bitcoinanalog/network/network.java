package com.bitcoinanalog.network;

import java.io.File;

public interface network {

    public void sendBroadcastMessage(String message); 
    
    public File[] getDatabase();
    
    public void sendDatabase();
    
}
