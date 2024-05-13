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
    public List<Publicacion> obtenerPublicacionesUusuario(Usuario usuario) {
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
    
}
