/**
 * 
 */
package org.qshp.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContext;

/**
 * @author qinyong
 *
 */
@Controller
public class LanguageController {
	
	@Autowired
	private CookieLocaleResolver localeResolver;
	
	@RequestMapping(value = "/lan/sess/{language}", method = RequestMethod.GET)
	public String languageForSession(HttpServletRequest request,
			@PathVariable(value = "language") String language) {
		Locale locale = getLocale(language);
		request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/lan/{language}", method = RequestMethod.GET)
	public String languageForCookie(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value = "language") String language) {
		Locale locale = getLocale(language);
		localeResolver.setLocale(request, response, locale);
		return "redirect:/index";
	}

	private Locale getLocale(String language) {
		Locale locale = null;
		if (language.equals("zh")) {
			locale = new Locale("zh", "CN");
		} else if (language.equals("en")) {
			locale = new Locale("en", "US");
		} else if (language.equals("in")) {
			locale = new Locale("in", "ID");
		}else {
			locale = LocaleContextHolder.getLocale();
			
		}
		return locale;
	}
	
	protected String getMessage(HttpServletRequest request,String code){
		RequestContext context = new RequestContext(request);
		return context.getMessage(code);
	}
	
}
