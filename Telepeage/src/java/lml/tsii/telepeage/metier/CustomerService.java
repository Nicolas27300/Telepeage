package lml.tsii.telepeage.metier;

import java.util.List;
import lml.persistence.CrudService;
import lml.tsii.telepeage.metier.entitys.Customer;
import lml.tsii.telepeage.metier.entitys.Subscription;

public interface CustomerService extends CrudService<Customer> {
    List<Customer> getByEmail(String email) throws Exception;
    List<Customer> getBySubscription(Subscription subscription) throws Exception;
}
