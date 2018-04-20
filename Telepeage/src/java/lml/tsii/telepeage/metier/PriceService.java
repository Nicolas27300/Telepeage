package lml.tsii.telepeage.metier;

import java.util.List;
import lml.persistence.CrudService;
import lml.tsii.telepeage.metier.entitys.Price;
import lml.tsii.telepeage.metier.entitys.Station;

public interface PriceService extends CrudService<Price> {
    List<Price> getByStation(Station station) throws Exception;
}
