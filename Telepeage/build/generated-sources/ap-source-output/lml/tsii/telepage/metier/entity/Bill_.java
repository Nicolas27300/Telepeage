package lml.tsii.telepage.metier.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lml.tsii.telepage.metier.entity.Customer;
import lml.tsii.telepage.metier.entity.Ride;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-01-19T10:08:36")
@StaticMetamodel(Bill.class)
public class Bill_ { 

    public static volatile SingularAttribute<Bill, Float> price;
    public static volatile SingularAttribute<Bill, Long> id;
    public static volatile ListAttribute<Bill, Ride> trajets;
    public static volatile SingularAttribute<Bill, Customer> customer;

}