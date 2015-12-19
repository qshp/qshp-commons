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

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author QinYong
 * @email qinyong@gmail.com
 * @version 2013年9月7日 下午2:17:44
 * @desc     
 */
public class BaseController {

	public String checkResult(BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			List<FieldError> errs = bindingResult.getFieldErrors();
			StringBuffer errors = new StringBuffer();
			for (FieldError fieldError : errs) {
				errors.append(fieldError.getDefaultMessage());
			}
			return errors.toString();
		}
		return null;
	}
	
	public boolean hasErrors(BindingResult bindingResult){
		if(bindingResult.hasErrors()) return true;
		else return false;
	}
}
