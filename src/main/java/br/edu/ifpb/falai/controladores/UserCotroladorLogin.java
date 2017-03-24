/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.controladores;

import br.edu.ifpb.falai.entidade.User;
import br.edu.ifpb.falai.servicos.UsuarioServico;
import br.edu.ifpb.falai.utilitario.Mensagem;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jose2
 */
@Named
@RequestScoped
public class UserCotroladorLogin {
     @Inject
    private UsuarioServico servico;
     @Inject
     private User usuario;
     @Inject
     private Mensagem msg;

    public UserCotroladorLogin() {
    }
     public String login() {
         
        User result = servico.login(usuario.getEmail(), usuario.getSenha());
        
        if (result != null) {
            usuario = result;
            msg.addMessage ("Bem vindo "+usuario.getNome()+"!");
             FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
            return "home?faces-redirect=true";
        } else {
            msg.addMessage("Usuário ou senha inválido");
           
            return null;
        }
    }
     public String logout() {
         System.err.println("logout -----------------------------------");
             FacesContext.getCurrentInstance().getExternalContext()
             .invalidateSession();
                   
                 return "index?faces-redirect=true";
}
    public String update(){
        servico.update(usuario);
        return "";
    }

    public String delete(){
        usuario=  (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
         boolean result = servico.delete(usuario.getId());
         if(result){
             msg.addMessage("conta excluida");
           return "index.xhtml";
         }
         msg.addMessage("Erro ao excluir");
         return null;
        
    }

    public User searchById(int id){
       return servico.searchById(usuario.getId());
        
    }

    public User searchByEmail(String userEmail){
     return servico.searchByEmail(userEmail);
        
    }
      public List<User> listar(){
         return servico.listar();
    }

 public String atualizarIsAdm(Integer id, boolean habilitar){
     System.err.println("con admi "+id+" "+habilitar);
         boolean result = servico.atualizarIsAdm(id, habilitar);
          if(result)
              msg.addMessage("Operação realizada com sucesso!");
          msg.addMessage("Erro");
         return null;
 }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
  
     
    
}
