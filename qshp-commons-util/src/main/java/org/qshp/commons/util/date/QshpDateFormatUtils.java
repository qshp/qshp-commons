/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

package org.qshp.commons.util.date;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;

/**
 * @author QinYong
 * 
 */
public class QshpDateFormatUtils extends DateFormatUtils {
	public static final String YEAR_FORMAT = "yyyy";
	public static final String MONTH_FORMAT = "yyyy-MM";
	public static final String MONTHONLY_FORMAT = "MM";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DAY_FORMAT = "dd";
	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String TIMEONLY_FORMAT = "HH:mm:ss";

	private QshpDateFormatUtils(){}

	/**
	 *  formatter for year without time zone. The format used is
	 * <tt>yyyy</tt>.
	 */
	public static final FastDateFormat QSHP_YEAR_FORMAT = FastDateFormat
			.getInstance(YEAR_FORMAT);

	/**
	 *  formatter for year-moth without time zone. The format used is
	 * <tt>yyyy-MM</tt>.
	 */
	public static final FastDateFormat QSHP_MONTH_FORMAT = FastDateFormat
			.getInstance(MONTH_FORMAT);

	/**
	 *  formatter for moth only without time zone. The format used is
	 * <tt>MM</tt>.
	 */
	public static final FastDateFormat QSHP_MONTHONLY_FORMAT = FastDateFormat
			.getInstance(MONTHONLY_FORMAT);
	/**
	 *  formatter for date without time zone. The format used is
	 * <tt>yyyy-MM-dd</tt>.
	 */
	public static final FastDateFormat QSHP_DATE_FORMAT = FastDateFormat
			.getInstance(DATE_FORMAT);

	/**
	 *  formatter for day without time zone. The format used is <tt>dd</tt>.
	 */
	public static final FastDateFormat QSHP_DAY_FORMAT = FastDateFormat
			.getInstance(DAY_FORMAT);

	/**
	 *  formatter for time without time zone. The format used is
	 * <tt>yyyy-MM-dd HH:mm:ss</tt>.
	 */
	public static final FastDateFormat QSHP_TIME_FORMAT = FastDateFormat
			.getInstance(TIME_FORMAT);

	/**
	 *  formatter for time only without time zone. The format used is
	 * <tt>HH:mm:ss</tt>.
	 */
	public static final FastDateFormat QSHP_TIMEONLY_FORMAT = FastDateFormat
			.getInstance(TIMEONLY_FORMAT);
	
	/**
	 * 获取当天开始时间,时间格式：yyyy-MM-dd hh:mm:ss
	 * @author QinYong
	 * @return Date
	 */
	public static Date getCurrDayBegin(){
		return getCurrDayByHms("00:00:00");
	}
	
	/**
	 * 获取当天结束时间,时间格式：yyyy-MM-dd hh:mm:ss
	 * @author QinYong
	 * @return Date
	 */
	public static Date getCurrDayEnd(){
		return getCurrDayByHms("23:59:59");
	}
	
	/**
	 * 获取当天指定的时分秒Date
	 * @author QinYong
	 * @param hms 指定时分秒
	 * @return Date
	 */
	private static Date getCurrDayByHms(String hms){
		String timestr = format(new Date(), QshpDateFormatUtils.DATE_FORMAT)+" "+hms;
		try {
			return DateUtils.parseDate(timestr, new String[]{"yyyy-MM-dd hh:mm:ss"});
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
