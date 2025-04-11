package bd_instituto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GestionAlumnos {

    //atributos
    Scanner in = new Scanner(System.in);

    //metodos de manipulacion, edicion y revision de la base de datos
    public void verAlumnos() {
        Connection conexion = ConexionBaseDatos.getConnection();
        if (conexion != null) {
            try {
                String listar = "SELECT * FROM alumno";
                Statement stmt = conexion.createStatement();
                ResultSet resultado = stmt.executeQuery(listar);

                while (resultado.next()) {
                    System.out.println("Id: " + resultado.getInt("id"));
                    System.out.println("Nombre: " + resultado.getNString("Nombre"));
                    System.out.println("Nota media: " + resultado.getInt("Nota_media"));
                    System.out.println("Curso: " + resultado.getNString("Curso"));
                }
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }
    }

    public void agregarAlumno() {
        Connection conexion = ConexionBaseDatos.getConnection();

        if (conexion != null) {
            try {
                String nombre = Alumno.setsNombre(in);
                float nota = Alumno.setfNota(in);
                String curso = Alumno.setsCurso(in);

                var stmt = conexion.prepareStatement("INSERT INTO alumno (Nombre, Nota_media, Curso) VALUES (?,?,?);");
                stmt.setString(1, nombre);
                stmt.setFloat(2, nota);
                stmt.setString(3, curso);
                stmt.execute();
                System.out.println("Alumno añadido correctamente");
            } catch (SQLException e) {
                System.out.println("ha ocurrido un error: " + e.getMessage());
            }
        }
    }

    public void eliminarAluumno() {
        try {
            System.out.print("Cual es el id del usuario qeu desea eliminar: ");
            int iId = in.nextInt();

        } catch (Exception e) {
        }
    }

    public void modificarAluumno() {

    }
}
