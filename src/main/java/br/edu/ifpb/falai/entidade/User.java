/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.entidade;

import br.edu.ifpb.falai.enums.EscolaridadeEnum;
import br.edu.ifpb.falai.enums.IdadeEnum;
import br.edu.ifpb.falai.enums.RendaEnum;
import br.edu.ifpb.falai.enums.SexoEnum;
import com.vividsolutions.jts.geom.Geometry;
import java.io.Serializable;
import java.util.Objects;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author jose2
 */
@Named
public class User implements Serializable {

    private int id;
    private String nome;
    private SexoEnum sexo;
    private IdadeEnum idade;
    private RendaEnum renda;
    private EscolaridadeEnum escolaridade;
    private Geometry localizacao;
    private String email;
    private String senha;
    private boolean eh_admin;
   
    public User() {
      
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public IdadeEnum getIdade() {
        return idade;
    }

    public void setIdade(IdadeEnum idade) {
        this.idade = idade;
    }

    public RendaEnum getRenda() {
        return renda;
    }

    public void setRenda(RendaEnum renda) {
        this.renda = renda;
    }

    public EscolaridadeEnum getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(EscolaridadeEnum escolaridade) {
        this.escolaridade = escolaridade;
    }

    public Geometry getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Geometry localizacao) {
        this.localizacao = localizacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isEh_admin() {
        return eh_admin;
    }

    public void setEh_admin(boolean eh_admin) {
        this.eh_admin = eh_admin;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nome=" + nome + ", sexo=" + sexo + ", idade=" + idade + ", renda=" + renda + ", escolaridade=" + escolaridade + ", localizacao=" + localizacao + ", email=" + email + ", senha=" + senha + ", eh_admin=" + eh_admin + '}';
    }

}
