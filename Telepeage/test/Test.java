
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import lml.tsii.telepeage.metier.BillService;
import lml.tsii.telepeage.metier.CustomerService;
import lml.tsii.telepeage.metier.MetierFactory;
import lml.tsii.telepeage.metier.entitys.Bill;
import lml.tsii.telepeage.metier.entitys.Customer;
import lml.tsii.telepeage.metier.simulation.SimulationBadge;
import lml.tsii.telepeage.metier.simulation.SimulationBill;
import lml.tsii.telepeage.metier.simulation.SimulationCustomer;
import lml.tsii.telepeage.metier.simulation.SimulationHighway;
import lml.tsii.telepeage.metier.simulation.SimulationRide;

public class Test {
    
    public static void main(String[] args) throws Exception {
        //SimulationHighway simulationHighway = new SimulationHighway();
        //SimulationStation simulationStation = new SimulationStation();
        //SimulationCustomer simulationCustomer = new SimulationCustomer();
        //SimulationRide simulationRide = new SimulationRide();
        //SimulationBill simulationBill = new SimulationBill();
        //SimulationBadge simulationBadge = new SimulationBadge();
        Date date = new Date();
        date.setMonth(3);
        String string = date.toString();
        String data[] = string.split(" ");
        BillService billSrv = MetierFactory.getBillService();
        CustomerService customerSrv = MetierFactory.getCustomerService();
        StringBuilder dateString = new StringBuilder();
        dateString.append(data[1] + " " + data[5]);
        System.out.println(dateString.toString());
        List<Customer> customers = customerSrv.getByEmail("lemoine-nicolas@hotmail.fr");
        List<Bill> bills = billSrv.getByCustomerName(customers.get(0), dateString.toString());
        if (bills.isEmpty()){
            System.out.println("Facture du mois pas encore crée");
            Bill bill = new Bill();
            bill.setName(dateString.toString());
            bill.setCustomer(customers.get(0));
            bill.setPrice(22);
            billSrv.add(bill);
        } else {
            System.out.println("T'as déjà une facture pour ce mois Jean-Pierre");
        }
    }
    
}
