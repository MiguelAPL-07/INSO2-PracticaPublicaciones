/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Publicacion;
import modelo.Usuario;

/**
 *
 * @author PC
 */
@Local
public interface PublicacionFacadeLocal {

    void create(Publicacion publicacion);

    void edit(Publicacion publicacion);

    void remove(Publicacion publicacion);

    Publicacion find(Object id);

    List<Publicacion> findAll();

    List<Publicacion> findRange(int[] range);

    int count();
    
    List<Publicacion> obtenerPublicacionesUsuario(Usuario usuario);
    
    List<Publicacion> obtenerPublicacionesPorCategoria(String nombre);
    
    List<Publicacion> obtenerPublicacionesPorValoracionAsc();
    
    List<Publicacion> obtenerPublicacionesPorValoracionDesc();
}
