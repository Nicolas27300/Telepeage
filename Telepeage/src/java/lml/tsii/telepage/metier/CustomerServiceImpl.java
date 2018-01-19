package lml.tsii.telepage.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.tsii.telepage.metier.entity.Customer;

public class CustomerServiceImpl extends AbstracCrudServiceJPA<Customer> implements CustomerService, Serializable {
    
    CustomerServiceImpl(String PU){
        super(PU);
    }

    @Override
    public List<Customer> getByEmail(String mail) throws Exception {
        List customers = new ArrayList();
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Customer m WHERE m.mail = :mail");
            query.setParameter("mail", mail);
            customers = query.getResultList();
            if (customers.isEmpty()){
                customers = null;
            }
        } finally {
            this.close();
        }
        return customers;
    }
}
