/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.repositorio;

import br.edu.ifpb.falai.conexao.Conexao;
import br.edu.ifpb.falai.conexao.ConexaoIF;
import br.edu.ifpb.falai.conexao.DataBaseException;
import br.edu.ifpb.falai.entidade.User;
import br.edu.ifpb.falai.entidade.Voto;
import br.edu.ifpb.falai.enums.EscolaridadeEnum;
import br.edu.ifpb.falai.enums.IdadeEnum;
import br.edu.ifpb.falai.enums.RendaEnum;
import br.edu.ifpb.falai.enums.SexoEnum;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jose2
 */
@Named
public class UserRepository implements Serializable, UserRepositoryIF {
    
    @Inject
    private ConexaoIF conn;
    private String query = null;
    private WKTReader reader;
    private Map<String, String> resultado;

    // private  UserRepository useRepository;
    public UserRepository() throws SQLException, IOException, ClassNotFoundException, URISyntaxException {
      
        this.reader = new WKTReader();
        //   this.user = new User();
        // this.useRepository = new UserRepository();
    }
    
    @Override
    public Map<String, String> Save(User u) {
        System.err.println("save repositorio " + u.toString());
        query = "INSERT INTO usuario (nome,local, idade, renda,  escolaridade,email,senha,sexo)"
                + "VALUES (?, ST_GeomFromText(?, 26910),?,?,?,?,?,?)";
        return saveBD(u, query);
    }
    
    @Override
    public Map<String, String> update(User u) {
        query = "UPDATE usuario SET nome =?, local = ST_GeomFromText(?, 26910) , idade =?, renda =?,  escolaridade =?,email =?,senha =?,sexo =? WHERE id =?";
        
        return saveBD(u, query);
    }
    
