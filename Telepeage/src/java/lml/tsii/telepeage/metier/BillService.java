package lml.tsii.telepeage.metier;

import java.util.List;
import lml.persistence.CrudService;
import lml.tsii.telepeage.metier.entitys.Bill;
import lml.tsii.telepeage.metier.entitys.Customer;

public interface BillService extends CrudService<Bill> {
    List<Bill> getByCustomerName(Customer customer, String name) throws Exception;
    List<Bill> getByCustomer(Customer customer) throws Exception;
}
