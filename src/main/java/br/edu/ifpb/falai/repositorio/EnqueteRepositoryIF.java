/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.repositorio;

import br.edu.ifpb.falai.entidade.Enquete;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jose2
 */
public interface EnqueteRepositoryIF {

    Map<String, String> Save(Enquete e);

    Map<String, String> update(Enquete e);

    boolean delete(int enqueteid);

    Enquete searchById(int enqueteid);

    List<Enquete> listar();

}
