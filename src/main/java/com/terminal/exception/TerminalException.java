package com.terminal.exception;

public class TerminalException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	public enum Errors{
		PRODUCT_SAVING("El producto no pudo ser guardado.");
		
		private String message = null;
		
		Errors(String msg){ this.message = msg;}
		
		public String getMessage(){
			return this.message;
		}
	}
	
	public TerminalException(String msg){
		super(msg);
	}

}
