package lml.tsii.telepage.metier;

import java.util.List;
import lml.persistence.CrudService;
import lml.tsii.telepage.metier.entity.Customer;

public interface CustomerService extends CrudService<Customer> {
    List<Customer> getByEmail(String email) throws Exception;
}
