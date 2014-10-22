package com.bitcoinanalog.utils;

public interface hashFunction {
    public int calculateHashTransaction(int transactionId);
    
    public int calculateHashBlock(int blockId);
}
