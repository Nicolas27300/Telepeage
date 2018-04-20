package lml.tsii.telepeage.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.tsii.telepeage.metier.entitys.Station;

public class StationServiceImpl extends AbstracCrudServiceJPA<Station> implements StationService, Serializable{
    
    StationServiceImpl(String PU){
        super(PU);
    }

    @Override
    public List<Station> getByHighway(String highway) throws Exception {
        List<Station> stations = new ArrayList();
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Station m WHERE m.highway = :highway");
            query.setParameter("highway", highway);
            stations = query.getResultList();
        } finally {
            this.close();
        }
        return stations;
    }

    @Override
    public List<Station> getByName(String name) throws Exception {
        List<Station> stations = new ArrayList();
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Station m WHERE m.name = :name");
            query.setParameter("name", name);
            stations = query.getResultList();
        } finally {
            this.close();
        }
        return stations;
    }
    
}
