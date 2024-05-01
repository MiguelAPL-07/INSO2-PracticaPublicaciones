/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.PublicacionFacadeLocal;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import modelo.Publicacion;

/**
 *
 * @author Miguel Ángel
 */

@Named
@RequestScoped
public class ListarPublicacionesController implements Serializable {
    
    @EJB
    private PublicacionFacadeLocal publicacionEJB;
    
    // Almacenar todas las publicaciones de la base de datos
    private List<Publicacion> publicacionesBD;
    
    // Acceso a la publicacion que se seleccione
    private Publicacion publicacion;
    
    @PostConstruct
    public void init() {
        publicacionesBD = publicacionEJB.findAll();
    }
    
    public void establecerPublicacion(Publicacion publicacion) {
        setPublicacion(publicacion);
    }

    public PublicacionFacadeLocal getPublicacionEJB() {
        return publicacionEJB;
    }

    public void setPublicacionEJB(PublicacionFacadeLocal publicacionEJB) {
        this.publicacionEJB = publicacionEJB;
    }

    public List<Publicacion> getPublicacionesBD() {
        return publicacionesBD;
    }

    public void setPublicacionesBD(List<Publicacion> publicacionesBD) {
        this.publicacionesBD = publicacionesBD;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.publicacionEJB);
        hash = 83 * hash + Objects.hashCode(this.publicacionesBD);
        hash = 83 * hash + Objects.hashCode(this.publicacion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ListarPublicacionesController other = (ListarPublicacionesController) obj;
        if (!Objects.equals(this.publicacionEJB, other.publicacionEJB)) {
            return false;
        }
        if (!Objects.equals(this.publicacionesBD, other.publicacionesBD)) {
            return false;
        }
        if (!Objects.equals(this.publicacion, other.publicacion)) {
            return false;
        }
        return true;
    }
    
    
}
