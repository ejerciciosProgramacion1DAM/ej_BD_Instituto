package bd_instituto;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Alumno {

    //metodos para declarar las variables
    public static float setfNota(Scanner in) {
        float fNota = 0;
        boolean estar, stay = true;
        do {
            estar = false;
            try {
                while (stay) {
                    System.out.print("Introduce la nota media que has sacado en este curso (1-10):");
                    fNota = in.nextFloat();

                    if (fNota < 1 || fNota > 10) {
                        System.out.println("La nota no esta en el rango de 1 a 10.");
                    } else {
                        stay = false;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("No has introducido un numero");
                in.nextLine();
                estar = true;
            }
        } while (estar);

        in.nextLine();
        return fNota;
    }

    public static String setsNombre(Scanner in) {
        String sNombre = "User";
        System.out.print("Introduce el nombre del alumno: ");
        sNombre = in.nextLine();

        // Recortar si excede los 30 caracteres
        if (sNombre.length() > 30) {
            sNombre = sNombre.substring(0, 30);
            System.out.println("El nombre ha sido acortado a: " + sNombre);
        }
        return sNombre;
    }

    public static String setsCurso(Scanner in) {
        String sCurso = "1A";
        boolean stay = true;
        try {
            while (stay) {
                System.out.print("Introduce el curso que esta cursando: ");
                sCurso = in.nextLine();
                if (sCurso.length() != 2) {
                    System.out.println("El curso es errorneo");
                } else {
                    stay = false;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("No has introducido un numero");
        }
        return sCurso;
    }
}
