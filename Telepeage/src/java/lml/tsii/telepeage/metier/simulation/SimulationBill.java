package lml.tsii.telepeage.metier.simulation;

import lml.tsii.telepeage.metier.BillService;
import lml.tsii.telepeage.metier.CustomerService;
import lml.tsii.telepeage.metier.MetierFactory;
import lml.tsii.telepeage.metier.RideService;
import lml.tsii.telepeage.metier.entitys.Bill;

public class SimulationBill {
    
    public SimulationBill() throws Exception{
        BillService billSrv = MetierFactory.getBillService();
        CustomerService customerSrv = MetierFactory.getCustomerService();
        RideService rideSrv = MetierFactory.getRideService();
        Bill bill = new Bill();
        bill.setCustomer(customerSrv.getAll().get(0));
        bill.setRides(rideSrv.getAll());
        bill.setPrice(12);
        billSrv.add(bill);
    }
    
}
