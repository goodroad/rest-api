package com.goni.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
/**
 * Created by dubu on 2017-01-23.
 */
@Entity
public class Report implements Serializable{

   enum group {
      pu(1,"포유류"),bird(2,"조류"),;

       private final int id;
       private final String name;

       group(int id, String name) {
          this.id = id ;
          this.name = name;
       }
   }

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    public float lat;

    @Column(nullable = false)
    public float lng;

    public String address;

    public String cont;

    public String kind;

}
