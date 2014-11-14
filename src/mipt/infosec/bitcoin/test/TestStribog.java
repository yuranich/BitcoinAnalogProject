package mipt.infosec.bitcoin.test;

import java.util.Arrays;

import mipt.infosec.secutils.hash.Stribog;

public class TestStribog {

public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage: java <programm> need to input 2 example for hash calculating and hashLength");
			return;
		}
		String example1 = args[0];
		String example2 = args[1];
		int length = Integer.parseInt(args[2]);
		
		Stribog stribog;
		try {
			stribog = new Stribog(length);
			byte[] mes1 = example1.getBytes();
			byte[] mes2 = example2.getBytes();
			
			byte[] hash1 = stribog.getHash(mes1);
			byte[] hash2 = stribog.getHash(mes2);
			
			System.out.println("Messages are hashed. The result is:\n" + 
							   example1 + " \n " + Arrays.toString(hash1) + " \n " +
							   example2 + " \n " + Arrays.toString(hash2) + " \n ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}
