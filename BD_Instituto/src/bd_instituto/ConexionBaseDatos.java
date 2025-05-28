package bd_instituto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionBaseDatos {
    private static final String URL="jdbc:mysql://158.179.213.25:3307/classicmodels";
    private static final String USER="root";
    private static final String PASSWORD="root";
    
    public static Connection getConnection(){
        Connection conexion=null;
        
        try {
            conexion=DriverManager.getConnection(URL,USER,PASSWORD);

        } catch (SQLException e) {
            System.out.println("Error en la conexion: "+e.getMessage());
        }
        return conexion;
    }
}
