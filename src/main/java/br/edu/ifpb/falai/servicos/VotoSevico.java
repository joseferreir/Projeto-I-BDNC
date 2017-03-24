/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.servicos;

import br.edu.ifpb.falai.entidade.InfoVoto;
import br.edu.ifpb.falai.entidade.Voto;
import br.edu.ifpb.falai.repositorio.VotoRepository;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author jose2
 */
@RequestScoped
public class VotoSevico {

    @Inject
    private VotoRepository repository;

    public VotoSevico() {
    }

    public Map<String, String> Save(Voto v) {

        Map<String, String> result = repository.Save(v);

//        
        return result;

    }
    public List<Voto> lista(){
    return repository.listar();
}
     public InfoVoto searchInfoVoto(int id){
        InfoVoto r = repository.searchInfoVoto(id);
         System.err.println("retono info voto "+r);
        return r;
     }
}
