package protocoloaplicacion;

public interface Protocolo {
    
	public static final int TCP_SERVICE_PORT = 6000;	
	public static final String NOMBRE = "NOMBRE";
	public static final String APELL = "APELLIDOS";
        public static final String DNI = "DNI";
        public static final String MAIL = "MAIL";
        public static final String PASS = "PASS";
	public static final String ECHO = "ECHO";
        public static final String RUT = "RUTINA";
        public static final String DIET = "DIETA";
        public static final String PES = "PESO";
        public static final String EV = "EVOLUCION";
	public static final String QUIT = "QUIT";
	public static final String OK   = "OK";
        public static final String FECHA_UP = "F_UP";
	public static final String ERROR = "ERROR";
	public static final String CRLF = "\r\n";
	public static final String SP = " ";
        
        public static final String HEADER = NOMBRE+SP+APELL+SP+DNI+SP+MAIL;
	
	public static final int S_USER=0; 
	public static final int S_PASS=1;
	public static final int S_OPER=2; 
	
	public static final String USERMAIL = "franciscojoseaguadomartin@gmail.com";
	public static final String PASSWORD = "12345";
}
