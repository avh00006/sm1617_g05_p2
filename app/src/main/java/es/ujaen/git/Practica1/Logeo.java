package es.ujaen.git.Practica1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.CRL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

;

/**
 * Created by Francisco on 29/11/2016.
 */

/**Clase utilizada para la autenticación
 *
 */
public class Logeo extends Activity implements View.OnClickListener,Protocolo{


    static String sesion;
    private Button btn;
    public String user;
    public String pass;
    EditText usuario;
    EditText contraseña;
    public static final String PREFERENCE = "Sesion";

    /**Método que crea el layout de autenticación
     * @param savedInstanceState recibe los datos almacenados
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        String cadena = getSharedPreferences(PREFERENCE,MODE_PRIVATE).getString("PREFERENCE", sesion);
        boolean expira = false;
        if(cadena!=null){
            Date fecha = new Date(System.currentTimeMillis()+3600000);


            String expiracion = sesion.substring(34);

            String an = expiracion.substring(0,4);
            String mes = expiracion.substring(5,7);
            String dia = expiracion.substring(8,10);
            String hora = expiracion.substring(11,13);
            String min = expiracion.substring(14,16);
            String seg = expiracion.substring(17,19);
            int año = Integer.parseInt(an);
            int me = Integer.parseInt(mes);
            int giorno = Integer.parseInt(dia);
            int h = Integer.parseInt(hora);
            int m = Integer.parseInt(min);
            int s = Integer.parseInt(seg);




            SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd-H-m-s");
            String expires = dt1.format(fecha);
            try {
                Date expiraSesion= dt1.parse(expiracion);
                Date fecha2=new Date(System.currentTimeMillis()+3600000);
                System.out.println(expiraSesion.toString());
                if(expiraSesion.before(fecha2)){
                    expira=true;

                }else
                    expira=false;

            } catch (ParseException e) {
                e.printStackTrace();
                expira=true;
            }

            //SimpleDateFormat dt2 = new SimpleDateFormat(expires);
            //Date fecha3 = new Date(año,me,giorno,h,m,s);

//            try{
//                fecha2=dt1.parse(expires);
//
//            }catch(ParseException e){
//                e.printStackTrace();
//
//            }
//
//            System.out.println(fecha2.getTime());
//            //System.out.println(fecha2.getTime()+360000);
//            fecha2.setTime(fecha.getTime()+30000);






        }



        if((cadena==null)|| expira){
            setContentView(R.layout.login);
            usuario = (EditText) findViewById(R.id.txtNick);
            contraseña = (EditText)findViewById(R.id.txtPass);

            btn=(Button)findViewById(R.id.btnLogin);
            btn.setOnClickListener(this);


        }else if(!expira){

            //System.currentTimeMillis();
            Intent i = new Intent(this, SharedPrefsActivity.class);
            i.putExtra("sesion", Logeo.sesion.toString());
            i.setClass(this,SharedPrefsActivity.class);
            startActivity(i);

        }

    }

    /**Método que se utiliza para conectarse al servidor
     * @param v es el objeto de la vista
     */
    @Override
    public void onClick(View v) {



        try{

            InetSocketAddress direccion = new InetSocketAddress("169.254.83.12",6000);
            user = usuario.getText().toString();
            pass = contraseña.getText().toString();
            MiTareaAsincrona conectar = new MiTareaAsincrona(this,user,pass);
            conectar.execute(direccion);

        }catch(Exception ex){
            System.out.println("Error en el cliente"+ex.getMessage());
            String error = "IOException: " + ex.toString();

        }

    }

}



/**Clase utilizada para la tarea asíncrona
 *
 */
class MiTareaAsincrona extends AsyncTask<InetSocketAddress, Void, String> implements Protocolo {


    private Context context;
    private String usuario;
    private String contraseña;

    /**Constructor utilizado para instanciar la clase
     *
     * @param context Le pasamos el contexto de la actividad "Logeo"
     * @param usuario Recogemos el usuario de la actividad "Logeo"
     * @param contraseña Recogemos la contraseña de la actividad "Logeo"
     */
    MiTareaAsincrona (Context context,String usuario,String contraseña){

        this.context = context;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }


    /**Sobreescribimos el método para realizar la comunicación
     *
     * @param arg0 Parámetro utilizado para recoger los datos de entrada
     * @return Devuelve un String con la sesión de comunicación
     */
    @Override
    protected String doInBackground(InetSocketAddress... arg0) {
        Socket cli = null;
        String entrada=null;



        try{

            cli=new Socket();
            cli.connect(arg0[0]);
            BufferedReader is = new BufferedReader(new InputStreamReader(cli.getInputStream()));
            DataOutputStream flujo = new DataOutputStream(cli.getOutputStream());
            entrada=is.readLine();


            String user = USER +SP+usuario+CRLF;


            flujo.write(user.getBytes());
            entrada=is.readLine();

            String pass = PASS+SP+contraseña+CRLF;

            flujo.write(pass.getBytes());
            entrada=is.readLine();

            if(entrada.equals("ERROR Usuario o clave incorrectos")){
                Logeo.sesion=null;


            }else{

                Logeo.sesion=entrada;
            }




            //String salir = QUIT + CRLF;
            //flujo.write(salir.getBytes());
            //entrada=is.readLine();
            flujo.flush();
        }catch(IOException ex){
            System.out.println("Error en el cliente"+ex.getMessage());
            entrada = "IOException: " + ex.toString();
        }

        return Logeo.sesion;

    }

    @Override
    protected void onProgressUpdate(Void... values) {

    }

    @Override
    protected void onPreExecute() {

    }

    /**Sobreescribimos el método para mostrar el resultado de la comunicación
     *
     * @param result En este parámetro guardamos la sesión de comunicación
     */
    @Override
    protected void onPostExecute(String result) {

        super.onPostExecute(result);
        SharedPreferences.Editor edit = context.getSharedPreferences(Logeo.PREFERENCE,context.MODE_PRIVATE).edit();
        edit.putString("Sesion",Logeo.sesion);
        edit.commit();
        if(Logeo.sesion!=null){

            Intent i = new Intent(context, SharedPrefsActivity.class);
            i.putExtra("sesion", Logeo.sesion.toString());
            i.setClass(context,SharedPrefsActivity.class);
            context.startActivity(i);


        }else{

            Toast toast1 =
                    Toast.makeText(context.getApplicationContext(),
                            "Usuario y/o contraseña incorrecta", Toast.LENGTH_SHORT);



            toast1.show();

        }



    }

    @Override
    protected void onCancelled() {

    }
}













