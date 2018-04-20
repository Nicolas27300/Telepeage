package lml.tsii.telepeage.metier.simulation;

import lml.tsii.telepeage.metier.BadgeService;
import lml.tsii.telepeage.metier.MetierFactory;
import lml.tsii.telepeage.metier.entitys.Badge;

public class SimulationBadge {
    
    public SimulationBadge() throws Exception{
        BadgeService badgeSrv = MetierFactory.getBadgeService();
        Badge badge = new Badge();
        badge.setNum("HJKBOBVOBOBOB");
        badgeSrv.add(badge);
    }
    
}
