package mipt.infosec.secutils.hash;

import java.util.Arrays;
import java.util.Collections;

import mipt.infosec.utils.ArrayUtils;

public class Stribog {

	//This might be either 256 bits or 512 bits
	private Integer hashLength;

	public Integer getHashLength() {
		return hashLength;
	}

	public void setHashLength(Integer hashLength) {
		this.hashLength = hashLength;
	}
	
	 private byte[] initial = new byte[64];

     private byte[] N = new byte[64];

     private byte[] Sigma = new byte[64];

     public Stribog(int outputLenght) throws Exception {
         if (outputLenght == 512) {
             for (int i = 0; i < 64; i++) {
                 N[i] = 0x00;
                 Sigma[i] = 0x00;
                 initial[i] = 0x00;
             }
             hashLength = new Integer(512);
         }
         else if (outputLenght == 256) {
             for (int i = 0; i < 64; i++) {
                 N[i] = 0x00;
                 Sigma[i] = 0x00;
                 initial[i] = 0x01;
             }
             hashLength = new Integer(256);
         }
         else {
        	 throw new Exception("Wrong lenght!");
         }
     }
     
     public byte[] getHash(byte[] message) throws Exception {
         byte[] paddedMes = new byte[64];
         int lengthOfMessageInBits = message.length * 8;
         byte[] h = Arrays.copyOf(initial, 64);
         byte[] N_0 ={
         0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
         0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
         0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
         0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00
         };
         
         if (hashLength == 512) {
             for (int i = 0; i < 64; i++) {
                 N[i] = 0x00;
                 Sigma[i] = 0x00;
                 initial[i] = 0x00;
             }                
         }
         else if (hashLength == 256) {
             for (int i = 0; i < 64; i++) {
                 N[i] = 0x00;
                 Sigma[i] = 0x00;
                 initial[i] = 0x01;
             }                
         }
         else {
        	 throw new Exception("Wrong lenght!");
         }
         
         byte[] N_512 = ArrayUtils.getBytesFromInt(512);
         Collections.reverse(Arrays.asList(N_512));
         int i = 0;
         while (lengthOfMessageInBits >= 512) {
             i++;
             byte[] tempMessage = new byte[64];
             ArrayUtils.copyBytesToBytes(message, message.length - i*64, tempMessage, 0, 64);
     //System.out.println("tempMessage = " + ArrayUtils.byteArrayToHex(tempMessage));
             h = HashSimpleFunctions.compression(N, h, tempMessage);
     //System.out.println("After compression h = " + ArrayUtils.byteArrayToHex(h));
             N = HashSimpleFunctions.addMod512(N, N_512);
     //System.out.println("After compression N = " + ArrayUtils.byteArrayToHex(N));
             Sigma = HashSimpleFunctions.addMod512(Sigma, tempMessage);
     //System.out.println("After compression Sigma = " + ArrayUtils.byteArrayToHex(Sigma) + "\n\n");
             lengthOfMessageInBits -= 512;                
         }
         
         byte[] message1 = new byte[message.length - i*64];
         ArrayUtils.copyBytesToBytes(message, 0, message1, 0, message.length - i*64);
         if (message1.length < 64) {
             for (int j = 0; j < (64 - message1.length - 1); j++) {
                 paddedMes[j] = 0;
             }
             paddedMes[64 - message1.length - 1] = 0x01;
             ArrayUtils.copyBytesToBytes(message1, 0, paddedMes, 64 - message1.length, message1.length);
         }            
    
         h=HashSimpleFunctions.compression(N, h, paddedMes);
         byte[] messageLength = ArrayUtils.getBytesFromInt(message1.length * 8);
         Collections.reverse(Arrays.asList(messageLength));
         
         N = HashSimpleFunctions.addMod512(N, messageLength);
         Sigma = HashSimpleFunctions.addMod512(Sigma, paddedMes);            
         h = HashSimpleFunctions.compression(N_0, h, N);
         h = HashSimpleFunctions.compression(N_0, h, Sigma);
         
         if (hashLength == 512) {
        	 return h;
         } else {
             byte[] h256 = new byte[32];
             ArrayUtils.copyBytesToBytes(h, 0, h256, 0, 32);
             return h256;
         }
     }
	
	
}
