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
public enum EscolaridadeEnum {
    ANALFABETO("ANALFABETO"),
    FUNDAMENTAL_COMPLETO("FUNDAMENTAL_COMPLETO"),
    FUNDAMENTAL_INCOMPLETO("FUNDAMENTAL_INCOMPLETO"),
    MEDIO_CONPLETO("MEDIO_CONPLETO"),
    MEDIO_INCOMPLETO("MEDIO_INCOMPLETO"),
    SUPERIOR_INCOPLETO("SUPERIOR_INCOPLETO"),
    SUPERIOR_COMPLETO("SUPERIOR_COMPLETO");
    private String escolaridade;

    private EscolaridadeEnum(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public static List<String> getnivelEscolar() {
        List<String> enumTipeEnum = new ArrayList<>();
        enumTipeEnum.add(EscolaridadeEnum.ANALFABETO.getEscolaridade());
        enumTipeEnum.add(EscolaridadeEnum.FUNDAMENTAL_COMPLETO.getEscolaridade());
        enumTipeEnum.add(EscolaridadeEnum.FUNDAMENTAL_INCOMPLETO.getEscolaridade());
        enumTipeEnum.add(EscolaridadeEnum.MEDIO_CONPLETO.getEscolaridade());
        enumTipeEnum.add(EscolaridadeEnum.MEDIO_INCOMPLETO.getEscolaridade());
        enumTipeEnum.add(EscolaridadeEnum.SUPERIOR_COMPLETO.getEscolaridade());
        enumTipeEnum.add(EscolaridadeEnum.SUPERIOR_INCOPLETO.getEscolaridade());
        return enumTipeEnum;
    }

}
