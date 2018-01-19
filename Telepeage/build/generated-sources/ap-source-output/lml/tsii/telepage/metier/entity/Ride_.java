package lml.tsii.telepage.metier.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lml.tsii.telepage.metier.entity.Customer;
import lml.tsii.telepage.metier.entity.Station;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-01-19T10:08:36")
@StaticMetamodel(Ride.class)
public class Ride_ { 

    public static volatile SingularAttribute<Ride, Date> date_ride;
    public static volatile SingularAttribute<Ride, Float> distance;
    public static volatile SingularAttribute<Ride, Double> price;
    public static volatile SingularAttribute<Ride, Station> start;
    public static volatile SingularAttribute<Ride, Station> end;
    public static volatile SingularAttribute<Ride, Long> id;
    public static volatile SingularAttribute<Ride, Customer> customer;

}