    @Override
    public boolean delete(int userid) {
        boolean result = false;
        PreparedStatement start = null;
        
        try {
            System.err.println("chegou no delete" + userid);
            conn = new Conexao();
            String sql = "DELETE FROM Usuario WHERE id = ?";
            start = conn.getConnection().prepareStatement(sql);
            start.setInt(1, userid);
            if (start.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException | IOException e) {
            System.err.println("Erro " + e.getMessage());
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException | URISyntaxException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.closeAll(start);
            } catch (DataBaseException ex) {
                Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }
    
    @Override
    public User login(String Email, String password) {
        System.err.println("entrou no repositoreo login ");
        User result = null;
        query = "SELECT id, nome, st_astext(local) as local_text, idade, renda, escolaridade, email, senha, sexo, eh_admin  FROM usuario WHERE email =  '" + Email + "' AND senha = '" + password + "'";
        try {
          //  System.err.println("novo dao");
            conn = new Conexao();
            PreparedStatement pst = conn.getConnection().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
          //  System.err.println("passou conn");
            if (rs.next()) {
                result =queryBD(query).get(0);
            }
            
        } catch (URISyntaxException | IOException | ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
      //  System.err.println("+++++=======+++++++++ " + result);
        return result;
    }
    
    @Override
    public User searchById(int userid) {
        // System.err.println("passou na query");

        query = "SELECT id, nome st_astext(local) as local_text, idade, renda, escolaridade, email, senha, sexo, eh_admin  FROM usuario WHERE id =" + userid + "";
        
        try {
            return queryBD(query).get(0);
        } catch (URISyntaxException | ParseException | IOException | ClassNotFoundException | DataBaseException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public User searchByEmail(String userEmail) {
        User result = null;
        query = "SELECT id,nome, st_astext(local) as local_text, idade, renda, escolaridade, email, senha, sexo, eh_admin  FROM usuario WHERE email =" + userEmail + "";
        try {
            result = queryBD(query).get(0);
        } catch (URISyntaxException | ParseException | IOException | ClassNotFoundException | DataBaseException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    @Override
    public List<User> listar() {
      //  System.err.println("===lll====lllll=====llll=======ll=======llll====");
        List<User> result = null;
        query = "SELECT id, nome, st_astext(local) as local_text, idade, renda, escolaridade, email, senha, sexo, eh_admin  FROM usuario";
        try {
            result = queryBD(query);
        } catch (URISyntaxException | ParseException | IOException | ClassNotFoundException | DataBaseException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
    }
    
    private Map<String, String> saveBD(User user, String query) {
        Map<String, String> resultado = new HashMap<>();
        PreparedStatement stat = null;
        
        try {
             this.conn = new Conexao();
            stat = conn.getConnection().prepareStatement(query);
            stat.setString(1, user.getNome());
            stat.setString(2, user.getLocalizacao().toText());
            stat.setString(3, user.getIdade().getIdade());
            stat.setString(4, user.getRenda().getRenda());
            stat.setString(5, user.getEscolaridade().getEscolaridade());
            stat.setString(6, user.getEmail());
            stat.setString(7, user.getSenha());
            stat.setString(8, user.getSexo().getSexo());
            if (user.getId() > 0) {
                stat.setInt(9, user.getId());
            }
            if (stat.executeUpdate() > 0) {
                resultado.put("passou", "true");
            } else {
                resultado.put("passou", "false");
            }
            
        } catch (Exception e) {
            System.err.println("erron no banco: " + e.getMessage());
            resultado.put("errobd", e.getMessage());
        } finally {
            try {
                conn.closeAll(stat);
            } catch (DataBaseException ex) {
                Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
                resultado.put("conexao", "Erro verifique sua conexao");
            }
            
            //System.err.println("DAO ======= DAO ======== resultado ==============" + resultado.toString());
        }
        return resultado;
        
    }
     @Override
    public boolean atualizarIsAdm(Integer id, boolean habilitar){
         System.err.println("admin dao");
        boolean result = false;
        PreparedStatement pst = null;

        try {
            conn = new Conexao();
            String sql = "UPDATE Usuario SET eh_admin = ? WHERE id = ?";
            pst = conn.getConnection().prepareStatement(sql);
            pst.setBoolean(1, habilitar);
            pst.setInt(2, id);
            if (pst.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException | IOException e) {
            System.err.println("Erro " + e.getMessage());
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.closeAll(pst);
            } catch (DataBaseException ex) {
                Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         System.err.println("saindo admin dao "+result);

        return result;
    }
    
    private List<User> queryBD(String query) throws URISyntaxException, ParseException, IOException, ClassNotFoundException, DataBaseException {
        
        PreparedStatement pst = null;
       // System.err.println("list query");
        List<User> result = null;
        try {
          //  System.err.println("try list");
            conn = new Conexao();
            pst = conn.getConnection().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
           // System.err.println("passou conn");
            result = montarUsuario(rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.closeAll(pst);
        }
        
        return result;
    }
    
    private List<User> montarUsuario(ResultSet rs) throws ParseException, SQLException {
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            Geometry local = reader.read(rs.getString("local_text"));
            Point pont = local.getInteriorPoint();
            System.err.println("ponto "+ pont.getX()+"" +pont.getY());
            User novo = new User();
            novo.setId(rs.getInt("id"));
            novo.setNome(rs.getString("nome"));
            novo.setLocalizacao(local);
            novo.setIdade(IdadeEnum.valueOf(rs.getString("idade")));
            novo.setRenda(RendaEnum.valueOf(rs.getString("renda")));
            //    System.err.println("enum "+ rs.getString("escolaridade"));
            novo.setEscolaridade(EscolaridadeEnum.
                    valueOf(rs.getString("escolaridade")));
            novo.setEmail(rs.getString("email"));
            novo.setSenha(rs.getString("senha"));
            novo.setSexo(SexoEnum.valueOf(rs.getString("sexo")));
            novo.setEh_admin(rs.getBoolean("eh_admin"));
            list.add(novo);
           // System.err.println(" user monte ============= " + novo.toString());
        }
        if (!list.isEmpty()) {
            
            return list;
        }
        return Collections.EMPTY_LIST;
        
    }
    
//    private void montarlogin(ResultSet rs) throws ParseException, SQLException {
//        //  reader = new WKTReader();
//        Geometry local = reader.read(rs.getString("local_text"));
//        //   User user = new User();
//        user.setId(rs.getInt("id"));
//        user.setNome(rs.getString("nome"));
//        user.setLocalizacao(local);
//        user.setIdade(IdadeEnum.valueOf(rs.getString("idade")));
//        user.setRenda(RendaEnum.valueOf(rs.getString("renda")));
//        //    System.err.println("enum "+ rs.getString("escolaridade"));
//        user.setEscolaridade(EscolaridadeEnum.
//                valueOf(rs.getString("escolaridade")));
//        user.setEmail(rs.getString("email"));
//        user.setSenha(rs.getString("senha"));
//        user.setSexo(SexoEnum.valueOf(rs.getString("sexo")));
//        user.setEh_admin(rs.getBoolean("eh_admin"));
//        System.err.println(" user monte ============= " + user.toString());
//        
//    }
}
