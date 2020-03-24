package util;

public class ApplicationException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ApplicationException(){ super(); };
	
	public ApplicationException(String mensaje){ super(mensaje); };
	
	public ApplicationException(String mensaje, Throwable causa){ super(mensaje, causa); };

}
