package mipt.infosec.bitcoin.wallet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Wallet {
	private int summ;
	private DataInputStream file; 
	
	
	public Wallet() {
		try {
			this.file =  new DataInputStream(new FileInputStream("Wallet.txt"));
			System.out.println("ПРочел");
			
				Scanner scanner = null;
				scanner = new Scanner(file);
				summ = scanner.nextInt();
				//String s = file.readLine();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	
	public int getSumm() {
		return this.summ;
	}
	
	public int increaseSumm(int money) {
		this.summ = this.summ + money;
		return this.summ;
		
	}
	
	public int reduceSumm(int money) {
		this.summ = this.summ - money;
		return this.summ;	
	}
	
	

}
