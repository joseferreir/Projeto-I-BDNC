/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.utilitario;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
;

/**
 *
 * @author jose2
 */
@RequestScoped
public class Mensagem {
      public void addMessage (String massagr) {
        FacesMessage mensagem = new FacesMessage (massagr);
        FacesContext.getCurrentInstance ().addMessage (null, mensagem);
         FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash().setKeepMessages(true);
    }
}
