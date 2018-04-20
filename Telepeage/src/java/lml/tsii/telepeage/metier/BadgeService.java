package lml.tsii.telepeage.metier;

import java.util.List;
import lml.persistence.CrudService;
import lml.tsii.telepeage.metier.entitys.Badge;
import lml.tsii.telepeage.metier.entitys.Subscription;

public interface BadgeService extends CrudService<Badge> {
    List<Badge> getBySubscription(Subscription subscription) throws Exception;
    List<Badge> getByNumber(String number) throws Exception;
}
