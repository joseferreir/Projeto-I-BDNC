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
public enum IdadeEnum {
    ENTRE_16_18ANOS("ENTRE_16_18ANOS"),
    ENTRE_19_24ANOS("ENTRE_19_24ANOS"),
    ENTRE_25_30ANOS("ENTRE_25_30ANOS"),
    ENTRE_31_40ANOS("ENTRE_31_40ANOS"),
    ENTRE_41_60ANOS("ENTRE_41_60ANOS"),
    MAIOR_DE_60_ANOS("MAIOR_DE_60_ANOS");

    private String idade;

    private IdadeEnum(String idade) {
        this.idade = idade;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public static List<String> getFachaEtaria() {
        List<String> enumTipeEnum = new ArrayList<>();
        enumTipeEnum.add(IdadeEnum.ENTRE_16_18ANOS.getIdade());
        enumTipeEnum.add(IdadeEnum.ENTRE_19_24ANOS.getIdade());
        enumTipeEnum.add(IdadeEnum.ENTRE_25_30ANOS.getIdade());
        enumTipeEnum.add(IdadeEnum.ENTRE_31_40ANOS.getIdade());
        enumTipeEnum.add(IdadeEnum.ENTRE_41_60ANOS.getIdade());
        enumTipeEnum.add(IdadeEnum.MAIOR_DE_60_ANOS.getIdade());
        return enumTipeEnum;
    }
}
