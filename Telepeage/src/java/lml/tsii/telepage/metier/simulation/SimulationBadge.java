package lml.tsii.telepage.metier.simulation;

import lml.tsii.telepage.metier.BadgeService;
import lml.tsii.telepage.metier.MetierFactory;
import lml.tsii.telepage.metier.entity.Badge;

public class SimulationBadge {
    
    public SimulationBadge() throws Exception{
        BadgeService badgeSrv = MetierFactory.getBadgeService();
        Badge badge = new Badge();
        badge.setNumber("HJKBOBVOBOBOB");
        badgeSrv.add(badge);
    }
    
}
