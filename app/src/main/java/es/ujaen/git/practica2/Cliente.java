package es.ujaen.git.practica2;

/**
 * Created by Francisco on 17/11/2016.
 */
public interface Cliente {

    public void login(String email, String pass);

    public void registro(String nombre, String apell, String dni, String email, String pass);

    public void cierresesion(boolean salir);

    public void bajausuario(int id_usuario);

    public String[] buscarrutina(int id_usuario);

    public String[] buscardieta(int id_usuario);

    public void registropeso(double peso);

    public void grafevoluc();



}
