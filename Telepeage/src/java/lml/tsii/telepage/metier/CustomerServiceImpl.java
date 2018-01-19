package lml.tsii.telepage.metier;

import java.io.Serializable;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.tsii.telepage.metier.entity.Customer;

public class CustomerServiceImpl extends AbstracCrudServiceJPA<Customer> implements CustomerService, Serializable {
    
    CustomerServiceImpl(String PU){
        super(PU);
    }
}
