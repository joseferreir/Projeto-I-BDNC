package br.edu.ifpb.falai.servicos;

import br.edu.ifpb.falai.repositorio.UserRepository;
import br.edu.ifpb.falai.entidade.User;
import br.edu.ifpb.falai.validadores.UserValidation;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * jose2
 */
@Named
public class UsuarioServico implements Serializable {

    @Inject
    private UserRepository repositorio;
    // private UsuarioServico servico;
    @Inject
    private UserValidation validation;

    public UsuarioServico() throws SQLException, IOException, ClassNotFoundException, URISyntaxException {
        //   repositorio = new UserRepository();
        // this.servico = new  UsuarioServico();
    }

    public Map<String, String> salvar(User u) {
        return salvarBD(u);
    }

    public List<User> listar() {
       
        return repositorio.listar();
    }

    public Map<String, String> update(User u) {
        return repositorio.update(u);
    }

    public boolean delete(int userid) {
        return repositorio.delete(userid);
    }

   public User searchById(int userid) {
        return repositorio.searchById(userid);
    }

    public User searchByEmail(String userEmail) {
        return repositorio.searchByEmail(userEmail);
    }

    public User login(String Email, String password) {
        if (Email == null || Email.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            return null;
        } else {
            User l = repositorio.login(Email, password);
          //  System.out.println("login sev -> "+l);
            return l;
        }
    }
     
    public boolean atualizarIsAdm(Integer id, boolean habilitar){
        if(!habilitar)
         return repositorio.atualizarIsAdm(id, true);
        else
         return noAdmin(id);
     }
     public boolean noAdmin(Integer id){
           return repositorio.atualizarIsAdm(id, false);
         
     }

    private Map<String, String> salvarBD(User u) {
        // System.out.println("dao ser valida "+repositorio.isEmailSenha(u.getEmail(), u.getSenha()));
        Map<String, String> resultValidation = validation.execute(u);
        if (resultValidation.get("passou").equalsIgnoreCase("true")) {
         resultValidation = repositorio.Save(u);
            }
        
        return resultValidation;
    }

}
