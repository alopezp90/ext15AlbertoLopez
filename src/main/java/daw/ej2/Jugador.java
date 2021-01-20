package daw.ej2;

import daw.ej1.Utilidades;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Alberto López Puertas
 * <alopezp90@gmail.com>
 */
public class Jugador {

    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private boolean eleccion; //true pares, false nones;

    private static int contadorInstancias = 0;
    private static final LocalDate DEFECTO = LocalDate.of(2000, 1, 1);

    //constructor por defecto
    public Jugador() {
        contadorInstancias++;
        this.nombre = "Juan Carlos";
        this.apellido = "Vico";
        this.fechaNacimiento = DEFECTO;
        //eleccion se inializa false por defecto (nones)
    }

    //constructor parametrizado
    public Jugador(String nombre, String apellido, LocalDate fecha, boolean eleccion) {
        contadorInstancias++;
        if (esMenor(fecha)) {
            this.nombre = "Juan Carlos";
            this.apellido = "Vico";
            this.fechaNacimiento = DEFECTO;
            //eleccion se inializa false por defecto (nones)
        } else {
            this.nombre = nombre;
            this.apellido = apellido;
            this.fechaNacimiento = fecha;
            this.eleccion = eleccion;
        }
    }

    //constructor para copiar jugadores
    public Jugador(Jugador jugador) {
        contadorInstancias++;
        this.nombre = jugador.getNombre();
        this.apellido = jugador.getApellido();
        this.fechaNacimiento = jugador.getFechaNacimiento();
        this.eleccion = jugador.isEleccion();
    }

    //metodo auxiliar del constructor
    //devuelve true si es menor o fecha erronea
    private static boolean esMenor(LocalDate fecha) {
        return Utilidades.calcularYear(fecha) < 18;
    }
    
    //metodo auxiliar para toString
    private static String eleccionString(boolean eleccion) {
        if (eleccion) {
            return "Pares";
        } else {
            return "Nones";
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (!esMenor(fechaNacimiento)) {
            this.fechaNacimiento = fechaNacimiento;
        }
    }

    public boolean isEleccion() {
        return eleccion;
    }
    
    public String getEleccion() {
        return eleccionString(this.eleccion);
    }

    public void setEleccion(boolean eleccion) {
        this.eleccion = eleccion;
    }

    //consulta contador de instancias
    public static int getContadorInstancias() {
        return contadorInstancias;
    }
    
    //consulta edad
    public int getEdad() {
        return Utilidades.calcularYear(this.fechaNacimiento);
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
        return "Nombre: " + this.nombre + "\tApellido: " + this.apellido +
                "\nFecha: " + this.fechaNacimiento.format(formato) +
                "\tElección: " + eleccionString(this.eleccion);
    }
    
    public int sacarDedos() {
        return Utilidades.intAleatorios(0, 10);
    }
}
