package lml.tsii.telepage.metier.simulation;

import java.util.logging.Level;
import java.util.logging.Logger;
import lml.tsii.telepage.metier.CustomerService;
import lml.tsii.telepage.metier.MetierFactory;
import lml.tsii.telepage.metier.entity.Customer;

public class SimulationCustomer {
    
    public SimulationCustomer(){
        CustomerService customerSrv = MetierFactory.getCustomerService();
        Customer customer = new Customer();
        customer.setName("Gobron");
        customer.setFirstName("Fabien");
        customer.setPassword("test");
        customer.setMail("gobron-fabien@hotmail.fr");
        customer.setKay("hjsgfhfh");
        customer.setAdministrator(false);
        try {
            customerSrv.add(customer);
        } catch (Exception ex) {
            Logger.getLogger(SimulationCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
