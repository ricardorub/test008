/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

/**
 *
 * @author LAB-USR-LNORTE
 */
public interface ListaSimpleEnlazada {
    void agregarAlInicio(Object objeto);
    void agregarAlFinal(Object objeto);
    Object buscarPorDni(String dni);
    boolean eliminarPorDni(String dni);
    Object[] mostrarTodos();
    boolean estaVacia();
    void vaciar();
    int contar();
}
