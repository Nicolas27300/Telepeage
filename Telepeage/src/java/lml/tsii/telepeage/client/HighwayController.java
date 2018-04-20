package lml.tsii.telepeage.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import lml.tsii.telepeage.metier.HighwayService;
import lml.tsii.telepeage.metier.MetierFactory;
import lml.tsii.telepeage.metier.entitys.Highway;

@ManagedBean
@ViewScoped
public class HighwayController implements Serializable {

    private HighwayService highwaySrv;
    private String name;
    private String startCity;
    private String endCity;
    private List<String> highways;
    private String highway;
    private float sectionPrice;

    @PostConstruct
    public void init() {
        try {
            highwaySrv = MetierFactory.getHighwayService();
            List<Highway> highways = highwaySrv.getAll();
            this.highways = new ArrayList();
            for (int i = 0; i < highways.size(); i++){
                this.highways.add(highways.get(i).getName());
            }
            if (!highways.isEmpty()){
                this.startCity = highways.get(0).getStartCity();
                this.endCity = highways.get(0).getEndCity();
                this.highway = highways.get(0).getName();
            }
        } catch (Exception ex) {
            Logger.getLogger(HighwayController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public List<String> getHighways() {
        return highways;
    }

    public void setHighways(List<String> highways) {
        this.highways = highways;
    }

    public String getHighway() {
        return highway;
    }

    public void setHighway(String highway) {
        this.highway = highway;
    }

    public float getSectionPrice() {
        return sectionPrice;
    }

    public void setSectionPrice(float sectionPrice) {
        this.sectionPrice = sectionPrice;
    }
    
    public void add() {
        Highway highway = new Highway();
        highway.setName(this.name);
        highway.setStartCity(this.startCity);
        highway.setEndCity(this.endCity);
        try {
            if (this.highwaySrv.getByName(this.name).isEmpty()) {
                this.highwaySrv.add(highway);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur", "Cette autoroute existe déjà"));
            }
        } catch (Exception ex) {
            Logger.getLogger(HighwayController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onChange() throws Exception{
        Highway highway = this.highwaySrv.getByName(this.highway).get(0);
        this.startCity = highway.getStartCity();
        this.endCity = highway.getEndCity();
        this.sectionPrice = highway.getSectionPrice();
    }
    
    public void edit() throws Exception{
        Highway highway = this.highwaySrv.getByName(this.highway).get(0);
        highway.setStartCity(this.startCity);
        highway.setEndCity(this.endCity);
        highway.setSectionPrice(this.sectionPrice);
        this.highwaySrv.update(highway);
    }

}
