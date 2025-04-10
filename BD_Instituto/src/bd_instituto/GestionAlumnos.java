package bd_instituto;

import java.awt.font.NumericShaper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionAlumnos {

    //atributos
    private float fNota;
    private String sNombre;
    private String sCurso;

    //constructor
    public GestionAlumnos() {
        this.fNota = 0;
        this.sNombre = "root";
        this.sCurso = "1A";
    }

    //metodos para declarar las variables
    public static float setfNota(float fNota, Scanner in) {
        boolean stay = true;
        try {
            while (stay) {

                System.out.print("Introduce la nota media que has sacado en este curso (1-10):");
                fNota = in.nextFloat();

                if (fNota < 1 || fNota > 10) {
                    System.out.println("La nota no está en el rango de 1 a 10.");
                }
                else{
                    stay = false;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("No has introducido un numero");
        }

        return fNota;
    }

    public static String setsNombre(String sNombre, Scanner in) {
        System.out.print("Introduzca el nombre: ");
        sNombre = in.nextLine();
        return sNombre;
    }

    public static String setsCurso(String sCurso, Scanner in) {
        return sCurso;
    }

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
        Scanner in = new Scanner(System.in);

        if (conexion != null) {
            try {
                var stmt = conexion.prepareStatement("INSERT INTO alumno (Nombre,Nota_media,Curso) values (?,?,?)");
                String nombre=GestionAlumnos.setsNombre(sNombre, in);
                String curso=GestionAlumnos.setsCurso(sCurso, in);
                float nota=GestionAlumnos.setfNota(fNota, in);
                
                stmt.setString(1, nombre);
                stmt.setFloat(2, nota);
                stmt.setString(3, curso);
                System.out.println("sa");
            } catch (SQLException e) {
                System.out.println("ha ocurrido un error: " + e.getMessage());
            }
        }
    }

}
