/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Cliente;
import Modelo.Pila;
import Modelo.Nodo;
/**
 *
 * @author USUARIO
 */
public class PilaModificacionesCliente implements Pila {
    
    private Nodo cima;

    public PilaModificacionesCliente() {
        this.cima = null;
    }

    @Override
    public boolean estaVacio() {
        return cima == null;
    }

    @Override
    public void limpiar() {
        Nodo actual = cima;
        while (actual != null) {
            Nodo siguiente = actual.getSiguiente();
            actual.setSiguiente(null);
            actual = siguiente;
        }
        cima = null;
    }

    @Override
    public Object pop() {
        if (cima == null) {
            throw new IllegalArgumentException("La pila está vacía");
        }
        Cliente cliente = (Cliente) cima.getDato();
        cima = cima.getSiguiente();
        return cliente;
    }

    @Override
    public void push(Object obj) {
        if (!(obj instanceof Cliente)) {
            throw new IllegalArgumentException("Solo se pueden apilar objetos de tipo Cliente.");
        }
        Cliente cliente = (Cliente) obj;
        Nodo nuevo = new Nodo(cliente);
        nuevo.setSiguiente(cima);
        cima = nuevo;
    }

    @Override
    public Object[] ArreglodeDatos() {
        int count = 0;
        Nodo actual = cima;
        while (actual != null) {
            count++;
            actual = actual.getSiguiente();
        }

        Object[] array = new Object[count];
        actual = cima;
        for (int i = 0; i < count; i++) {
            array[i] = actual.getDato();
            actual = actual.getSiguiente();
        }
        return array;
    }
    
}
