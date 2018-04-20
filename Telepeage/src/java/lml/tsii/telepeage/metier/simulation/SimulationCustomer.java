package lml.tsii.telepeage.metier.simulation;

import java.util.logging.Level;
import java.util.logging.Logger;
import lml.tsii.telepeage.metier.CustomerService;
import lml.tsii.telepeage.metier.MetierFactory;
import lml.tsii.telepeage.metier.entitys.Customer;

public class SimulationCustomer {
    
    public SimulationCustomer(){
        CustomerService customerSrv = MetierFactory.getCustomerService();
        Customer customer = new Customer();
        customer.setName("Gobron");
        customer.setFirstName("Fabien");
        customer.setPassword("test");
        customer.setMail("gobron-fabien@hotmail.fr");
        customer.setAdministrator(false);
        try {
            customerSrv.add(customer);
        } catch (Exception ex) {
            Logger.getLogger(SimulationCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
