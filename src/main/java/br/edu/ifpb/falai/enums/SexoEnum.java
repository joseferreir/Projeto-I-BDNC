/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose2
 */
public enum SexoEnum {
    MASCULINO("MASCULINO") , FEMININO("FEMININO") ,OUTROS("OUTROS");
    private  String sexo;

    private SexoEnum(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public static List<String> getgenero() {
        List<String> enumTipeEnum = new ArrayList<>();
        enumTipeEnum.add(SexoEnum.FEMININO.getSexo());
        enumTipeEnum.add(SexoEnum.MASCULINO.getSexo());
        enumTipeEnum.add(SexoEnum.OUTROS.getSexo());

        return enumTipeEnum;
    }
}
