/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.enums;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author jose2
 */
@RequestScoped
public class TypeEnums {

    public TypeEnums() {
    }

    public List<String> getnivelEscolar() {
        return EscolaridadeEnum.getnivelEscolar();
    }

    public List<String> getgenero() {
        return SexoEnum.getgenero();
    }

    public List<String> getFachaEtaria() {
        return IdadeEnum.getFachaEtaria();
    }

    public List<String> getRenda() {
        return RendaEnum.getTodasRenda();
    }
}
