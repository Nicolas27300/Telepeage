package lml.tsii.telepeage.metier;

import java.util.List;
import lml.persistence.CrudService;
import lml.tsii.telepeage.metier.entitys.Station;

public interface StationService extends CrudService<Station> {
    List<Station> getByHighway(String highway) throws Exception;
    List<Station> getByName(String name) throws Exception;
}
