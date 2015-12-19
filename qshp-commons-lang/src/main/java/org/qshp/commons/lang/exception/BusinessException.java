/**
 * 
 */
package org.qshp.commons.lang.exception;


/**
 * @author QinYong
 * @description
 */
public class BusinessException extends AbstractException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String messageCode;
	
	
	public BusinessException(){
		super();
	}
	
	public BusinessException(String message){
		super(message);
	}
	
	public BusinessException(Throwable cause){
		super(cause);
	}
	
	public BusinessException(String message,Throwable cause){
		super(message,cause);
	}
	
	public BusinessException(String message, String messageCode){
		super(message);
	}
	
	public BusinessException(String message,Throwable cause, String messageCode){
		super(message,cause);
	}
	
	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

}
