package lml.tsii.telepeage.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lml.tsii.telepeage.metier.HighwayService;
import lml.tsii.telepeage.metier.MetierFactory;
import lml.tsii.telepeage.metier.entitys.Highway;
import lml.tsii.telepeage.metier.entitys.Ride;

@ManagedBean
@ViewScoped
public class TraficController implements Serializable {
    
    private HighwayService highwaySrv;
    private List<String> highways;
    private String highway;
    List<Ride> rides;
    
    @PostConstruct
    public void init(){
        try {
            this.highwaySrv = MetierFactory.getHighwayService();
            List<Highway> highways = highwaySrv.getAll();
            this.highways = new ArrayList();
            for (int i = 0; i < highways.size(); i++){
                this.highways.add(highways.get(i).getName());
            }
        } catch (Exception ex) {
            Logger.getLogger(TraficController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public void search(){
        try {
            this.rides = MetierFactory.getRideService().getByHighway(this.highway);
        } catch (Exception ex) {
            Logger.getLogger(TraficController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
