/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Habitacion.Habitacion;
import Modelo.Habitacion.ListaCircularHabitaciones;
import Modelo.Nodo;
import Modelo.NodoABB;

/**
 *
 * @author Tamara Alison
 */
public class ControladorHabitaciones {

    private ListaCircularHabitaciones listaHabitaciones;
    private NodoABB raiz;
    private Nodo pilaTop;
    private Nodo frenteCola;
    private Nodo finCola;

    public ControladorHabitaciones() {
        listaHabitaciones = new ListaCircularHabitaciones();
        raiz = null;
        pilaTop = null;
        frenteCola = null;
        finCola = null;

        // 🔽 Agregar habitaciones de ejemplo
        Habitacion habitacion1 = new Habitacion(101, "Individual", true);
        Habitacion habitacion2 = new Habitacion(102, "Doble", false);
        Habitacion habitacion3 = new Habitacion(201, "Suite", true);
        Habitacion habitacion4 = new Habitacion(202, "Triple", true);
        Habitacion habitacion5 = new Habitacion(203, "Doble", false);

        // Lista circular
        listaHabitaciones.agregarAlFinal(habitacion1);
        listaHabitaciones.agregarAlFinal(habitacion2);
        listaHabitaciones.agregarAlFinal(habitacion3);
        listaHabitaciones.agregarAlFinal(habitacion4);
        listaHabitaciones.agregarAlFinal(habitacion5);

        // Árbol binario de búsqueda
        insertarEnABB(habitacion1);
        insertarEnABB(habitacion2);
        insertarEnABB(habitacion3);
        insertarEnABB(habitacion4);
        insertarEnABB(habitacion5);

        // Pila (habitaciones más usadas)
        pushHabitacionMasUsada(habitacion2);
        pushHabitacionMasUsada(habitacion3);

        // Cola (habitaciones para limpieza)
        encolarHabitacionParaLimpieza(habitacion2);
        encolarHabitacionParaLimpieza(habitacion5);
    }

    public void agregarHabitacion(Habitacion h) {
        listaHabitaciones.agregarAlFinal(h);
    }

    public boolean eliminarHabitacion(int numero) {
        return listaHabitaciones.eliminarPorId(numero);
    }

    public Object[] mostrarHabitaciones() {
        return listaHabitaciones.mostrarTodos();
    }

    public Habitacion navegarHabitacionesSiguiente() {
        return listaHabitaciones.siguiente();
    }

    public Habitacion navegarHabitacionesAnterior() {
        return listaHabitaciones.anterior();
    }

    public void ordenarHabitacionesBurbuja() {
        listaHabitaciones.ordenarBurbuja();
    }

    public Habitacion buscarHabitacionSecuencial(int numero) {
        return (Habitacion) listaHabitaciones.buscarPorId(numero);
    }

    public void insertarEnABB(Habitacion h) {
        raiz = insertarEnABBRec(raiz, h);
    }

    private NodoABB insertarEnABBRec(NodoABB nodo, Habitacion h) {
        if (nodo == null) {
            return new NodoABB(h);
        }
        Habitacion actual = (Habitacion) nodo.getDato();
        if (h.getNumero() < actual.getNumero()) {
            nodo.setIzquierda(insertarEnABBRec(nodo.getIzquierda(), h));
        } else {
            nodo.setDerecha(insertarEnABBRec(nodo.getDerecha(), h));
        }
        return nodo;
    }

    public Habitacion buscarEnABB(int numero) {
        NodoABB temp = raiz;
        while (temp != null) {
            Habitacion h = (Habitacion) temp.getDato();
            if (numero == h.getNumero()) {
                return h;
            }
            temp = (numero < h.getNumero()) ? temp.getIzquierda() : temp.getDerecha();
        }
        return null;
    }

    public void pushHabitacionMasUsada(Habitacion h) {
        Nodo nuevo = new Nodo(h);
        nuevo.setSiguiente(pilaTop);
        pilaTop = nuevo;
    }

    public Habitacion popHabitacionMasUsada() {
        if (pilaTop == null) {
            return null;
        }
        Habitacion h = (Habitacion) pilaTop.getDato();
        pilaTop = pilaTop.getSiguiente();
        return h;
    }

    public void encolarHabitacionParaLimpieza(Habitacion h) {
        Nodo nuevo = new Nodo(h);
        if (frenteCola == null) {
            frenteCola = finCola = nuevo;
        } else {
            finCola.setSiguiente(nuevo);
            finCola = nuevo;
        }
    }

    public Habitacion desencolarHabitacionParaLimpieza() {
        if (frenteCola == null) {
            return null;
        }
        Habitacion h = (Habitacion) frenteCola.getDato();
        frenteCola = frenteCola.getSiguiente();
        if (frenteCola == null) {
            finCola = null;
        }
        return h;
    }
}
