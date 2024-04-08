/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.CategoriasFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Categorias;

/**
 *
 * @author Miguel Angel
 */

@Named
@ViewScoped
public class AltaCategoriaController implements Serializable {
    private Categorias categoria;
    
    @EJB
    private CategoriasFacadeLocal categoriasEJB;
    
    @PostConstruct
    public void init() {
        categoria = new Categorias();
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }
    
    
    
    public void insertarCategoria(){
        try {
            categoriasEJB.create(categoria);
        } catch (Exception e) {
            System.out.println("Error al insertar la categoria " + e.getMessage());
        }
        
        
        /*System.out.println("LLEGA");
        if(categoria.getNombreCategoria().length()==0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al añadir: atributos incompletos", "Error al registrar la categoria: atributos incompletos"));
            return;
        }
        
        try{
            categoriasEJB.create(categoria);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Adicción correcta", "Categoria registrada con éxito"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al añadir", "Error al registrar la categoria"));
            System.out.println("Error al insertar la categoria "+e.getMessage());
        
        */
    }
    
}
