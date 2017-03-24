/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.entidade;

import com.vividsolutions.jts.geom.Geometry;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jose2
 */
public class InfoVoto {

    private int totalVotos;

    private List<Geometry> locas;
    private String enquete;
    private Map<String, String> porcentagens;
    private List<Integer> respostas;

    public InfoVoto() {
        this.porcentagens = new HashMap<>();
    }

    public void calcularPorcentargem() {
        System.err.println("cac porc");
        float sim = 0, nao = 0, naoSei = 0;
        totalVotos = respostas.size();
        System.err.println("total " + totalVotos);
        for (int k = 0; k < totalVotos; k++) {
            switch (respostas.get(k)) {

                case 1:
                    sim++;
                    System.err.println("sim " + sim);
                    break;
                case 2:
                    nao++;
                    System.err.println("nao " + nao);
                    break;
                case 3:
                    naoSei++;
                    System.err.println("nao sei " + naoSei);
                    break;
            }
        }
       
        porcentagens.put("sim",String.format("%.2f", (sim / totalVotos) * 100 ) + " %");
        porcentagens.put("nao",String.format("%.2f", (nao / totalVotos) * 100) + " %");
        porcentagens.put("naoSei",String.format("%.2f", (naoSei / totalVotos) * 100 ) + " %");
        System.err.println("rep "+porcentagens.values());
    }

    public List<Geometry> getLocas() {
        return Collections.unmodifiableList(locas);
    }

    public void setLocas(List<Geometry> locas) {
        this.locas = locas;
    }

    public String getEnquete() {
        return enquete;
    }

    public void setEnquete(String enquete) {
        this.enquete = enquete;
    }

    public Map<String, String> getPorcentagens() {
        
        return porcentagens;
    }

    public void setPorcentagens(Map<String, String> porcentagens) {
        this.porcentagens = porcentagens;
    }

    public List<Integer> getRespostas() {
        return Collections.unmodifiableList(respostas);
    }

    public void setRespostas(List<Integer> respostas) {
        this.respostas = respostas;
    }

    public int getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(int totalVotos) {
        this.totalVotos = totalVotos;
    }

    @Override
    public String toString() {
        return "InfoVoto{" + "totalVotos=" + totalVotos + ", locas=" + locas + ", enquete=" + enquete + ", porcentagens=" + porcentagens + ", respostas=" + respostas + '}';
    }

}
