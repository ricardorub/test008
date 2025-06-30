/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


/**
 *
 * @author Alicia Huamani
 */
public interface MetodosABB {

    public void insertar(Object dato);
    
    public Object buscar(String dni);
    
    public void eliminar(String dni);
    
    public Object[] obtenerEnOrden();
    
    NodoABB getRaiz();
}
