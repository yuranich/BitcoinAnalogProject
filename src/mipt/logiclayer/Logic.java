package mipt.logiclayer;

import mipt.infosec.ejb.Transaction;

public class Logic {
 // мы должны получать информацию о получателе отрпавители и кол-ве монет от клиетского слоя
	private static int maxId = 0;
	public static void createTransaction(int from,int to,int money){
		
		Transaction tr = new Transaction();
		maxId= tr.getMaxId();
		tr.createTransaction(from, to, money);
		
		/*
		 * тут вызаваем класс который делает рассылку об изминение файла transaction.xml
		 * 
		 * его входные агрументы это класс Transaction у которого есть поля id, from, to ,hash, money.
		 * 
		 */
		
		
	}
	
	
	// этот метод вызывается когда приходит рассылка об изменении файла
	public static void updateTransaction(Transaction tr){
		Transaction trans = new Transaction();
		trans.updateTransaction(tr.getFrom(),tr.getTo(),tr.getMoney(),tr.getId(),tr.getHash());
	}
}
