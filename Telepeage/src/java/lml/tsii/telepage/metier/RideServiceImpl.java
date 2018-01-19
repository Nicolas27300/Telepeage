package lml.tsii.telepage.metier;

import java.io.Serializable;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.tsii.telepage.metier.entity.Ride;

public class RideServiceImpl extends AbstracCrudServiceJPA<Ride> implements RideService, Serializable {
    
    public RideServiceImpl(String PU){
        super(PU);
    }
    
}
