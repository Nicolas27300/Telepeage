package lml.tsii.telepage.metier;

import java.io.Serializable;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.tsii.telepage.metier.entity.Subscription;

public class SubscriptionServiceImpl extends AbstracCrudServiceJPA<Subscription> implements SubscriptionService, Serializable {
    
    public SubscriptionServiceImpl(String PU){
        super(PU);
    }
    
}
