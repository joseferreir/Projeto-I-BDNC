/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.validadores;

import br.edu.ifpb.falai.entidade.Enquete;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author jose2
 */
@Named
public class EnqueteValidation {
     public Map<String, String> execute(Enquete E){
         Map<String,String> result = new HashMap<>();
         if(E.getPergunta().length()<4||E.getPergunta().length()>50)
             result.put("enquete", "A enquete deve conter de 4 a 50 caracteres");
         if (!E.getPergunta().matches("[A-Za-zÀ-ú0-9 ?]+")) {
            result.put("enquete", "A enquete não deve conter sibolos especiais (% - $ _ # @, por exemplo).");
        }
         if(!result.isEmpty()){
             result.put("passou", "false");
         }
         
         else result.put("passou", "true");
         return result;
     }
    
}
