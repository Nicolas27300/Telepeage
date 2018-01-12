package lml.tsii.telepage.client;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lml.tsii.telepage.metier.CustomerService;
import lml.tsii.telepage.metier.MetierFactory;

@ManagedBean
@ViewScoped
public class CustomerController implements Serializable {
    
    CustomerService customerSrv;
    long count;
    
    @PostConstruct
    public void init(){
        this.customerSrv = MetierFactory.getCustomerService();
        try {
            this.count = this.customerSrv.getCount();
        } catch (Exception ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CustomerService getCustomerSrv() {
        return customerSrv;
    }

    public void setCustomerSrv(CustomerService customerSrv) {
        this.customerSrv = customerSrv;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
    
}
