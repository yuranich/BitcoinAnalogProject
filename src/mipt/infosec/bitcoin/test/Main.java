package mipt.infosec.bitcoin.test;

import mipt.infosec.ejb.Block;
import mipt.infosec.ejb.Transaction;

public class Main {
	public static void main(String[] argv) {
		
		Transaction tr = new Transaction();
		System.out.println( tr.getMaxId());
//		tr.deleteTransaction(24);
		//		tr.updateTransaction(11121, 11, 545,4,"ssds");
	//	tr.createTransaction(11, 22,124);
//		System.out.println(Block.getMaxId());
		Block b = new Block();
		b.updateblock(11,3);
//		System.out.println(b.getMaxId());
		
//		b.createBlock();
	//	b.addTransaction(3,3);
		//	b.createBlock();
	//	b.addTransaction(111,222);
	}

}
