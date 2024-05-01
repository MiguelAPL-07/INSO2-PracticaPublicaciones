/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.CategoriaFacadeLocal;
import EJB.PublicacionFacadeLocal;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.Categoria;
import modelo.Publicacion;

/**
 *
 * @author Miguel Ángel
 */

@Named
@ViewScoped
public class EditarPublicacionController implements Serializable {
    
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
    @EJB
    private PublicacionFacadeLocal publicacionEJB;
    
    @Inject
    private ListarPublicacionesController listPubCon;
    
    // Acceso a la publicacion que se muestra para editar
    private Publicacion publicacion;
    
    // Todas las categorias
    private List<String> categorias;
    
    // Nombre de la categoria seleccionada
    private String nombreCategoria;
    
    @PostConstruct
    public void init() {
        // Se carga la publicacion
        publicacion = listPubCon.getPublicacion();
        
        // Se actualiza la categoria de la publicacion
        nombreCategoria = publicacion.getCategoria().getNombreCategoria();
        
        // Recupera todas las categorias de la base de datos
        List<Categoria> categoriasBD = categoriaEJB.findAll();
        for(Categoria cActual : categoriasBD) {
            categorias.add(cActual.getNombreCategoria());
        }
    } 
    
    public String actualizarPublicacion() {
        String navegacion = "listarPublicaciones.xhtml";
        try {
            // Actualizamos la categoria seleccionada
            publicacion.setCategoria(categoriaEJB.findByName(nombreCategoria));
            // Editamos la publicacion en base de datos
            publicacionEJB.edit(publicacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización correcta", "Publicación actualizada correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al actualizar", "Error al actualizar la publicación"));
            System.out.println("Error al insertar la publicación " + e.getMessage());
        }
        
        return navegacion;
    }

    public CategoriaFacadeLocal getCategoriaEJB() {
        return categoriaEJB;
    }

    public void setCategoriaEJB(CategoriaFacadeLocal categoriaEJB) {
        this.categoriaEJB = categoriaEJB;
    }

    public PublicacionFacadeLocal getPublicacionEJB() {
        return publicacionEJB;
    }

    public void setPublicacionEJB(PublicacionFacadeLocal publicacionEJB) {
        this.publicacionEJB = publicacionEJB;
    }

    public ListarPublicacionesController getListPubCon() {
        return listPubCon;
    }

    public void setListPubCon(ListarPublicacionesController listPubCon) {
        this.listPubCon = listPubCon;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.categoriaEJB);
        hash = 53 * hash + Objects.hashCode(this.publicacionEJB);
        hash = 53 * hash + Objects.hashCode(this.listPubCon);
        hash = 53 * hash + Objects.hashCode(this.publicacion);
        hash = 53 * hash + Objects.hashCode(this.categorias);
        hash = 53 * hash + Objects.hashCode(this.nombreCategoria);
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
        final EditarPublicacionController other = (EditarPublicacionController) obj;
        if (!Objects.equals(this.nombreCategoria, other.nombreCategoria)) {
            return false;
        }
        if (!Objects.equals(this.categoriaEJB, other.categoriaEJB)) {
            return false;
        }
        if (!Objects.equals(this.publicacionEJB, other.publicacionEJB)) {
            return false;
        }
        if (!Objects.equals(this.listPubCon, other.listPubCon)) {
            return false;
        }
        if (!Objects.equals(this.publicacion, other.publicacion)) {
            return false;
        }
        if (!Objects.equals(this.categorias, other.categorias)) {
            return false;
        }
        return true;
    }
    
    
}
