package Modelo.CheckOut;

//Autor : Ricardo Ruben Gutierrez Espinoza

import Modelo.NodoABB; 
import Modelo.Reservas.Reserva;

import Modelo.Cliente.ListaSimpleClientes; 

public class ArbolCheckOut {

    private NodoABB raiz;

    public ArbolCheckOut() {
        this.raiz = null;
    }

    public NodoABB getRaiz() {
        return raiz;
    }

    // --- Inserción using Reservation ID (int) ---
    public void insertar(Reserva reserva) {
        if (reserva == null) {
            System.err.println("Error: Reserva nula, no se puede insertar.");
            return;
        }
        // Reserva ID is the key
        int reservaIdKey = reserva.getId();
        if (reservaIdKey <= 0) { // Assuming IDs are positive
            System.err.println("Error: ID de Reserva inválido (" + reservaIdKey + "), no se puede insertar.");
            return;
        }
        this.raiz = insertarRecursivo(this.raiz, reserva, reservaIdKey);
    }

    private NodoABB insertarRecursivo(NodoABB nodoActual, Reserva reserva, int reservaIdKey) {
        if (nodoActual == null) {
            return new NodoABB(reserva); // Store Reserva object directly
        }

        int currentIdInNode = ((Reserva) nodoActual.dato).getId();

        if (reservaIdKey < currentIdInNode) {
            nodoActual.izquierda = insertarRecursivo(nodoActual.izquierda, reserva, reservaIdKey);
        } else if (reservaIdKey > currentIdInNode) {
            nodoActual.derecha = insertarRecursivo(nodoActual.derecha, reserva, reservaIdKey);
        } else {
            // ID ya existe, manejar como se requiera (e.g., actualizar o ignorar)
            System.out.println("Advertencia: Ya existe una reserva con ID " + reservaIdKey + " en el árbol de CheckOut.");
        }
        return nodoActual;
    }

    // --- Búsqueda using Reservation ID (int) ---
    public Reserva buscarPorId(int reservaIdKey) {
        if (reservaIdKey <= 0) { // Assuming IDs are positive
             System.err.println("Advertencia: ID de Reserva para búsqueda es inválido (" + reservaIdKey + ").");
            return null;
        }
        NodoABB resultado = buscarRecursivo(this.raiz, reservaIdKey);
        return (resultado == null) ? null : (Reserva) resultado.dato;
    }

    private NodoABB buscarRecursivo(NodoABB nodoActual, int reservaIdKey) {
        if (nodoActual == null) {
            return null;
        }
        int currentIdInNode = ((Reserva) nodoActual.dato).getId();

        if (reservaIdKey == currentIdInNode) {
            return nodoActual;
        } else if (reservaIdKey < currentIdInNode) {
            return buscarRecursivo(nodoActual.izquierda, reservaIdKey);
        } else {
            return buscarRecursivo(nodoActual.derecha, reservaIdKey);
        }
    }

    // --- Eliminación using Reservation ID (int) ---
    public void eliminarPorId(int reservaIdKey) {
         if (reservaIdKey <= 0) { // Assuming IDs are positive
            System.err.println("Advertencia: ID de Reserva para eliminación es inválido (" + reservaIdKey + ").");
            return;
        }
        this.raiz = eliminarRecursivo(this.raiz, reservaIdKey);
    }

    private NodoABB eliminarRecursivo(NodoABB nodoActual, int reservaIdKey) {
        if (nodoActual == null) {
            return null; 
        }
        
        int currentIdInNode = ((Reserva) nodoActual.dato).getId();

        if (reservaIdKey < currentIdInNode) {
            nodoActual.izquierda = eliminarRecursivo(nodoActual.izquierda, reservaIdKey);
        } else if (reservaIdKey > currentIdInNode) {
            nodoActual.derecha = eliminarRecursivo(nodoActual.derecha, reservaIdKey);
        } else {
            // Nodo encontrado, proceder a eliminar
            if (nodoActual.izquierda == null && nodoActual.derecha == null) { // Caso 1: Nodo hoja
                return null;
            }
            if (nodoActual.izquierda == null) { // Caso 2: Nodo con un solo hijo (derecho)
                return nodoActual.derecha;
            }
            if (nodoActual.derecha == null) { // Caso 2: Nodo con un solo hijo (izquierdo)
                return nodoActual.izquierda;
            }
            // Caso 3: Nodo con dos hijos
            NodoABB sucesor = encontrarMinimo(nodoActual.derecha);
            nodoActual.dato = sucesor.dato; // Copiar el dato del sucesor
            // Eliminar el sucesor de la subrama derecha (usando el ID del sucesor)
            nodoActual.derecha = eliminarRecursivo(nodoActual.derecha, ((Reserva) sucesor.dato).getId());
        }
        return nodoActual;
    }

    private NodoABB encontrarMinimo(NodoABB nodo) {
        while (nodo.izquierda != null) {
            nodo = nodo.izquierda;
        }
        return nodo;
    }
    
    // --- Obtener Todas las Reservas Activas (In-Order Traversal) ---
    // This method's utility might change if the primary lookup is always by ID,
    // but it's kept for completeness or potential debugging.
    public Object[] obtenerTodasLasReservasActivas() {
        ListaSimpleClientes listaTemporal = new ListaSimpleClientes(); 
        recorridoEnOrden(this.raiz, listaTemporal);
        return listaTemporal.mostrarTodos(); 
    }

    private void recorridoEnOrden(NodoABB nodo, ListaSimpleClientes lista) {
        if (nodo != null) {
            recorridoEnOrden(nodo.izquierda, lista);
            if (nodo.dato instanceof Reserva) { 
                lista.agregarAlFinal(nodo.dato); 
            }
            recorridoEnOrden(nodo.derecha, lista);
        }
    }
    
    public boolean estaVacio() {
        return this.raiz == null;
    }
}
