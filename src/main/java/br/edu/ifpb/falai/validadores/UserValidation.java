/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.validadores;

import br.edu.ifpb.falai.entidade.User;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.inject.Named;

/**
 *
 * @author jose2
 */
@Named
public class UserValidation {

    private final Pattern REGEX_EMAIL_VALIDO = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public UserValidation() {
    }

    public Map<String, String> execute(User usuario) {
        System.err.println("validado" + usuario.toString());

        Map<String, String> resultado = new HashMap<>();

        if (usuario == null) {
            return null;
        }

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()
                || usuario.getNome().length()<10 ||usuario.getNome().length()>60) {
            resultado.put("nome", "Nome deve conter de 10 a 60 caracteres.");
        } else if (!usuario.getNome().matches("[A-Za-zÀ-ú0-9 ]+")) {
            resultado.put("nome", "Nome não deve conter sibolos especiais (% - $ _ # @, por exemplo).");
        }
        if (usuario.getEscolaridade() == null) {
            resultado.put("escolaridade", "Informe nivel escolar.");
        }
        if (usuario.getIdade() == null) {
            resultado.put("idaade", "Informe sua facha de idade.");
        }
        if (usuario.getLocalizacao() == null) {
            resultado.put("local", "Informe sua localização .");
        }

        if (usuario.getRenda() == null) {
            resultado.put("renda", "Informe sua renda media.");
        }
        if (usuario.getSexo() == null) {
            resultado.put("sexo", "Informe sua opção sexoal.");
        }

        if (usuario.getEmail() == null
                || usuario.getEmail().trim().isEmpty()
                || !REGEX_EMAIL_VALIDO.matcher(usuario.getEmail()).find()) {
            resultado.put("email", "Informe um email válido: exemplo fulano@gmail.com .");

        }
        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()
                || usuario.getSenha().length() < 6 || usuario.getSenha().length() > 14) {
            resultado.put("senha", "A senha deve conter no minimo 6 e no maximo 14 digitos.");
        }

//        if (resultado.isEmpty()) {
//            resultado = isEmailSenha = dao.isEmailSenha(usuario.getEmail(), usuario.getSenha());
//           
//       }
//        if (!resultado.get("email").isEmpty()) {
//            resultado.put("email", " email já cadastrado");
//        }
//        if (!isEmailSenha.get("senha").isEmpty()) {
//            resultado.put("senha", "senha invalida");
//        }
        if (resultado.isEmpty()) {
            resultado.put("passou", "true");
        } else {
            resultado.put("passou", "false");
        }

        return resultado;
    }

}
