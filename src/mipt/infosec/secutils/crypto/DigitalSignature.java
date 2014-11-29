package mipt.infosec.secutils.crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SignatureException;

public class DigitalSignature {

	private static final String CHARSETNAME = "UTF8";
	
	private static final String RSA = "RSA";
	private static final String RSA_SIGNING_ALGORITHM = "MD5WithRSA";
	private static final int RSA_LENGTH = 1024;
	
	
	public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
	    kpg.initialize(RSA_LENGTH);
	    return kpg.genKeyPair();
	}
	
	/** 
	 * This method should be invoked during the new transaction creation.
	 * The result should fill the field <signature> in transaction.xml for the specified transaction.
	 */
	public static Signature generateRSASignature(String message, PrivateKey privateKey) throws NoSuchAlgorithmException, UnsupportedEncodingException, 
                                                                                         InvalidKeyException, SignatureException {		
		byte[] data = message.getBytes(CHARSETNAME);

	    Signature signature = Signature.getInstance(RSA_SIGNING_ALGORITHM);
	    signature.initSign(privateKey);
	    signature.update(data);
	    
	    return signature;
	}
	
	/** 
	 * This method should be invoked during the new block (!!!) creation.
	 * If the result is TRUE, then new block could be added to block.xml.
	 * If the result is FALSE, then it means that somebody tries to attack our system and to make fraud transaction.
	 */
	public static Boolean checkSignature(String message, Signature signature, PublicKey publicKey) throws SignatureException, InvalidKeyException, 
	                                                                                                  UnsupportedEncodingException {
		byte[] data = message.getBytes(CHARSETNAME);
		byte[] signatureBytes = signature.sign();
	    
	    signature.initVerify(publicKey);
	    signature.update(data);
	    
	    return new Boolean(signature.verify(signatureBytes));
	}

}
