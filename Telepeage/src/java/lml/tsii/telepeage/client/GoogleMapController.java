package lml.tsii.telepeage.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import lml.tsii.telepeage.metier.HighwayService;
import lml.tsii.telepeage.metier.MetierFactory;
import lml.tsii.telepeage.metier.PriceService;
import lml.tsii.telepeage.metier.StationService;
import lml.tsii.telepeage.metier.entitys.Highway;
import lml.tsii.telepeage.metier.entitys.Price;
import lml.tsii.telepeage.metier.entitys.Station;
import lml.tsii.telepeage.metier.entitys.Type;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
@ViewScoped
public class GoogleMapController implements Serializable {

    private PriceService priceSrv;
    private StationService stationSrv;
    private HighwayService highwaySrv;
    private MapModel model;
    private double latitude;
    private double longitude;
    private String name;
    private Station station;
    private float pk;
    private String startHighway;
    private String endHighway;
    private boolean ticket;
    private String highway;
    private List<String> highways;
    private float classe1d;
    private float classe1a;
    private float classe2d;
    private float classe2a;
    private float classe3d;
    private float classe3a;
    private float classe4d;
    private float classe4a;
    private float classe5d;
    private float classe5a;
    private Marker marker;

    @PostConstruct
    public void init() {
        this.stationSrv = MetierFactory.getStationService();
        this.highwaySrv = MetierFactory.getHighwayService();
        this.priceSrv = MetierFactory.getPriceService();
        try {
            List<Highway> listHighways = this.highwaySrv.getAll();
            this.highways = new ArrayList();
            for (int i = 0; i < listHighways.size(); i++) {
                this.highways.add(listHighways.get(i).getName());
            }
        } catch (Exception ex) {
            Logger.getLogger(GoogleMapController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.model = new DefaultMapModel();
    }

    public MapModel getModel() {
        this.markers();
        return model;
    }

    public void setModel(MapModel model) {
        this.model = model;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isTicket() {
        return ticket;
    }

    public void setTicket(boolean ticket) {
        this.ticket = ticket;
    }

    public float getPk() {
        return pk;
    }

    public void setPk(float pk) {
        this.pk = pk;
    }

    public String getHighway() {
        return highway;
    }

    public void setHighway(String highway) {
        this.highway = highway;
    }

    public List<String> getHighways() {
        return highways;
    }

    public void setHighways(List<String> highways) {
        this.highways = highways;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public float getClasse1d() {
        return classe1d;
    }

    public void setClasse1d(float classe1d) {
        this.classe1d = classe1d;
    }

    public float getClasse1a() {
        return classe1a;
    }

    public void setClasse1a(float classe1a) {
        this.classe1a = classe1a;
    }

    public float getClasse2d() {
        return classe2d;
    }

    public void setClasse2d(float classe2d) {
        this.classe2d = classe2d;
    }

    public float getClasse2a() {
        return classe2a;
    }

    public void setClasse2a(float classe2a) {
        this.classe2a = classe2a;
    }

    public float getClasse3d() {
        return classe3d;
    }

    public void setClasse3d(float classe3d) {
        this.classe3d = classe3d;
    }

    public float getClasse3a() {
        return classe3a;
    }

    public void setClasse3a(float classe3a) {
        this.classe3a = classe3a;
    }

    public float getClasse4d() {
        return classe4d;
    }

    public void setClasse4d(float classe4d) {
        this.classe4d = classe4d;
    }

    public float getClasse4a() {
        return classe4a;
    }

    public void setClasse4a(float classe4a) {
        this.classe4a = classe4a;
    }

    public float getClasse5d() {
        return classe5d;
    }

    public void setClasse5d(float classe5d) {
        this.classe5d = classe5d;
    }

    public float getClasse5a() {
        return classe5a;
    }

    public void setClasse5a(float classe5a) {
        this.classe5a = classe5a;
    }

    public HighwayService getHighwaySrv() {
        return highwaySrv;
    }

    public void setHighwaySrv(HighwayService highwaySrv) {
        this.highwaySrv = highwaySrv;
    }

    public String getStartHighway() {
        return startHighway;
    }

    public void setStartHighway(String startHighway) {
        this.startHighway = startHighway;
    }

    public void markers() {
        try {
            List<Station> stations = this.stationSrv.getAll();
            for (int i = 0; i < stations.size(); i++) {
                LatLng coor = new LatLng(stations.get(i).getLatitude(), stations.get(i).getLongitude());
                if (!stations.get(i).isTicket()) {
                    this.model.addOverlay(new Marker(coor, stations.get(i).getName(), "test", "http://maps.google.com/mapfiles/ms/micons/green-dot.png"));
                } else {
                    this.model.addOverlay(new Marker(coor, stations.get(i).getName(), "test", "http://maps.google.com/mapfiles/ms/micons/red-dot.png"));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GoogleMapController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void adMarker() throws Exception {
        Station station = new Station();
        station.setLongitude(this.longitude);
        station.setName(this.name);
        station.setPk(this.pk);
        station.setTicket(this.ticket);
        station.setLatitude(this.latitude);
        station.setHighway(this.highwaySrv.getByName(this.highway).get(0));
        try {
            station = this.stationSrv.add(station);
            Price price = new Price();
            Map<Type, Float> startPrice = new HashMap<Type, Float>();
            startPrice.put(Type.Classe1, this.classe1d);
            startPrice.put(Type.Classe2, this.classe2d);
            startPrice.put(Type.Classe3, this.classe3d);
            startPrice.put(Type.Classe4, this.classe4d);
            startPrice.put(Type.Classe5, this.classe5d);
            Map<Type, Float> endPrice = new HashMap<Type, Float>();
            endPrice.put(Type.Classe1, this.classe1a);
            endPrice.put(Type.Classe2, this.classe2a);
            endPrice.put(Type.Classe3, this.classe3a);
            endPrice.put(Type.Classe4, this.classe4a);
            endPrice.put(Type.Classe5, this.classe5a);
            price.setStartPrice(startPrice);
            price.setEndPrice(endPrice);
            price = this.priceSrv.add(price);
            station.setPrice(price);
            this.stationSrv.update(station);
        } catch (Exception ex) {
            Logger.getLogger(GoogleMapController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        Marker marker = (Marker) event.getOverlay();
        this.marker = marker;
        List<Station> stations;
        try {
            stations = this.stationSrv.getByName(marker.getTitle());
            if (!stations.isEmpty()) {
                this.station = stations.get(0);
                this.name = this.station.getName();
                this.pk = this.station.getPk();
                this.ticket = this.station.isTicket();
                //this.startHighway = this.
                if (!this.ticket) {
                    this.classe1d = this.station.getPrice().getStartPrice().get(Type.Classe1);
                    this.classe2d = this.station.getPrice().getStartPrice().get(Type.Classe2);
                    this.classe3d = this.station.getPrice().getStartPrice().get(Type.Classe3);
                    this.classe4d = this.station.getPrice().getStartPrice().get(Type.Classe4);
                    this.classe5d = this.station.getPrice().getStartPrice().get(Type.Classe5);
                    this.classe1a = this.station.getPrice().getEndPrice().get(Type.Classe1);
                    this.classe2a = this.station.getPrice().getEndPrice().get(Type.Classe2);
                    this.classe3a = this.station.getPrice().getEndPrice().get(Type.Classe3);
                    this.classe4a = this.station.getPrice().getEndPrice().get(Type.Classe4);
                    this.classe5a = this.station.getPrice().getEndPrice().get(Type.Classe5);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GoogleMapController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.marker = null;
    }

    public void remove() {
        try {
            this.stationSrv.remove(this.station);
            this.model.getMarkers().remove(this.marker);
            ExternalContext content = FacesContext.getCurrentInstance().getExternalContext();
            content.redirect("/Telepeage/administration.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(GoogleMapController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void edit() throws Exception {
        this.station.setName(this.name);
        this.station.setPk(this.pk);
        if (!this.station.isTicket()) {
            Price price = this.station.getPrice();
            Map<Type, Float> startPrice = new HashMap<Type, Float>();
            startPrice.put(Type.Classe1, this.classe1d);
            startPrice.put(Type.Classe2, this.classe2d);
            startPrice.put(Type.Classe3, this.classe3d);
            startPrice.put(Type.Classe4, this.classe4d);
            startPrice.put(Type.Classe5, this.classe5d);
            Map<Type, Float> endPrice = new HashMap<Type, Float>();
            endPrice.put(Type.Classe1, this.classe1a);
            endPrice.put(Type.Classe2, this.classe2a);
            endPrice.put(Type.Classe3, this.classe3a);
            endPrice.put(Type.Classe4, this.classe4a);
            endPrice.put(Type.Classe5, this.classe5a);
            price.setStartPrice(startPrice);
            price.setEndPrice(endPrice);
            this.priceSrv.update(price);
        } else {
            station.setPrice(null);
        }
        this.station.setTicket(this.ticket);
        if ((!this.ticket) && (this.station.getPrice() == null)) {
            Price price = new Price();
            price = this.priceSrv.add(price);
            this.station.setPrice(price);
        }
        this.stationSrv.update(this.station);
        ExternalContext content = FacesContext.getCurrentInstance().getExternalContext();
        content.redirect("/Telepeage/administration.xhtml");
    }
}
