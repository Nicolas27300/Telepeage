package lml.tsii.telepeage.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.tsii.telepeage.metier.entitys.Highway;

public class HighwayServiceImpl extends AbstracCrudServiceJPA<Highway> implements HighwayService, Serializable {
    
    HighwayServiceImpl(String PU){
        super(PU);
    }

    @Override
    public List<Highway> getByName(String name) throws Exception {
        List<Highway> highways = new ArrayList();
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Highway m WHERE m.name = :name");
            query.setParameter("name", name);
            highways = query.getResultList();
        } finally {
            this.close();
        }
        return highways;
    }
    
}
