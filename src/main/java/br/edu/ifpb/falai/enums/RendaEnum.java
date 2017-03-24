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
public enum RendaEnum {
    ENTRE_0_E_1_SALARIOS("ENTRE_0_E_1_SALARIOS"),
    ENTRE_1_E_2_SALARIOS("ENTRE_1_E_2_SALARIOS"),
    ENTRE_2_E_3_SALARIOS("ENTRE_2_E_3_SALARIOS"),
    ENTRE_3_E_5_SALARIOS("ENTRE_3_E_5_SALARIOS"),
    MAIS_DE_5_SALARIOS("MAIS_DE_5_SALARIOS");

    private String renda;

    private RendaEnum(String renda) {
        this.renda = renda;
    }

    public String getRenda() {
        return renda;
    }

    public void setRenda(String renda) {
        this.renda = renda;
    }
     public static List<String> getTodasRenda() {
        List<String> enumTipeEnum = new ArrayList<>();
      //  enumTipeEnum.add("Remda");
        enumTipeEnum.add(RendaEnum.ENTRE_0_E_1_SALARIOS.getRenda());
        enumTipeEnum.add(RendaEnum.ENTRE_1_E_2_SALARIOS.getRenda());
        enumTipeEnum.add(RendaEnum.ENTRE_2_E_3_SALARIOS.getRenda());
        enumTipeEnum.add(RendaEnum.ENTRE_3_E_5_SALARIOS.getRenda());
        enumTipeEnum.add(RendaEnum.MAIS_DE_5_SALARIOS.getRenda());
        return enumTipeEnum;
    }

}
