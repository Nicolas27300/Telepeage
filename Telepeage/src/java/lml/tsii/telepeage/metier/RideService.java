package lml.tsii.telepeage.metier;

import java.util.List;
import lml.persistence.CrudService;
import lml.tsii.telepeage.metier.entitys.Customer;
import lml.tsii.telepeage.metier.entitys.Ride;

public interface RideService extends CrudService<Ride> {
    List<Ride> getByCustomer(Customer customer) throws Exception;
    Ride getOneByCustomer(Customer customer) throws Exception;
    List<Ride> getByHighway(String name) throws Exception;
}
