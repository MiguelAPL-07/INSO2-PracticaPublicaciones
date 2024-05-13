/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.CategoriaFacadeLocal;
import EJB.PublicacionFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Categoria;
import modelo.Publicacion;
import modelo.Usuario;

/**
 *
 * @author Miguel Ángel
 */

@Named
@RequestScoped
public class ListarPublicacionesController {
    
    @EJB
    private PublicacionFacadeLocal publicacionEJB;
    
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
    // Almacenar todas las publicaciones de la base de datos
    private List<Publicacion> publicacionesBD;
    
    // Almacenar todas las publicaciones de la base de datos de un usuario
    private List<Publicacion> publicacionesBDUsuario;
    
    // Almacenar todas las publicaciones de la base de datos por categoria
    private List<Publicacion> publicacionesBDCategoria;
    
    // Almacenar todas las publicaciones de la base de datos por valoracion
    private List<Publicacion> publicacionesBDValoracion;
    
    // Acceso a la publicacion que se seleccione
    private Publicacion publicacion;
    
    // Todas las categorias
    private List<String> categorias;
    
    // Nombre de la categoria seleccionada
    private String nombreCategoria;
    
    @PostConstruct
    public void init() {
        publicacionesBD = publicacionEJB.findAll();
        
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        publicacionesBDUsuario = publicacionEJB.obtenerPublicacionesUsuario(usuario);
        
        
        categorias = new ArrayList<>();
        
        // Recupera todas las categorias de la base de datos
        List<Categoria> categoriasBD = categoriaEJB.findAll();
        for(Categoria cActual : categoriasBD) {
            categorias.add(cActual.getNombreCategoria());
        }
        nombreCategoria = categorias.get(0);
        
        publicacionesBDCategoria = publicacionEJB.obtenerPublicacionesPorCategoria(nombreCategoria);
        filtrarListaValoracionMejorAPeor();
    }
    
    public void establecerPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }
    
    public void filtrarListaCategoria() {
        publicacionesBDCategoria = publicacionEJB.obtenerPublicacionesPorCategoria(nombreCategoria);
    }
    
    public void filtrarListaValoracionMejorAPeor() {
        publicacionesBDValoracion = publicacionEJB.obtenerPublicacionesPorValoracionDesc();
    }
    
    public void filtrarListaValoracionPeorAMejor() {
        publicacionesBDValoracion = publicacionEJB.obtenerPublicacionesPorValoracionAsc();
    }
    
    public void eliminarPublicacion(Publicacion publicacion) {
         try {
            publicacionEJB.remove(publicacion);
            Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            publicacionesBDUsuario = publicacionEJB.obtenerPublicacionesUsuario(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminación correcta", "Publicación eliminada con éxito"));
        } catch(Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al eliminar", "Error al eliminar la publicación"));
            System.out.println("Error al eliminar la publicación "+e.getMessage());
        }
    }
    
    public void valorarPublicacion() {
        try {
            // Editamos la publicacion en base de datos
            publicacionEJB.edit(publicacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Valorado correcta", "Publicación actualizada correctamente"));
            publicacionesBD = publicacionEJB.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al actualizar", "Error al actualizar la publicación"));
            System.out.println("Error al insertar la publicación " + e.getMessage());
        }
    }
    
    public void comentarPublicacion() {
        try {
            // Editamos la publicacion en base de datos
            publicacionEJB.edit(publicacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Comentario correcta", "Publicación actualizada correctamente"));
            publicacionesBD = publicacionEJB.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al actualizar", "Error al actualizar la publicación"));
            System.out.println("Error al insertar la publicación " + e.getMessage());
        }
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

    public List<Publicacion> getPublicacionesBDUsuario() {
        return publicacionesBDUsuario;
    }

    public void setPublicacionesBDUsuario(List<Publicacion> publicacionesBDUsuario) {
        this.publicacionesBDUsuario = publicacionesBDUsuario;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public CategoriaFacadeLocal getCategoriaEJB() {
        return categoriaEJB;
    }

    public void setCategoriaEJB(CategoriaFacadeLocal categoriaEJB) {
        this.categoriaEJB = categoriaEJB;
    }

    public List<Publicacion> getPublicacionesBDCategoria() {
        return publicacionesBDCategoria;
    }

    public void setPublicacionesBDCategoria(List<Publicacion> publicacionesBDCategoria) {
        this.publicacionesBDCategoria = publicacionesBDCategoria;
    }

    public List<Publicacion> getPublicacionesBDValoracion() {
        return publicacionesBDValoracion;
    }

    public void setPublicacionesBDValoracion(List<Publicacion> publicacionesBDValoracion) {
        this.publicacionesBDValoracion = publicacionesBDValoracion;
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
        hash = 97 * hash + Objects.hashCode(this.publicacionEJB);
        hash = 97 * hash + Objects.hashCode(this.categoriaEJB);
        hash = 97 * hash + Objects.hashCode(this.publicacionesBD);
        hash = 97 * hash + Objects.hashCode(this.publicacionesBDUsuario);
        hash = 97 * hash + Objects.hashCode(this.publicacionesBDCategoria);
        hash = 97 * hash + Objects.hashCode(this.publicacionesBDValoracion);
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
        final ListarPublicacionesController other = (ListarPublicacionesController) obj;
        if (!Objects.equals(this.nombreCategoria, other.nombreCategoria)) {
            return false;
        }
        if (!Objects.equals(this.publicacionEJB, other.publicacionEJB)) {
            return false;
        }
        if (!Objects.equals(this.categoriaEJB, other.categoriaEJB)) {
            return false;
        }
        if (!Objects.equals(this.publicacionesBD, other.publicacionesBD)) {
            return false;
        }
        if (!Objects.equals(this.publicacionesBDUsuario, other.publicacionesBDUsuario)) {
            return false;
        }
        if (!Objects.equals(this.publicacionesBDCategoria, other.publicacionesBDCategoria)) {
            return false;
        }
        if (!Objects.equals(this.publicacionesBDValoracion, other.publicacionesBDValoracion)) {
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
