 /*
 *Copyright 2013-2013 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

/**
 */
package org.qshp.commons.web.url;

import java.util.Collections;
import java.util.Map;

/**
 * @author QinYong
 * @desc 
 */
public class UrlBuilders {

	  private final Map<String, UrlBuilder> urlBuilders;

	    public UrlBuilders(Map<String, UrlBuilder> urlBuilders) {
	        this.urlBuilders = Collections.unmodifiableMap(urlBuilders);
	    }

	    /**
	     * fetch predefined url builder by key.
	     * 
	     * @param key
	     * @return
	     * @throws NullPointerException
	     *             no url builder found.
	     */
	    public UrlBuilder get(String key) throws NullPointerException {
	        UrlBuilder urlBuilder = urlBuilders.get(key);
	        if (urlBuilder == null) {
	            throw new NullPointerException("No such predefined url builder: "
	                    + key);
	        }
	        return urlBuilders.get(key);
	    }

}
