package lml.tsii.telepage.metier.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lml.tsii.telepage.metier.MetierFactory;
import lml.tsii.telepage.metier.StationService;
import lml.tsii.telepage.metier.entity.Station;

public class SimulationStation {

    public SimulationStation() {
        StationService stationSrv = MetierFactory.getStationService();
        List<Station> stations = new ArrayList();
        Station stationCaen = new Station();
        Station stationDozule = new Station();
        Station stationBeuzeuville = new Station();
        Station stationHeudeubouville = new Station();
        stationCaen.setName("Caen");
        stationCaen.setPk(224);
        stationDozule.setName("Dozule");
        stationDozule.setPk(205);
        stationBeuzeuville.setName("Beuzeuville");
        stationBeuzeuville.setPk(168);
        stationHeudeubouville.setName("Heudeubouville");
        stationHeudeubouville.setPk(93);
        stations.add(stationCaen);
        stations.add(stationDozule);
        stations.add(stationBeuzeuville);
        stations.add(stationHeudeubouville);
        for (int i = 0; i < stations.size(); i++){
            try {
                stationSrv.add(stations.get(i));
            } catch (Exception ex) {
                Logger.getLogger(SimulationStation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
  
}
