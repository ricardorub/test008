/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

/**
 *
 * @author Alicia Huamani
 */
public interface Cola {
    
    public void encolar(Object obj);
    public void desencolar();
    public int contarNodos();
    public Object getFrente();
    public boolean estaVacio();
    public Object[] mostrarTodos();
}
