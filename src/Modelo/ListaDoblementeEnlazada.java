/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

/**
 *
 * @author Alicia Huamani
 */
public interface ListaDoblementeEnlazada {

    public void agregarAlFinal(Object objeto);

    public Object buscarPorId(int id);

    public boolean eliminarPorId(int id);

    public Object[] mostrarTodos();
    
    public boolean estaVacia();
    
    public void vaciar();
}
