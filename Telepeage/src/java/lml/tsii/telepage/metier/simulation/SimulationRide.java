package lml.tsii.telepage.metier.simulation;

import java.util.Date;
import java.util.List;
import lml.tsii.telepage.metier.CustomerService;
import lml.tsii.telepage.metier.MetierFactory;
import lml.tsii.telepage.metier.RideService;
import lml.tsii.telepage.metier.StationService;
import lml.tsii.telepage.metier.entity.Customer;
import lml.tsii.telepage.metier.entity.Ride;
import lml.tsii.telepage.metier.entity.Station;

public class SimulationRide {
    
    public SimulationRide() throws Exception {
        CustomerService customerSrv = MetierFactory.getCustomerService();
        StationService stationSrv = MetierFactory.getStationService();
        RideService rideSrv = MetierFactory.getRideService();
        List<Customer> customers = customerSrv.getAll();
        List<Station> stations = stationSrv.getAll();
        Ride ride1 = new Ride();
        ride1.setStart(stations.get(0));
        ride1.setEnd(stations.get(1));
        ride1.setDistance(ride1.getStart().getPk() - ride1.getEnd().getPk());
        ride1.setDate_ride(new Date());
        ride1.setPrice(1.80);
        ride1.setCustomer(customers.get(0));
        rideSrv.add(ride1);
        Ride ride2 = new Ride();
        ride2.setStart(stations.get(1));
        
        ride2.setEnd(stations.get(2));
        ride2.setDistance(ride2.getStart().getPk() - ride2.getEnd().getPk());
        ride2.setDate_ride(new Date());
        ride2.setPrice(1.80);
        ride2.setCustomer(customers.get(0));
        rideSrv.add(ride2);
        Ride ride3 = new Ride();
        ride3.setStart(stations.get(0));
        ride3.setEnd(stations.get(3));
        ride3.setDistance(ride3.getStart().getPk() - ride3.getEnd().getPk());
        ride3.setDate_ride(new Date());
        ride3.setPrice(1.80);
        ride3.setCustomer(customers.get(0));
        rideSrv.add(ride3);
    }
    
}
