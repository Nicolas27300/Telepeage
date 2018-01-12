package lml.tsii.telepage.metier.entity;

import java.util.List;

public class Bill {
    
    private long id;
    private List<Ride> trajets;
    private float price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Ride> getTrajets() {
        return trajets;
    }

    public void setTrajets(List<Ride> trajets) {
        this.trajets = trajets;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
       
}
