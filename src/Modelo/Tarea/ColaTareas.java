package Modelo.Tarea;

import Modelo.Cola;
import Modelo.Nodo;

/**
 *
 * @author Alicia Huamani
 */
public class ColaTareas implements Cola {

    private Nodo frente;
    private Nodo fin;

    @Override
    public void encolar(Object obj) throws IllegalArgumentException {
        Tarea tarea = (Tarea) obj;
        Nodo nuevo = new Nodo(tarea);

        if (fin == null) {
            frente = fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    @Override
    public void desencolar() {
        if (frente == null) {
            return;
        }
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }
    }

    @Override
    public int contarNodos() {
        Nodo actual = frente;
        int i = 0;
        while (actual != null) {
            i++;
            actual = actual.getSiguiente();
        }
        return i;
    }

    @Override

    public Nodo getFrente() {
        return frente;
    }

    @Override

    public boolean estaVacio() {
        return frente == null;
    }

}

