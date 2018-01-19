package lml.tsii.telepage.metier.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lml.tsii.telepage.metier.entity.Station;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-01-19T10:08:36")
@StaticMetamodel(Highway.class)
public class Highway_ { 

    public static volatile SingularAttribute<Highway, String> name;
    public static volatile SingularAttribute<Highway, Long> id;
    public static volatile ListAttribute<Highway, Station> stations;

}