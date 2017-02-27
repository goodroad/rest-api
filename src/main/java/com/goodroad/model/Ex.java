package com.goodroad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by dubu on 2017-02-22.
 */
@Entity
public class Ex implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    public String writeDate,line,way,group1,group2,etc, address;

    public float milestone;

    public float lng;
    public float lat;
}

