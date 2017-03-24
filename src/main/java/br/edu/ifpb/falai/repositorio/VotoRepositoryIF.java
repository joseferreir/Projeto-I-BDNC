/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.repositorio;

import br.edu.ifpb.falai.entidade.InfoVoto;
import br.edu.ifpb.falai.entidade.Voto;
import com.vividsolutions.jts.geom.Geometry;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jose2
 */
public interface VotoRepositoryIF {

    Map<String, String> Save(Voto v);

    Map<String, String> update(Voto v);

    boolean delete(int idVoto);

    Voto searchById(int enqueteid);

    List<Voto> listar();

    List< Geometry> buscarAtributos(Map<String, String> map);

    InfoVoto searchInfoVoto(int id);

}
