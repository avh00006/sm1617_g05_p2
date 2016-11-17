package protocoloaplicacion;

import java.io.IOException;
import java.net.*;

public abstract class Servidor implements Protocolo{


	static ServerSocket mServidor = null;
        
        abstract boolean registro(String nombre,String apell, String dni, String email,String pass);   
        
        abstract boolean login(String email,String pass);
        
        abstract boolean cierresesion(boolean salir);
        
        abstract boolean bajausuario(int id);
        
        abstract void buscarrutina();
        
        abstract void buscardieta();
	
        abstract double registropeso(double peso);
        
        abstract void grafevoluc();
	

}