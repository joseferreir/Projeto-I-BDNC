/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.servicos;

import br.edu.ifpb.falai.entidade.Enquete;
import br.edu.ifpb.falai.repositorio.EnqueteRepository;
import br.edu.ifpb.falai.validadores.EnqueteValidation;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jose2
 */
@Named
public class EqueteServico {
    @Inject
    private EnqueteRepository repository;
    @Inject
    private EnqueteValidation validation;

    public EqueteServico() {
    }
    public Map<String, String> Save(Enquete e){
         
        Map<String, String> result = validation.execute(e);
       // System.err.println("sava www" +e.getId());
        if(result.get("passou").equalsIgnoreCase("true")){
            System.err.println("valai serv id=  "+e.getId() );
            if(e.getId()<1){
            result = repository.Save(e);
             //   System.err.println("id uuu ");
            }
            else {
              //  System.err.println("uuu sss "+e.getId());
                result = repository.update(e);}
            return result;
            
        }else
        return result;
         
     }
    public boolean delete(int id){
        boolean r = repository.delete(id);
        System.err.println("sev en delete id" +id+ "resulte "+r);
        return r;
    }
//
//    Map<String, String> update(Enquete e){
//        
//    }
//
//    boolean delete(int enqueteid){
//        
//    }
//
   public Enquete searchById(int enqueteid){
        Enquete e = repository.searchById(enqueteid);
     //   System.err.println("enque sev id"+e);
        return e;
    }

  public List<Enquete> listar(){
        return repository.listar();
    }
    
}
