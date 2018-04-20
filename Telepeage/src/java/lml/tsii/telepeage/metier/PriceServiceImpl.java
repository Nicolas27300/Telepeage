package lml.tsii.telepeage.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.tsii.telepeage.metier.entitys.Price;
import lml.tsii.telepeage.metier.entitys.Station;

public class PriceServiceImpl extends AbstracCrudServiceJPA<Price> implements PriceService, Serializable {
    
    PriceServiceImpl(String PU){
        super(PU);
    }

    @Override
    public List<Price> getByStation(Station station) throws Exception {
        List<Price> prices = new ArrayList();
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Price m WHERE m.station = :station");
            query.setParameter("station", station);
            prices = query.getResultList();
        } finally {
            this.close();
        }
        return prices;
    }
    
}
