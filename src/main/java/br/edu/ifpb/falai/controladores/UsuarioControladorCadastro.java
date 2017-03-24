package br.edu.ifpb.falai.controladores;

import br.edu.ifpb.falai.entidade.User;
import br.edu.ifpb.falai.enums.TypeEnums;
import br.edu.ifpb.falai.servicos.UsuarioServico;
import br.edu.ifpb.falai.utilitario.Mensagem;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.io.IOException;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * Jose2
 */


@ViewScoped
@Named
//@RequestScoped // javax.enterprise.context.RequestScoped
public class UsuarioControladorCadastro implements Serializable {

   @Inject
    private User usuario;
    private WKTReader reader = new WKTReader();
    @Inject
    private TypeEnums enunfom;
    @Inject
    private UsuarioServico servico;
    @Inject
    private Mensagem msg;
  
    

    public UsuarioControladorCadastro() throws SQLException, IOException, ClassNotFoundException, URISyntaxException {
 
   
    }

    public String cadastrar() throws ParseException {
       String localizacao = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("local");
        usuario.setLocalizacao(reader.read("POINT(" + localizacao + ")"));
        Map<String, String> result = servico.salvar(usuario);
        if (result.get("passou").equalsIgnoreCase("true")) {
            msg.addMessage("Cadastro realizado com sucesso, faça seu login!");
            return "index?faces-redirect=true";
        }
        result.remove("passou");
        msg.addMessage(result.values().toString());
        return null;
    }



    public List<User> listar() {

        return servico.listar();
    }

   
    public User getUsuario() {
        return usuario;
    }

  

    public TypeEnums getEnunfom() {
        return enunfom;
    }

   

}
