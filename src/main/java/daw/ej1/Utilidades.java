package daw.ej1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Alberto López Puertas
 * <alopezp90@gmail.com>
 */
public class Utilidades {

    //atributos
    private static Random rd = new Random();
    private static Scanner sc = new Scanner(System.in);

    //metodos
    public static int intAleatorios(int min, int max) {
        return rd.nextInt(max - min + 1) + min;
    }

    public static int leerEnteroRango(int min, int max) {
        boolean valido = false;
        int enteroRango = Integer.MIN_VALUE;
        while (!valido) {
            try {
                enteroRango = sc.nextInt();
                if (enteroRango >= min && enteroRango <= max) {
                    valido = true;
                }
            } catch (InputMismatchException ime) {
                sc.nextLine();
            }
        }
        return enteroRango;
    }

    public static int calcularYear(LocalDate fecha) {
        if (fecha.isBefore(LocalDate.now())) {
            return (int) ChronoUnit.YEARS.between(fecha, LocalDate.now());
        } else {
            return -1;
        }

    }

}
