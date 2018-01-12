package lml.tsii.telepage.metier.entity;

import java.util.Date;

public class Ride {
    
    private long id;
    private Station start;
    private Station end;
    float distance;
    float price;
    Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Station getStart() {
        return start;
    }

    public void setStart(Station start) {
        this.start = start;
    }

    public Station getEnd() {
        return end;
    }

    public void setEnd(Station end) {
        this.end = end;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
      
}
