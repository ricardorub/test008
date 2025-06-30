/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Cliente;

import Modelo.MetodosABB;
import Modelo.NodoABB;

/**
 *
 * @author Omara Baylòn
 */
public class ArbolCliente implements MetodosABB{
    
    private NodoABB raiz;
    private int contador = 0;

    public ArbolCliente() {
        this.raiz = null;
    }

    @Override
    public void insertar(Object dato) {
        Cliente cliente = (Cliente) dato;
        if (raiz == null) {
            raiz = new NodoABB(cliente);
        } else {
            insertarRecursivo(raiz, cliente);
        }
    }
    
    private void insertarRecursivo(NodoABB nodo, Cliente cliente) {
        Cliente actual = (Cliente) nodo.getDato();

        if (cliente.getDni().compareToIgnoreCase(actual.getDni()) < 0) {
            if (nodo.getIzquierda() == null) {
                nodo.setIzquierda(new NodoABB(cliente));
            } else {
                insertarRecursivo(nodo.getIzquierda(), cliente);
            }
        } else if (cliente.getDni().compareToIgnoreCase(actual.getDni()) > 0) {
            if (nodo.getDerecha() == null) {
                nodo.setDerecha(new NodoABB(cliente));
            } else {
                insertarRecursivo(nodo.getDerecha(), cliente);
            }
        }
    }

    @Override
    public Object buscar(String dni) {
        return buscarRecursivo(raiz, dni);
    }
    
    private Object buscarRecursivo(NodoABB nodo, String dni) {
        if (nodo == null) {
            return null;
        }

        Cliente cliente = (Cliente) nodo.getDato();

        if (dni.equalsIgnoreCase(cliente.getDni())) {
            return cliente;
        } else if (dni.compareToIgnoreCase(cliente.getDni()) < 0) {
            return buscarRecursivo(nodo.getIzquierda(), dni);
        } else {
            return buscarRecursivo(nodo.getDerecha(), dni);
        }
    }

    @Override
    public void eliminar(String dni) {
        raiz = eliminarRecursivo(raiz, dni);
    }
    
    private NodoABB eliminarRecursivo(NodoABB nodo, String dni) {
        if (nodo == null) {
            return null;
        }

        Cliente cliente = (Cliente) nodo.getDato();

        if (dni.compareToIgnoreCase(cliente.getDni()) < 0) {
            nodo.setIzquierda(eliminarRecursivo(nodo.getIzquierda(), dni));
        } else if (dni.compareToIgnoreCase(cliente.getDni()) > 0) {
            nodo.setDerecha(eliminarRecursivo(nodo.getDerecha(), dni));
        } else {
            if (nodo.getIzquierda() == null && nodo.getDerecha() == null) {
                return null;
            }
            if (nodo.getIzquierda() == null) {
                return nodo.getDerecha();
            } else if (nodo.getDerecha() == null) {
                return nodo.getIzquierda();
            }
            NodoABB sucesor = encontrarMinimo(nodo.getDerecha());
            nodo.setDato(sucesor.getDato());
            nodo.setDerecha(eliminarRecursivo(nodo.getDerecha(), ((Cliente) sucesor.getDato()).getDni()));
        }

        return nodo;  
    }
    
    private NodoABB encontrarMinimo(NodoABB nodo) {
        while (nodo.getIzquierda() != null) {
            nodo = nodo.getIzquierda();
        }
        return nodo;
    }
    
    @Override
    public Object[] obtenerEnOrden() {
        int total = contarNodos(raiz);
        Object[] clientes = new Object[total];
        contador = 0;
        recorrerEnOrden(raiz, clientes);
        return clientes;
    }
    
    private void recorrerEnOrden(NodoABB nodo, Object[] arreglo) {
        if (nodo != null) {
            recorrerEnOrden(nodo.getIzquierda(), arreglo);
            arreglo[contador++] = nodo.getDato();
            recorrerEnOrden(nodo.getDerecha(), arreglo);
        }
    }
    
    private int contarNodos(NodoABB nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.getIzquierda()) + contarNodos(nodo.getDerecha());
    }
    
    @Override
    public NodoABB getRaiz() {
        return raiz;
    }

}
