package mipt.infosec.bitcoin.test;

import java.util.Arrays;

import mipt.infosec.secutils.hash.Stribog;

public class TestStribog {

public static void main(String[] args) {
		if (args.length != 3) {
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
			
			byte[] testInput1 = { (byte)0xfb, (byte)0xe2, (byte)0xe5, (byte)0xf0, (byte)0xee, 
					              (byte)0xe3, (byte)0xc8, (byte)0x20, (byte)0xfb, 
					              (byte)0xea, (byte)0xfa, (byte)0xeb, (byte)0xef, 
					              (byte)0x20, (byte)0xff, (byte)0xfb, (byte)0xf0, 
					              (byte)0xe1, (byte)0xe0, (byte)0xf0, (byte)0xf5, 
					              (byte)0x20, (byte)0xe0, (byte)0xed, (byte)0x20, 
					              (byte)0xe8, (byte)0xec, (byte)0xe0, (byte)0xeb, 
					              (byte)0xe5, (byte)0xf0, (byte)0xf2, (byte)0xf1, 
					              (byte)0x20, (byte)0xff, (byte)0xf0, (byte)0xee, 
					              (byte)0xec, (byte)0x20, (byte)0xf1, (byte)0x20, 
					              (byte)0xfa, (byte)0xf2, (byte)0xfe, (byte)0xe5, 
					              (byte)0xe2, (byte)0x20, (byte)0x2c, (byte)0xe8, 
					              (byte)0xf6, (byte)0xf3, (byte)0xed, (byte)0xe2, 
					              (byte)0x20, (byte)0xe8, (byte)0xe6, (byte)0xee, 
					              (byte)0xe1, (byte)0xe8, (byte)0xf0, (byte)0xf2, 
					              (byte)0xd1, (byte)0x20, (byte)0x2c, (byte)0xe8, 
					              (byte)0xf0, (byte)0xf2, (byte)0xe5, (byte)0xe2, 
					              (byte)0x20, (byte)0xe5, (byte)0xd1 };
			byte[] testOutput1 = { (byte)0x50, (byte)0x8f, (byte)0x7e, (byte)0x55, 
					               (byte)0x3c, (byte)0x06, (byte)0x50, (byte)0x1d, 
					               (byte)0x74, (byte)0x9a, (byte)0x66, (byte)0xfc, 
					               (byte)0x28, (byte)0xc6, (byte)0xca, (byte)0xc0, 
					               (byte)0xb0, (byte)0x05, (byte)0x74, (byte)0x6d, 
					               (byte)0x97, (byte)0x53, (byte)0x7f, (byte)0xa8, 
					               (byte)0x5d, (byte)0x9e, (byte)0x40, (byte)0x90, 
					               (byte)0x4e, (byte)0xfe, (byte)0xd2, (byte)0x9d };
			
			byte[] testInput2 = { (byte)0x32, (byte)0x31, (byte)0x30, (byte)0x39, 
					              (byte)0x38, (byte)0x37, (byte)0x36, (byte)0x35, 
					              (byte)0x34, (byte)0x33, (byte)0x32, (byte)0x31, 
					              (byte)0x30, (byte)0x39, (byte)0x38, (byte)0x37,  
					              (byte)0x36, (byte)0x35, (byte)0x34, (byte)0x33, 
					              (byte)0x32, (byte)0x31, (byte)0x30, (byte)0x39, 
					              (byte)0x38, (byte)0x37, (byte)0x36, (byte)0x35, 
					              (byte)0x34, (byte)0x33, (byte)0x32, (byte)0x31,  
					              (byte)0x30, (byte)0x39, (byte)0x38, (byte)0x37, 
					              (byte)0x36, (byte)0x35, (byte)0x34, (byte)0x33, 
					              (byte)0x32, (byte)0x31, (byte)0x30, (byte)0x39, 
					              (byte)0x38, (byte)0x37, (byte)0x36, (byte)0x35,  
					              (byte)0x34, (byte)0x33, (byte)0x32, (byte)0x31, 
					              (byte)0x30, (byte)0x39, (byte)0x38, (byte)0x37, 
					              (byte)0x36, (byte)0x35, (byte)0x34, (byte)0x33, 
					              (byte)0x32, (byte)0x31, (byte)0x30 };
			byte[] testOutput2 = { (byte)0x00, (byte)0x55, (byte)0x7B, (byte)0xE5, 
					               (byte)0xE5, (byte)0x84, (byte)0xFD, (byte)0x52, 
					               (byte)0xA4, (byte)0x49, (byte)0xB1, (byte)0x6B, 
					               (byte)0x02, (byte)0x51, (byte)0xD0, (byte)0x5D, 
					               (byte)0x27, (byte)0xF9, (byte)0x4A, (byte)0xB7, 
					               (byte)0x6C, (byte)0xBA, (byte)0xA6, (byte)0xDA, 
					               (byte)0x89, (byte)0x0B, (byte)0x59, (byte)0xD8, 
					               (byte)0xEF, (byte)0x1E, (byte)0x15, (byte)0x9D };
			
			byte[] hash1 = stribog.getHash(mes1);
			byte[] hash2 = stribog.getHash(mes2);
			
			byte[] testHash1 = stribog.getHash(testInput1);
			byte[] testHash2 = stribog.getHash(testInput2);
			
			System.out.println("Messages are hashed. The result is:\n" + 
							   example1 + " \n " + Arrays.toString(hash1) + " \n" +
							   example2 + " \n " + Arrays.toString(hash2) + " \n" +
							   testInput1 + " = " + Arrays.toString(testHash1) + " \n" +
							   "should be  = " + Arrays.toString(testOutput1) + "\n\n" +
							   testInput2 + " = " + Arrays.toString(testHash2) + " \n " +
							   "should be  = " + Arrays.toString(testOutput2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
}
