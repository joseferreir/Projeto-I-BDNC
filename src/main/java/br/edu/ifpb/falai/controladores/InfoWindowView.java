package br.edu.ifpb.falai.controladores;

import br.edu.ifpb.falai.entidade.InfoVoto;
import br.edu.ifpb.falai.entidade.User;
import br.edu.ifpb.falai.servicos.VotoSevico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@Named
@ViewScoped
public class InfoWindowView implements Serializable {

    private String center;
    //   private InfoVoto info =new InfoVoto();
    @Inject
    private VotoSevico votoServico;
    private MapModel advancedModel;
    private InfoVoto info;
    private Marker marker;
    private List<String> porcento;

    @PostConstruct
    public void init() {
        advancedModel = new DefaultMapModel();
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        double lat = user.getLocalizacao().getInteriorPoint().getX();
        double lng = user.getLocalizacao().getInteriorPoint().getY();
        this.center = lat + "," + lng;
        int idEnquete = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEnquete");
        info = votoServico.searchInfoVoto(idEnquete);
        printPorcentagem();
        markerVotos();
        System.err.println("contro " + info.getPorcentagens());

//    //  teste();
//        //Shared coordinates
//        LatLng coord1 = new LatLng(lat, lng);
//        LatLng coord2 = new LatLng(-6.847747, -38.466754);
//        LatLng coord3 = new LatLng(-6.842464, -38.455768);
//        LatLng coord4 = new LatLng(-6.839737, -38.475165);
//
//        //Icons and Data
//        advancedModel.addOverlay(new Marker(coord1, "sim", "konyaalti.png", "http://maps.google.com/mapfiles/ms/micons/green-dot.png"));
//        advancedModel.addOverlay(new Marker(coord2, "não", "ataturkparki.png"));
//        advancedModel.addOverlay(new Marker(coord3, "nao sei", "karaalioglu.png", "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
//        advancedModel.addOverlay(new Marker(coord4, "nao sei", "karaalioglu.png", "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
//    
    }

    private void markerVotos() {
        for (int k = 0; k < info.getRespostas().size(); k++) {
            double lat, lng;
            lat = info.getLocas().get(k).getInteriorPoint().getX();
            lng = info.getLocas().get(k).getInteriorPoint().getY();
            switch (info.getRespostas().get(k)) {

                case 1:
                    LatLng coord1 = new LatLng(lat, lng);
                    advancedModel.addOverlay(new Marker(coord1, "sim", "konyaalti.png", "http://maps.google.com/mapfiles/ms/micons/green-dot.png"));

                    break;
                case 2:
                    LatLng coord2 = new LatLng(lat, lng);
                    advancedModel.addOverlay(new Marker(coord2, "não", "ataturkparki.png"));

                    break;
                case 3:
                    LatLng coord3 = new LatLng(lat, lng);
                    advancedModel.addOverlay(new Marker(coord3, "nao sei", "karaalioglu.png", "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));

                    break;
            }
        }
    }

    public InfoVoto getInfo() {
        return info;
    }

    public void setInfo(InfoVoto info) {
        this.info = info;
    }

    public String getCenter() {
        return center;
    }

    public MapModel getAdvancedModel() {
        return advancedModel;
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }

    public Marker getMarker() {
        return marker;
    }

    public List<String> getPorcento() {
        return porcento;
    }

    private void printPorcentagem() {
        info.calcularPorcentargem();
        porcento = new ArrayList<>();
        porcento.add(0, "Sim: " + info.getPorcentagens().get("sim"));
        porcento.add(1, "Não: " + info.getPorcentagens().get("nao"));
        porcento.add(2, "Não Sei: " + info.getPorcentagens().get("naoSei"));

    }
    
    @PreDestroy
    private void and() {
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().remove("idEnquete");

    }
}
