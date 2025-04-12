package bd_instituto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionAlumnos {

    //meno de las opciones para modificar
    public static void menu() {
        System.out.println("\n==========================");
        System.out.println("=====Modificar Alumno====");
        System.out.println("==========================");
        System.out.println("___Opciones___");
        System.out.println("1.Nombre");
        System.out.println("2.Nota media");
        System.out.println("3.Curso");
        System.out.println("4.Salir");
    }

    //Metodos de manipulacion, edicion y revision de la base de datos
    //mostrar un listado de todos los alumnos
    public static void verAlumnos() {
        Connection conexion = ConexionBaseDatos.getConnection();
        if (conexion != null) {
            try {
                String listar = "SELECT * FROM alumno";
                Statement stmt = conexion.createStatement();
                ResultSet resultado = stmt.executeQuery(listar);

                while (resultado.next()) {
                    System.out.println("Id: " + resultado.getInt("id"));
                    System.out.println("Nombre: " + resultado.getNString("Nombre"));
                    System.out.println("Nota media: " + resultado.getFloat("Nota_media"));
                    System.out.println("Curso: " + resultado.getNString("Curso"));
                }
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }
    }

    //agregar a un alumno nuevo a la base de datos
    public static void agregarAlumno(Scanner in) {
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

    //eliminar a un alumno nuevo a la base de datos
    public static void eliminarAluumno(Scanner in) {
        Connection conexion = ConexionBaseDatos.getConnection();
        try {
            System.out.print("Cual es el id del usuario que desea eliminar: ");
            int iId = in.nextInt();

            var stmt = conexion.prepareStatement("DELETE FROM alumno WHERE alumno.id = ?");
            stmt.setInt(1, iId);
            stmt.execute();
            System.out.println("Alumno eliminado correctamente");

        } catch (InputMismatchException e) {
            System.out.println("ha ocurrido un error al ingresar el numero: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error en la base de datos: " + e.getMessage());
        }
    }

    //actualizar un valir de un alumno de la base de datos
    public static void modificarAlumno(Scanner in) {
        Connection conexion = ConexionBaseDatos.getConnection();
        try {
            System.out.print("Cual es el id del usuario que desea modificar: ");
            int iId = in.nextInt();

            menu();
            System.out.print("Que valo va a modificar: ");
            int opci = in.nextInt();
            in.nextLine();

            switch (opci) {
                case 1 -> {
                    String nombre = Alumno.setsNombre(in);
                    var stmt = conexion.prepareStatement("UPDATE alumno SET Nombre=? WHERE id=?");
                    stmt.setString(1, nombre);
                    stmt.setInt(2, iId);
                    stmt.execute();
                }
                case 2 ->{
                    float nota = Alumno.setfNota(in);
                    var stmt = conexion.prepareStatement("UPDATE alumno SET Nota_media=? WHERE id=?");
                    stmt.setFloat(1, nota);
                    stmt.setInt(2, iId);
                    stmt.execute();
                }
                
                case 3 ->{
                    String curso = Alumno.setsCurso(in);
                    var stmt = conexion.prepareStatement("UPDATE alumno SET Curso=? WHERE id=?");
                    stmt.setString(1, curso);
                    stmt.setInt(2, iId);
                    stmt.execute();
                }
                case 4 ->{System.out.println("Saliendo...");}
                default -> System.out.println("Opcion inexistente :/");
            }
            
        } catch (InputMismatchException e) {
            System.out.println("ha ocurrido un error al ingresar el numero: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error en la base de datos: " + e.getMessage());
        }
    }
}
