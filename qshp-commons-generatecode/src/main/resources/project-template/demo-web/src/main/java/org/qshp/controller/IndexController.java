/*
 *Copyright 2013-2013 qshp.org All right reserved. This software is the
 * confidential and proprietary information of qshp.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with qshp.org.
 */

/**
 */
package org.qshp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author QinYong
 * @email qinyong@gmail.com
 * @version 2013年9月7日 下午2:19:47
 * @desc
 */
@Controller
public class IndexController extends BaseController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "vm/index";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index1() {
		return index();
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index2() {
		return index();
	}

	
}
