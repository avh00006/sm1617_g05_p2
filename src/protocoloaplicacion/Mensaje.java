/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocoloaplicacion;

/**
 *
 * @author Francisco
 */
public class Mensaje {
    
    protected String header="";
    protected Datos data = null;
    protected Protocolo CRLF;
    
    public Mensaje(String cabecera, Datos data){
        
        this.header = cabecera;
        this.data = data;
        
    
    }
    
    public byte[]toByteArray(){
    
        String mensaje = header +" " +data.toString()+CRLF;
        return mensaje.getBytes();
               
    }
    
  
    
}


class Datos{
    
    protected int param1;
    protected String param2;
    
    public Datos(int p1, String p2){
    
        this.param1 = p1;
        this.param2 = p2;
        
    
    }
    
    

    public String toString(){
        return param2+""+String.valueOf(param1);
    }
    
    
}

