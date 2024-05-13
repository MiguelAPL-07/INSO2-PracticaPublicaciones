/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Publicacion;
import modelo.Usuario;

/**
 *
 * @author PC
 */
@Stateless
public class PublicacionFacade extends AbstractFacade<Publicacion> implements PublicacionFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PublicacionFacade() {
        super(Publicacion.class);
    }

    @Override
    public List<Publicacion> obtenerPublicacionesUsuario(Usuario usuario) {
        List<Publicacion> publicaciones = new ArrayList<>();
        try {
            // Consulta que se quiere realizar
            String consulta = "FROM Publicacion p WHERE p.persona.idPersona=:param1";
            
            // Crear consulta
            Query query = em.createQuery(consulta);
            
            // Cambiar parametros del WHERE
            query.setParameter("param1", usuario.getPersona().getIdPersona());
            
            // Ejecutar consulta
            publicaciones = query.getResultList();
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return publicaciones;
    }
    
    @Override
    public List<Publicacion> obtenerPublicacionesPorCategoria(String nombre) {
        List<Publicacion> publicaciones = new ArrayList<>();
        try {
            // Consulta que se quiere realizar
            String consulta = "FROM Publicacion p WHERE p.categoria.nombreCategoria=:param1";
            
            // Crear consulta
            Query query = em.createQuery(consulta);
            
            // Cambiar parametros del WHERE
            query.setParameter("param1", nombre);
            
            // Ejecutar consulta
            publicaciones = query.getResultList();
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return publicaciones;
    }

    @Override
    public List<Publicacion> obtenerPublicacionesPorValoracionAsc() {
        List<Publicacion> publicaciones = new ArrayList<>();
        try {
            // Consulta que se quiere realizar
            String consulta = "FROM Publicacion p ORDER BY valoracion ASC";
            
            // Crear consulta
            Query query = em.createQuery(consulta);
            
            // Ejecutar consulta
            publicaciones = query.getResultList();
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return publicaciones;
    }

    @Override
    public List<Publicacion> obtenerPublicacionesPorValoracionDesc() {
        List<Publicacion> publicaciones = new ArrayList<>();
        try {
            // Consulta que se quiere realizar
            String consulta = "FROM Publicacion p ORDER BY valoracion DESC";
            
            // Crear consulta
            Query query = em.createQuery(consulta);
            
            // Ejecutar consulta
            publicaciones = query.getResultList();
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return publicaciones;
    }
    
    
}
