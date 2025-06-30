/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Cliente;
import Modelo.Nodo;
import Modelo.Cola;
/**
 *
 * @author USUARIO
 */
public class ColaTurnosClientes implements Cola{
    
    private Nodo frente;
    private Nodo fin;
    
    @Override
    public void encolar(Object obj)throws IllegalArgumentException {
        Cliente cliente = (Cliente) obj;
        Nodo nuevo = new Nodo(cliente);

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
    public Object getFrente() {
        return frente.getDato();
    }

    @Override
    public boolean estaVacio() {
        return frente == null;
    }

    @Override
    public Object[] mostrarTodos() {
        Object[] clientes = new Object[contarNodos()];
        Nodo actual = frente;
        int i = 0;
        while (actual != null) {
            clientes[i++] = actual.getDato();
            actual = actual.getSiguiente();
        }
        return clientes;
    }
    
}
