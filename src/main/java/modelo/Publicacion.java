/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Miguel Ángel
 */

@Entity
@Table(name="publicaciones")
public class Publicacion implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idPublicacion;
    
    @Column(name="titulo")
    private String titulo;
    
    @Column(name="cuerpo")
    private String cuerpo;
    
    @Column(name="comentarioProfesor")
    private String comentarioProfesor;
    
    @Column(name="valoracion")
    private int valoracion;
    
    @Column(name="fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @JoinColumn(name="idPersona")
    @ManyToOne
    private Persona persona;
    
    @JoinColumn(name="idCategoria")
    @ManyToOne
    private Categoria categoria;
    
    @Column(name="url")
    private String url;
    
    @Column(name="urlProfesor")
    private String urlProfesor;

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getComentarioProfesor() {
        return comentarioProfesor;
    }

    public void setComentarioProfesor(String comentarioProfesor) {
        this.comentarioProfesor = comentarioProfesor;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlProfesor() {
        return urlProfesor;
    }

    public void setUrlProfesor(String urlProfesor) {
        this.urlProfesor = urlProfesor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.idPublicacion;
        hash = 19 * hash + Objects.hashCode(this.titulo);
        hash = 19 * hash + Objects.hashCode(this.cuerpo);
        hash = 19 * hash + Objects.hashCode(this.comentarioProfesor);
        hash = 19 * hash + this.valoracion;
        hash = 19 * hash + Objects.hashCode(this.fecha);
        hash = 19 * hash + Objects.hashCode(this.persona);
        hash = 19 * hash + Objects.hashCode(this.categoria);
        hash = 19 * hash + Objects.hashCode(this.url);
        hash = 19 * hash + Objects.hashCode(this.urlProfesor);
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
        final Publicacion other = (Publicacion) obj;
        if (this.idPublicacion != other.idPublicacion) {
            return false;
        }
        if (this.valoracion != other.valoracion) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.cuerpo, other.cuerpo)) {
            return false;
        }
        if (!Objects.equals(this.comentarioProfesor, other.comentarioProfesor)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.urlProfesor, other.urlProfesor)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.persona, other.persona)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        return true;
    }

    
    
    
}
