
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Cliente;

import Modelo.Nodo;
import Modelo.ListaSimpleEnlazada;

/**
 *
 * @author Omara Baylón
 */
public class ListaSimpleClientes implements ListaSimpleEnlazada {

    private Nodo inicio;

    public ListaSimpleClientes() {
        this.inicio = null;
    }

    public Nodo getInicio() {
        return inicio;
    }
    
    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }
    
    @Override
    public void agregarAlInicio(Object objeto) {
        if (!(objeto instanceof Cliente)) {
            throw new IllegalArgumentException("Solo se aceptan objetos de tipo Cliente");
        }
        Nodo nuevo = new Nodo(objeto);
        nuevo.setSiguiente(inicio);
        inicio = nuevo;
    }

    @Override
    public void agregarAlFinal(Object objeto) {
        if (!(objeto instanceof Cliente)) {
            throw new IllegalArgumentException("Solo se aceptan objetos de tipo Cliente");
        }
        Nodo nuevo = new Nodo(objeto);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Nodo temp = inicio;
            while (temp.getSiguiente() != null) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevo);
        }
    }

    @Override
    public Cliente buscarPorDni(String dni) {
        Nodo temp = inicio;
        while (temp != null) {
            Cliente cliente = (Cliente) temp.getDato();
            if (cliente.getDni().equalsIgnoreCase(dni)) {
                return cliente;
            }
            temp = temp.getSiguiente();
        }
        return null;
    }

    @Override
    public boolean eliminarPorDni(String dni) {
        Nodo actual = inicio;
        Nodo anterior = null;

        while (actual != null) {
            Cliente cliente = (Cliente) actual.getDato();
            if (cliente.getDni().equalsIgnoreCase(dni)) {
                if (anterior == null) { 
                    inicio = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                return true;
            }
            anterior = actual;
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

        Object[] clientes = new Object[count];

        temp = inicio;
        int index = 0;
        while (temp != null) {
            clientes[index++] = temp.getDato();
            temp = temp.getSiguiente();
        }

        return clientes;
    }

    @Override
    public boolean estaVacia() {
        return inicio == null;
    }
    
    @Override
    public int contar() {
        int count = 0;
        Nodo temp = inicio;
        while (temp != null) {
            count++;
            temp = temp.getSiguiente();
        }
        return count;
    }

    @Override
    public void vaciar() {
        inicio = null;
    }

}
