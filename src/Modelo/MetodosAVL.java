/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


/**
 *
 * @author Alicia Huamani
 */
public interface MetodosAVL {

    public void insertar(Object param);
    
    public Object buscar(String dni);

    NodoAVL balancear(NodoAVL nodo);

    int altura(NodoAVL nodo);

    NodoAVL rotarDerecha(NodoAVL y);

    NodoAVL rotarIzquierda(NodoAVL x);

}
