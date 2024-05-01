/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.CategoriaFacadeLocal;
import EJB.PublicacionFacadeLocal;
import EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Categoria;
import modelo.Publicacion;
import modelo.Usuario;

/**
 *
 * @author Miguel Ángel
 */
@Named
@ViewScoped
public class AltaPublicacionController implements Serializable {
    
    @EJB 
    private UsuarioFacadeLocal usuarioEJB;
    
    @EJB 
    private CategoriaFacadeLocal categoriaEJB;
    
    @EJB
    private PublicacionFacadeLocal publicacionEJB;
    
    // Publicacion actual
    private Publicacion publicacion;
    
    // Todas las categorias
    private List<String> categorias;
    
    // Nombre de la categoria seleccionada
    private String nombreCategoria;
    
    @PostConstruct
    public void init() {
        publicacion = new Publicacion();
        categorias = new ArrayList<>();
        
        // Recupera todas las categorias de la base de datos
        List<Categoria> categoriasBD = categoriaEJB.findAll();
        for(Categoria cActual : categoriasBD) {
            categorias.add(cActual.getNombreCategoria());
        }
    }
    
    // Titulo, cuerpo, fecha, idPersona(Objeto), idCategoria(Objeto)
    public void insertarPublicacion() {
        publicacion.setCategoria(categoriaEJB.findByName(nombreCategoria));
        // Se coge el usuario actual de la variable global
        Usuario usuarioActual = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        publicacion.setPersona(usuarioActual.getPersona());
        publicacion.setFecha(new Date());
        
        try {
            publicacionEJB.create(publicacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro correcto", "Publicación registrada correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al registrar", "Error al registrar la publicación"));
            System.out.println("Error al insertar la publicación " + e.getMessage());
        }
    }

    public UsuarioFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuarioFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
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
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.usuarioEJB);
        hash = 97 * hash + Objects.hashCode(this.categoriaEJB);
        hash = 97 * hash + Objects.hashCode(this.publicacionEJB);
        hash = 97 * hash + Objects.hashCode(this.publicacion);
        hash = 97 * hash + Objects.hashCode(this.categorias);
        hash = 97 * hash + Objects.hashCode(this.nombreCategoria);
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
        final AltaPublicacionController other = (AltaPublicacionController) obj;
        if (!Objects.equals(this.nombreCategoria, other.nombreCategoria)) {
            return false;
        }
        if (!Objects.equals(this.usuarioEJB, other.usuarioEJB)) {
            return false;
        }
        if (!Objects.equals(this.categoriaEJB, other.categoriaEJB)) {
            return false;
        }
        if (!Objects.equals(this.publicacionEJB, other.publicacionEJB)) {
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
