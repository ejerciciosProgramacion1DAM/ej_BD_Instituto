package bd_instituto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GestionAlumnos {
    //atributos
    private int iId;
    private float fNota;
    private String sNombre;
    private String sCurso;

    //constructor
    public GestionAlumnos(int iId, float fNota, String sNombre, String sCurso) {
        this.iId = iId;
        this.fNota = fNota;
        this.sNombre = sNombre;
        this.sCurso = sCurso;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public void setfNota(float fNota) {
        this.fNota = fNota;
    }

    public String setsNombre(String sNombre, Scanner entrada) {
        System.out.print("Introduzca el nombre: ");
        sNombre = entrada.nextLine();

        return this.sNombre;
    }

    public void setsCurso(String sCurso) {
        this.sCurso = sCurso;
    }

    public void verAlumnos() {
        Connection conexion = ConexionBaseDatos.getConnection();
        if (conexion != null) {
            try {
                String listar = "SELECT * FROM contacto";
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
                var stmt = conexion.prepareStatement("INSERT INTO alumno (Nombre,Nota_media,Curso) values (?,?,?)");
                stmt.setString(1, this.sNombre);
                stmt.setFloat(2, this.fNota);
                stmt.setString(3, this.sCurso);
                
            } catch (Exception e) {
            }
        }
    }

}
