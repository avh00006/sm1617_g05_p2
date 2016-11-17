package es.ujaen.git.practica2;

/**
<<<<<<< HEAD
 * Created by ANGEL on 18/11/2016.
 */
/** Clase para la interfaz del usuario
 * Created by Francisco and Ángel on 17/11/2016.
 */
public interface Cliente {

    /**Método abstracto para logeo de usuarios
     *
     * @param email correo eléctronico del usuario
     * @param pass contraseña del usuario
     */
    void login(String email, String pass);


    /**Método abstracto para el registro en la aplicación
     *
     * @param nombre define el nombre del usuario
     * @param apell define los apellidos del usuario
     * @param dni identificación del DNI del usuario
     * @param email correo eléctronico del usuario
     * @param pass es la contraseña del usuario
     */
    void registro(String nombre, String apell, String dni,String email, String pass);


    /**Método abstracto para cierre de sesión
     *
     * @param salir booleano utilizado para cerrar la sesión
     */
    void cierresesion(boolean salir);


    /**Método abstracto para realizar la baja del usuario
     *
     * @param id_usuario identificador del usuario generado cuando se registra
     */
    void bajausuario(int id_usuario);


    /**Método abstracto que busca la rutina asociada al usuario gracias a su identificador
     *
     * @param id_usuario identificador del usuario generado cuando se registra
     */
    String[] buscarrutina(int id_usuario);


    /**Método abstracto que busca la dieta asociada al usuario gracias a su identificador
     *
     * @param id_usuario identificador del usuario generado cuando se registra
     */
    String[] buscardieta(int id_usuario);


    /**Método abstracto que recoge el peso del usuario
     *
     * @param peso variable para el peso del usuario
     */
    void registropeso(double peso);


    /**Método abstracto que devuelve una gráfica con la evolución del usuario
     *
     */
    void grafevoluc();

