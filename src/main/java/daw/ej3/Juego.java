package daw.ej3;

import daw.ej1.Utilidades;
import daw.ej2.Jugador;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * @author Alberto López Puertas
 * <alopezp90@gmail.com>
 */
public class Juego {

    private static Scanner sc = new Scanner(System.in);

    private static Jugador creaJugador() {
        String nombre;
        String apellido;
        LocalDate fecha;
        boolean eleccion;
        System.out.println("Introduce nombre:");
        nombre = sc.nextLine();
        System.out.println("Introduce apellido:");
        apellido = sc.nextLine();
        try {
            fecha = LocalDate.of(pideAno(), pideMes(), pideDia());
        } catch (DateTimeException dte) {
            fecha = LocalDate.now(); //El constructor corregira el problema
            System.out.println("La fecha introducida no tiene el formato correcto(null)");
        }
        eleccion = elige();

        return new Jugador(nombre, apellido, fecha, eleccion);
    }

    private static int pideAno() {
        System.out.println("Introduce año de nacimineto:");
        return Utilidades.leerEnteroRango(1900, LocalDate.now().getYear());
    }

    private static int pideMes() {
        System.out.println("Introduce mes de nacimiento (1-12):");
        return Utilidades.leerEnteroRango(1, 12);
    }

    private static int pideDia() {
        System.out.println("Introduce dia de nacimineto (1-31):");
        return Utilidades.leerEnteroRango(1, 31);
    }

    private static boolean elige() {
        System.out.println("Eleccion (1-pares, 2-nones):");
        return Utilidades.leerEnteroRango(1, 2) == 1;
    }

    private static int sacarDedosMaquina() {
        return Utilidades.intAleatorios(0, 10);
    }

    private static int contarRondasGanadasJugador(char[] array) {
        int contador = 0;
        for (char caracter : array) {
            if (caracter == 'J') {
                contador++;
            }
        }
        return contador;
    }

    public static void main(String[] args) {
        final int K = 10; //???
        Jugador jugador = creaJugador();
        System.out.println("El jugador creado es:");
        System.out.println(jugador.toString());
        System.out.println("------------------------------");
        
        char[] resultado = new char[K];

        int dJugador;
        int dMaquina;

        //Rondas de juego
        for (int i = 0;
                i < K;
                i++) {
            //Jugador y maquina sacan dedos
            dJugador = jugador.sacarDedos();
            dMaquina = sacarDedosMaquina();

            System.out.println("El jugador ha sacado " + dJugador + " dedos.");
            System.out.println("La máquina ha sacado " + dMaquina + " dedos.");

            //El jugador gana si la suma es par y ha elegido pares(true) o
            //la suma es impar y ha elegido nones(impar)
            if (((dJugador + dMaquina) % 2 == 0 && jugador.isEleccion())
                    || (dJugador + dMaquina) % 2 != 0 && !jugador.isEleccion()) {
                resultado[i] = 'J';
                System.out.println("Gana el Jugador");
            } else {
                resultado[i] = 'M';
                System.out.println("Gana la Máquina");
            }
        }
        //Resultado del juego
        int puntos = contarRondasGanadasJugador(resultado);
        System.out.println(puntos + " - " + (K - puntos));
        if (puntos > K - puntos) {
            System.out.println("EL JUGADOR GANA LA PARTIDA");
        } else if (puntos < K - puntos) {
            System.out.println("LA MÁQUINA GANA LA PARTIDA");
        } else {
            System.out.println("HA HABIDO EMPATE");
        }
    }
}
