/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.repositorio;

import br.edu.ifpb.falai.entidade.User;
import br.edu.ifpb.falai.entidade.Voto;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jose2
 */
public interface UserRepositoryIF {

    Map<String, String> Save(User u);

    Map<String, String> update(User u);

    boolean delete(int userid);

    User searchById(int userid);

    User searchByEmail(String userEmail);

    User login(String Email, String password);

    List<User> listar();
 public boolean atualizarIsAdm(Integer id, boolean habilitar);
 
     
}
