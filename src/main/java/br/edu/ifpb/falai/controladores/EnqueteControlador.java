/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.controladores;

import br.edu.ifpb.falai.entidade.Enquete;
import br.edu.ifpb.falai.entidade.InfoVoto;
import br.edu.ifpb.falai.entidade.User;
import br.edu.ifpb.falai.entidade.Voto;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.ifpb.falai.servicos.EqueteServico;
import br.edu.ifpb.falai.servicos.VotoSevico;
import br.edu.ifpb.falai.utilitario.Mensagem;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author jose2
 */
@RequestScoped
@Named
public class EnqueteControlador implements Serializable {

    @Inject
    private Enquete enquete;
    @Inject
    private EqueteServico enqueteServico;
    @Inject
    private VotoSevico votoSevico;
    @Inject
    private Voto voto;
    @Inject
    private Mensagem msg;

    public EnqueteControlador() {

    }

    public String cadastrar() {
      
        Map<String, String> result = enqueteServico.Save(enquete);
        if (result.get("passou").equalsIgnoreCase("true")) {
           msg.addMessage("Enquete salva com sucesso");
            return "PageAdmin?faces-redirect=true";
        } else {

            result.remove("passou");
            msg.addMessage(result.values().toString());
            
            return null;
        }
      
    }
    public String votar(){
         
       moteVoto();
        Map<String, String> result = votoSevico.Save(voto);
       
        if(result.get("passou").equalsIgnoreCase("true")){
             System.err.println("if voto controle "+result.values());
           msg.addMessage("voto concluido");
             FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEnquete", voto.getIdEnquete());
      
           return  "VotoView?faces-redirect=true";
        }
        else{
            result.remove("passou");
            msg.addMessage(result.values().toString());
        }
        return null;
    }

    public List<Enquete> listar() {
        return enqueteServico.listar();
    }

    public String delete() {
        
        if (enqueteServico.delete(enquete.getId())) {
            msg.addMessage("Enquete excluida com secesso!");
             return "PageAdmin?faces-redirect=true";
        } else {
            msg.addMessage("Erro ao  excluida tente novamente!");
            
             return "PageAdmin?faces-redirect=true";
        }
       
    }

    public void buscaid() {
        enquete = enqueteServico.searchById(enquete.getId());
    }

    public Enquete getEnquete() {
        return enquete;
    }

    public void setEnquete(Enquete enquete) {
        this.enquete = enquete;
    }

    public Voto getVoto() {
        return voto;
    }

    public void setVoto(Voto voto) {
        this.voto = voto;
    }
    
    private void moteVoto(){
         User user = (User) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("usuario");
        voto.setIdUser(user.getId());
        voto.setIdEnquete(enquete.getId());
    }

}
