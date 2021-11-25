package hacs.configuration.exception;

@SuppressWarnings("serial")
public class AttachFileException extends RuntimeException{
	
	public AttachFileException (String m) {
		super(m);
	}
	public AttachFileException (String m, Throwable cause) {
		super(m,cause);
	}
}
