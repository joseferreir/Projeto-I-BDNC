/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.repositorio;

import br.edu.ifpb.falai.conexao.Conexao;
import br.edu.ifpb.falai.conexao.ConexaoIF;
import br.edu.ifpb.falai.conexao.DataBaseException;
import br.edu.ifpb.falai.entidade.Enquete;
import com.vividsolutions.jts.io.ParseException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.NoneScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jose2
 */
@Named
public class EnqueteRepository implements EnqueteRepositoryIF{
 @Inject
    private ConexaoIF conn;
    private String SQL = null;
  //  @Inject
  //  private Enquete enqute;

    public EnqueteRepository() throws SQLException, IOException, ClassNotFoundException, URISyntaxException {
       // conn = new Conexao();
    }
    
    
    @Override
    public Map<String, String> Save(Enquete e) {
        SQL = "INSERT INTO enquete(pergunta) values(?)";
        return saveBD(e, SQL);
    }

    @Override
    public Map<String, String> update(Enquete e) {
        System.err.println("");
        SQL= "UPDATE enquete SET pergunta =? WHERE id =?";
        return saveBD(e, SQL);
    }

    @Override
    public boolean delete(int enqueteid) {
        boolean result = false;
        PreparedStatement start = null;

        try {
            System.err.println("chegou no delete  " + enqueteid);
            //conn = new Conexao();
            String sql = "DELETE FROM enquete WHERE id = ?";
            start = conn.getConnection().prepareStatement(sql);
            start.setInt(1, enqueteid);
            if (start.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            System.err.println("Erro " + e.getMessage());
            Logger.getLogger(EnqueteRepository.class.getName()).log(Level.SEVERE, null, e);
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
    public Enquete searchById(int enqueteid) {
       SQL = "SELECT * FROM enquete WHERE id = '"+enqueteid +"'";
     try {
         return queryBD(SQL).get(0);
     } catch (URISyntaxException | ParseException | IOException | ClassNotFoundException | SQLException ex) {
         Logger.getLogger(EnqueteRepository.class.getName()).log(Level.SEVERE, null, ex);
     }
     return null;
    }

    @Override
    public List<Enquete> listar() {
        SQL = "SELECT * FROM enquete";
          List<Enquete> result = null;
     try {
         result = queryBD(SQL);
        // System.err.println("retorno das equetes "+result.toString());
     } catch (URISyntaxException | ParseException | IOException | ClassNotFoundException | SQLException ex) {
         Logger.getLogger(EnqueteRepository.class.getName()).log(Level.SEVERE, null, ex);
     }
     if(!result.isEmpty())
         return result;
       return Collections.EMPTY_LIST;
    }
     private Map<String, String> saveBD(Enquete enquete, String SQL) {
       // System.err.println("enquete saveDAO " + enquete);
        //System.err.println("query --------" + SQL);
      
        Map<String, String> resultado = new HashMap<>();
        PreparedStatement stat = null;

        try {
           // System.err.println("chegou std--" + enquete.getPergunta());
            stat = conn.getConnection().prepareStatement(SQL);
            stat.setString(1, enquete.getPergunta());
           
            if (enquete.getId() > 0) {
             //   System.err.println("if up -> "+enquete.getId());
                stat.setInt(2, enquete.getId());
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
                Logger.getLogger(EnqueteRepository.class.getName()).log(Level.SEVERE, null, ex);
                resultado.put("conexao", "Erro verifique sua conexao");
            }
           // System.err.println("DAO ======= DAO ======== resultado ==============" + resultado.toString());
        }
        return resultado;

    }

    private List<Enquete> queryBD(String query) throws URISyntaxException, ParseException, IOException, ClassNotFoundException, SQLException {
        List<Enquete> result = new ArrayList<>();
        PreparedStatement pst = null;

        try {
        
              conn = new Conexao();
            pst = conn.getConnection().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result.add(montarEnquete(rs));
            }
           // System.err.println("é paia ------------>"+result);
            conn.closeAll(pst);
           // System.err.println("fecho");
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!result.isEmpty()) {
            return result;
        }
        return Collections.EMPTY_LIST;

    }

   
    private Enquete montarEnquete(ResultSet rs) throws SQLException {
        Enquete enquete = new Enquete();
        enquete.setId(rs.getInt("id"));
        enquete.setPergunta(rs.getString("pergunta"));
     //   System.err.println("enquete query  "+enquete);
        return enquete;
    }
    
}
