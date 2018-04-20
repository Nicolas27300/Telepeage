package lml.tsii.telepeage.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.tsii.telepeage.metier.entitys.Customer;
import lml.tsii.telepeage.metier.entitys.Subscription;

public class CustomerServiceImpl extends AbstracCrudServiceJPA<Customer> implements CustomerService, Serializable {
    
    CustomerServiceImpl(String PU){
        super(PU);
    }

    @Override
    public List<Customer> getByEmail(String mail) throws Exception {
        List<Customer> customers = new ArrayList();
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

    @Override
    public List<Customer> getBySubscription(Subscription subscription) throws Exception {
        List<Customer> customers = new ArrayList();
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Customer m WHERE m.subscription = :subscription");
            query.setParameter("subscription", subscription);
            customers = query.getResultList();
        } finally {
            this.close();
        }
        return customers;
    }
}
