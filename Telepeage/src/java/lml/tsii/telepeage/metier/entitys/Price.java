package lml.tsii.telepeage.metier.entitys;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;

@Entity
public class Price implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ElementCollection
    Map<Type, Float> startPrice;
    @ElementCollection
    Map<Type, Float> endPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<Type, Float> getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Map<Type, Float> startPrice) {
        this.startPrice = startPrice;
    }

    public Map<Type, Float> getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Map<Type, Float> endPrice) {
        this.endPrice = endPrice;
    }
    
}
