package com.bitcoinanalog.ejb;

public interface Transaction {
    public void createTransaction(int userId);
    
    public void putTransactionIntoBlock(int transcationId);
    
    public void createBlock(int userId);
    
    
    
    
    
}
