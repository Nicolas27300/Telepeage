package lml.tsii.telepage.metier.simulation;

import lml.tsii.telepage.metier.BillService;
import lml.tsii.telepage.metier.CustomerService;
import lml.tsii.telepage.metier.MetierFactory;
import lml.tsii.telepage.metier.RideService;
import lml.tsii.telepage.metier.entity.Bill;

public class SimulationBill {
    
    public SimulationBill() throws Exception{
        BillService billSrv = MetierFactory.getBillService();
        CustomerService customerSrv = MetierFactory.getCustomerService();
        RideService rideSrv = MetierFactory.getRideService();
        Bill bill = new Bill();
        bill.setCustomer(customerSrv.getAll().get(0));
        bill.setTrajets(rideSrv.getAll());
        bill.setPrice(12);
        billSrv.add(bill);
    }
    
}
