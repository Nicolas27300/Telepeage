package lml.tsii.telepeage.metier;

import java.util.List;
import lml.persistence.CrudService;
import lml.tsii.telepeage.metier.entitys.Badge;
import lml.tsii.telepeage.metier.entitys.Customer;
import lml.tsii.telepeage.metier.entitys.Subscription;

public interface SubscriptionService extends CrudService<Subscription> {
    List<Subscription> getByBadge(Badge badge) throws Exception;
}
