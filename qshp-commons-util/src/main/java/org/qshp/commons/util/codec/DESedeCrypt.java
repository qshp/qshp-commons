/*
 *Copyright 2013-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

/**
 */
package org.qshp.commons.util.codec;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @author QinYong
 * @desc
 */
public class DESedeCrypt {
	
	/**
	 * 
	 */
	public final static String KEY_ALGORITHM = "DESede";
	
	/**
	 * 168位
	 */
	public final static String CRYPT_ALGORITHM = "DESede/ECB/PKCS5Padding";

	private DESedeCrypt(){}
	
	/**
	 * 编码 DESede
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, String key) throws Exception {
		Key k = toKey(Base64.decodeBase64(key));
		Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}

	/**
	 * 解码 DESede
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, String key) throws Exception {
		Key k = toKey(Base64.decodeBase64(key));
		Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	/**
	 * 生成私钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] initKey() throws Exception {
		return initKey(null);
	}
	
	/**
	 * 生成私钥
	 * @param seed
	 * @return
	 * @throws Exception
	 */
	public static byte[] initKey(String seed) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator
				.getInstance(KEY_ALGORITHM);
		SecureRandom random;
		if(seed != null){
			random = new SecureRandom(Base64.decodeBase64(seed));
		}else{
			random = new SecureRandom();
		}
		keyGenerator.init(random);
		SecretKey secretKey = keyGenerator.generateKey();
		return Base64.encodeBase64(secretKey.getEncoded());
	}

	/**
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static Key toKey(byte[] key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}

	public static void main(String[] args) {
		try {
//			String key = "SVE7dbp2kjEjzf7jiVFYznYyiVspV/H4";
			String key = "YWJjZAECA2wACW9hYmNkAQIDbAAJbwpo";
			String cookie ="name=muyu;domain=qshp.org";
			byte[] e = encrypt(cookie.getBytes(), key);
			System.out.println(Base64.encodeBase64String(e));
			System.out.println(new String(decrypt(e, key)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(Base64.encodeBase64String(new byte[]{'a','b','c','d',1,2,3,'l',0,9,'o','a','b','c','d',1,2,3,'l',0,9,'o'}));
		//System.out.println();
	}
}
