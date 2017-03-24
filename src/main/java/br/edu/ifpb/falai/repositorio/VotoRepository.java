/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.falai.repositorio;

import br.edu.ifpb.falai.conexao.Conexao;
import br.edu.ifpb.falai.conexao.ConexaoIF;
import br.edu.ifpb.falai.conexao.DataBaseException;
import br.edu.ifpb.falai.entidade.InfoVoto;
import br.edu.ifpb.falai.entidade.Voto;
import com.vividsolutions.jts.geom.Geometry;
import java.io.IOException;
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
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose2
 */
public class VotoRepository implements VotoRepositoryIF {
    //  @Inject

    private ConexaoIF conn;
    private String query;
    private Voto voto;

    public Voto getVoto() {
        return voto;
    }

    public void setVoto(Voto voto) {
        this.voto = voto;
    }

    public VotoRepository() {
    }

    @Override
    public Map<String, String> Save(Voto v) {
        query = "INSERT INTO voto (id_usuario, id_enquete, resposta)"
                + "VALUES (?,?,?)";
        return saveBD(v, query);

    }

    @Override
    public Map<String, String> update(Voto v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int idVoto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Voto searchById(int enqueteid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Voto> listar() {
         List<Voto> result = Collections.EMPTY_LIST;
        try {
             result = queryBD(query);
             System.err.println("lista de votos "+result);
        } catch (IOException | ClassNotFoundException | URISyntaxException ex) {
            Logger.getLogger(VotoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
  return result;
    }

    private Map<String, String> saveBD(Voto v, String query) {
        Map<String, String> resultado = new HashMap<>();
        PreparedStatement stat = null;

        try {
            conn = new Conexao();
            stat = conn.getConnection().prepareStatement(query);
            stat.setInt(1, v.getIdUser());
            stat.setInt(2, v.getIdEnquete());
            stat.setInt(3, v.getResposta());

            if (v.getId() > 0) {
                stat.setInt(4, v.getId());
            }
            if (stat.executeUpdate() > 0) {
                resultado.put("passou", "true");
            } else {
                resultado.put("passou", "false");
            }

        } catch (SQLException | IOException | ClassNotFoundException | URISyntaxException e) {
            System.err.println("erron no banco: " + e.getMessage());
            resultado.put("errobd", e.getMessage());
        } finally {
            try {
                conn.closeAll(stat);
            } catch (DataBaseException ex) {
                Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
                resultado.put("conexao", "Erro verifique sua conexao");
            }

            System.err.println(" voto DAO ======= DAO ======== resultado ==============" + resultado.toString());
        }
        return resultado;
    }

    private List<Voto> queryBD(String query) throws IOException, ClassNotFoundException, URISyntaxException {
        List<Voto> result = new ArrayList<>();
        PreparedStatement pst = null;

        try {

            conn = new Conexao();
            pst = conn.getConnection().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result.add(montarVoto(rs));
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

    private Voto montarVoto(ResultSet rs) throws SQLException {
        voto.setId(rs.getInt("id"));
        voto.setIdEnquete(rs.getInt("id_enquete"));
         voto.setIdUser(rs.getInt("id_usuario"));
          voto.setResposta(rs.getInt("resposta"));
        return voto;
    }

  @Override
    public List< Geometry> buscarAtributos(Map<String, String> map) {
         StringBuilder sql = null;
          PreparedStatement ps = null;
        try {

           conn = new Conexao();

            sql = new StringBuilder("SELECT * FROM usuario WHERE ");

            Set<String> keys = map.keySet();
            Iterator<String> it = keys.iterator();

            String key;
            while (it.hasNext()) {
                key = it.next();
                sql.append(key);
                sql.append(" = ");
                sql.append("'").append(map.get(key)).append("'");
                if (it.hasNext()) {
                    sql.append(" AND ");
                }
            }

           ps = conn.getConnection().prepareStatement(sql.toString());

            ResultSet rs = ps.executeQuery();
            List<Geometry> locais = new ArrayList<>();

            while (rs.next()) {
                 Geometry local = preencherObjeto(rs);
                if (local != null) {
                    locais.add(local);
                }
            }

            return locais;
        } catch (SQLException | URISyntaxException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } finally {
             try {
                 conn.closeAll(ps);
             } catch (DataBaseException ex) {
                 Logger.getLogger(VotoRepository.class.getName()).log(Level.SEVERE, null, ex);
             }
        }

    }

    private Geometry preencherObjeto(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InfoVoto searchInfoVoto(int id) {
        PreparedStatement pst = null;
         
 InfoVoto infoVoto = null;
        try {
 query = "SELECT e.pergunta , st_astext(local) as local_text, v.resposta FROM  ENQUETE e, USUARIO u, VOTO V "
         + "WHERE e.id = v.id_enquete AND u.id = v.id_usuario AND e.id = '"+id+"'";
            conn = new Conexao();
            pst = conn.getConnection().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
           
             infoVoto = preecher(rs);
            
                
            // System.err.println("é paia ------------>"+result);
            conn.closeAll(pst);
            // System.err.println("fecho");
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException | URISyntaxException ex) {
            Logger.getLogger(VotoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("retono info eenquete "+infoVoto);
      return infoVoto;
    }

    private InfoVoto preecher(  ResultSet rs) {
        InfoVoto infoVoto = new InfoVoto();
        List<Geometry> locais = new ArrayList<>();
          List<Integer> respostas = new ArrayList();
        WKTReader reader = new WKTReader();
        try {
            try {
                 while (rs.next()) {
                    Geometry local = reader.read(rs.getString("local_text"));
                    locais.add(local);
                infoVoto.setEnquete(rs.getString("pergunta"));
                respostas.add(rs.getInt("resposta"));
                 }
                 infoVoto.setLocas(locais);
                 infoVoto.setRespostas(respostas);
            } catch (ParseException ex) {
                Logger.getLogger(VotoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VotoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           System.err.println(infoVoto);
        return infoVoto;
    }
}
