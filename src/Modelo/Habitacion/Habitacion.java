/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Modelo.Habitacion;

/**
 *
 * @author carlospalominovidal
 */

public class Habitacion {
    int numero;
    String tipo;
    boolean disponible;
    
    public Habitacion(int numero, String tipo, boolean disponible) {
        this.numero = numero;
        this.tipo = tipo;
        this.disponible = disponible;
    }
    
    public Habitacion(Habitacion otraHabitacion) {
        this.numero = otraHabitacion.numero;
        this.tipo = otraHabitacion.tipo;
        this.disponible = otraHabitacion.disponible;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

}
