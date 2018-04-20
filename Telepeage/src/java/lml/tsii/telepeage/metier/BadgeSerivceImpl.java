package lml.tsii.telepeage.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.tsii.telepeage.metier.entitys.Badge;
import lml.tsii.telepeage.metier.entitys.Subscription;

public class BadgeSerivceImpl extends AbstracCrudServiceJPA<Badge> implements BadgeService, Serializable {
    
    public BadgeSerivceImpl(String PU){
        super(PU);
    }

    @Override
    public List<Badge> getBySubscription(Subscription subscription) throws Exception {
        List<Badge> badges = new ArrayList();
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Badge m WHERE m.subscription = :subscription");
            query.setParameter("subscription", subscription);
            badges = query.getResultList();
        } finally {
            this.close();
        }
        return badges;
    }

    @Override
    public List<Badge> getByNumber(String number) throws Exception {
        List<Badge> badges = new ArrayList();
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Badge m WHERE m.num = :number");
            query.setParameter("number", number);
            badges = query.getResultList();
        } finally {
            this.close();
        }
        return badges;
    }
    
}
