/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        if (!(obj instanceof Tarea)) {
            // Optional: Add a check to ensure obj is a Tarea, though current code casts directly.
            // throw new IllegalArgumentException("El objeto a encolar debe ser una instancia de Tarea.");
        }
        Tarea tarea = (Tarea) obj;
        Nodo nuevo = new Nodo(tarea);

        if (fin == null) { // If queue is empty
            frente = fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    @Override
    public void desencolar() {
        if (frente == null) { // If queue is empty
            return;
        }
        frente = frente.getSiguiente();
        if (frente == null) { // If queue became empty after dequeuing
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
    public Nodo getFrente() { // Reverted to return Nodo to fix OperacionesColas
        return this.frente;
    }

    @Override
    public boolean estaVacio() {
        return frente == null;
    }

    @Override
    public Object[] mostrarTodos() {
        if (estaVacio()) {
            return new Object[0]; // Return an empty array if the queue is empty
        }

        int cantidad = contarNodos();
        Object[] todos = new Object[cantidad];
        Nodo actual = frente;
        int i = 0;
        while (actual != null && i < cantidad) { 
            todos[i] = actual.getDato(); 
            actual = actual.getSiguiente();
            i++;
        }
        return todos;
    }
}


