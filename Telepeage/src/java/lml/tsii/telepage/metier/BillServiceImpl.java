package lml.tsii.telepage.metier;

import java.io.Serializable;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.tsii.telepage.metier.entity.Bill;

public class BillServiceImpl extends AbstracCrudServiceJPA<Bill> implements BillService, Serializable {
    
    public BillServiceImpl(String PU){
        super(PU);
    }
    
}
