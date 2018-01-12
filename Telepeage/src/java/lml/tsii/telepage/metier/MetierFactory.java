package lml.tsii.telepage.metier;

public class MetierFactory {
    
    private MetierFactory() {
        
    }
    
    private static CustomerService customerSrv;
    
    public synchronized static CustomerService getCustomerService(){
        customerSrv = new CustomerServiceImpl("TelepeagePU");
        return MetierFactory.customerSrv;
    }
    
}
