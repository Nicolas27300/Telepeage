package lml.tsii.telepage.metier;

import java.io.Serializable;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.tsii.telepage.metier.entity.Badge;

public class BadgeSerivceImpl extends AbstracCrudServiceJPA<Badge> implements BadgeService, Serializable {
    
    public BadgeSerivceImpl(String PU){
        super(PU);
    }
    
}
