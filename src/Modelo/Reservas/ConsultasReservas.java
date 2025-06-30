/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Reservas;

import Modelo.Nodo;

/**
 *
 * @author Alicia Huamani
 */
public class ConsultasReservas {

    public Reserva buscarPorNombreSecuencial(ListaDobleReservas lista, String nombreCliente) {
        Nodo actual = lista.getInicio();
        while (actual != null) {
            Reserva r = (Reserva) actual.getDato();
            if (r.getCliente().getNombre().equalsIgnoreCase(nombreCliente)) {
                return r;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public Nodo mergeSort(Nodo cabeza) {
        if (cabeza == null || cabeza.getSiguiente() == null) {
            return cabeza;
        }

        Nodo medio = dividir(cabeza);
        Nodo siguienteMedio = medio.getSiguiente();
        medio.setSiguiente(null);

        Nodo izquierda = mergeSort(cabeza);
        Nodo derecha = mergeSort(siguienteMedio);

        return fusionar(izquierda, derecha);

    }

    private Nodo dividir(Nodo cabeza) {
        Nodo lento = cabeza;
        Nodo rapido = cabeza.getSiguiente();

        while (rapido != null && rapido.getSiguiente() != null) {
            lento = lento.getSiguiente();
            rapido = rapido.getSiguiente().getSiguiente();
        }
        return lento;
    }

    private Nodo fusionar(Nodo izquierda, Nodo derecha) {
        Nodo resultado;

        if (izquierda == null) {
            return derecha;
        }
        if (derecha == null) {
            return izquierda;
        }

        Reserva izq = (Reserva) izquierda.getDato();
        Reserva der = (Reserva) derecha.getDato();

        if (izq.getId() <= der.getId()) {
            resultado = izquierda;
            resultado.setSiguiente(fusionar(izquierda.getSiguiente(), derecha));
        } else {
            resultado = derecha;
            resultado.setSiguiente(fusionar(izquierda, derecha.getSiguiente()));
        }

        return resultado;
    }
    
    private static void reconstruir(ListaDobleReservas lista, Nodo nuevaCabeza) {
        lista.vaciar();  

        Nodo actual = nuevaCabeza;
        while (actual != null) {
            Reserva r = (Reserva) actual.getDato();
            lista.agregarAlFinal(new Reserva(r));
            actual = actual.getSiguiente();
        }
    }
    
    public void ordenarPorIdMergeSort(ListaDobleReservas lista) {
        Nodo cabezaOrdenada = mergeSort(lista.getInicio());
        reconstruir(lista, cabezaOrdenada);
    }


    public  Reserva buscarPorIdBinaria(ListaDobleReservas lista, int id) {
        Object[] arreglo = lista.mostrarTodos();
        int izquierda = 0;
        int derecha = arreglo.length - 1;

        while (izquierda <= derecha) {
            int medio = (izquierda + derecha) / 2;
            Reserva r = (Reserva) arreglo[medio];

            if (r.getId() == id) {
                return r;
            } else if (r.getId() < id) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return null;
    }

}
