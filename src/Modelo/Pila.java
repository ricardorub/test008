/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

/**
 *
 * @author Alicia Huamani
 */
public interface Pila {

    public boolean estaVacio();

    public void limpiar();

    public Object pop();

    public void push(Object obj);
    
    public Object[] ArreglodeDatos();
}
