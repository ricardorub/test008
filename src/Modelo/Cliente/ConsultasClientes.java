/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Cliente;
import Modelo.Nodo;
/**
 *
 * @author Omara Baylón
 */
public class ConsultasClientes {
    
    public Cliente buscarClienteSecuencial(ListaSimpleClientes lista, String nombre) {
        Nodo actual = lista.getInicio();
        while (actual != null) {
            Cliente c = (Cliente) actual.getDato();
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
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

        Cliente izq = (Cliente) izquierda.getDato();
        Cliente der = (Cliente) derecha.getDato();

        if (izq.getNombre().compareToIgnoreCase(der.getNombre()) <= 0) {
            resultado = izquierda;
            resultado.setSiguiente(fusionar(izquierda.getSiguiente(), derecha));
        } else {
            resultado = derecha;
            resultado.setSiguiente(fusionar(izquierda, derecha.getSiguiente()));
        }

        return resultado;
    }
    
    private void reconstruir(ListaSimpleClientes lista, Nodo nuevaCabeza) {
        lista.vaciar();
        
        Nodo actual = nuevaCabeza;
        while (actual != null) {
            Cliente c = (Cliente) actual.getDato();
            lista.agregarAlFinal(new Cliente(c));
            actual = actual.getSiguiente();
        }
    }
    
    public void ordenarClientesPorDniMergeSort(ListaSimpleClientes lista) {
        Nodo cabezaOrdenada = mergeSort(lista.getInicio());
        reconstruir(lista, cabezaOrdenada);
    }
    
    
    public Cliente buscarClienteBinaria(ListaSimpleClientes lista, String dni) {
        Object[] arreglo = lista.mostrarTodos();
        int izquierda = 0;
        int derecha = arreglo.length - 1;

        while (izquierda <= derecha) {
            int medio = (izquierda + derecha) / 2;
            Cliente c = (Cliente) arreglo[medio];

            int comparacion = c.getDni().compareToIgnoreCase(dni);
            if (comparacion == 0) {
                return c;
            } else if (comparacion < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return null;
    }
    
}
