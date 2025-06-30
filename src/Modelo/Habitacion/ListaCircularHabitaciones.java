/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Habitacion;
import Modelo.Nodo;
import Modelo.ListaHabitacionCircular;
/**
 *
 * @author Tamara Alison
 */
public class ListaCircularHabitaciones implements ListaHabitacionCircular {
    private Nodo inicio;
    private Nodo actual;

    public ListaCircularHabitaciones() {
        this.inicio = null;
        this.actual = null;
    }
     @Override
    public void agregarAlFinal(Object objeto) {
        if (!(objeto instanceof Habitacion)) {
            throw new IllegalArgumentException("Solo Habitacion");
        }
        Nodo nuevo = new Nodo(objeto);
        if (inicio == null) {
            inicio = nuevo;
            inicio.setSiguiente(inicio);
            inicio.setAnterior(inicio);
            actual = inicio;
        } else {
            Nodo ultimo = inicio.getAnterior();
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            nuevo.setSiguiente(inicio);
            inicio.setAnterior(nuevo);
        }
    }
    @Override
    public Object buscarPorId(int id) {
        if (inicio == null) return null;
        Nodo temp = inicio;
        do {
            Habitacion h = (Habitacion) temp.getDato();
            if (h.getNumero() == id) return h;
            temp = temp.getSiguiente();
        } while (temp != inicio);
        return null;
    }
     @Override
    public boolean eliminarPorId(int id) {
        if (inicio == null) return false;
        Nodo temp = inicio;
        do {
            Habitacion h = (Habitacion) temp.getDato();
            if (h.getNumero() == id) {
                if (temp == inicio && temp.getSiguiente() == inicio) {
                    inicio = null;
                } else {
                    temp.getAnterior().setSiguiente(temp.getSiguiente());
                    temp.getSiguiente().setAnterior(temp.getAnterior());
                    if (temp == inicio) inicio = temp.getSiguiente();
                }
                return true;
            }
            temp = temp.getSiguiente();
        } while (temp != inicio);
        return false;
    }
    @Override
    public Object[] mostrarTodos() {
        if (inicio == null) return new Object[0];
        int count = 0;
        Nodo temp = inicio;
        do {
            count++;
            temp = temp.getSiguiente();
        } while (temp != inicio);

        Object[] habitaciones = new Object[count];
        temp = inicio;
        int index = 0;
        do {
            habitaciones[index++] = temp.getDato();
            temp = temp.getSiguiente();
        } while (temp != inicio);
        return habitaciones;
    }
     @Override
    public boolean estaVacia() {
        return inicio == null;
    }
     @Override
    public void vaciar() {
        inicio = null;
        actual = null;
    }
    public Habitacion siguiente() {
        if (actual != null) {
            actual = actual.getSiguiente();
            return (Habitacion) actual.getDato();
        }
        return null;
    }
    public Habitacion anterior() {
        if (actual != null) {
            actual = actual.getAnterior();
            return (Habitacion) actual.getDato();
        }
        return null;
    }
    public void ordenarBurbuja() {
        if (inicio == null) return;
        boolean ordenado;
        do {
            ordenado = true;
            Nodo temp = inicio;
            do {
                Nodo sig = temp.getSiguiente();
                Habitacion h1 = (Habitacion) temp.getDato();
                Habitacion h2 = (Habitacion) sig.getDato();
                if (h1.getNumero() > h2.getNumero()) {
                    temp.setDato(h2);
                    sig.setDato(h1);
                    ordenado = false;
                }
                temp = temp.getSiguiente();
            } while (temp.getSiguiente() != inicio);
        } while (!ordenado);
    }
}
