/**
 * 
 */
package org.qshp.commons.util.codec;

import java.util.Random;
import java.util.UUID;

/**
 * @author QinYong 2013-3-21 上午10:20:22
 * @description
 */
public class KeyGenerator {

	public final static char[] range = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
			'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9' };
	
	public final static int maxNum = 62;

	private static int reandomLength = 8;

	private static Random rand = new Random();

	private KeyGenerator(){}

	public static int createRandomInt() {
		// 得到0.0到1.0之间的数字，并扩大100000倍
		double temp = Math.random() * 100000;
		// 如果数据等于100000，则减少1
		if (temp >= 100000) {
			temp = 99999;
		}
		int tempint = (int) Math.ceil(temp);
		return tempint;
	}

	public static String random(int len) {
		Random rd = new Random();
		
		StringBuffer buffer = new StringBuffer();
		int rdGet;// 取得随机数
		int count = 0;
		while (count < len) {
			rdGet = Math.abs(rd.nextInt(maxNum));// 生成的数最大为62-1
			if (rdGet >= 0 && rdGet < range.length) {
				buffer.append(range[rdGet]);
				count++;
			}
		}
		return buffer.toString();
	}
	
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		StringBuffer buffer = new StringBuffer(48);
		// 去掉"-"符号
		buffer.append(str.substring(0, 8));
		buffer.append(str.substring(9, 13));
		buffer.append(str.substring(14, 18) + str.substring(19, 23));
		buffer.append(str.substring(24));
		return buffer.toString();
	}

	public static String secretKeyGenerate() {
		return getUUID() + random(reandomLength);
	}
	
	public static String accessKeyGenerate() {
		return getUUID();
	}
	
	public static String randCode(){
		int tmp = Math.abs(rand.nextInt());
		tmp = tmp % (900000) + 100000;
		
		return tmp+"";
	}
	public static void main(String[] args) {
		String accessKey = KeyGenerator.accessKeyGenerate();
		String secretKey = KeyGenerator.secretKeyGenerate();
		System.out.println(accessKey);
		System.out.println(secretKey);
		System.out.println(random(32));
		System.out.println(randCode());
	}
}
