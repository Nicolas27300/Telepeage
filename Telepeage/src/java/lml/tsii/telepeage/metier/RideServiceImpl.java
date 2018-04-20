package lml.tsii.telepeage.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.tsii.telepeage.metier.entitys.Customer;
import lml.tsii.telepeage.metier.entitys.Ride;

public class RideServiceImpl extends AbstracCrudServiceJPA<Ride> implements RideService, Serializable {
    
    public RideServiceImpl(String PU){
        super(PU);
    }

    @Override
    public List<Ride> getByCustomer(Customer customer) throws Exception {
        List<Ride> rides = new ArrayList();
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Ride m WHERE m.customer = :customer AND ((m.start.ticket = 0) OR (m.end IS NOT NULL))");
            query.setParameter("customer", customer);
            rides = query.getResultList();
        } finally {
            this.close();
        }
        return rides;
    }

    @Override
    public Ride getOneByCustomer(Customer customer) throws Exception {
        Ride ride = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Ride m WHERE m.customer = :customer ORDER BY m.id DESC ");
            query.setParameter("customer", customer);
            ride = (Ride) query.getResultList().get(0);
        } finally {
            this.close();
        }
        return ride;
    }

    @Override
    public List<Ride> getByHighway(String name) throws Exception {
        List<Ride> rides = new ArrayList();
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Ride m WHERE m.start.highway.name = :name");
            query.setParameter("name", name);
            rides = query.getResultList();
        } finally {
            this.close();
        }
        return rides;
    }
    
}
