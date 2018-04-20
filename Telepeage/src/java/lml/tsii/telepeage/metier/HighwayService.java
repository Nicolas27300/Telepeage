package lml.tsii.telepeage.metier;

import java.util.List;
import lml.persistence.CrudService;
import lml.tsii.telepeage.metier.entitys.Highway;

public interface HighwayService extends CrudService<Highway> {
    List<Highway> getByName(String name) throws Exception;
}
