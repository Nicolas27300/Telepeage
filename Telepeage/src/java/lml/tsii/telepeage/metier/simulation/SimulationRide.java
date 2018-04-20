package lml.tsii.telepeage.metier.simulation;

import java.util.Date;
import java.util.List;
import lml.tsii.telepeage.metier.BadgeService;
import lml.tsii.telepeage.metier.CustomerService;
import lml.tsii.telepeage.metier.MetierFactory;
import lml.tsii.telepeage.metier.RideService;
import lml.tsii.telepeage.metier.StationService;
import lml.tsii.telepeage.metier.SubscriptionService;
import lml.tsii.telepeage.metier.entitys.Badge;
import lml.tsii.telepeage.metier.entitys.Customer;
import lml.tsii.telepeage.metier.entitys.Ride;
import lml.tsii.telepeage.metier.entitys.Station;
import lml.tsii.telepeage.metier.entitys.Subscription;

public class SimulationRide {
    
    public SimulationRide() throws Exception {
        StationService stationSrv = MetierFactory.getStationService();
        SubscriptionService subscriptionSrv = MetierFactory.getSubscriptionService();
        RideService rideSrv = MetierFactory.getRideService();
        BadgeService badgeSrv = MetierFactory.getBadgeService();
        List<Badge> badges = badgeSrv.getByNumber("123456789");
        List<Station> stations = stationSrv.getByName("Péage A14 Montesson");
        if (!badges.isEmpty()){
            System.out.println("Un badge a été trouvé Roger");
            List<Subscription> subscriptions = subscriptionSrv.getByBadge(badges.get(0));
            //System.out.println(subscriptions.get(0).getCustomer().getMail());
            Ride ride = new Ride();
            if (!stations.isEmpty()){
                //ride.setCustomer(subscriptions.get(0).getCustomer());
                ride.setDate_ride(new Date());
                ride.setStart(stations.get(0));
                ride.setPrice(stations.get(0).getPrice().getStartPrice().get(subscriptions.get(0).getType()));
                rideSrv.add(ride);
            }
        }
    }
    
}
