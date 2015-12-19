/*
 * Copyright 2014-2014 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */
package org.qshp.commons.web.passport.ticket;

import java.util.Date;

import org.qshp.commons.lang.logging.Log;
import org.qshp.commons.lang.logging.LogFactory;
import org.qshp.commons.web.passport.FieldNullException;

/**
 * @author QinYong
 * 
 */
public class AuthenticationTicketUtils {

	private static Log log = LogFactory.getLog(AuthenticationTicketUtils.class);

	public static String generateTicket(String userName,
			String userData) throws FieldNullException {
		AuthenticationTicket ticket = new AuthenticationTicket(userName,
				userData, null, null, null, 0);
		return ticket.toString();
	}

	/**
	 * 
	 * @param value
	 * @param key
	 * @return
	 */
	public static AuthenticationTicket parseTicket(String value, String key) {
		String[] ticketArrs = value.split(",");
		AuthenticationTicket ticket = null;
		try {
			if(ticketArrs.length > 3){
				String username = ticketArrs[0].split("=")[1];
				String[] userdataArr = ticketArrs[1].split("=");
				String[] expiresArr = ticketArrs[2].split("=");
				String[] issueDataArr = ticketArrs[3].split("=");
				String userdata = userdataArr.length > 1 ? userdataArr[1] : "";
				Date expires = expiresArr.length > 1 ? new Date(Long.parseLong(expiresArr[1])) : null;
				Date issueData = issueDataArr.length > 1 ? new Date(Long.parseLong(issueDataArr[1])) : null;
				ticket = new AuthenticationTicket(username, userdata, null, expires,
						issueData, 0);
				/*if(usernameArr.length==2){
					String username = usernameArr[1];
					String[] userdataArr = ticketArrs[1].split("=");
					String[] expiresArr = ticketArrs[2].split("=");
					String[] issueDataArr = ticketArrs[3].split("=");
					String userdata = userdataArr.length > 1 ? userdataArr[1] : "";
					Date expires = expiresArr.length > 1 ? new Date(Long.parseLong(expiresArr[1])) : null;
					Date issueData = issueDataArr.length > 1 ? new Date(Long.parseLong(issueDataArr[1])) : null;
					ticket = new AuthenticationTicket(username, userdata, null, expires,
							issueData, 0);
				}*/
				
			}
			
		} catch (FieldNullException e) {
			log.error("parse cookie ticket error !", e);
		}
		return ticket;
	}
}
