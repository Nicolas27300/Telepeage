package lml.tsii.telepeage.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.tsii.telepeage.metier.entitys.Badge;
import lml.tsii.telepeage.metier.entitys.Customer;
import lml.tsii.telepeage.metier.entitys.Subscription;

public class SubscriptionServiceImpl extends AbstracCrudServiceJPA<Subscription> implements SubscriptionService, Serializable {
    
    public SubscriptionServiceImpl(String PU){
        super(PU);
    }

    @Override
    public List<Subscription> getByBadge(Badge badge) throws Exception {
        List<Subscription> subscriptions = new ArrayList();
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Subscription m WHERE m.badge = :badge");
            query.setParameter("badge", badge);
            subscriptions = query.getResultList();
        } finally {
            this.close();
        }
        return subscriptions;
    }
    
}
