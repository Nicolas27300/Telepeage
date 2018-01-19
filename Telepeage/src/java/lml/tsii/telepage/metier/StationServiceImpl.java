package lml.tsii.telepage.metier;

import java.io.Serializable;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.tsii.telepage.metier.entity.Station;

public class StationServiceImpl extends AbstracCrudServiceJPA<Station> implements StationService, Serializable{
    
    StationServiceImpl(String PU){
        super(PU);
    }
    
}
