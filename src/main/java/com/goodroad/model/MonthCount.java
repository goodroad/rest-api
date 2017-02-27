package com.goodroad.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by rigel on 2/20/17.
 */
@Entity
public class MonthCount implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    public String mon , cnt;
}
