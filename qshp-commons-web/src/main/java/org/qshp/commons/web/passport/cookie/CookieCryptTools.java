/*
 *Copyright 2013-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

/**
 */
package org.qshp.commons.web.passport.cookie;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.qshp.commons.lang.logging.Log;
import org.qshp.commons.lang.logging.LogFactory;
import org.qshp.commons.util.codec.DESedeCrypt;

/**
 * @author QinYong
 * @desc cookie加密解密工具
 */
public class CookieCryptTools {

	private final static Log log = LogFactory.getLog(CookieCryptTools.class);

	/**
	 * 编码
	 * 默认：utf-8
	 */
	private String charsetName = "utf-8";

	public String encrypt(String value, String key) {

		try {
			byte[] data;
			data = DESedeCrypt.encrypt(value.getBytes(charsetName), key);
			return encoding(data);
		} catch (Exception e) {
			log.error("cookie encrypt error !", e);
		}
		return null;
	}

	public String decrypt(String value, String key) {
		try {
			byte[] data = decoding(value);
			byte[] sData = DESedeCrypt.decrypt(data, key);
			try {
				return new String(sData, charsetName);
			} catch (UnsupportedEncodingException e) {
				log.error("cookie decrypt charsetName error !", e);
			}
		} catch (Exception e) {
			log.error("cookie decrypt error !", e);
		}
		return null;
	}

	private String encoding(byte[] data) {
		return Base64.encodeBase64String(data);
	}

	private byte[] decoding(String data) {
		return Base64.decodeBase64(data);
	}

	public String getCharsetName() {
		return charsetName;
	}

	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}

	public static void main(String[] args) {
		CookieCryptTools cryptTools = new CookieCryptTools();
		String key = "SVE7dbp2kjEjzf7jiVFYznYyiVspV/H4";
		String cookie = "domain=www.qshp.org;path=/;qshp.org=thinksdfa;";
		String encrypt = cryptTools.encrypt(cookie, key);
		String decrypt = cryptTools.decrypt(encrypt, key);
		System.out.println("sourced=" + cookie);
		System.out.println("decrypt=" + decrypt);
		System.out.println("encrypt=" + encrypt);
	}

}
