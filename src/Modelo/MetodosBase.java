    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

/**
 *
 * @author Alicia Huamani sin commit
 */
public interface MetodosBase {

    void agregar(Object obj);

    void editar(Object criterio);

    void mostrar();

    Object buscar(Object criterio);

    void eliminar(Object criterio);
}
