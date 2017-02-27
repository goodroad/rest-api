package com.goodroad.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by rigel on 2/15/17.
 */
@Entity
public class Population implements Serializable {


    @Id
    @GeneratedValue
    private Long id;

    public  String code ,name ,population;
}
