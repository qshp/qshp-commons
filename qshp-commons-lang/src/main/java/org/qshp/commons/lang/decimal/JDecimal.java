/*
 * Copyright 1998-2013 jcloud.com All right reserved. This software is the
 * confidential and proprietary information of jcloud.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with jcloud.com.
 */

/**
 * 
 */
package org.qshp.commons.lang.decimal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 十进制运算扩展类
 * @author QinYong
 * @desc
 */
public class JDecimal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8042358946436135052L;

	/**
	 * 精确小数位数
	 */
	private int precision = 8;

	
	protected RoundingMode defualtRoundingMode = RoundingMode.HALF_UP;

	// protected static MathContext defaultMathContext = new
	// MathContext(precision,defualtRoundingMode);

	private BigDecimal decimal;

	public JDecimal(double d) {
		this.decimal = new BigDecimal(d);
	}

	public JDecimal(BigDecimal d1) {
		this.decimal = d1;
	}

	public JDecimal(JDecimal d1) {
		this.decimal = d1.decimal;
	}

	public BigDecimal getDecimal() {
		return decimal.setScale(precision, defualtRoundingMode);
	}

	public void setDecimal(BigDecimal decimal) {
		this.decimal = decimal;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	/**
	 *做加法运算
	 * 
	 * @param d1
	 * @return JDecimal + d1 	结果进行四舍五入
	 */
	public JDecimal add(BigDecimal d1) {
		return newJdDecimalWithDefault(decimal.add(d1));
	}

	/**
	 *做加法运算
	 * 
	 * @param d1
	 * @return JDecimal + d1 	结果进行四舍五入
	 */
	public JDecimal add(JDecimal d1) {
		return newJdDecimalWithDefault(decimal.add(d1.decimal));
	}

	/**
	 * 做减法运算
	 * 
	 * @param d1
	 * @return JDecimal - d1	结果进行四舍五入
	 */
	public JDecimal subtract(BigDecimal d1) {
		return newJdDecimalWithDefault(decimal.subtract(d1));
	}

	/**
	 * 做减法运算
	 * @param d1
	 * @return JDecimal - d1	结果进行四舍五入
	 */
	public JDecimal subtract(JDecimal d1) {
		return newJdDecimalWithDefault(decimal.subtract(d1.decimal));
	}
	
	/**
	 * 做乘法运算
	 * 
	 * @param d1
	 * @return JDecimal * d1	结果进行四舍五入
	 */
	public JDecimal multiply(BigDecimal d1) {
		return newJdDecimalWithDefault(decimal.multiply(d1));
	}

	/**
	 * 做乘法运算
	 * 
	 * @param d1
	 * @return JDecimal * d1	结果进行四舍五入
	 */
	public JDecimal multiply(JDecimal d1) {
		return newJdDecimalWithDefault(decimal.multiply(d1.decimal));
	}

	/**
	 * 做除法运算
	 * 
	 * @param d1
	 * @return JDecimal	/ d1	结果进行四舍五入
	 */
	public JDecimal divide(BigDecimal d1) {
		return newJdDecimalWithDivide(decimal.divide(decimal, precision,
				defualtRoundingMode));
	}

	/**
	 * 做除法运算
	 * 
	 * @param d1
	 * @return JDecimal	/ d1	结果进行四舍五入
	 */
	public JDecimal divide(JDecimal d1) {
		return newJdDecimalWithDivide(decimal.divide(d1.decimal, precision,
				defualtRoundingMode));
	}

	/**
	 * 求余运算
	 * 
	 * @author QinYong 2014-1-27 上午10:21:20
	 * @param divisor
	 * @return
	 */
	public JDecimal remainder(JDecimal divisor) {
		return newJdDecimalWithDefault(decimal.remainder(divisor.decimal));
	}
	
	public double doubleValue() {
		return decimal.doubleValue();
	}

	public float floatValue() {
		return decimal.floatValue();
	}

	protected JDecimal newJdDecimalWithDivide(BigDecimal d1) {
		return new JDecimal(d1);
	}

	protected JDecimal newJdDecimalWithDefault(BigDecimal d1) {
		return new JDecimal(d1.setScale(precision, defualtRoundingMode));
	}

}
