package mipt.infosec.bitcoin.test;

import mipt.infosec.ejb.Block;
import mipt.infosec.ejb.Transaction;

public class Main {
	public static void main(String[] argv) {
		
		Transaction tr = new Transaction();
		System.out.println(Block.getMaxId());
		Block b = new Block();
		b.createBlock();
		b.addTransaction(114,222);
	}

}
