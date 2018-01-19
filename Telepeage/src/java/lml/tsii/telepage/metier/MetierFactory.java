package lml.tsii.telepage.metier;

public class MetierFactory {
    
    private MetierFactory() {
        
    }
    
    private static CustomerService customerSrv = null;
    private static StationService stationSrv = null;
    private static RideService rideSrv = null;
    private static BillService billSrv = null;
    private static BadgeService badgeSrv = null;
    
    public synchronized static CustomerService getCustomerService(){
        if (customerSrv == null){
            customerSrv = new CustomerServiceImpl("TelepeagePU");  
        }
        return MetierFactory.customerSrv;
    }
    
    public synchronized static StationService getStationService(){
        if (stationSrv == null){
            stationSrv = new StationServiceImpl("TelepeagePU");
        }
        return MetierFactory.stationSrv;
    }
    
    public synchronized static RideService getRideService(){
        if (rideSrv == null){
            rideSrv = new RideServiceImpl("TelepeagePU");
        }
        return MetierFactory.rideSrv;
    }
    
    public synchronized static BillService getBillService(){
        if (billSrv == null){
            billSrv = new BillServiceImpl("TelepeagePU");
        }
        return MetierFactory.billSrv;
    }
    
    public synchronized static BadgeService getBadgeService(){
        if (badgeSrv == null){
            badgeSrv = new BadgeSerivceImpl("TelepeagePU");
        }
        return MetierFactory.badgeSrv;
    }
    
}
