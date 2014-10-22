package com.bitcoinanalog.transactionLogic;

public interface TransactionBean {
    
    public void createTransaction(int recipientId, int coin);
    
    public void confirmTransaction(int transactionId);
    
    public void createBlock(int userId);
    
}
