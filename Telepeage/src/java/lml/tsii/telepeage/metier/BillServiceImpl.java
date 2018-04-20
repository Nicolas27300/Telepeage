package lml.tsii.telepeage.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.tsii.telepeage.metier.entitys.Bill;
import lml.tsii.telepeage.metier.entitys.Customer;

public class BillServiceImpl extends AbstracCrudServiceJPA<Bill> implements BillService, Serializable {
    
    public BillServiceImpl(String PU){
        super(PU);
    }

    @Override
    public List<Bill> getByCustomerName(Customer customer, String name) throws Exception {
        List<Bill> bills = new ArrayList();
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Bill m WHERE m.customer = :customer AND m.name = :name");
            query.setParameter("customer", customer);
            query.setParameter("name", name);
            bills = query.getResultList();
        } finally {
            this.close();
        }
        return bills;
    }

    @Override
    public List<Bill> getByCustomer(Customer customer) throws Exception {
        List<Bill> bills = new ArrayList();
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Bill m WHERE m.customer = :customer");
            query.setParameter("customer", customer);
            bills = query.getResultList();
        } finally {
          this.close();
        }
        return bills;
    }
    
}
