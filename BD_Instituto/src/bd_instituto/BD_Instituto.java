package bd_instituto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BD_Instituto {

    public static void verAlumnos() {
        Connection conexion = ConexionBaseDatos.getConnection();
        if (conexion != null) {
            try {
                String listar = "SELECT customerNumber FROM customers LIMIT 2";
                Statement stmt = conexion.createStatement();
                ResultSet resultado = stmt.executeQuery(listar);

                while (resultado.next()) {
                    System.out.println("Id: " + resultado.getInt("customerNumber"));
                }
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        verAlumnos();
//        GestionAlumnos.agregarAlumno(in);
//        GestionAlumnos.eliminarAluumno(in);
//        GestionAlumnos.modificarAlumno(in);
//        GestionAlumnos.verAlumnos();
    }
}
