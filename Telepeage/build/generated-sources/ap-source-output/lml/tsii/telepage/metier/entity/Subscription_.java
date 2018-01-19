package lml.tsii.telepage.metier.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lml.tsii.telepage.metier.entity.Badge;
import lml.tsii.telepage.metier.entity.Customer;
import lml.tsii.telepage.metier.entity.Type;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-01-19T10:08:36")
@StaticMetamodel(Subscription.class)
public class Subscription_ { 

    public static volatile SingularAttribute<Subscription, Badge> badge;
    public static volatile SingularAttribute<Subscription, Long> id;
    public static volatile SingularAttribute<Subscription, Type> type;
    public static volatile SingularAttribute<Subscription, Customer> customer;

}