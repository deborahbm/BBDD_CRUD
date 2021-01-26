package ciclos;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dealm
 */
public class Conexion {
    /**
     * con permite establecer la conexión con la bbdd.
     * driver indica la librería a utilizar.
     * user indica el nombre de usuario determinado en phpmyadmin
     * password indica la contraseña determinada en phpmyadmin
     * url indica la dirección de conexión con la bbdd.
     */
    private static Connection con;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user ="root";
    private static final String password ="";
    private static final String url ="jdbc:mysql://localhost:3306/ciclos";
    
    public Conexion (){
        
        con=null;
        try{
            //abre conexión con las variables declaradas
            Class.forName(driver);
            con=(Connection) DriverManager.getConnection(url, user, password);
            if(con!=null){
                System.out.println("Conectado a BBDD");
            }
        }catch (ClassNotFoundException | SQLException e){
            System.out.println("ERROR al conectar");
        }
    }

    public void desconectar() {
        //declara la conexión vacía.
        con = null;
    }

    public void crearTabla(String nombreTabla) throws SQLException {
        //declaración vacía de consulta jdbc
        Statement st = null;
        try {
            //conexión, declaración y ejecución de la consulta.
            st = con.createStatement();
            String sql = "CREATE TABLE " + nombreTabla + " ("
                    + "codigo    TEXT,"
                    + "nombre    VARCHAR (100),"
                    + "horas  TEXT)";
            st.execute(sql);
            System.out.println("Se ha creado la tabla: " + nombreTabla);
        } finally {
            //cierre de la consulta
            if (st != null) {
                st.close();
            }
        }

    }

    public void eliminarTabla(String nombreTabla) throws SQLException {
        Statement st = null;
        try {
            st = con.createStatement();
            String sql = "DROP TABLE IF EXISTS  " + nombreTabla + "";
            st.executeUpdate(sql);
            System.out.println("Se ha eliminado la tabla: " + nombreTabla);
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    public void insertarModulo(String nombreTabla, String codigo, String nombre, String horas) throws SQLException {
        Statement st = null;
        try {
            st = con.createStatement();           
            String sql = "INSERT INTO " + nombreTabla + " (codigo, nombre, horas) values ('"
                    + codigo + "','"
                    + nombre + "','"
                    + horas + "')";
            st.executeUpdate(sql);
            System.out.println("Se ha creado el módulo: " + codigo +" "+ nombre+" " + horas+ " en la tabla: " + nombreTabla);
        } finally {
            if (st != null) {
                st.close();
            }
        }

    }

    public void eliminarModulo(String nombreTabla, String codigo) throws SQLException {
        Statement st = null;
        try {
            st = con.createStatement();
            String sql = "DELETE FROM " + nombreTabla + " WHERE codigo='"+codigo+"'";
            st.executeUpdate(sql);
            System.out.println("Se ha eliminado el módulo: " + codigo +"en la tabla: " + nombreTabla);
        } finally {
            if (st != null) {
                st.close();
            }
        }

    }

}
