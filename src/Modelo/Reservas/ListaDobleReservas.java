/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Reservas;

import Modelo.ListaDoblementeEnlazada;
import Modelo.Nodo;

/**
 *
 * @author Alicia Huamani
 */
public class ListaDobleReservas implements ListaDoblementeEnlazada {

    private Nodo inicio;
    private Nodo fin;

    public ListaDobleReservas() {
        inicio = fin = null;
    }

    public Nodo getInicio() {
        return inicio;
    }

    @Override
    public void agregarAlFinal(Object objeto) {
        if (!(objeto instanceof Reserva)) {
            throw new IllegalArgumentException("Solo se aceptan objetos de tipo Reserva");
        }
        Nodo nuevo = new Nodo(objeto);

        if (inicio == null) {
            inicio = fin = nuevo;
        } else {
            nuevo.setAnterior(fin);
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    @Override
    public Object buscarPorId(int id) {
        Nodo actual = inicio;
        while (actual != null) {
            Reserva reserva = (Reserva) actual.getDato();
            if (reserva.getId() == id) {
                return reserva;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    @Override
    public boolean eliminarPorId(int id) {
        Nodo actual = inicio;

        while (actual != null) {
            Reserva reserva = (Reserva) actual.getDato();

            if (reserva.getId() == id) {

                if (inicio == fin) {
                    inicio = fin = null;
                } else if (actual == inicio) {
                    inicio = inicio.getSiguiente();
                    inicio.setAnterior(null);
                } else if (actual == fin) {
                    fin = fin.getAnterior();
                    fin.setSiguiente(null);
                } else {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                }
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    @Override
    public Object[] mostrarTodos() {
        int count = 0;
        Nodo temp = inicio;
        while (temp != null) {
            count++;
            temp = temp.getSiguiente();
        }

        Object[] reservas = new Object[count];

        temp = inicio;
        int index = 0;
        while (temp != null) {
            reservas[index++] = temp.getDato();
            temp = temp.getSiguiente();
        }

        return reservas;
    }

    @Override
    public boolean estaVacia() {
        return inicio == null;
    }

    public int contar() {
        int i = 0;
        Nodo temp = inicio;

        while (temp != null) {
            i++;
            temp = temp.getSiguiente();
        }
        return i;
    }

    @Override
    public void vaciar() {
        inicio = null;
        fin = null;

    }
}
