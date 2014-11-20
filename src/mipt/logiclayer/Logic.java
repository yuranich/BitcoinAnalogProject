package mipt.logiclayer;

import mipt.infosec.ejb.Transaction;

public class Logic {
 // �� ������ �������� ���������� � ���������� ����������� � ���-�� ����� �� ���������� ����
	private static int maxId = 0;
	public static void createTransaction(int from,int to,int money){
		
		Transaction tr = new Transaction();
		maxId= tr.getMaxId();
		tr.createTransaction(from, to, money);
		
		/*
		 * ��� �������� ����� ������� ������ �������� �� ��������� ����� transaction.xml
		 * 
		 * ��� ������� ��������� ��� ����� Transaction � �������� ���� ���� id, from, to ,hash, money.
		 * 
		 */
		
		
	}
	
	
	// ���� ����� ���������� ����� �������� �������� �� ��������� �����
	public static void updateTransaction(Transaction tr){
		Transaction trans = new Transaction();
		trans.updateTransaction(tr.getFrom(),tr.getTo(),tr.getMoney(),tr.getId(),tr.getHash());
	}
}
