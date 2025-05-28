package bd_instituto;

import java.util.Scanner;

public class BD_Instituto {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        GestionAlumnos.agregarAlumno(in);
        GestionAlumnos.eliminarAluumno(in);
        GestionAlumnos.modificarAlumno(in);
        GestionAlumnos.verAlumnos();
    }
}
